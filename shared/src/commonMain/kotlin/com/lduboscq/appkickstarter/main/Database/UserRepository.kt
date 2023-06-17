package com.lduboscq.appkickstarter.main.Database

import com.lduboscq.appkickstarter.main.Model.UserData

interface UserRepository {

    suspend fun getUser(userName : String) : UserData?
    suspend fun addUser(userData: UserData) : UserData?


}