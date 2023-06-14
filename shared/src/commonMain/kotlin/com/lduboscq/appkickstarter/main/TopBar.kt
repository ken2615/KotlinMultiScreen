package com.lduboscq.appkickstarter.main
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * This is the top bar that shows the name of the Page and
 * clickable Navigation Icon.
 **/
@Composable
fun TopBar(onMenuClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "Sign Up", color = Color.White)
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",

                modifier = Modifier.clickable (onClick = onMenuClicked),
                tint = Color.White
            )
        },
        backgroundColor = Color(0xFFF6977A)
    )
}
