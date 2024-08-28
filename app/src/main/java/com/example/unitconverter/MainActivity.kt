package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  UnitConverter()
                }
            }
        }
    }
}
@Composable
 fun UnitConverter(){
     var inputvalue by remember { mutableStateOf("") }
    var outputvalue by remember { mutableStateOf("") }
    var inputunit  by remember { mutableStateOf("Metres") }
    var outputunit  by remember { mutableStateOf("meters") }
    var iexpanded  by remember { mutableStateOf(false) }
    var oexpanded  by remember { mutableStateOf(false) }
    val conversionfactor= remember { mutableStateOf(1.00) }
    val oconversionfactor = remember { mutableStateOf(1.00)

    }


    fun convertunits() {
        // ?: - elvis operator
        val inputvaluedouble = inputvalue.toDoubleOrNull() ?: 0.0
        val result = (inputvaluedouble * conversionfactor.value * 100.0 / oconversionfactor.value).roundToInt() / 100.0
        outputvalue =result.toString()
    }

    Column (modifier = Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Unit Converter",
            style=MaterialTheme.typography.headlineMedium

        )
        Spacer(modifier = Modifier.height((16.dp)))
        OutlinedTextField(value = inputvalue,
            onValueChange = {
                inputvalue= it
                convertunits()


        },
            label = { Text( "enter  value")})

        Row {
            Box{
                //input button
                Button(onClick = { iexpanded=true }) {
                    Text(inputunit)
                     Icon(Icons.Default.ArrowDropDown,contentDescription = null)

                }
                DropdownMenu(expanded = iexpanded, onDismissRequest = { iexpanded=false }) {
                    DropdownMenuItem(text = { Text("Centimetres") },
                        onClick = {
                            iexpanded=false
                            inputunit="centimetres"
                            conversionfactor.value=0.01
                            convertunits()}
                    )
                    DropdownMenuItem(text = { Text("Millimetres") },
                        onClick = { iexpanded=false
                        inputunit="Millimetres"
                            conversionfactor.value=0.001
                        convertunits()
                        }
                    )
                    DropdownMenuItem(text = { Text("Metres") },
                        onClick = { iexpanded=false
                        inputunit="Metres"
                            conversionfactor.value=1.0
                        convertunits() }
                    )
                    DropdownMenuItem(text = { Text("Feets") },
                        onClick = { iexpanded=false
                        inputunit="feets"
                            conversionfactor.value=0.3048
                        convertunits() }
                    )

                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box{
                //output button
                Button(onClick = { oexpanded=true }) {
                    Text(outputunit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = null)

                }
                DropdownMenu(expanded = oexpanded, onDismissRequest = {oexpanded=false
                }) {
                    DropdownMenuItem(text = { Text("Centimetres") },
                        onClick = { oexpanded=false
                            outputunit="Centimetres"
                            oconversionfactor.value=0.01

                            convertunits()
                        }
                    )
                    DropdownMenuItem(text = { Text("Millimetres") },
                        onClick = { oexpanded=false

                            outputunit="millimetres"
                            oconversionfactor.value=0.01

                            convertunits()}
                    )
                    DropdownMenuItem(text = { Text("Metres") },
                        onClick = { oexpanded=false

                            outputunit="Metres"
                            oconversionfactor.value=1.00

                            convertunits()}
                    )
                    DropdownMenuItem(text = { Text("Feets") },
                        onClick = { oexpanded=false

                            outputunit="feets"
                            oconversionfactor.value=0.3048

                            convertunits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

        }
        // result text
            Text(text = "Result: $outputvalue $outputunit",
                    style = MaterialTheme.typography.headlineMedium

                )


    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
   UnitConverter()
}
