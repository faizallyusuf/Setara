package com.capstone.setara

import com.capstone.setara.remote.JobRecommendationResponse
import com.capstone.setara.remote.ApiService
import com.capstone.setara.remote.JobRecommendationRequest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

class ApiResponseTest {

    private val apiService = Mockito.mock(ApiService::class.java)

    @Test
    fun testJobRecommendationResponse() = runBlocking {
        // Siapkan data yang akan dikirim
        val request = JobRecommendationRequest(
            disability = "Daksa",
            age = "25-30",
            experience = "3-5",
            city = "Bogor"
        )

        // Siapkan respons yang diharapkan
        val expectedResponse = JobRecommendationResponse(message = "kerjaan")

        // Mocking API service untuk mengembalikan respons yang diharapkan
        Mockito.`when`(apiService.sendJobRecommendation(request)).thenReturn(expectedResponse)

        // Panggil metode untuk mendapatkan respons
        val actualResponse = apiService.sendJobRecommendation(request)

        // Periksa apakah respons yang diterima sesuai dengan yang diharapkan
        assertEquals(expectedResponse.message, actualResponse.message)
    }
}