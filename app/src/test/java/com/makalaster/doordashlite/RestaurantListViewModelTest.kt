package com.makalaster.doordashlite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.mock.Calls

class RestaurantListViewModelTest {
    companion object {
        private const val HQ_LAT = 37.422740
        private const val HQ_LNG = -122.139956
    }

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var api: RestaurantApi

    lateinit var restaurantListViewModel: RestaurantListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        restaurantListViewModel = RestaurantListViewModel(ApiClient.getClient)
    }

    @Test
    fun testLoadingRestaurantsNearHq() {
        val observer = mock(Observer::class.java) as Observer<List<Restaurant>>

        val restaurantA = Restaurant(
            Business(123, "Restaurant A"),
            "35 mins", arrayOf("Breakfast", "Dessert"), "https://www.thisisanimage.url"
        )

        val restaurantB = Restaurant(
            Business(456, "Restaurant B"),
            "36 mins", arrayOf("Drinks", "Snacks"), "https://www.thisisanimage.url"
        )

        val restaurantC =  Restaurant(
            Business(789, "Restaurant C"),
            "Closed", arrayOf("Japanese", "Omakase"), "https://www.thisisanimage.url"
        )

        val restaurantList = arrayOf(restaurantA, restaurantB, restaurantC).toList()

        val call = Calls.response(restaurantList)

        Mockito.`when`(api.getRestaurants(HQ_LAT, HQ_LNG)).thenReturn(call)

        restaurantListViewModel.restaurants.observeForever(observer)
        restaurantListViewModel.loadRestaurants()

        //assert(restaurantListViewModel.restaurants.value?.size <= 50)
    }
}