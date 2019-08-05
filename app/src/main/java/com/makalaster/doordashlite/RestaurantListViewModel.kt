package com.makalaster.doordashlite

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantListViewModel : ViewModel() {
    val restaurants: MutableLiveData<List<Restaurant>> = MutableLiveData()

    companion object {
        private const val HQ_LAT = 37.422740
        private const val HQ_LNG = -122.139956
    }

    init {
        loadRestaurants()
    }

    fun loadRestaurants() {
        val call: Call<List<Restaurant>> = ApiClient.getClient.getRestaurants(HQ_LAT, HQ_LNG)
        call.enqueue(object : Callback<List<Restaurant>>{
            override fun onResponse(call: Call<List<Restaurant>>, response: Response<List<Restaurant>>) {
                response.body()?.let {
                    restaurants.value = it
                }
            }

            override fun onFailure(call: Call<List<Restaurant>>, t: Throwable) {
                Log.e("RestaurantListViewModel", "It broke!", t)
            }
        })
    }
}
