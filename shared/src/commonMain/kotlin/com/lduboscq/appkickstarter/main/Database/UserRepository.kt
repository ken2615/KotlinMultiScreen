package com.lduboscq.appkickstarter.main.Database

interface UserRepository {

    suspend fun getUser(userName : String) : UserData?
    suspend fun addUser(userData: UserData) : UserData?


}