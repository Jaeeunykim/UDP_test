package io.subak.viewmodeltest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UDPViewModel: ViewModel() {

    private var dataSize = MutableLiveData<Int>()
    val _dataSize: LiveData<Int>
        get() = dataSize

    fun setBattery(remaining:Int){
        dataSize.postValue(remaining)
    }
}