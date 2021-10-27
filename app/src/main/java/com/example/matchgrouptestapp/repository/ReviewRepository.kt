package com.example.matchgrouptestapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.matchgrouptestapp.model.Review
import com.example.matchgrouptestapp.model.ServiceRequestStatus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Abstraction layer to interacts with the Review API to return Review items
 */
class ReviewRepository : Repository() {


    /**
     * Returns a live data of the Review Items received from the API call.
     */
    fun getReviews(): LiveData<ServiceRequestStatus> {
        //EspressoIdlingResource.countingIdlingResource.increment()
        val reviewLiveData = MutableLiveData<ServiceRequestStatus>()
        reviewService.getReview().enqueue(object : Callback<List<Review>> {
            override fun onResponse(
                call: Call<List<Review>>,
                response: Response<List<Review>>
            ) {
                if (response.isSuccessful) {
                    val reviewList = response.body() as List<Review>
                    reviewLiveData.postValue(ServiceRequestStatus.Success(reviewList))
                } else {
                    reviewLiveData.postValue(ServiceRequestStatus.Failure("API Response failed!"))
                }
                //EspressoIdlingResource.countingIdlingResource.decrement()
            }

            override fun onFailure(call: Call<List<Review>>, t: Throwable) {
                reviewLiveData.value = ServiceRequestStatus.Failure(t.localizedMessage)
            }

        })
        return reviewLiveData
    }
}