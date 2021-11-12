package com.example.matchgrouptestapp.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.matchgrouptestapp.R
import com.example.matchgrouptestapp.model.Review
import com.example.matchgrouptestapp.model.ServiceRequestStatus
import com.example.matchgrouptestapp.model.ServiceRequestStatus.Failure
import com.example.matchgrouptestapp.model.ServiceRequestStatus.Success
import com.example.matchgrouptestapp.repository.ReviewRepository
import com.example.matchgrouptestapp.ui.theme.MatchGroupTestAppTheme
import com.example.matchgrouptestapp.viewmodel.MainActivityViewModel

const val COFFEE_REVIEW__LIST_TEST = "coffee_review_list_test"
/**
 * Class that shows List of Reviews of Coffee Shops in California
 */
class MainActivity : ComponentActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val mainActivityViewModel by viewModels<MainActivityViewModel> {
        MainActivityViewModel.Factory(ReviewRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MatchGroupTestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ShowAppBar()
                }
            }
        }
    }

    /**
     * A method that shows the Top App bar with title
     */
    @Composable
    fun ShowAppBar() {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text = stringResource(R.string.app_name))
                })
            }
        ) {
            GetCoffeeShopReviewAndDisplayInList(mainActivityViewModel)
        }
    }

    /**
     * Composable method the request Reviews from a few Coffee Shops and display a Toast
     * Message when there's a failed API request
     */
    @Composable
    private fun GetCoffeeShopReviewAndDisplayInList(mainActivityViewModel: MainActivityViewModel) {
        val serviceRequestStatus: ServiceRequestStatus by mainActivityViewModel.getReview()
            .observeAsState(
                Success(
                    listOf()
                )
            )

        when (serviceRequestStatus) {
            is Failure -> {
                val error = (serviceRequestStatus as Failure).exception
                Log.d(TAG, "Some Error $error")
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()


            }
            is Success -> {
                val reviews = (serviceRequestStatus as Success).data
                Log.d(TAG, "Review size ${reviews.size}")
                ShowReviewList(reviews)
            }
        }
    }

    /**
     * Composable that display a List of Coffee Shop Reviews
     */
    @Composable
    fun ShowReviewList(review: List<Review>) {
        LazyColumn(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .testTag(COFFEE_REVIEW__LIST_TEST)
        ) {
            items(items = review) { review ->
                ReviewItem(review = review) {
                    Log.d(TAG, "Item Click is $review")
                    launchReviewDetailsActivity(review)
                }
            }
        }
    }

    /**
     * Display the Review Details Activity of the Coffee Shop
     */
    private fun launchReviewDetailsActivity(review: Review) {
        val intent = Intent(this, ReviewDetailActivity::class.java).apply {
            putExtra(ReviewDetailActivity.REVIEW_PARAMETER, review)
        }
        startActivity(intent)
    }

    /**
     * Composable that show Review Items in the List: Coffee Shop name, Review and Logo
     */
    @Composable
    fun ReviewItem(review: Review, onItemClick: () -> Unit) {
        Surface(
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .clickable(onClick = onItemClick)
                    .padding(8.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painterResource(id = Review.getIconResourceFromName(review.name)),
                    modifier = Modifier.size(50.dp),
                    contentDescription = "Cafe Logo"
                )
                Spacer(Modifier.size(4.dp))
                Column {
                    Text(text = review.name, style = MaterialTheme.typography.h6)
                    Text(text = review.review, style = MaterialTheme.typography.body1)
                }
            }
        }
    }
}