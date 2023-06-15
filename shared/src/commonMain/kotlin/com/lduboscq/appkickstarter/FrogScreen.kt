package com.lduboscq.appkickstarter

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
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

class FrogScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel() { FrogScreenModel(FrogRepository()) }
        val state by screenModel.state.collectAsState()
        var frogFound: Frog? by remember { mutableStateOf(null) }
        Column {
            when (val result = state) {
                is FrogScreenModel.State.Init -> Text("Just initialized")
                is FrogScreenModel.State.Loading -> Text("Loading")
                is FrogScreenModel.State.Result -> Text("Success" ?: "No result")
            }

            Button(onClick = { screenModel.addFrog() }) {
                Text("Add Frog")
            }
            Button(onClick = { screenModel.getFrog() }) {
                Text("Get Frog")
            }
            if (frogFound != null) {
                Text("Found frog" + frogFound?.name)
            }
        }
    }
}