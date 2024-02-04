package com.example.restoranapp.domain.item.impl

import com.example.realestateapp.data.network.Resource
import com.example.restoranapp.data.Item.ItemRepository
import com.example.restoranapp.domain.item.ItemInteractor
import com.example.restoranapp.domain.model.MenuItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ItemInteractorImpl(private val repository: ItemRepository): ItemInteractor {
    override fun getItem(): Flow<Resource<List<MenuItem>>> {
        return repository.getItem().map { result ->
            when (result) {
                is Resource.Success -> {
                    (Resource.Success(result.data))
                }
                is Resource.Error -> {
                    Resource.Error(result.message, null)
                }
            } as Resource<List<MenuItem>>
        }
    }
}