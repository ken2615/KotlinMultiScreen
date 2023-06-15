package com.lduboscq.appkickstarter

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.internal.platform.runBlocking
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.AppConfiguration
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import io.realm.kotlin.schema.RealmStorageType
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FrogRepositoryLocal() {
    lateinit var realm: Realm
    private fun setupRealmSync() {
        val config = RealmConfiguration.Builder(setOf(Frog::class))
//            .inMemory()
            .build()
        realm = Realm.open(config)
    }

    suspend fun addFrog(name1: String, age1: Int, species1: String, owner1: String): Frog? {
        CoroutineScope(context = Dispatchers.Default).launch {
//        if (!this::realm.isInitialized) {
            setupRealmSync()
//        }

            var frog2: Frog? = null
            realm.write {
                // create a frog object in the realm
                frog2 = this.copyToRealm(Frog().apply {
                    _id = "" + RealmStorageType.UUID.name
                    name = name1
                    age = age1
                    species = species1
                    owner = owner1
                })
            }
            if (frog2 != null)
                println("Added frog " + frog2?.name)

        }
        return null
    }

    fun getFrog(): Frog? {

//        if (!this::realm.isInitialized) {
            setupRealmSync()
//        }

            // Search equality on the primary key field name
            val frog: Frog? = realm.query<Frog>(Frog::class, "name = 'Kermit'").first().find()
            return frog

    }
}
