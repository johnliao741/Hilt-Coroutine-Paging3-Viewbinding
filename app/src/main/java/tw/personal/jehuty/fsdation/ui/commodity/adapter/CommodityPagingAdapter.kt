package tw.personal.jehuty.fsdation.ui.commodity.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItem
import tw.personal.jehuty.fsdation.view.recyclerView.AppListAdapter
import tw.personal.jehuty.fsdation.view.recyclerView.AppViewHolder

abstract class CommodityPagingAdapter<TItem : Any>(diffCallback: DiffUtil.ItemCallback<TItem>)
    :PagingDataAdapter<TItem,AppViewHolder<TItem>>(diffCallback = diffCallback){
    abstract val typeMap: Map<Int, AppViewHolder.Factory<TItem>>
    var itemClickListener: (TItem) -> Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder<TItem> =
        typeMap[viewType]?.create(parent)
            ?: throw IllegalArgumentException("unknown view type $viewType")

    override fun onBindViewHolder(holder: AppViewHolder<TItem>, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
        holder.itemView.setOnClickListener { itemClickListener.invoke(item) }
    }

    companion object {
        const val DEFAULT_ITEM_VIEW_TYPE = 0

        fun <TItem : Any> createSimpleAdapter(
            viewHolderFactory: AppViewHolder.Factory<TItem>,
            diffCallback: DiffUtil.ItemCallback<TItem>,
        ): CommodityPagingAdapter<TItem> =
            object : CommodityPagingAdapter<TItem>(diffCallback) {
                override val typeMap: Map<Int, AppViewHolder.Factory<TItem>> =
                    mapOf(DEFAULT_ITEM_VIEW_TYPE to viewHolderFactory)
            }


    }
}