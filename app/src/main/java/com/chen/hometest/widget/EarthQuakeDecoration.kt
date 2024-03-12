package com.chen.hometest.widget

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class EarthQuakeDecoration: ItemDecoration() {
    private val paint:Paint=Paint().apply {
        color=Color.parseColor("#cccccc")
        strokeWidth=2f
    }
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val childCount=parent.childCount
        IntRange(0,childCount-1).forEach {
            val childAt = parent.getChildAt(it)
            val paddingStart=childAt.paddingStart
            val paddingEnd=childAt.paddingEnd
            c.drawLine((childAt.left+paddingStart).toFloat(), childAt.bottom.toFloat(),
                childAt.right-paddingEnd.toFloat(),childAt.bottom.toFloat(),paint)
        }
    }
}