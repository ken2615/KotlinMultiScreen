package com.lduboscq.appkickstarter

import io.realm.kotlin.types.MutableRealmInt
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmUUID
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Frog: RealmObject {
    @PrimaryKey
    var _id: String = RealmUUID.random().toString()
    var name: String = ""
    var age: Int = 0
    var species: String = ""
    var owner: String = ""
}

/** Since we can't access the content of a Frog realm object without
 *    a lot of complications, this stores the latest information
 *    explicitly for the view.  We also store the Frog that supplied
 *    the information in case we need it.
 *  We'll use id instead of _id since it is more general
 */
data class FrogData(
                var id: String? = null,
                var name: String = "",
                var age: Int = 0,
                var species: String = "",
                var owner: String = "",
                var frog: Frog?)