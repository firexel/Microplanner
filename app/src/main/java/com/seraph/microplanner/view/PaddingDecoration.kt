package com.seraph.microplanner.view

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class PaddingDecoration(private var pixels: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        outRect!!.set(0, 0, 0, pixels)
    }
}