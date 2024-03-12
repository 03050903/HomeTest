package com.chen.hometest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chen.hometest.adapter.EarthQuakeListAdapter
import com.chen.hometest.databinding.ActivityMainBinding
import com.chen.hometest.util.CLog
import com.chen.hometest.viewmodel.EventViewModel
import com.chen.hometest.widget.EarthQuakeDecoration
import java.net.HttpURLConnection

class MainActivity : AppCompatActivity() {
    private val eventViewModel:EventViewModel by lazy {
        ViewModelProvider(this).get(EventViewModel::class.java)
    }
    private lateinit var viewBinding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()

    }
    private fun init(){
        eventViewModel.eventLiveData.observe(this){
            CLog.log("set list data")
            var adapter = viewBinding.listEarthQuake.adapter
            if (adapter==null){
                adapter=EarthQuakeListAdapter(it)
                viewBinding.listEarthQuake.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
                viewBinding.listEarthQuake.addItemDecoration(EarthQuakeDecoration())
                viewBinding.listEarthQuake.adapter=adapter
            }else{
                (adapter as EarthQuakeListAdapter).resetData(it)
            }
        }
        eventViewModel.getEventList()


    }
}