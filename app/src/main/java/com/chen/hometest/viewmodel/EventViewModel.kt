package com.chen.hometest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chen.hometest.model.EarthQuakeItemModel
import com.chen.hometest.net.NetManager
import com.chen.hometest.net.NetResult
import com.chen.hometest.util.CLog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class EventViewModel:ViewModel() {
    val eventLiveData:MutableLiveData<List<EarthQuakeItemModel>> = MutableLiveData()

    fun getEventList(){
        CLog.log("request earth quake")
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val result = NetManager.instance.getEarthQuakeList()
                eventLiveData.postValue(result)
            }
        }
    }
}