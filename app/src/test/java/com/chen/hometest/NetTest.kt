package com.chen.hometest

import com.chen.hometest.net.NetManager
import kotlinx.coroutines.runBlocking
import org.junit.Test

class NetTest{
    @Test
    fun getEarthTest(){
        runBlocking {
            println("============================================")
            val result = NetManager.instance.getEarthQuakeList()
            println(result)
        }
    }
}