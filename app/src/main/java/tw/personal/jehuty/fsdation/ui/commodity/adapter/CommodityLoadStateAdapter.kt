package tw.personal.jehuty.fsdation.ui.commodity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import tw.personal.jehuty.fsdation.databinding.LoadStateViewBinding
import tw.personal.jehuty.fsdation.ui.commodity.adapter.viewholder.CommodityLoadStateViewHolder

data class CommodityLoadStateAdapter(private val retry :()->Unit)
    :LoadStateAdapter<CommodityLoadStateViewHolder>(){
    override fun onBindViewHolder(holder: CommodityLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): CommodityLoadStateViewHolder {
        return CommodityLoadStateViewHolder(
            LoadStateViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            retry = retry
        )
    }
}