package com.makalaster.doordashlite

import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.restaurant_list_fragment.*

class RestaurantListFragment : Fragment(), CheckListener {
    private val adapter = RestaurantListAdapter(this)

    companion object {
        private const val FAVORITE_PREFERENCE = "favorite_preference"

        fun newInstance() = RestaurantListFragment()
    }

    private lateinit var viewModel: RestaurantListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.restaurant_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            initViewModel(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipe_layout.setOnRefreshListener {
            viewModel.loadRestaurants()
        }
    }

    private fun initViewModel(activity: FragmentActivity) {
        viewModel = ViewModelProviders.of(activity, RestaurantListViewModelFactory(ApiClient.getClient, activity.getSharedPreferences(FAVORITE_PREFERENCE, MODE_PRIVATE)))
            .get(RestaurantListViewModel::class.java)

        initRecyclerView()

        viewModel.restaurants.observe(this, Observer {
            adapter.submitList(it)

            swipe_layout.isRefreshing = false
        })
    }

    private fun initRecyclerView() {
        restaurant_list.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
        restaurant_list.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        restaurant_list.adapter = adapter
    }

    override fun onChecked(isFav: Boolean, id: Int) {
        viewModel.onChecked(isFav, id)
    }
}