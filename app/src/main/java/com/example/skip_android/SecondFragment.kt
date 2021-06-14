package com.example.skip_android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_second.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private val args: SecondFragmentArgs by navArgs()
    private lateinit var mRestaurant: Restaurant
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRestaurant = args.restaurant
        restaurantName.text = mRestaurant.Name
        Picasso.get().load(mRestaurant.LogoUrl.replace("http://", "https://")).into(restaurantLogo)
        rating.text = mRestaurant.Rating.StarRating.toString()
        address1.text = mRestaurant.Address.FirstLine
        address2.text = "${mRestaurant.Address.City} ${mRestaurant.Address.Postcode}"
        openingTime.text = mRestaurant.OpeningTime
        deliveryTime.text = mRestaurant.DeliveryTime
        for (deal in mRestaurant.Deals) {
            var newDeal = TextView(this.context)
            newDeal.text = deal.Description
            dealsLayout.addView(newDeal)
        }


    }
}