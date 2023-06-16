package com.lduboscq.appkickstarter.main.Database

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserScreenModel(private val repository: UserRepository)
    :StateScreenModel<UserScreenModel.State>(State.Init){

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
                        user = null
                    )
                ))
            }
        }

}