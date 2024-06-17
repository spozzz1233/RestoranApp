package com.example.autorus.util

import android.content.Context
import android.content.SharedPreferences
import com.example.restoranapp.domain.model.MenuItem
import com.example.restoranapp.domain.model.cart
import com.example.restoranapp.domain.model.item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class CartSave(private val context:Context) {
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    fun clearSharedPreferences() {
        sharedPreferences = context.getSharedPreferences("SaveHistory", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("history")
        editor.apply()
        cart.clear()
    }
    fun saveCartHistory(items: MenuItem) {
        sharedPreferences = context.getSharedPreferences("SaveHistory", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val historyCart = items
        cart.add(0, historyCart)
        val updatedJsonHistory = Gson().toJson(cart)
        editor.putString("history", updatedJsonHistory)
        editor.apply()

    }
    fun getHistoryCart() {
        sharedPreferences = context.getSharedPreferences("SaveHistory", Context.MODE_PRIVATE)
        val jsonHistory = sharedPreferences.getString("history", null)
        if (jsonHistory != null) {
            val type = object : TypeToken<ArrayList<MenuItem>>() {}.type
            val loadedTracks: ArrayList<MenuItem> = gson.fromJson(jsonHistory, type)
            cart.clear()
            cart.addAll(loadedTracks)
        }
    }
}