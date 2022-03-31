package tw.personal.jehuty.fsdation.view.recyclerView

import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tw.personal.jehuty.fsdation.ui.commodity.adapter.CommodityPagingAdapter


abstract class AppListAdapter<TItem>(diffCallback: DiffUtil.ItemCallback<TItem>) :
    ListAdapter<TItem, AppViewHolder<TItem>>(diffCallback) {

    abstract val typeMap: Map<Int, AppViewHolder.Factory<TItem>>

    var itemClickListener: (TItem) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder<TItem> =
        typeMap[viewType]?.create(parent)
            ?: throw IllegalArgumentException("unknown view type $viewType")

    override fun onBindViewHolder(holder: AppViewHolder<TItem>, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { itemClickListener.invoke(item) }
    }


    override fun getItemViewType(position: Int): Int = DEFAULT_ITEM_VIEW_TYPE

    override fun onViewAttachedToWindow(holder: AppViewHolder<TItem>) {
        holder.onViewAttachedToWindow()
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: AppViewHolder<TItem>) {
        holder.onViewDetachedFromWindow()
        super.onViewDetachedFromWindow(holder)
    }

    fun interface Factory<TItem> {
        fun create(): AppListAdapter<TItem>
    }

    companion object {
        const val DEFAULT_ITEM_VIEW_TYPE = 0

        fun <TItem> createSimpleAdapter(
            viewHolderFactory: AppViewHolder.Factory<TItem>,
            diffCallback: DiffUtil.ItemCallback<TItem>,
        ): AppListAdapter<TItem> =
            object : AppListAdapter<TItem>(diffCallback) {
                override val typeMap: Map<Int, AppViewHolder.Factory<TItem>> =
                    mapOf(DEFAULT_ITEM_VIEW_TYPE to viewHolderFactory)
            }


        fun <TItem> createSimpleAdapter(
            viewHolderFactory: AppViewHolder.Factory<TItem>,
            areItemsTheSame: (TItem, TItem) -> Boolean,
            areContentsTheSame: (TItem, TItem) -> Boolean
        ): AppListAdapter<TItem> =
            createSimpleAdapter(
                viewHolderFactory,
                object : DiffUtil.ItemCallback<TItem>() {
                    override fun areItemsTheSame(oldItem: TItem, newItem: TItem): Boolean =
                        areItemsTheSame.invoke(oldItem, newItem)

                    override fun areContentsTheSame(oldItem: TItem, newItem: TItem): Boolean =
                        areContentsTheSame.invoke(oldItem, newItem)
                })
    }
}

abstract class AppViewHolder<TItem>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(_item: TItem)

    open fun onViewAttachedToWindow() {}
    open fun onViewDetachedFromWindow() {}

    fun interface Factory<T> {
        fun create(parent: ViewGroup): AppViewHolder<T>
    }
}
