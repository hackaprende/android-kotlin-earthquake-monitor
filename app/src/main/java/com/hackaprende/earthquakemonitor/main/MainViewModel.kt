package com.hackaprende.earthquakemonitor.main

import android.app.Application
import androidx.lifecycle.*
import com.hackaprende.earthquakemonitor.api.ApiResponseStatus
import com.hackaprende.earthquakemonitor.database.getDatabase
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val repository = MainRepository(database)

    private val _statusLiveData = MutableLiveData<ApiResponseStatus>()
    val statusLiveData: LiveData<ApiResponseStatus>
        get() = _statusLiveData

    val eqListLiveData = repository.eqList

    init {
        viewModelScope.launch {
            try {
                _statusLiveData.value = ApiResponseStatus.LOADING
                repository.fetchEqList()
                _statusLiveData.value = ApiResponseStatus.DONE
            } catch (e: UnknownHostException) {
                if (eqListLiveData.value == null || eqListLiveData.value!!.isEmpty()) {
                    _statusLiveData.value = ApiResponseStatus.NO_INTERNET_CONNECTION
                } else {
                    _statusLiveData.value = ApiResponseStatus.DONE
                }
            }
        }
    }
}