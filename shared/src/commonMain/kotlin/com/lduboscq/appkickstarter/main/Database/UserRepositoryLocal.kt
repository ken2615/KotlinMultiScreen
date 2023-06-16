package com.lduboscq.appkickstarter.main.Database

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class UserRepositoryLocal() : UserRepositoryRealm() {

    override suspend fun setupRealmSync() {
        val config = RealmConfiguration.Builder(setOf(User::class)).build()
        realm = Realm.open(config)
    }
}