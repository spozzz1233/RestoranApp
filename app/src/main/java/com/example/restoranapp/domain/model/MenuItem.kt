package com.example.restoranapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MenuItem(
    val id: Int,
    val itemName: String,
    val description: String,
    val price: Int,
    val image: String
): Parcelable

var item = ArrayList<MenuItem>()


var cart = ArrayList<MenuItem>()