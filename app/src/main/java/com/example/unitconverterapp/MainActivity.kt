package com.example.unitconverterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.unitconverterapp.ui.theme.UnitConverterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    val categories = listOf("Temperature", "Length")
    val temperatureUnits = listOf("Celsius", "Fahrenheit", "Kelvin")
    val lengthUnits = listOf("Meters", "Kilometers", "Feet", "Miles")

    var inputValue by remember { mutableStateOf("") }
    var category by remember { mutableStateOf(categories[0]) }
    var fromUnit by remember { mutableStateOf("") }
    var toUnit by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val unitOptions = when (category) {
        "Temperature" -> temperatureUnits
        "Length" -> lengthUnits
        else -> emptyList()
    }

    // Reset units if they're no longer valid in the new category
    LaunchedEffect(category) {
        fromUnit = unitOptions.first()
        toUnit = unitOptions.getOrNull(1) ?: unitOptions.first()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DropdownMenuBox(
            label = "Category",
            selectedUnit = category,
            units = categories,
            onUnitSelected = { category = it }
        )

        OutlinedTextField(
            value = inputValue,
            onValueChange = { inputValue = it },
            label = { Text("Enter value") },
            singleLine = true
        )

        DropdownMenuBox(
            label = "From Unit",
            selectedUnit = fromUnit,
            units = unitOptions,
            onUnitSelected = { fromUnit = it }
        )

        DropdownMenuBox(
            label = "To Unit",
            selectedUnit = toUnit,
            units = unitOptions,
            onUnitSelected = { toUnit = it }
        )

        Button(onClick = {
            val input = inputValue.toDoubleOrNull()
            result = if (input != null) {
                val output = when (category) {
                    "Temperature" -> convertTemperature(input, fromUnit, toUnit)
                    "Length" -> convertLength(input, fromUnit, toUnit)
                    else -> null
                }
                if (output != null) "$input $fromUnit = $output $toUnit" else "Conversion error"
            } else {
                "Invalid input"
            }
        }) {
            Text("Convert")
        }

        Text(text = result, style = MaterialTheme.typography.titleMedium)
    }
}


@Composable
fun DropdownMenuBox(
    label: String,
    selectedUnit: String,
    units: List<String>,
    onUnitSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(text = label)
        Box {
            OutlinedButton(onClick = { expanded = true }) {
                Text(text = selectedUnit)
            }

            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                units.forEach { unit ->
                    DropdownMenuItem(
                        text = { Text(unit) },
                        onClick = {
                            onUnitSelected(unit)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
/** fun convertTemperature(value: Double, from: String, to: String): Double {
    val celsius = when (from) {
        "Celsius" -> value
        "Fahrenheit" -> (value - 32) * 5 / 9
        "Kelvin" -> value - 273.15
        else -> value
    }

    return when (to) {
        "Celsius" -> celsius
        "Fahrenheit" -> (celsius * 9 / 5) + 32
        "Kelvin" -> celsius + 273.15
        else -> celsius
    }
}
**/

/**
fun convertLength(value: Double, from: String, to: String): Double? {
    val meters = when (from) {
        "Meters" -> value
        "Kilometers" -> value * 1000
        "Feet" -> value * 0.3048
        "Miles" -> value * 1609.34
        else -> return null
    }

    return when (to) {
        "Meters" -> meters
        "Kilometers" -> meters / 1000
        "Feet" -> meters / 0.3048
        "Miles" -> meters / 1609.34
        else -> null
    }
}
**/
