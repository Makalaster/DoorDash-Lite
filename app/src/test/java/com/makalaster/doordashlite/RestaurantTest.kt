package com.makalaster.doordashlite

import org.junit.Test

class RestaurantTest {
    private val restaurantA = Restaurant(
        Business(123, "Restaurant A"),
        "35 mins", arrayOf("Breakfast", "Dessert"), "https://www.thisisanimage.url"
    )

    private val restaurantB = Restaurant(
        Business(456, "Restaurant B"),
        "35 mins", arrayOf("Breakfast", "Dessert"), "https://www.thisisanimage.url"
    )

    private val restaurantC =  Restaurant(
        Business(456, "Restaurant B"),
        "35 mins", arrayOf("Breakfast", "Dessert"), "https://www.thisisanimage.url"
    )

    @Test
    fun testFalseEquality() {
        assert(restaurantA != restaurantB)
    }

    @Test
    fun testTrueEquality() {
        assert(restaurantB == restaurantC)
    }

    @Test
    fun testAttributes() {
        assert(restaurantA.business.id == 123)
        assert(restaurantA.business.name == "Restaurant A")

        assert(restaurantA.status == "35 mins")
        assert(restaurantA.imageUrl == "https://www.thisisanimage.url")
        assert(restaurantA.tags.size == 2)
    }
}