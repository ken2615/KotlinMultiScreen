package com.lduboscq.appkickstarter

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.launch

class FrogScreenModel(private val repository: FrogRepositoryLocal)
    : StateScreenModel<FrogScreenModel.State>(State.Init) {

    sealed class State {
        object Init : State()
        object Loading : State()
        sealed class Result: State() {
            class SingleResult(val frogData: FrogData?) : Result()
            class ListResult(val frogList: List<Frog>?) : Result()
        }
    }

    fun getFrog(frogName : String) {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result.SingleResult(repository.getFrog(frogName))
        }
    }

    /* Sample add function.  It accepts a name string, but fills in the other
         Frog data fields with fixed values for now.
     */
    fun addFrog(frogName : String) {
         coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result.SingleResult(repository.addFrog(
                FrogData(
                name = frogName,
                age = 45,
                species = "Green",
                owner = "Jim",
                frog = null)))
        }
    }

}
