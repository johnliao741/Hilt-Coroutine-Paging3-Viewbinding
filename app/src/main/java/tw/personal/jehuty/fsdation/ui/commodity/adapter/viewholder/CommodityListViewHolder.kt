package tw.personal.jehuty.fsdation.ui.commodity.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import tw.personal.jehuty.fsdation.data.remote.model.CommodityItem
import tw.personal.jehuty.fsdation.databinding.CardLayoutBinding
import tw.personal.jehuty.fsdation.util.toPriceString
import tw.personal.jehuty.fsdation.view.getImage
import tw.personal.jehuty.fsdation.view.recyclerView.AppViewHolder

class CommodityListViewHolder(private val binding: CardLayoutBinding) :
    AppViewHolder<CommodityItem>(binding.root) {

    override fun bind(_item: CommodityItem) {
        with(binding) {
            this.itemTitle.text = _item.name
            this.btn.text = _item.status.btnString
            this.itemPrice.text = _item.price.toPriceString()
            this.itemImage.getImage(_item.url)
        }
    }

    companion object Factory : AppViewHolder.Factory<CommodityItem> {

        override fun create(parent: ViewGroup): AppViewHolder<CommodityItem> =
            CommodityListViewHolder(
                CardLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        val diffCallback = object : DiffUtil.ItemCallback<CommodityItem>() {
            override fun areItemsTheSame(
                oldItem: CommodityItem,
                newItem: CommodityItem
            ): Boolean = oldItem.id == newItem.id &&
                    oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: CommodityItem,
                newItem: CommodityItem
            ): Boolean = oldItem.status == newItem.status
        }
    }
}