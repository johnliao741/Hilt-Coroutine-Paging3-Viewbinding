package tw.personal.jehuty.fsdation.ui.commodity

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eva.lsms.view.recyclerView.itemDecoration.GeneralVerticalItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import tw.personal.jehuty.fsdation.R
import tw.personal.jehuty.fsdation.databinding.CommodityListFragmentBinding
import tw.personal.jehuty.fsdation.ui.AppFragment
import tw.personal.jehuty.fsdation.ui.commodity.adapter.CommodityLoadStateAdapter
import tw.personal.jehuty.fsdation.ui.commodity.adapter.CommodityPagingAdapter
import tw.personal.jehuty.fsdation.ui.commodity.adapter.viewholder.CommodityListViewHolder
import tw.personal.jehuty.fsdation.util.viewBinding
import tw.personal.jehuty.fsdation.view.gone
import tw.personal.jehuty.fsdation.view.showMessageDialog
import tw.personal.jehuty.fsdation.view.visible

@AndroidEntryPoint
class CommodityListFragment(val position: Int) : AppFragment(R.layout.commodity_list_fragment) {
    private val binding: CommodityListFragmentBinding by viewBinding()
    private val viewModel: CommodityListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listAdapter = CommodityPagingAdapter.createSimpleAdapter(
            CommodityListViewHolder,
            CommodityListViewHolder.diffCallback
        ).apply {
            itemClickListener = {
                val bundle = bundleOf("id" to it.id)
                findNavController().navigate(R.id.to_commodityDetailFragment, bundle)
            }
            addLoadStateListener { state ->
                val refreshState = state.refresh
                binding.srRefresh.isRefreshing = refreshState is LoadState.Loading
                when (refreshState) {
                    is LoadState.Loading -> {
                        if (this.itemCount == 0) {
                            showProgressDialog()
                        }
                    }
                    is LoadState.NotLoading -> {
                        dismissProgressDialog()
                        if (this.itemCount == 0) {
                            binding.rvCommodity.gone()
                            binding.groupNoDeliveryCommodity.visible()
                        } else {
                            binding.rvCommodity.visible()
                            binding.groupNoDeliveryCommodity.gone()
                        }
                    }
                    is LoadState.Error -> {
                        context?.showMessageDialog(
                            refreshState.error.message ?: getString(R.string.unknown_error)
                        ) { _, _ ->
                        }
                    }

                }
            }
        }

        with(binding.rvCommodity) {
            addItemDecoration(GeneralVerticalItemDecoration())
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter.withLoadStateFooter(
                footer = CommodityLoadStateAdapter { listAdapter.retry() }
            ).apply {
                registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                        super.onItemRangeInserted(positionStart, itemCount)
                        if (binding.srRefresh.isRefreshing) {
                            layoutManager?.scrollToPosition(0)
                        }

                    }
                })

            }
        }
        viewModel.getInfoResult.observe(viewLifecycleOwner) {
            dismissProgressDialog()
            listAdapter.submitData(lifecycle, it)
        }

        binding.srRefresh.setOnRefreshListener {
            listAdapter.refresh()
        }
        viewModel.position = position
    }
}