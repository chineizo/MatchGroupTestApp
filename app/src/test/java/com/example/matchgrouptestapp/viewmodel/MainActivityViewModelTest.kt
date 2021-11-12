package com.example.matchgrouptestapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.matchgrouptestapp.model.ServiceRequestStatus
import com.example.matchgrouptestapp.repository.ReviewRepository
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    @Mock
    private lateinit var viewModel: MainActivityViewModel

    @Spy
    private lateinit var repository: ReviewRepository

    @Mock
    private lateinit var observer: Observer<in ServiceRequestStatus>

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainActivityViewModel(repository)
        viewModel.getReview().observeForever(observer)
    }

    @Test
    fun `Verify livedata values changes on event`() {

        assertNotNull(viewModel.getReview())
    }
}