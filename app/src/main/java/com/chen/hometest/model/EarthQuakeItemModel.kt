package com.chen.hometest.model

import com.chen.hometest.util.CLog
import org.json.JSONObject
import java.lang.Exception

data class EarthQuakeItemModel(val place:String,val time:Long,val cdi:Double,val title:String){
    companion object{
        fun build(modelJson:JSONObject):EarthQuakeItemModel?{
            CLog.log("read item model:$modelJson")
            try {
                val json = modelJson.optJSONObject("properties")
                val title = json.optString("title")
                val time = json.optLong("time")
                val place = json.optString("place")
                val cdi = json.optDouble("cdi")
                CLog.log("place:$place,title:$title,time:$time,cdi:$cdi")
                return EarthQuakeItemModel(place,time,cdi,title)
            }catch (e:Exception){
                //log parse model error message
                e.printStackTrace()
            }
            return null
        }
    }
}