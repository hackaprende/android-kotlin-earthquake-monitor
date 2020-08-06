package com.hackaprende.earthquakemonitor.api

class Geometry(private val coordinates: Array<Double>) {
    val longitude = coordinates[0]
    val latitude = coordinates[1]
}