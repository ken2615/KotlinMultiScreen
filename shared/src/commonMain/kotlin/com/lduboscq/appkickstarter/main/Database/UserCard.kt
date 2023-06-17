package com.lduboscq.appkickstarter.main.Database

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.lduboscq.appkickstarter.main.Model.UserData

@Composable
fun UserCard(userData: UserData?) {

    Card(
        //modifier = Modifier.size(width = 180.dp, height = 100.dp)
            //.clip(MaterialTheme.shapes.small)
    ) {
        if(userData != null){
            Column {
                Text("Name: " + userData.name)
                Text("Password: " + userData.password)
                Text("Email: " + userData.email)
                Text("BMI: " + userData.bmi)
            }
        }
        else {
            Text("No User")
        }
    }
}