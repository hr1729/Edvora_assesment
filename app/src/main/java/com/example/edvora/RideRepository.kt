package com.example.edvora

import com.example.edvora.API.RetrofitInstance
import com.example.edvora.API.services

class RideRepository {
    suspend fun getAllRides()=RetrofitInstance.api.getRides()

    suspend fun getuser()=RetrofitInstance.api.getuser()
}