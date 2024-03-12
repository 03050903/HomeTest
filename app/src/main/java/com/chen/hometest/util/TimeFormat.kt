package com.chen.hometest.util

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date

class TimeFormat {
    companion object{
        @JvmStatic
        fun format(time:Long):String{
            try{
                val format=SimpleDateFormat("yyyy-MM-dd")
                return format.format(Date(time))
            }catch (e:Exception){
                e.printStackTrace()
            }
            return time.toString()
        }
    }
}