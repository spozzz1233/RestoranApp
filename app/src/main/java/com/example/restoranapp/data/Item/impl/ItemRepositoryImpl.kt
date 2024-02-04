package com.example.restoranapp.data.Item.impl

import com.example.realestateapp.data.network.NetworkClient
import com.example.realestateapp.data.network.Resource
import com.example.restoranapp.data.Item.ItemRepository
import com.example.restoranapp.data.dto.itemResponse
import com.example.restoranapp.domain.ErrorType
import com.example.restoranapp.domain.model.MenuItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ItemRepositoryImpl(private val networkClient: NetworkClient): ItemRepository {
    override fun getItem(): Flow<Resource<List<MenuItem>>> = flow{
        try {
            val response = networkClient.doRequest()
            when (response.resultCode) {
                -1 -> {
                    emit(Resource.Error(ErrorType.CONNECTION_ERROR))
                }
                200 -> {
                    val partsList = (response as itemResponse).results
                    emit(Resource.Success(partsList))
                }
                else -> {
                    emit(Resource.Error(ErrorType.CONNECTION_ERROR))
                }
            }
        } catch (error: Error) {
            throw Exception(error)
        }
    }
}
