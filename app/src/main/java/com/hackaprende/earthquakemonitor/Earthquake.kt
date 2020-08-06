package com.hackaprende.earthquakemonitor

data class Earthquake(val id: String, val place: String, val magnitude: Double, val time: Long,
                      val latitude: Double, val longitude: Double)