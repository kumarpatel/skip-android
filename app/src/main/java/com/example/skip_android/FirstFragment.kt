package com.example.skip_android

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_first.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

//    private lateinit var mRestaurants: List<Restaurant>
    private lateinit var mRestaurants: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRestaurants = recyclerView
        val retrofit = Retrofit.Builder()
                .baseUrl("https://uk.api.just-eat.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(Api::class.java)


        searchButton.setOnClickListener {

            api.fetchRestaurants(searchInput.text.toString()).enqueue(object: Callback<RestaurantResponse> {
                override fun onResponse(call: Call<RestaurantResponse>, response: Response<RestaurantResponse>) {
                    Log.d("api", response.body().toString())
//                    mRestaurants = response.body()!!.Restaurants
                    displayRestaurants(response.body()!!.Restaurants)
                }

                override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                    Log.d("api", t.message.toString())
                }
            })
        }


    }

    private fun displayRestaurants(restaurants: List<Restaurant>) {
        mRestaurants.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = RestaurantsAdapter(restaurants)

        }
    }
}