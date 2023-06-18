package com.lduboscq.appkickstarter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen

class FrogScreen : Screen {
    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel() { FrogScreenModel(FrogRepositoryRealmLocal()) }
        val state by screenModel.state.collectAsState()
        val frogDatasState by screenModel.frogDatasState.collectAsState()

        var frogName by remember { mutableStateOf("") }

        Column {
            when (val result = state) {
                is FrogScreenModel.State.Init -> Text("Just initialized")
                is FrogScreenModel.State.Loading -> Text("Loading")
                is FrogScreenModel.State.Result -> {
                    Text("Success")
                }
            }

            Text("Please enter the name of the frog to add/get/update/delete")
            TextField(value = frogName, onValueChange = { frogName = it })

            if (!frogName.isNullOrEmpty()) {
                /* This should be extended in a composable to ask for all the frog information */
                Row {
                    Button(onClick = { screenModel.addFrog(frogName) }) {
                        Text("Add Frog")
                    }
                    Button(onClick = { screenModel.getFrog(frogName) }) {
                        Text("Get Frog")
                    }
                    Button(onClick = { screenModel.updateFrog(frogName) }) {
                        Text("Update Frog")
                    }
                    Button(onClick = { screenModel.deleteFrog(frogName) }) {
                        Text("Delete Frog")
                    }
                }
            }
            Button(onClick = {
                if (frogName.isNullOrEmpty()) screenModel.getAllFrogs(null)
                else screenModel.getAllFrogs(frogName)
            })
            {
                Text("Get All Frogs Flow")
            }
            Button(onClick = {
                if (frogName.isNullOrEmpty()) screenModel.getAllFrogsList(null)
                else screenModel.getAllFrogsList(frogName)
            })
            {
                Text("Get All Frogs List")
            }
            if (state is FrogScreenModel.State.Result.SingleResult) {
                if ((state as FrogScreenModel.State.Result.SingleResult).frogData == null) {
                    Text("Not found.  Please try again.")
                } else {
                    Text("The results of the action are:")
                    FrogCard(frogData = (state as FrogScreenModel.State.Result.SingleResult).frogData)
                }
            } else if (state is FrogScreenModel.State.Result.MultipleResult) {
                if ((state as FrogScreenModel.State.Result.MultipleResult).frogDatas == null) {
                    Text("Not found.  Please try again.")
                } else {
                    Text("The results of the action are:")
                    // Access the fetched frogs from the state
                    val frogDatas = (state as FrogScreenModel.State.Result.MultipleResult).frogDatas

                    FrogCardList(frogDatas.value)
                }
            }
            else if (state is FrogScreenModel.State.Result.MultipleResultList) {
                if ((state as FrogScreenModel.State.Result.MultipleResultList).frogDatas == null) {
                    Text("Not found.  Please try again.")
                } else {
                    Text("The results of the action are:")
                    // Access the fetched frogs from the state
                    val frogDatas = (state as FrogScreenModel.State.Result.MultipleResultList).frogDatas

                    FrogCardList(frogDatas)
                }
            }
            Text("Here is the always-on List")
            FrogCardList(frogDatas = frogDatasState)
        }
    }
}
