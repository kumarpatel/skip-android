package com.example.skip_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class RestaurantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}