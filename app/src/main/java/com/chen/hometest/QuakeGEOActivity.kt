package com.chen.hometest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.chen.hometest.databinding.ActivityQuakeGeoBinding

class QuakeGEOActivity:AppCompatActivity() {
    private lateinit var viewBinding: ActivityQuakeGeoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = DataBindingUtil.setContentView<ActivityQuakeGeoBinding>(this,R.layout.activity_quake_geo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }
    private fun init(){
       val geo = intent.getStringExtra("geo")
        viewBinding.tvGeo.text=geo
    }
}