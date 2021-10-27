package com.example.matchgrouptestapp.model

import android.os.Parcelable
import com.example.matchgrouptestapp.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    var name: String,
    var review: String,
    var rating: Int,
    var location: String?
) : Parcelable {

    override fun toString(): String {
        return name
    }

    companion object {
        @JvmStatic
        fun getIconResourceFromName(name: String): Int {
            when (name) {
                "Lofty" -> return R.drawable.bean_bag
                "Zumbar" -> return R.drawable.coffee_cup
                "Blue Bottle" -> return R.drawable.coffee_grinder
                "Bird Rock" -> return R.drawable.coffee_maker
                "Better Buzz Coffee" -> return R.drawable.coffee_shop
            }
            return -1
        }

        @JvmStatic
        fun getLocation(name: String): String? {
            when (name) {
                "Lofty" -> {
                    return CoffeeShopReviews.list.find { it.name == "Lofty" }?.location
                }
                "Zumbar" -> {
                    return CoffeeShopReviews.list.find { it.name == "Zumbar" }?.location
                }
                "Blue Bottle" -> {
                    return CoffeeShopReviews.list.find { it.name == "Blue Bottle" }?.location
                }
                "Bird Rock" -> {
                    return CoffeeShopReviews.list.find { it.name == "Bird Rock" }?.location
                }
                "Better Buzz Coffee" -> {
                    return CoffeeShopReviews.list.find { it.name == "Better Buzz Coffee" }?.location
                }
            }
            return null
        }
    }
}
