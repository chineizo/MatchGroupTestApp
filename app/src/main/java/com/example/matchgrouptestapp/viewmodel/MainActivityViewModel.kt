package com.example.matchgrouptestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.matchgrouptestapp.model.ServiceRequestStatus
import com.example.matchgrouptestapp.repository.ReviewRepository

/**
 * View model class that exposes Review API access and Live Data to observe for Review Items returned
 * from the API request
 */
open class MainActivityViewModel(private val reviewRepository: ReviewRepository) : ViewModel() {

    /**
     * Get Review
     */
    fun getReview(): LiveData<ServiceRequestStatus> = reviewRepository.getReviews()

    /**
     * Factory class to produce an instance of MainActivityViewModel
     */
    internal class Factory(private val reviewRepository: ReviewRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MainActivityViewModel(reviewRepository) as T

    }
}