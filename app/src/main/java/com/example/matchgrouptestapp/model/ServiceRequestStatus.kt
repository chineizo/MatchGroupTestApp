package com.example.matchgrouptestapp.model

/**
 * Seal class that indicate state of Review API requests.
 */
sealed class ServiceRequestStatus {
    data class Success(val data: List<Review>) : ServiceRequestStatus()
    data class Failure(val exception: String?) : ServiceRequestStatus()
}