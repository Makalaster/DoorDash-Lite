package com.makalaster.doordashlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val RESTAURANT_LIST_TAG = "restaurant list"
    }

    private val listFragment: RestaurantListFragment by lazy {
        RestaurantListFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(
            R.id.content_holder, listFragment, RESTAURANT_LIST_TAG).commit()
    }
}
