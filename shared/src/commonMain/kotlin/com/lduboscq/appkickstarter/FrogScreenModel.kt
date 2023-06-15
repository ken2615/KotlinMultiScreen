package com.lduboscq.appkickstarter

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.launch

class FrogScreenModel(private val repository: FrogRepository)
    : StateScreenModel<FrogScreenModel.State>(State.Init) {

    sealed class State {
        object Init : State()
        object Loading : State()
        data class Result(val frog: Frog?) : State()
    }

    fun getFrog() {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result(frog = repository.getFrog())
        }
    }

    fun addFrog() {
         coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result(frog = repository.addFrog( name1 = "Kermit",
                age1 = 45,
                species1 = "Green",
                owner1 = "Jim"))
        }
    }

}
