package com.lduboscq.appkickstarter.main
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi

/**
 * This is the function that creates the Scaffold Composable. The scaffold
 * contains the TopBar, BottomBar, Body and the Drawer.
 * The drawer is inspired from: https://www.geeksforgeeks.org/scaffold-in-android-using-jetpack-compose/
 */

@OptIn(ExperimentalResourceApi::class)
@Composable
fun NeatScreen() {

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
            Body()
        },

        drawerContent = {
            Drawer()
        }
    )
}
