package com.lduboscq.appkickstarter.main.Database

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.lduboscq.appkickstarter.main.Model.Exercise
import com.lduboscq.appkickstarter.main.Model.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserScreenModel(private val repository: UserRepository)
    :StateScreenModel<UserScreenModel.State>(State.Init){
    private val exercises = mutableListOf<Exercise>()

    /**
     * Create a list of exercises for the user
     * */
    init {
        exercises.addAll(
            listOf(
                Exercise("https://edbv2-ff7foj6vca-uc.a.run.app/image/RElVlxZ6hGj1jM", "Burpee", "Body Weight", 8),
                Exercise("https://edbv2-ff7foj6vca-uc.a.run.app/image/wnDXPLghLLa+oH", "Band alternating biceps curl", "Band", 8),
                Exercise("https://edbv2-ff7foj6vca-uc.a.run.app/image/g13m3HxC-9HKMN", "All fours squad stretch", "Body Weight", 8)
            )
        )
    }

        sealed class State {
            object Init : State()
            object Loading : State()
            sealed class Result: State() {
                class SingleResult(val userData: UserData?) : Result()
                class MultipleResult(val userDatas: Flow<UserData>?) : Result()
            }
        }

        fun getUser(userName: String){
            coroutineScope.launch {
                mutableState.value = State.Loading
                mutableState.value = State.Result.SingleResult(repository.getUser(userName))
            }
        }

        fun addUser(userName: String, passWord: String, Email: String, Bmi: Double){
            coroutineScope.launch {

                mutableState.value = State.Loading
                mutableState.value = State.Result.SingleResult(repository.addUser(
                    UserData(
                        name = userName,
                        password = passWord,
                        email = Email,
                        bmi = Bmi,
                        user = null,
                        exerciseList = exercises
                    )
                ))
                println(exercises)
            }
        }

    fun updateBMI(userName: String, newBMI: Double) {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result.SingleResult(repository.updateBMI(userName, newBMI))
        }
    }

    /*suspend fun getUserExercises(userName: String): List<Exercise> {
        return withContext(Dispatchers.Default) {
            val userData = repository.getUser(userName)
            userData?.exerciseList ?: emptyList()
        }
    }*/




}