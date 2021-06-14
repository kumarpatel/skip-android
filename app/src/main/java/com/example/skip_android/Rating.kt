package com.example.skip_android

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rating (
    val StarRating: Double
): Parcelable
