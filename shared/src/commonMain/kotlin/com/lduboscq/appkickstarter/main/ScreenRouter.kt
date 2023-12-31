package com.lduboscq.appkickstarter.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.lduboscq.appkickstarter.main.ExerciseScreens.BackScreen
import com.lduboscq.appkickstarter.main.ExerciseScreens.CardioScreen
import com.lduboscq.appkickstarter.main.ExerciseScreens.ChestScreen
import com.lduboscq.appkickstarter.main.ExerciseScreens.LegsScreen
import com.lduboscq.appkickstarter.main.ExerciseScreens.ShoulderScreen

fun ScreenRouter(screen: AllScreens) : Screen{
    //val navigator = LocalNavigator.currentOrThrow
    var userName = ""

    return when (screen) {
        is AllScreens.Home -> NeatScreen()
        is AllScreens.Settings -> SettingScreen()
        //is AllScreens.User -> UserScreen(userName)
        is AllScreens.Shopping -> ShoppingScreen()
        is AllScreens.Chest -> ChestScreen()
        is AllScreens.Cardio -> CardioScreen()
        is AllScreens.Legs -> LegsScreen()
        is AllScreens.Back -> BackScreen()
        is AllScreens.Shoulder -> ShoulderScreen()
    }
}