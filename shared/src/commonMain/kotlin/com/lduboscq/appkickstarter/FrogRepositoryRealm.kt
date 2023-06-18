package com.lduboscq.appkickstarter

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import io.realm.kotlin.Realm
import io.realm.kotlin.notifications.DeletedObject
import io.realm.kotlin.notifications.InitialObject
import io.realm.kotlin.notifications.PendingObject
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.notifications.UpdatedObject
import io.realm.kotlin.notifications.UpdatedResults
import io.realm.kotlin.types.RealmUUID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

//    val emojis = listOf("🐤","🐦","🐔","🦤","🕊","️","🦆","🦅","🪶","🦩","🐥","-","🐣","🦉","🦜","🦚","🐧","🐓","🦢","🦃","🦡","🦇","🐻","🦫","🦬","🐈","‍","⬛","🐗","🐪","🐈","🐱","🐿","️","🐄","🐮","🦌","🐕","🐶","🐘","🐑","🦊","🦒","🐐","🦍","🦮","🐹","🦔","🦛","🐎","🐴","🦘","🐨","🐆","🦁","🦙","🦣","🐒","🐵","🐁","🐭","🦧","🦦","🐂","🐼","🐾","🐖","🐷","🐽","🐻","‍","❄","️","🐩","🐇","🐰","🦝","🐏","🐀","🦏","🐕","‍","🦺","🦨","🦥","🐅","🐯","🐫","-","🦄","🐃","🐺","🦓","🐳","🐡","🐬","🐟","🐙","🦭","🦈","🐚","🐳","🐠","🐋","🌱","🌵","🌳","🌲","🍂","🍀","🌿","🍃","🍁","🌴","🪴","🌱","☘","️","🌾","🐊","🐊","🐉","🐲","🦎","🦕","🐍","🦖","-","🐢")

abstract class FrogRepositoryRealm : FrogRepository {
    lateinit var realm: Realm
    private var currentJob: Job? = null

    abstract suspend fun setupRealmSync()

    private fun cancelCurrentJob() {
        currentJob?.cancel()
        currentJob = null
    }

