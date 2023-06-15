@file:OptIn(ExperimentalMaterialApi::class)

package com.lduboscq.appkickstarter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun FrogCard(frogData:FrogData?) {
    Card(
    modifier = Modifier.size(width = 180.dp, height = 100.dp)
    .clip(MaterialTheme.shapes.small)
    ) {
        if (frogData != null) {
            Column {
                Text("Name : " + frogData?.name)
                Text("Age : " + frogData?.age)
                Text("Species : " + frogData?.species)
                Text("owner : " + frogData?.owner)
            }
        } else {
            Text("No frog result")
        }
    }
}