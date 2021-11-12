package com.example.matchgrouptestapp.repository

import com.example.matchgrouptestapp.service.ReviewService
import com.example.matchgrouptestapp.service.ServiceFactory

abstract class Repository {
    val reviewService = ServiceFactory.createService<ReviewService>()
}