package com.hackaprende.earthquakemonitor.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackaprende.earthquakemonitor.Earthquake
import com.hackaprende.earthquakemonitor.api.EarthquakesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.net.UnknownHostException

class MainViewModel: ViewModel() {
    private val _eqList = MutableLiveData<MutableList<Earthquake>>()
    val eqList: LiveData<MutableList<Earthquake>>
        get() = _eqList

    init {
        getEqList()
    }

    private fun getEqList() {
        viewModelScope.launch {
            _eqList.value = fetchEqList()
        }
    }

    private suspend fun fetchEqList(): MutableList<Earthquake> {
        return withContext(Dispatchers.IO) {
            val eqList: MutableList<Earthquake> =
            try {
                val lastHourEarthquakes = EarthquakesApi.retrofitService.getLastHourEarthquakes()
                parseStringResult(lastHourEarthquakes)
            } catch (e: UnknownHostException) {
                mutableListOf()
            } catch (e: JSONException) {
                mutableListOf()
            }
            eqList
        }
    }

    private fun parseStringResult(stringResult: String): MutableList<Earthquake> {
        val eqList = mutableListOf<Earthquake>()

        val eqJsonObject = JSONObject(stringResult)
        val featuresJsonArray = eqJsonObject.getJSONArray("features")

        for (i in 0 until featuresJsonArray.length()) {
            val jsonFeature = featuresJsonArray[i] as JSONObject

            val id = jsonFeature.getString("id")
            val jsonProperties = jsonFeature.getJSONObject("properties")
            val place = jsonProperties.getString("place")
            val magnitude = jsonProperties.getDouble("mag")
            val time = jsonProperties.getLong("time")
            val alert = jsonProperties.getString("alert")

            val jsonCoordinates = jsonFeature.getJSONObject("geometry").getJSONArray("coordinates")
            val latitude = jsonCoordinates.getDouble(0)
            val longitude = jsonCoordinates.getDouble(1)

            val earthquake = Earthquake(id, place, magnitude, time, alert, latitude, longitude)
            eqList.add(earthquake)
        }

        return eqList
    }
}