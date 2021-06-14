package com.example.skip_android

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Restaurant(
    val Id: String,
    val Name: String,
    val LogoUrl: String,
    val Rating: Rating,
    val OpeningTime: String,
    val DeliveryTime: String,
    val Address: Address,
    val Deals: List<Deal>,
): Parcelable