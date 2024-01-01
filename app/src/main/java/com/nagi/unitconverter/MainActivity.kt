package com.nagi.unitconverter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nagi.unitconverter.ui.theme.UnitConverterTheme
import kotlin.jvm.internal.Intrinsics.Kotlin

class MainActivity : ComponentActivity() {

    val unit = Unit(2, 6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create a unit object

        setContent {
          MainUi(

          )
        }
    }
}

data class Unit(var celcius: Int, var fahrenheit: Int){
    fun updateCelcius(celcius: Int) {
        Log.d("Unit", "updateCelcius: " + celcius)
        this.celcius = celcius
        // update fahrenheit
        convertCelciusToFahrenheit()

    }

    fun updateFahrenheit(fahrenheit: Int) {
        this.fahrenheit = fahrenheit
    }

    fun convertCelciusToFahrenheit() {
        Log.d("Unit", "convertCelciusToFahrenheit: " + this.celcius)
        this.fahrenheit = (this.celcius * 9/5) + 32

        Log.d("Unit", "convertCelciusToFahrenheit: " + this.fahrenheit)
    }
}

@Composable
fun MainUi(
) {

    var text by remember { mutableStateOf("0") }

    val unit by remember { mutableStateOf(Unit(0, 0)) }



    UnitConverterTheme {
        Surface(modifier = Modifier
            .fillMaxSize()
            .background(
                Color.Red
            )) {
          Column(
              Modifier
                  .fillMaxWidth()
                  .background(Color.Magenta)
                  .padding(top = 16.dp)) {

              Row {
                  Text(text = "Unit Converter",
                      fontSize = 36.sp,
                      color = Color.White,
                      modifier = Modifier.fillMaxWidth(),
                      textAlign = TextAlign.Center
                  )
              }

              Row (
                  Modifier
                      .fillMaxWidth()
                      .padding(bottom = 10.dp)){
                  Text(text = "Celcius to Fahrenheit",
                      fontSize = 16.sp,
                      color = Color.White,
                      modifier = Modifier.fillMaxWidth(),
                      textAlign = TextAlign.Center
                  )
              }

              Row {
                    TextField(value = text, onValueChange ={
                        text = it
                        unit.updateCelcius(text.toInt())
                    },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Enter celcius Value") }
                    )
              }

              Row (
                  Modifier
                      .fillMaxWidth()
                      .padding(10.dp)) {
                 Text(text = "Fahrenheit Value = ${unit.fahrenheit} °F",
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                    )
              }

              Row {
                  Button(onClick = {
                        unit.convertCelciusToFahrenheit()
                  }, modifier = Modifier.fillMaxWidth().padding(15.dp)) {
                        Text(text = "Convert",
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                  }
              }
          }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverterTheme {
        MainUi(
        )
    }
}