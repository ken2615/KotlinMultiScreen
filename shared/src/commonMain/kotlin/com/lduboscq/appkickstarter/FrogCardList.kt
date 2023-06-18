package com.lduboscq.appkickstarter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FrogCardList(frogDatas : List<FrogData>) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        // Display the fetched frogs in your UI
        for (frogData in frogDatas) {
            // Display each FrogData object
            FrogCard(frogData)
            // Add other UI components as needed
        }
    }
}