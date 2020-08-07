package com.hackaprende.earthquakemonitor.main

import android.app.Application
import androidx.lifecycle.*
import com.hackaprende.earthquakemonitor.Earthquake
import com.hackaprende.earthquakemonitor.database.getDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _eqList = MutableLiveData<MutableList<Earthquake>>()
    val eqList: LiveData<MutableList<Earthquake>>
        get() = _eqList

    private val database = getDatabase(application)
    private val repository = MainRepository(database)

    init {
        getEqList()
    }

    private fun getEqList() {
        viewModelScope.launch {
            _eqList.value = repository.fetchEqList()
        }
    }
}