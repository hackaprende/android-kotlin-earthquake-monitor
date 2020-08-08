package com.hackaprende.earthquakemonitor.api

enum class ApiResponseStatus {
    DONE, LOADING, NO_INTERNET_CONNECTION
}

class ApiResponse(var status: ApiResponseStatus = ApiResponseStatus.DONE)