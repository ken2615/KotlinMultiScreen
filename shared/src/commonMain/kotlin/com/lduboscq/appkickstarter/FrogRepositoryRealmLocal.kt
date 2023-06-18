package com.lduboscq.appkickstarter

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class FrogRepositoryRealmLocal() : FrogRepositoryRealm() {

    override suspend fun setupRealmSync() {
        val config = RealmConfiguration.Builder(setOf(Frog::class))
//            .inMemory()
            .build()
        realm = Realm.open(config)
    }
}
