package com.example.skip_android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_restaurant_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RestaurantListFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mApi: Api
    private lateinit var mRecyclerAdapter: RestaurantsAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = recyclerView
        mRecyclerAdapter = RestaurantsAdapter(listOf<Restaurant>())

        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = mRecyclerAdapter

        }
        val retrofit = Retrofit.Builder()
                .baseUrl("https://uk.api.just-eat.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        mApi = retrofit.create(Api::class.java)

        swipeRefreshRestaurants.setOnRefreshListener {
            fetchAndDisplayRestaurants(searchInput.text.toString())
        }
        searchButton.setOnClickListener {
            fetchAndDisplayRestaurants(searchInput.text.toString())
        }


    }

    private fun fetchAndDisplayRestaurants(searchTerm: String) {
        if (searchInput.text.toString().isBlank()) {
            swipeRefreshRestaurants.isRefreshing = false
            return
        }

        mApi.fetchRestaurants(searchTerm).enqueue(object : Callback<RestaurantResponse> {
            override fun onResponse(call: Call<RestaurantResponse>, response: Response<RestaurantResponse>) {
                displayRestaurants(response.body()!!.Restaurants)
                swipeRefreshRestaurants.isRefreshing = false
            }

            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                swipeRefreshRestaurants.isRefreshing = false

            }
        })
    }

    private fun displayRestaurants(restaurants: List<Restaurant>) {
        mRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = RestaurantsAdapter(restaurants)

        }
    }

}