package com.eva.lsms.view.recyclerView.itemDecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import tw.personal.jehuty.fsdation.R

class GeneralVerticalItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val gapSize = view.resources.getDimensionPixelSize(R.dimen.default_gap)
        if (parent.getChildAdapterPosition(view) == 0)
            outRect.top = gapSize
        outRect.bottom = gapSize
    }
}