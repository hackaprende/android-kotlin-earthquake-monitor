package com.hackaprende.earthquakemonitor.main

import android.app.Application
import androidx.lifecycle.*
import com.hackaprende.earthquakemonitor.database.getDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val repository = MainRepository(database)

    val eqList = repository.eqList

    init {
        viewModelScope.launch {
            repository.fetchEqList()
        }
    }
}