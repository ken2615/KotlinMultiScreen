package com.lduboscq.appkickstarter.main.ExerciseScreens

import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import com.lduboscq.appkickstarter.main.BottomBar
import com.lduboscq.appkickstarter.main.Drawer
import com.lduboscq.appkickstarter.main.TopBar
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi

internal class CardioScreen : Screen {

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {

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
                    }, "Cardio Exercise")
            },

            bottomBar = { BottomBar() },

            content = {
                Text("Cardio API")
            },

            drawerContent = {
                Drawer()
            }
        )
    }

}