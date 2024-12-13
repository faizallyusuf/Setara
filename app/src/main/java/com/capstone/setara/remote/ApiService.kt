package com.capstone.setara.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("job-recommendation-inference")
    suspend fun sendJobRecommendation(
        @Body request: JobRecommendationRequest
    ): JobRecommendationResponse
}