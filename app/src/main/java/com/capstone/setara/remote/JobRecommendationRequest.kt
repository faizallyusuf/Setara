package com.capstone.setara.remote

data class JobRecommendationRequest(
    val disability: String,
    val age: String,
    val experience: String,
    val city: String
)
