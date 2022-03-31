package tw.personal.jehuty.fsdation.ui.commodity.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import tw.personal.jehuty.fsdation.data.remote.model.Prompt
import tw.personal.jehuty.fsdation.databinding.PromptLabelBinding
import tw.personal.jehuty.fsdation.view.recyclerView.AppViewHolder

class CommodityPromptLabelViewHolder(private val binding: PromptLabelBinding) :
    AppViewHolder<Prompt>(binding.root) {

    override fun bind(_item: Prompt) {
        with(binding) {
            this.tvLabel.text = _item.display
        }
    }

    companion object Factory : AppViewHolder.Factory<Prompt> {

        override fun create(parent: ViewGroup): AppViewHolder<Prompt> =
            CommodityPromptLabelViewHolder(
                PromptLabelBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        val diffCallback = object : DiffUtil.ItemCallback<Prompt>() {
            override fun areItemsTheSame(
                oldItem: Prompt,
                newItem: Prompt
            ): Boolean = oldItem.id == newItem.id


            override fun areContentsTheSame(
                oldItem: Prompt,
                newItem: Prompt
            ): Boolean = oldItem.display == newItem.display
        }
    }
}