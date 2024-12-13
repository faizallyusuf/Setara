package com.capstone.setara

import org.junit.Assert.assertEquals
import org.junit.Test

class VariableTest {

    @Test
    fun testVariableValues() {
        // Variabel yang ingin diuji
        val disability = "Daksa"
        val age = "25-30"
        val experience = "3-5"
        val city = "Bogor"

        // Nilai yang diharapkan
        val expectedDisability = "Daksa"
        val expectedAge = "25-30"
        val expectedExperience = "3-5"
        val expectedCity = "Bogor"

        // Memeriksa apakah nilai variabel sesuai dengan yang diharapkan
        assertEquals(expectedDisability, disability)
        assertEquals(expectedAge, age)
        assertEquals(expectedExperience, experience)
        assertEquals(expectedCity, city)
    }
}