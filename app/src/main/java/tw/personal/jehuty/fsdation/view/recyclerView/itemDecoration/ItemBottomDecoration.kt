package tw.personal.jehuty.fsdation.view.recyclerView.itemDecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import tw.personal.jehuty.fsdation.R

class ItemBottomDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = view.resources.getDimensionPixelSize(R.dimen.default_gap)
    }
}