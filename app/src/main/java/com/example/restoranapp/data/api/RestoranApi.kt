package com.example.restoranapp.data.api

import com.example.restoranapp.data.dto.itemResponse
import com.example.restoranapp.domain.model.MenuItem
import retrofit2.http.GET

interface RestoranApi {
    @GET("/api/Items")
    suspend fun getRealEstate(): ArrayList<MenuItem>

}