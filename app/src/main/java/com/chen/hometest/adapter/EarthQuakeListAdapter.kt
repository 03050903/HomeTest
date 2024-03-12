package com.chen.hometest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.chen.hometest.R
import com.chen.hometest.adapter.viewholder.EarthQuakeItemViewHolder
import com.chen.hometest.databinding.ItemEarthQuakeBinding
import com.chen.hometest.model.EarthQuakeItemModel
import com.chen.hometest.util.CLog

class EarthQuakeListAdapter(): Adapter<EarthQuakeItemViewHolder>() {
    constructor(list: List<EarthQuakeItemModel>) : this() {
        earthQuakeList.addAll(list)
    }
    private val earthQuakeList= mutableListOf<EarthQuakeItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EarthQuakeItemViewHolder {
        val itemEarthQuakeBinding=DataBindingUtil.inflate<ItemEarthQuakeBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_earth_quake,
            parent,false)
        return EarthQuakeItemViewHolder(itemEarthQuakeBinding)
    }
    fun resetData(list: List<EarthQuakeItemModel>){
        earthQuakeList.clear()
        earthQuakeList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = earthQuakeList.size

    override fun onBindViewHolder(holder: EarthQuakeItemViewHolder, position: Int) {
        CLog.log("bind item data:${earthQuakeList[position]}")
        holder.bindData(earthQuakeList[position])
    }
}