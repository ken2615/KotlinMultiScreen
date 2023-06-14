package com.lduboscq.appkickstarter.main
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * This is the bottom bar of the Scaffolding. Here we show
 * the different sections of the App like the Home Page,
 * User Page, Shopping Page and Settings.
 **/
@Composable
fun BottomBar() {

    BottomAppBar(
        backgroundColor = Color(0xFFF6977A)//f6977a

    ) {
        //Text(text = "Bottom App Bar", color = Color.White)
        Spacer(Modifier.weight(0.5f))
        Icon(Icons.Filled.Home, contentDescription = "Home Page")
        Spacer(Modifier.weight(1f))
        Icon(Icons.Filled.Person, contentDescription = "User Page")
        Spacer(Modifier.weight(1f))
        Icon(Icons.Filled.ShoppingCart, contentDescription = "Shopping Page")
        Spacer(Modifier.weight(1f))
        Icon(Icons.Filled.Settings, contentDescription = "Settings Page")
        Spacer(Modifier.weight(0.5f))

    }
}