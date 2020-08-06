package com.hackaprende.earthquakemonitor.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackaprende.earthquakemonitor.Earthquake
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _eqList = MutableLiveData<MutableList<Earthquake>>()
    val eqList: LiveData<MutableList<Earthquake>>
        get() = _eqList

    private val repository = MainRepository()

    init {
        getEqList()
    }

    private fun getEqList() {
        viewModelScope.launch {
            _eqList.value = repository.fetchEqList()
        }
    }
}