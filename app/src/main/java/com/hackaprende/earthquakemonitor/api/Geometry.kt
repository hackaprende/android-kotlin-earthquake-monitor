package com.hackaprende.earthquakemonitor.api

class Geometry(val type: String, val coordinates: Array<Double>) {
    val longitude: Double
        get() = coordinates[0]
    val latitude: Double
        get() = coordinates[1]
}