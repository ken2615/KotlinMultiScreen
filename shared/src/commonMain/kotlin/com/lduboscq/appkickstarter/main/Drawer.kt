package com.lduboscq.appkickstarter.main
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

/***
 * This is where the contents for the drawer goes when the user
 * click the navigation icon in the Top Bar. Here we will show
 * links for various exercises on a specific region of the body.
 */
@Composable
fun Drawer() {
    val navigator = LocalNavigator.currentOrThrow
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Text(text = "Chest Exercise", modifier = Modifier.padding(8.dp).clickable (onClick = {
            navigator.push(ScreenRouter(AllScreens.Chest))
        }), color = Color.Black)
        Text(text = "Cardio Exercise", modifier = Modifier.padding(8.dp).clickable (onClick = {
            navigator.push(ScreenRouter(AllScreens.Cardio))
        }), color = Color.Black)
        Text(text = "Legs Exercise", modifier = Modifier.padding(8.dp).clickable (onClick = {
            navigator.push(ScreenRouter(AllScreens.Legs))
        }), color = Color.Black)
        Text(text = "Back Exercise", modifier = Modifier.padding(8.dp).clickable (onClick = {
            navigator.push(ScreenRouter(AllScreens.Back))
        }), color = Color.Black)
        Text(text = "Shoulder Exercise", modifier = Modifier.padding(8.dp).clickable (onClick = {
            navigator.push(ScreenRouter(AllScreens.Shoulder))
        }), color = Color.Black)
    }
}