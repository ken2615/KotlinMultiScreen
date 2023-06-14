package com.lduboscq.appkickstarter.main
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * This is the body of the scaffold where we show the logo of the app
 * and the Signup Form.
 **/
@Composable
fun Body() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        MyForm()
    }
}