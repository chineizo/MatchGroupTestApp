package com.example.matchgrouptestapp.service

import com.example.matchgrouptestapp.model.Review
import retrofit2.Call
import retrofit2.http.GET

/**
 * Abstraction for the Review API requests
 */
interface ReviewService {
    @GET("jsonBlob/893163324234285056")
    fun getReview(): Call<List<Review>>
}