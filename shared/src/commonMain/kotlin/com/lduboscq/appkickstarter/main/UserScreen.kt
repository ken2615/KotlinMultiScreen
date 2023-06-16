package com.lduboscq.appkickstarter.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.lduboscq.appkickstarter.main.Database.UserRepositoryLocal
import com.lduboscq.appkickstarter.main.Database.UserScreenModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi

internal class UserScreen : Screen {

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {

        val screenModel = rememberScreenModel() { UserScreenModel(UserRepositoryLocal()) }
        val state by screenModel.state.collectAsState()
        var userName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var bmi by remember { mutableStateOf(0.0) }
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed)) //initial value of scaffold is that Drawer is close

        val coroutineScope = rememberCoroutineScope() // this is like AsyncTask where the task is executed at the background instead of the main thread

        Scaffold(

            scaffoldState = scaffoldState,

            topBar = {
                TopBar(
                    onMenuClicked = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()        //open the Drawer when the navigation icon is clicked
                        }
                    })
            },

            bottomBar = { BottomBar() },

            content = {
                //Text("This is the User Screen")
                Column {
                    TextField(value = userName, onValueChange = {userName = it})
                    TextField(value = email, onValueChange = {email = it})
                    TextField(value = password, onValueChange = {password = it})
                    TextField(value = bmi.toString(), onValueChange = {newValue ->
                        bmi = newValue.toDoubleOrNull() ?: 0.0})


                    Button(onClick = {screenModel.addUser(userName,password,email, bmi)}){
                        Text("Add User")
                    }
                    Button(onClick = {screenModel.getUser(userName)}) {
                        Text("Get User")
                    }

                }
            },

            drawerContent = {
                Drawer()
            }
        )
    }

}
