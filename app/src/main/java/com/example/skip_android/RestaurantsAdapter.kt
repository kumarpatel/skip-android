package com.example.skip_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RestaurantsAdapter(private val restaurants: List<Restaurant>): RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val name: TextView = itemView.findViewById(R.id.restaurantName)
        val logo: ImageView = itemView.findViewById(R.id.restaurantLogo)
        val rating: TextView = itemView.findViewById(R.id.rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_card, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurantName = restaurants[position].Name;
        holder.name.text = restaurantName
        Picasso.get().load(restaurants[position].LogoUrl.replace("http://", "https://")).into(holder.logo)
        holder.rating.text = restaurants[position].Rating.StarRating.toString()

        holder.itemView.setOnClickListener{
            val action = RestaurantListFragmentDirections.actionRestaurantListFragmentToRestaurantDetailFragment(restaurants[position])
            Navigation.findNavController(holder.itemView).navigate(action)
        }

    }

    override fun getItemCount() = restaurants.size


}