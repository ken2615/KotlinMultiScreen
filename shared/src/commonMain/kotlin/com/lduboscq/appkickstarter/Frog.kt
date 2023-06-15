package com.lduboscq.appkickstarter

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Frog() : RealmObject {
    @PrimaryKey
    var _id: String = ""
    var name: String = ""
    var age: Int = 0
    var species: String = ""
    var owner: String = ""
}
