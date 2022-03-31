package com.eva.lsms.view.recyclerView.itemDecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import tw.personal.jehuty.fsdation.R

class GeneralHorizontalItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val gapSize = view.resources.getDimensionPixelSize(R.dimen.dp4_gap)

        outRect.right = gapSize
    }
}