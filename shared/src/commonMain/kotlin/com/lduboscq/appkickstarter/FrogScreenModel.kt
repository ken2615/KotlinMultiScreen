package com.lduboscq.appkickstarter

import androidx.compose.runtime.MutableState
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FrogScreenModel(private val repository: FrogRepository)
    : StateScreenModel<FrogScreenModel.State>(State.Init) {
    private val _frogList = MutableStateFlow<List<FrogData>>(emptyList())
    val frogDatasState: StateFlow<List<FrogData>> = _frogList.asStateFlow()

    init {
        coroutineScope.launch {
            var frogDatas: Flow<List<FrogData>> = repository.getAllFrogsFlow(null)
            frogDatas.collect { frogs ->
                _frogList.value = frogs
            }
        }
    }

    sealed class State {
        object Init : State()
        object Loading : State()
        sealed class Result: State() {
            class SingleResult(val frogData: FrogData?) : Result()
            class MultipleResult(val frogDatas: MutableState<List<FrogData>>) : Result()
            class MultipleResultList(val frogDatas: List<FrogData>) : Result()
        }
    }

    fun getFrog(frogName : String) {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result.SingleResult(repository.getFrog(frogName))
        }
    }
    fun getAllFrogs(frogName : String?) {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result.MultipleResult(repository.getAllFrogs(frogName))
        }
    }

    fun getAllFrogsList(frogName : String?) {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result.MultipleResultList(repository.getAllFrogsList(frogName))
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

    fun updateFrog(frogName : String) {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result.SingleResult(repository.updateFrog(frogName))
        }
    }

    fun deleteFrog(frogName : String) {
        coroutineScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result.SingleResult(repository.deleteFrog(frogName))
        }
    }

}
