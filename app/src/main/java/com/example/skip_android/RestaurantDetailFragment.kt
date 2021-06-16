package com.example.skip_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_restaurant_detail.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class RestaurantDetailFragment : Fragment() {
    private val args: RestaurantDetailFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var restaurant: Restaurant = args.restaurant
        restaurantName.text = restaurant.Name
        Picasso.get().load(restaurant.LogoUrl.replace("http://", "https://")).into(restaurantLogo)
        rating.text = restaurant.Rating.StarRating.toString()
        address1.text = restaurant.Address.FirstLine
        address2.text = "${restaurant.Address.City} ${restaurant.Address.Postcode}"
        openingTime.text = restaurant.OpeningTime
        deliveryTime.text = restaurant.DeliveryTime
        for (deal in restaurant.Deals) {
            var newDeal = TextView(this.context)
            newDeal.text = deal.Description
            dealsLayout.addView(newDeal)
        }



    }
}