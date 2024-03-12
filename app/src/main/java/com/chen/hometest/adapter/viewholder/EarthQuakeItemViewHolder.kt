package com.chen.hometest.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chen.hometest.databinding.ItemEarthQuakeBinding
import com.chen.hometest.model.EarthQuakeItemModel

class EarthQuakeItemViewHolder(private val binding: ItemEarthQuakeBinding):ViewHolder(binding.root) {
    fun bindData(model:EarthQuakeItemModel){
        binding.earthQuakeModel=model
    }
}