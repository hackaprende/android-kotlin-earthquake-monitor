package com.hackaprende.earthquakemonitor.api

import com.hackaprende.earthquakemonitor.Constants
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder()
    .baseUrl(Constants.BASE_URL)
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()

interface EarthquakeApiService {
    @GET(Constants.GET_LAST_HOUR_URL)
    suspend fun getLastHourEarthquakes(): String
}

object EarthquakesApi {
    val retrofitService : EarthquakeApiService by lazy {
        retrofit.create(EarthquakeApiService::class.java)
    }
}