package com.example.edvora.API

import com.example.edvora.data.RidesX
import com.example.edvora.data.user
import retrofit2.Response
import retrofit2.http.GET

interface services {
    companion object{
        val BASE_URL="https://assessment.api.vweb.app/"
    }
    @GET("rides")
   suspend fun getRides():Response<RidesX>
   @GET("user")
   suspend fun getuser():Response<user>
}