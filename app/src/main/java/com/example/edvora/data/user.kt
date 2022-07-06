package com.example.edvora.data


import com.google.gson.annotations.SerializedName

data class user(
    @SerializedName("name")
    var name: String,
    @SerializedName("station_code")
    var stationCode: Int,
    @SerializedName("url")
    var url: String
)