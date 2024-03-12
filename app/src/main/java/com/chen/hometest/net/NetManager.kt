package com.chen.hometest.net

import android.util.Log
import com.chen.hometest.adapter.viewholder.EarthQuakeItemViewHolder
import com.chen.hometest.model.EarthQuakeItemModel
import com.chen.hometest.util.CLog
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class NetManager private constructor(){

    companion object{
        const val CONNECTION_TIME_OUT = 5000
        const val READ_TIME_OUT = 10000
        val instance :NetManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            NetManager()
        }
    }
    suspend fun getEarthQuakeList():List<EarthQuakeItemModel>{
        val url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2023-01-01&endtime=2024-01-01&minmagnitude=7"
        val result = getDataByGet(url)
        val list= mutableListOf<EarthQuakeItemModel>()
        if (result.code==HttpURLConnection.HTTP_OK){
            try{
                val jsonObject = JSONObject(result.result)
                CLog.log("read result info")
                jsonObject.optJSONArray("features").let {array->
                   val length = array.length()
                    IntRange(0,length-1).forEach {index->
                        val itemModel = EarthQuakeItemModel.build(array.optJSONObject(index))
                        if (itemModel!=null){
                            list.add(itemModel)
                        }else{
                            //track parse model error
                        }
                    }
                }

            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        return list

    }
    private  fun getDataByGet(requestUrl:String):NetResult{
        var code=-1
        try {
            val url = URL(requestUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod="GET"
            connection.connectTimeout = CONNECTION_TIME_OUT
            connection.readTimeout = READ_TIME_OUT
            connection.connect()
            code = connection.responseCode
            CLog.log("code is $code")
            if (code == HttpURLConnection.HTTP_OK){
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val result = reader.readText()
                CLog.log("result is $result")
                return NetResult(code,result,null)
            }
            return NetResult(code,null,null)

        }catch (e:Exception){
            e.printStackTrace()
            CLog.log("request error:${e.message}")
            return NetResult(code,null,e)
        }
    }


}