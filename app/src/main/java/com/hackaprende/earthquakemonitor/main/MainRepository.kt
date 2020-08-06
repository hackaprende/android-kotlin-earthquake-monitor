package com.hackaprende.earthquakemonitor.main

import com.hackaprende.earthquakemonitor.Earthquake
import com.hackaprende.earthquakemonitor.api.EarthquakeJsonResponse
import com.hackaprende.earthquakemonitor.api.EarthquakesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import java.net.UnknownHostException

class MainRepository {

    suspend fun fetchEqList(): MutableList<Earthquake> {
        return withContext(Dispatchers.IO) {
            val eqList: MutableList<Earthquake> = try {
                val earthquakeJsonResponse = EarthquakesApi.retrofitService.getLastHourEarthquakes()
                getEarthquakeListFromResponse(earthquakeJsonResponse)
            } catch (e: UnknownHostException) {
                mutableListOf()
            } catch (e: JSONException) {
                mutableListOf()
            }

            eqList
        }
    }

    private fun getEarthquakeListFromResponse(earthquakeJsonResponse: EarthquakeJsonResponse):
            MutableList<Earthquake> {
        val eqList = mutableListOf<Earthquake>()

        val features = earthquakeJsonResponse.features
        for (feature in features) {
            val id = feature.id
            val place = feature.properties.place
            val magnitude = feature.properties.mag
            val time = feature.properties.time
            val latitude = feature.geometry.latitude
            val longitude = feature.geometry.longitude
            val earthquake = Earthquake(id, place, magnitude, time, latitude, longitude)
            eqList.add(earthquake)
        }

        return eqList
    }
}