package com.lduboscq.appkickstarter

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.lduboscq.appkickstarter.list.ListScreenContent
import com.lduboscq.appkickstarter.list.PersonsListScreen
import com.lduboscq.appkickstarter.main.NeatScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
public fun MainApp() {
//    Navigator(PersonsListScreen()) { navigator ->
//        SlideTransition(navigator)
//    }

    Navigator(FrogScreen()) { navigator ->
        SlideTransition(navigator)
    }

//    Navigator(NeatScreen()) { navigator ->
//        SlideTransition(navigator)
//    }

}
