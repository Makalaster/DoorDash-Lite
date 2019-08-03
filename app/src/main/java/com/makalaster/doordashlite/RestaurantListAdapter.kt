package com.makalaster.doordashlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RestaurantListAdapter : ListAdapter<Restaurant, RestaurantViewHolder>(diff_callback) {

    companion object {
        val diff_callback = object : DiffUtil.ItemCallback<Restaurant>() {
            override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
                return oldItem.business.name == newItem.business.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_restaurant_viewholder, parent, false))
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentItem = getItem(position)

        Picasso.get().load(currentItem.imageUrl).into(holder.icon)

        holder.name.text = currentItem.business.name
        val tags = when (currentItem.tags.size) {
            2 -> currentItem.tags[0] + ", " + currentItem.tags[1]
            1 -> currentItem.tags[0]
            else -> ""
        }
        holder.tags.text = tags
        holder.status.text = currentItem.status
    }

    fun updateItems(restaurants: List<Restaurant>) {
        submitList(restaurants)
    }

}

class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val icon: ImageView = itemView.findViewById(R.id.icon)
    val name: TextView = itemView.findViewById(R.id.name)
    val tags: TextView = itemView.findViewById(R.id.tags)
    val status: TextView = itemView.findViewById(R.id.status)
}