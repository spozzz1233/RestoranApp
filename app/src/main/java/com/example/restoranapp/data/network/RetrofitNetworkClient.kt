package com.example.realestateapp.data.network

import com.example.restoranapp.data.api.RestoranApi
import com.example.restoranapp.data.dto.itemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitNetworkClient(
    private val Service: RestoranApi,
) : NetworkClient {
    override suspend fun doRequest(): Response {
        return withContext(Dispatchers.IO) {
            val response = itemResponse(Service.getRealEstate())
            try {
                response.apply { resultCode = 200 }
            } catch (e: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }
}

