package com.example.skip_android

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address (
    val City: String,
    val FirstLine: String,
    val Postcode: String,
): Parcelable
