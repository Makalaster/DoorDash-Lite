package com.makalaster.doordashlite

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantListViewModel(private val restaurantApi: RestaurantApi,
                              private val sharedPrefs: SharedPreferences) : ViewModel() {
    val restaurants: MutableLiveData<List<Restaurant>> = MutableLiveData()

    companion object {
        private const val HQ_LAT = 37.422740
        private const val HQ_LNG = -122.139956
    }

    init {
        loadRestaurants()
    }

    fun loadRestaurants() {
        val call: Call<List<Restaurant>> = restaurantApi.getRestaurants(HQ_LAT, HQ_LNG)
        call.enqueue(object : Callback<List<Restaurant>>{
            override fun onResponse(call: Call<List<Restaurant>>, response: Response<List<Restaurant>>) {
                response.body()?.let {
                    updateFavoriteStatus(it)
                }
            }

            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                Log.e("RestaurantListViewModel", "It broke!", t)
            }
        })
    }

    fun onChecked(isFav: Boolean, id: Int) {
        val editor = sharedPrefs.edit()

        editor.putBoolean(id.toString(), isFav)

        editor.apply()
    }

    private fun updateFavoriteStatus(restaurantList: List<Restaurant>) {
        for (restaurant: Restaurant in restaurantList) {
            restaurant.favStatus = sharedPrefs.getBoolean(restaurant.business.id.toString(), false)
        }

        restaurants.value = restaurantList
    }
}

class RestaurantListViewModelFactory(private val restaurantApi: RestaurantApi,
                                     private val sharedPrefs: SharedPreferences) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RestaurantListViewModel(restaurantApi, sharedPrefs) as T
    }
}
