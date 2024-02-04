package com.example.restoranapp.domain.item

import com.example.realestateapp.data.network.Resource
import com.example.restoranapp.domain.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface ItemInteractor {
    fun getItem(): Flow<Resource<List<MenuItem>>>
}