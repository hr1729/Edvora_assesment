package com.example.edvora.data


import com.google.gson.annotations.SerializedName

data class RidesItemX(
    @SerializedName("city")
    var city: String,
    @SerializedName("date")
    var date: String,
    @SerializedName("destination_station_code")
    var destinationStationCode: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("map_url")
    var mapUrl: String,
    @SerializedName("origin_station_code")
    var originStationCode: Int,
    @SerializedName("state")
    var state: String,
    @SerializedName("station_path")
    var stationPath: List<Int>
)