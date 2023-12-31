package com.lduboscq.appkickstarter.main.Database

import com.lduboscq.appkickstarter.main.Model.User
import com.lduboscq.appkickstarter.main.Model.UserData
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmUUID

abstract class UserRepositoryRealm : UserRepository{
    lateinit var realm: Realm

    abstract suspend fun setupRealmSync()

    /**
     * get user's data
     * */

    suspend fun getUserData(user: User?) : UserData? {
        if(!this::realm.isInitialized) {
            setupRealmSync()
        }
        var userData : UserData? = null
        realm.write {
            if(user != null){
                userData = UserData(
                    id = user!!._id,
                    name = user!!.name,
                    password = user!!.password,
                    email = user!!.email,
                    bmi = user!!.bmi,
                    //exerciseList = user!!.exerciseList.toList(),
                    user = user
                )
            }
        }
        return userData
    }

    private fun closeRealmSync() {
        realm.close()
    }

    /** Adds user
     * */
    override suspend fun addUser(userData: UserData) : UserData? {
        if(!this::realm.isInitialized) {
            setupRealmSync()
        }

        var user2: User? = null
        realm.write {
            user2 = this.copyToRealm(User().apply {
                _id = userData.id ?: RealmUUID.random().toString()
                name = userData.name
                password = userData.password
                email = userData.email
                bmi = userData.bmi
                //exerciseList = userData.exerciseList.toRealmList()
            })
        }
        return getUserData(user2)
    }

    // returns the first user that matches the given name
    override suspend fun getUser(userName : String): UserData? {
        if(!this::realm.isInitialized) {
            setupRealmSync()
        }
        val user : User? = realm.query<User>(User::class, "name = \"$userName\"").first().find()
        return getUserData(user)

    }

    /**
     * Updates the user BMI
     * */
    override suspend fun updateBMI(userName: String, newBMI: Double): UserData? {
        if(!this::realm.isInitialized){
            setupRealmSync()
        }
        var userData: UserData? = null
        try{
            val user: User? =
                realm.query<User>(User::class, "name = \"$userName\"").first().find()

            realm.write {
                if(user != null) {
                    findLatest(user)!!.bmi = newBMI
                }
            }
            userData = getUserData(user)

        }catch (e: Exception){
            println(e.message)
        }
        return userData
    }
}