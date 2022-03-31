package tw.personal.jehuty.fsdation.view.recyclerView

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.DiffUtil
import tw.personal.jehuty.fsdation.view.recyclerView.AppListAdapter
import tw.personal.jehuty.fsdation.view.recyclerView.AppViewHolder

abstract class SwipeableItemListAdapter<TItem>(diffCallback: DiffUtil.ItemCallback<TItem>) :
    AppListAdapter<TItem>(diffCallback) {

    private val removedItems = mutableListOf<TItem>()

    @CallSuper
    override fun submitList(list: List<TItem>?) {
        submit(list, false)
    }

    private fun submit(list: List<TItem>?, isLocalSubmit: Boolean) {
        if (!isLocalSubmit) removedItems.clear()
        super.submitList(list)
    }

    fun removeItem(position: Int): TItem? {
        if (position >= itemCount) return null
        val item = currentList[position]
        removedItems.add(item)
        val actualList = currentList - removedItems
        if (actualList.isEmpty()) removedItems.clear()
        submit(actualList, true)
        return item
    }

    companion object {
        fun <TItem> createSimpleAdapter(
            viewHolderFactory: AppViewHolder.Factory<TItem>,
            diffCallback: DiffUtil.ItemCallback<TItem>,
        ): SwipeableItemListAdapter<TItem> =
            object : SwipeableItemListAdapter<TItem>(diffCallback) {
                override val typeMap: Map<Int, AppViewHolder.Factory<TItem>> =
                    mapOf(DEFAULT_ITEM_VIEW_TYPE to viewHolderFactory)

                override fun getItemViewType(position: Int): Int = DEFAULT_ITEM_VIEW_TYPE
            }
    }
}