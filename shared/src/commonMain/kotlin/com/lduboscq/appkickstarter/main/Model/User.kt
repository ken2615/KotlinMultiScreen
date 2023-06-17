package com.lduboscq.appkickstarter.main.Model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmUUID

class User: RealmObject {

    var _id: String = RealmUUID.random().toString()
    var name: String = ""
    var password: String = ""
    var email: String = ""
    var bmi: Double = 0.0
}

data class UserData(
    var id: String? = null,
    var name: String = "",
    var password: String = "",
    var email: String = "",
    var bmi: Double = 0.0,
    var user: User?
)