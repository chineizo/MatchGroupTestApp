package com.example.matchgrouptestapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.matchgrouptestapp.R
import com.example.matchgrouptestapp.model.Review
import com.example.matchgrouptestapp.ui.theme.MatchGroupTestAppTheme

/**
 * Class that shows the details of the Review, containing Review, Location and Rating
 */
class ReviewDetailActivity : ComponentActivity() {
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
                    Text(text = stringResource(R.string.coffee_shop_details))
                })
            }
        ) {
            val review = intent.extras?.getParcelable<Review>(REVIEW_PARAMETER)
            ShowReviewDetail(review)
        }
    }

    /**
     * A method that shows Review Details of the Coffee shop
     */
    @Composable
    fun ShowReviewDetail(review: Review?) {
        Column(modifier = Modifier.padding(8.dp)) {
            review?.let { data ->
                Review.getLocation(data.name)?.let { location ->
                    Text(
                        text = stringResource(R.string.location),
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                    Text(
                        text = location,
                        style = MaterialTheme.typography.body1
                    )
                }
                Text(
                    text = stringResource(R.string.details),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(text = data.review, modifier = Modifier.padding(bottom = 4.dp))
                Text(text = stringResource(R.string.rating, data.rating))
            }
        }
    }

    companion object {
        const val REVIEW_PARAMETER = "REVIEW_PARAM"
    }
}