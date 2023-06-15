package com.lduboscq.appkickstarter

import io.realm.kotlin.Realm
import io.realm.kotlin.types.RealmUUID

abstract class FrogRepository {
    lateinit var realm: Realm

    abstract suspend fun setupRealmSync()

    /** Function to convert all the latest data in a Frog realm object into
     *    a implementation-independent FrogData object so that it
     *    can be read and displayed in the view
     */
    suspend fun getFrogData(frog: Frog?) : FrogData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }

        var frogData : FrogData? = null
        realm.write {
            if (frog != null) {
                frogData = FrogData(
                    id = frog!!._id,
                    name= frog!!.name,
                    age = frog!!.age,
                    species = frog!!.species,
                    owner = frog!!.owner,
                    frog = frog)
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
    suspend fun addFrog(frogData : FrogData): FrogData? {
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
        return getFrogData(frog2)
    }

    /** Returns the first frog found that matches the given name *
     *   Returns a FrogData if a match is found, null otherwise.
     */
    suspend fun getFrog(frogName : String): FrogData? {
        if (!this::realm.isInitialized) {
            setupRealmSync()
        }

        // Search equality on the primary key field name
        val frog: Frog? = realm.query<Frog>(Frog::class, "name = \"$frogName\"").first().find()
        return getFrogData(frog)
    }
}