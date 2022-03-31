package tw.personal.jehuty.fsdation.ui.commodity.adapter.viewholder

import androidx.core.view.isVisible
import androidx.paging.LoadState
import tw.personal.jehuty.fsdation.databinding.LoadStateViewBinding
import tw.personal.jehuty.fsdation.view.recyclerView.AppViewHolder

class CommodityLoadStateViewHolder(private val binding:LoadStateViewBinding,private val retry:()->Unit) : AppViewHolder<LoadState>(binding.root){

    override fun bind(_state: LoadState) {
        binding.loadStateRetry.apply {
            isVisible = _state !is LoadState.Loading
            setOnClickListener {  retry.invoke() }
        }
        binding.loadStateErrorMessage.isVisible = _state !is LoadState.Loading
        binding.loadStateProgress.isVisible = _state is LoadState.Loading

        if (_state is LoadState.Error){
            binding.loadStateErrorMessage.text = _state.error.localizedMessage
        }

    }

}