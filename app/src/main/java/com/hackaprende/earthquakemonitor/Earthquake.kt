package com.hackaprende.earthquakemonitor

data class Earthquake(val id: String, val title: String, val magnitude: Double, val time: Long,
                      val alert: String, val latitude: Double, val longitude: Double)