    /** Function to convert all the latest data in a Frog realm object into
     *    a implementation-independent FrogData object so that it
     *    can be read and displayed in the view
     */
    suspend fun convertToFrogData(frog: Frog?): FrogData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }

        var frogData: FrogData? = null
        realm.write {
            if (frog != null) {
                frogData = FrogData(
                    id = findLatest(frog)!!._id,
                    name = findLatest(frog)!!.name,
                    age = findLatest(frog)!!.age,
                    species = findLatest(frog)!!.species,
                    owner = findLatest(frog)!!.owner,
                    frog = frog
                )
            }
        }
        return frogData
    }

    private fun closeRealmSync() {
        realm.close()
    }

    /** Add a frog with the given data to the repository.
     *    Initializes the id field to a random UUID if not specified.
     *  Returns the new FrogData object or null if the operation failed. */
    override suspend fun addFrog(frogData: FrogData): FrogData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }

        var frog2: Frog? = null
        realm.write {
            // create a frog object in the realm
            frog2 = this.copyToRealm(Frog().apply {
                _id = frogData.id ?: RealmUUID.random().toString()
                name = frogData.name
                age = frogData.age
                species = frogData.species
                owner = frogData.owner
            })
        }
        return convertToFrogData(frog2)
    }

    /** Returns the first frog found that matches the given name *
     *   Returns a FrogData if a match is found, null otherwise.
     */
    override suspend fun getFrog(frogName: String): FrogData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }
        cancelCurrentJob()
        // Search equality on the primary key field name
        val frog: Frog? = realm.query<Frog>(Frog::class, "name = \"$frogName\"").first().find()
        return convertToFrogData(frog)
    }

    override suspend fun getAllFrogsList(frogName: String?): List<FrogData>
    {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }
        cancelCurrentJob()
        val frogs: List<Frog> =
            if (frogName == null)
                realm.query<Frog>(Frog::class).find()
            else
                realm.query<Frog>(Frog::class, "name = \"$frogName\"").find()

        val frogDataList = frogs.map { frog ->
            FrogData(
                id = frog._id,
                name = frog.name,
                age = frog.age,
                species = frog.species,
                owner = frog.owner,
                frog = frog
            )
        }
        return frogDataList
    }

    override suspend fun getAllFrogs(frogName: String?): MutableState<List<FrogData>> {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }
        val frogsState: MutableState<List<FrogData>> = mutableStateOf(emptyList())
        cancelCurrentJob()
        currentJob = CoroutineScope(Dispatchers.Default).launch {
            // Listen to the Realm query result as a Flow
            val frogFlow: Flow<ResultsChange<Frog>> =
                if (frogName == null)
                    realm.query<Frog>(Frog::class).find().asFlow()
                else
                    realm.query<Frog>(Frog::class, "name = \"$frogName\"").find().asFlow()

            frogFlow.collect { resultsChange: ResultsChange<Frog> ->
                // Convert each Frog object to FrogData
                val frogs = resultsChange.list
                val frogDataFlow = frogs.map { frog ->
                    FrogData(
                        id = frog._id,
                        name = frog.name,
                        age = frog.age,
                        species = frog.species,
                        owner = frog.owner,
                        frog = frog
                    )
                }

                // Update the mutable state with the new result
                frogsState.value = frogDataFlow
            }
        }

        return frogsState
    }


    override suspend fun getAllFrogsFlow(frogName: String?): Flow<List<FrogData>> {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }
        var frogsState: Flow<List<FrogData>> = flowOf(emptyList())
        cancelCurrentJob()

        currentJob = CoroutineScope(Dispatchers.Default).launch {
            // Listen to the Realm query result as a Flow
            val frogFlow: Flow<ResultsChange<Frog>> =
                if (frogName == null)
                    realm.query<Frog>(Frog::class).find().asFlow()
                else
                    realm.query<Frog>(Frog::class, "name = \"$frogName\"").find().asFlow()

            frogFlow.collect { resultsChange: ResultsChange<Frog> ->
                // Convert each Frog object to FrogData
                val frogs = resultsChange.list
                val frogDataFlow = frogs.map { frog ->
                    FrogData(
                        id = frog._id,
                        name = frog.name,
                        age = frog.age,
                        species = frog.species,
                        owner = frog.owner,
                        frog = frog
                    )
                }

                // Update the mutable state with the new result
                frogsState = flowOf(frogDataFlow)
            }
        }

        return frogsState
    }


    /** Updates the frog that is the first match to the given name
     *   Placeholder operation:  Just adds to the name in fixed manner
     *   TODO: Make this accept variety of parameters reflecting the user's desired changes
     *   Returns an updated FrogData if a match is found, null otherwise.
     */
    override suspend fun updateFrog(frogName: String): FrogData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }
        var frogData: FrogData? = null
        try {
            // Search equality on the primary key field name
            val frog: Frog? =
                realm.query<Frog>(Frog::class, "name = \"$frogName\"").first().find()

            // Update one object asynchronously
            realm.write {
                if (frog != null) {
                    findLatest(frog)!!.age = findLatest(frog)!!.age + 1
                }
            }
            frogData = convertToFrogData(frog)
        } catch (e: Exception) {
            println(e.message)
        }

        return frogData
    }

    /** Deletes the frog that is the first match to the given name
     *   Returns the FrogData if a match is found, null otherwise.
     */
    override suspend fun deleteFrog(frogName: String): FrogData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }
        var frog2: FrogData? = null
        try {
            // Search for first match on the name field
            val frog: Frog? =
                realm.query<Frog>(Frog::class, "name = \"$frogName\"").first().find()

            realm.write {
                if (frog != null) {
                    // We need to extract the latest information about the frog
                    //   before deleting it.  We can't call the convertToFrogData
                    //   since we are in a realm.write block.
                    frog2 = FrogData(
                        id = findLatest(frog)!!._id,
                        name = findLatest(frog)!!.name,
                        age = findLatest(frog)!!.age,
                        species = findLatest(frog)!!.species,
                        owner = findLatest(frog)!!.owner,
                        frog = null
                    )
                    findLatest(frog)
                        ?.also { delete(it) }
                }
            }
        } catch (e: Exception) {
            println(e.message)
        }

        return frog2
    }
}
