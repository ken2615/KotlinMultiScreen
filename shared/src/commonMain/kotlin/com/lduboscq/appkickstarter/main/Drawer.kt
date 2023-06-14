package com.lduboscq.appkickstarter.main
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/***
 * This is where the contents for the drawer goes when the user
 * click the navigation icon in the Top Bar. Here we will show
 * links for various exercises on a specific region of the body.
 */
@Composable
fun Drawer() {
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Text(text = "Chest Exercise", modifier = Modifier.padding(8.dp), color = Color.Black)
        Text(text = "Cardio Exercise", modifier = Modifier.padding(8.dp), color = Color.Black)
        Text(text = "Legs Exercise", modifier = Modifier.padding(8.dp), color = Color.Black)
        Text(text = "Back Exercise", modifier = Modifier.padding(8.dp), color = Color.Black)
        Text(text = "Shoulder Exercise", modifier = Modifier.padding(8.dp), color = Color.Black)
    }
}