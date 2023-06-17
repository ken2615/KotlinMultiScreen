package com.lduboscq.appkickstarter.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.lduboscq.appkickstarter.main.Database.UserCard
import com.lduboscq.appkickstarter.main.Database.UserRepositoryLocal
import com.lduboscq.appkickstarter.main.Database.UserScreenModel
import com.lduboscq.appkickstarter.ui.Image
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi

internal class UserScreen(val personName: String, val passWord: String, val emailValue: String, val bmiValue: Double) : Screen {

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
                    }, "User Screen")
            },

            bottomBar = { BottomBar() },

            content = {
                //Text("This is the User Screen")
                Column {
                    Text("Hello $personName")
//                    TextField(value = userName, onValueChange = {userName = it})
//                    TextField(value = email, onValueChange = {email = it})
//                    TextField(value = password, onValueChange = {password = it})
//                    TextField(value = bmi.toString(), onValueChange = {newValue ->
//                        bmi = newValue.toDoubleOrNull() ?: 0.0})
//
//
//                    Button(onClick = {screenModel.addUser(userName,password,email, bmi)}){
//                        Text("Add User")
//                    }
//                    Button(onClick = {screenModel.getUser(userName)}) {
//                        Text("Get User")
//                    }

                    Button(onClick = {screenModel.addUser(personName, passWord,emailValue, bmiValue)}){
                        Text("Add User")
                    }
                    Button(onClick = {screenModel.getUser(personName)}) {
                        Text("Get User")
                    }

                    if(state is UserScreenModel.State.Result.SingleResult){
                        Text("The results are: ")
                        UserCard(userData = (state as UserScreenModel.State.Result.SingleResult).userData)
                    }

                    Card(
                        Modifier
                            //.fillMaxSize()
                            .fillMaxWidth()
                            .wrapContentHeight(Alignment.CenterVertically)
                            .padding(horizontal = 16.dp)
                    ){
                        Row(Modifier.padding(16.dp)) {
                            Image(
                                "https://edbv2-ff7foj6vca-uc.a.run.app/image/RElVlxZ6hGj1jM",
                                Modifier.size(100.dp).clip(CircleShape)
                            )
                            Spacer(Modifier.width(4.dp))
                            Column {
                                Text("Burpee")
                                Text("Equipment: Body Weight")
                                Text("3x8 sets")
                            }
                        }
                    }
                    //Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        Modifier
                            //.fillMaxSize()
                            .fillMaxWidth()
                            .wrapContentHeight(Alignment.CenterVertically)
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    ){
                        Row(Modifier.padding(16.dp)) {
                            Image(
                                "https://edbv2-ff7foj6vca-uc.a.run.app/image/wnDXPLghLLa+oH",
                                Modifier.size(100.dp).clip(CircleShape)
                            )
                            Spacer(Modifier.width(4.dp))
                            Column {
                                Text("Band alternating biceps curl")
                                Text("Equipment: Band")
                                Text("3x8 sets")
                            }
                        }
                    }

                    Card(
                        Modifier
                            //.fillMaxSize()
                            .fillMaxWidth()
                            .wrapContentHeight(Alignment.CenterVertically)
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    ){
                        Row(Modifier.padding(16.dp)) {
                            Image(
                                "https://edbv2-ff7foj6vca-uc.a.run.app/image/g13m3HxC-9HKMN",
                                Modifier.size(100.dp).clip(CircleShape)
                            )
                            Spacer(Modifier.width(4.dp))
                            Column {
                                Text("All fours squad stretch")
                                Text("Equipment: Body Weight")
                                Text("3x8 sets")
                            }
                        }
                    }


                }

            },

            drawerContent = {
                Drawer()
            }
        )
    }

}
