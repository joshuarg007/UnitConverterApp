package com.example.unitconverterapp

fun convertTemperature(value: Double, from: String, to: String): Double {
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
