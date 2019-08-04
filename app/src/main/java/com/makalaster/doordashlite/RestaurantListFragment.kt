package com.makalaster.doordashlite

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.restaurant_list_fragment.*

class RestaurantListFragment : Fragment() {
    private val adapter = RestaurantListAdapter()

    companion object {
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

    private fun initViewModel(activity: FragmentActivity) {
        viewModel = ViewModelProviders.of(activity).get(RestaurantListViewModel::class.java)

        initRecyclerView()

        viewModel.restaurants.observe(this, Observer {
            adapter.updateItems(it)
        })
    }

    private fun initRecyclerView() {
        restaurant_list.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        restaurant_list.adapter = adapter
    }
}