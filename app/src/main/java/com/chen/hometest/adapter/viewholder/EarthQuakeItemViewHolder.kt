package com.chen.hometest.adapter.viewholder

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chen.hometest.QuakeGEOActivity
import com.chen.hometest.databinding.ItemEarthQuakeBinding
import com.chen.hometest.model.EarthQuakeItemModel

class EarthQuakeItemViewHolder(private val binding: ItemEarthQuakeBinding):ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            val intent=Intent(it.context,QuakeGEOActivity::class.java)
            intent.putExtra("geo",binding.earthQuakeModel?.geometry)
            it.context.startActivity(intent)
        }
    }
    fun bindData(model:EarthQuakeItemModel){
        binding.earthQuakeModel=model
    }
}