package com.lduboscq.appkickstarter.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lduboscq.appkickstarter.ui.Image
import com.lduboscq.appkickstarter.ui.theme.ThemeShapes
import dev.icerock.moko.resources.compose.painterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
//import org.jetbrains.compose.resources.painterResource
//import ui.theme.ThemeShapes

/**
 * This is a signup form that gets the user's name, email, password,
 * weight and height. It has an option for the user to choose between
 * Metric and Imperial measurements. When the user filled-up all the
 * required fields, the Submit button will appear. If the Submit button
 * is pressed, it will calculate the user's BMI.
 **/
@OptIn(ExperimentalResourceApi::class)
@Composable
fun MyForm() {
    val nameValue = rememberSaveable{ mutableStateOf("") }
    val emailValue = rememberSaveable{ mutableStateOf("") }
    val passwordValue = rememberSaveable{ mutableStateOf("") }
    var metric by rememberSaveable { mutableStateOf(true) }
    val showText = rememberSaveable {(mutableStateOf(false))}
    val metricHeight = rememberSaveable { (mutableStateOf("")) }
    val metricWeight = rememberSaveable { (mutableStateOf("")) }
    val imperialFoot = rememberSaveable { (mutableStateOf("")) }
    val imperialInch = rememberSaveable { (mutableStateOf("")) }
    val imperialWeight = rememberSaveable { (mutableStateOf("")) }
    var bmiWeight = rememberSaveable { (mutableStateOf(0.0)) }
    var bmiHeight = rememberSaveable { (mutableStateOf(0.0)) }
    var bmi = rememberSaveable { (mutableStateOf(0.0)) }

    LazyColumn(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(start = 10.dp, end = 10.dp)
    ) {
        item {
            Image(
                url = "https://cdn5.vectorstock.com/i/1000x1000/45/24/sport-fitness-banner-promotion-vector-28784524.jpg",
                //url="https://e1.pxfuel.com/desktop-wallpaper/885/698/desktop-wallpaper-girl-fitness-exercise-gym-dumbbells-workout-gym-workout.jpg",
                //painterResource("Exercise.jpg"),
                contentDescription = "Image Description",
                modifier = Modifier.fillMaxWidth()
            )


            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().clip(ThemeShapes.small),
                value = nameValue.value,
                onValueChange = { nameValue.value = it },
                textStyle = TextStyle(textAlign = TextAlign.Start),
                label = { Text(text = "Enter your Name") },
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Name") }
            )


            Spacer(modifier = Modifier.height(20.dp).width(20.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().clip(ThemeShapes.small),
                value = emailValue.value,
                onValueChange = { emailValue.value = it },
                textStyle = TextStyle(textAlign = TextAlign.Start),
                label = { Text(text = "Enter your Email") },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email") }
            )
            Spacer(modifier = Modifier.height(20.dp).width(20.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().clip(ThemeShapes.small),
                value = passwordValue.value,
                onValueChange = { passwordValue.value = it },
                textStyle = TextStyle(textAlign = TextAlign.Start),
                label = { Text(text = "Enter your Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password") }
            )

            Row {
                OutlinedButton(onClick = {
                    metric = true
                    showText.value = false
                }) {
                    Text(text = "Metric")
                    //println(metric)
                }
                Spacer(modifier = Modifier.width(10.dp))
                OutlinedButton(onClick = {
                    metric = false
                    showText.value = false
                }) {
                    Text(text = "Imperial")
                    //println(metric)
                }
            }

            if (metric) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().clip(ThemeShapes.small),
                    value = metricHeight.value,
                    onValueChange = { metricHeight.value = it },
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    label = { Text(text = "Height: centimeters") }
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().clip(ThemeShapes.small),
                    value = metricWeight.value,
                    onValueChange = { metricWeight.value = it },
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    label = { Text(text = "Weight: kilograms") }
                )
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f).clip(ThemeShapes.small),
                        value = imperialFoot.value,
                        onValueChange = { imperialFoot.value = it },
                        textStyle = TextStyle(textAlign = TextAlign.Center),
                        label = { Text(text = "Foot") }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    OutlinedTextField(
                        modifier = Modifier.weight(1f).clip(ThemeShapes.small),
                        value = imperialInch.value,
                        onValueChange = { imperialInch.value = it },
                        textStyle = TextStyle(textAlign = TextAlign.Center),
                        label = { Text(text = "Inch") }
                    )
                }
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth().clip(ThemeShapes.small),
                    value = imperialWeight.value,
                    onValueChange = { imperialWeight.value = it },
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    label = { Text(text = "Weight: pounds") }
                )
            }

            if (nameValue.value.isNotEmpty() && emailValue.value.isNotEmpty() && passwordValue.value.isNotEmpty()) {
                if (metric && metricHeight.value.isNotEmpty() && metricWeight.value.isNotEmpty()) {
                    Button(onClick = {
                        bmiWeight.value = metricWeight.value.toDouble()
                        bmiHeight.value = metricHeight.value.toDouble() / 100.0
                        bmi.value = bmiWeight.value / (bmiHeight.value * bmiHeight.value)
                        showText.value = true
                    }) {
                        Text(text = "Submit")
                    }
                } else if (!metric && imperialFoot.value.isNotEmpty() && imperialInch.value.isNotEmpty() && imperialWeight.value.isNotEmpty()) {
                    Button(onClick = {
                        bmiHeight.value =
                            ((imperialFoot.value.toDouble() * 12) + imperialInch.value.toDouble()) / 39.37
                        bmiWeight.value = imperialWeight.value.toDouble() / 2.205
                        bmi.value = bmiWeight.value / (bmiHeight.value * bmiHeight.value)
                        showText.value = true
                    }) {
                        Text(text = "Submit")

                    }
                }
                if (showText.value) {
                    Text(text = "Your BMI is ${bmi.value.toInt()}")
                    CheckBMI(bmi.value.toInt())
                }
            }
        }
    }
}
