package com.hackaprende.earthquakemonitor.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackaprende.earthquakemonitor.Earthquake
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            val eqList = mutableListOf<Earthquake>()
            eqList.add(Earthquake("1", "Earthquake 1", 5.5, 1234456778L,
                "red", 24.1029, -110.17236))
            eqList.add(Earthquake("2", "Earthquake 2", 8.2, 1234456778L,
                "red", 24.1029, -110.17236))
            eqList.add(Earthquake("3", "Earthquake 3", 3.0, 1234456778L,
                "red", 24.1029, -110.17236))
            eqList.add(Earthquake("4", "Earthquake 4", 4.3, 1234456778L,
                "red", 24.1029, -110.17236))
            eqList.add(Earthquake("5", "Earthquake 5", 4.8, 1234456778L,
                "red", 24.1029, -110.17236))
            eqList.add(Earthquake("6", "Earthquake 6", 7.1, 1234456778L,
                "red", 24.1029, -110.17236))

            eqList
        }
    }
}