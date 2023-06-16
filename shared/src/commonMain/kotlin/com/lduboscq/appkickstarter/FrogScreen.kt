package com.lduboscq.appkickstarter

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositeKeyHash
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.lduboscq.appkickstarter.list.ListScreenContent
import com.lduboscq.appkickstarter.list.PersonListScreenModel
import io.realm.kotlin.ext.asFlow

class FrogScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel() { FrogScreenModel(FrogRepositoryLocal()) }
        val state by screenModel.state.collectAsState()
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
            TextField (value = frogName, onValueChange={frogName = it})

            if (!frogName.isNullOrEmpty()) {
                /* This should be extended in a composable to ask for all the frog information */
                Button(onClick = { screenModel.addFrog(frogName) }) {
                    Text("Add Frog")
                }
            }
            if (!frogName.isNullOrEmpty()) {
                Button(onClick = { screenModel.getFrog(frogName) }) {
                    Text("Get Frog")
                }
            }
            if (!frogName.isNullOrEmpty()) {
                Button(onClick = { screenModel.updateFrog(frogName) }) {
                    Text("Update Frog")
                }
            }
            if (!frogName.isNullOrEmpty()) {
                Button(onClick = { screenModel.deleteFrog(frogName) }) {
                    Text("Delete Frog")
                }
            }
            if (state is FrogScreenModel.State.Result.SingleResult) { Text("The results of the action are:")
                FrogCard(frogData=(state as FrogScreenModel.State.Result.SingleResult).frogData)
            }
        }
    }
}