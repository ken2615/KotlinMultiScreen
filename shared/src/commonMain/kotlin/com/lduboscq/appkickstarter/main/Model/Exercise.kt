package com.lduboscq.appkickstarter.main.Model

import io.realm.kotlin.types.RealmObject
/**
 * Exercise Model
 * */

data class Exercise (
    val url: String,
    val name: String,
    val equipment: String,
    val repetition: Int
)