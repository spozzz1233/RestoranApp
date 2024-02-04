package com.example.restoranapp.data.Item

import com.example.realestateapp.data.network.Resource
import com.example.restoranapp.domain.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getItem(): Flow<Resource<List<MenuItem>>>
}