package com.lduboscq.appkickstarter.main
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

/**
 * Base on the calculated BMI, this function will classify
 * the user's BMI and give the recommended exercises.
 *
 * */
@Composable
fun CheckBMI(bmi: Int ) {
    if(bmi >= 40){
        Text( text = "base on your BMI you are Severe Obese, these our recommended exercises for you", textAlign = TextAlign.Justify)
    }
    else if(bmi in 35..39) {
        Text( text = "base on your BMI you are Moderate Obese, these our recommended exercises for you", textAlign = TextAlign.Justify)
    }
    else if(bmi in 30 .. 34) {
        Text( text = "base on your BMI you are Mild Obese, these our recommended exercises for you", textAlign = TextAlign.Justify)
    }
    else if(bmi in 25 .. 29){
        Text( text = "base on your BMI you are Overweight, these our recommended exercises for you", textAlign = TextAlign.Justify)
    }
    else if(bmi in 18 .. 24){
        Text( text = "base on your BMI you have Normal Weight, these our recommended exercises for you", textAlign = TextAlign.Justify)
    }
    else if (bmi < 18){
        Text( text = "base on your BMI you are Under weight, these our recommended exercises for you", textAlign = TextAlign.Justify)
    }
}