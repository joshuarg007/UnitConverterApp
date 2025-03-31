package com.example.unitconverterapp

import org.junit.Assert.assertEquals
import org.junit.Test

class ConversionUtilsTest {

    // Temperature Tests
    @Test
    fun testCelsiusToFahrenheit() {
        val result = convertTemperature(0.0, "Celsius", "Fahrenheit")
        assertEquals(32.0, result, 0.01)
    }

    @Test
    fun testFahrenheitToKelvin() {
        val result = convertTemperature(32.0, "Fahrenheit", "Kelvin")
        assertEquals(273.15, result, 0.01)
    }

    @Test
    fun testKelvinToCelsius() {
        val result = convertTemperature(300.0, "Kelvin", "Celsius")
        assertEquals(26.85, result, 0.01)
    }

    // Length Tests
    @Test
    fun testMetersToFeet() {
        val result = convertLength(1.0, "Meters", "Feet")
        if (result != null) {
            assertEquals(3.28084, result, 0.01)
        }
    }

    @Test
    fun testMilesToKilometers() {
        val result = convertLength(1.0, "Miles", "Kilometers")
        if (result != null) {
            assertEquals(1.60934, result, 0.01)
        }
    }

    @Test
    fun testKilometersToMeters() {
        val result = convertLength(1.0, "Kilometers", "Meters")
        if (result != null) {
            assertEquals(1000.0, result, 0.01)
        }
    }
}
