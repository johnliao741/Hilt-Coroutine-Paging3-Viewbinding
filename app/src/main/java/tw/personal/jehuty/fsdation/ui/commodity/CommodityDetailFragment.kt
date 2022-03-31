package tw.personal.jehuty.fsdation.ui.commodity

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.eva.lsms.view.recyclerView.itemDecoration.GeneralHorizontalItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import tw.personal.jehuty.fsdation.R
import tw.personal.jehuty.fsdation.data.remote.model.CommodityDetailResponse
import tw.personal.jehuty.fsdation.data.vo.Result
import tw.personal.jehuty.fsdation.databinding.CommodityDetailFragmentBinding
import tw.personal.jehuty.fsdation.ui.AppFragment
import tw.personal.jehuty.fsdation.ui.commodity.adapter.viewholder.CommodityPromptLabelViewHolder
import tw.personal.jehuty.fsdation.util.convertToRedColor
import tw.personal.jehuty.fsdation.util.toPriceString
import tw.personal.jehuty.fsdation.util.toStrikePriceString
import tw.personal.jehuty.fsdation.util.viewBinding
import tw.personal.jehuty.fsdation.view.getImage
import tw.personal.jehuty.fsdation.view.recyclerView.AppListAdapter
import tw.personal.jehuty.fsdation.view.showMessageDialog

@AndroidEntryPoint
class CommodityDetailFragment() : AppFragment(R.layout.commodity_detail_fragment){
    private val binding: CommodityDetailFragmentBinding by viewBinding()
    private val args: CommodityDetailFragmentArgs by navArgs()
    private val viewModel: CommodityDetailViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listAdapter = AppListAdapter.createSimpleAdapter(
            CommodityPromptLabelViewHolder,
            CommodityPromptLabelViewHolder.diffCallback
        ).apply {
            itemClickListener = {

            }
        }

        with(binding.rvPrompt){
            addItemDecoration(GeneralHorizontalItemDecoration())
            adapter = listAdapter
        }
        viewModel.getCommodityDetailResult.observe(viewLifecycleOwner){
            when(it){
                is Result.Loading -> showProgressDialog()
                is Result.Success -> {
                    dismissProgressDialog()
                    setView(it.data)
                    listAdapter.submitList(it.data.promptLabel.labelList)
                }
                is Result.Error -> {
                    dismissProgressDialog()

                    context?.showMessageDialog(
                        it.throwable.message ?: getString(R.string.unknown_error)
                    ) { _, _ ->
                    }
                }
                else -> dismissProgressDialog()
            }
        }
        viewModel.getCommodityDetail(args.id)

    }

    private fun setView(commodity: CommodityDetailResponse) {
        binding.img.getImage(commodity.url)
        binding.tvFormat.text = commodity.format
        binding.tvName.text = commodity.name
        val spb = SpannableStringBuilder()
        spb.append(getString(R.string.internet_price))
        binding.tvInternetPrice.text = spb.append(commodity.internetPrice.toPriceString().toStrikePriceString())
        binding.tvTitleDiscountPrice.text = getString(R.string.title_discount_price)
        binding.tvDiscountPrice.text = "${commodity.discountPrice.toPriceString()}"
        binding.tvRemarkDiscountPrice.text = getString(R.string.remark_discount_price)
        binding.tvPv.text = "PV${commodity.pv}"
        spb.clear()
        spb.append("${commodity.vendor.name}最高回饋")
        binding.tvVendorMaxCashBack.text = spb.append(commodity.vendor.maxCashBack.convertToRedColor()).append("枚")
        spb.clear()
        spb.append("使用${commodity.vendor.name}最高折抵")
        binding.tvVendorMaxDiscount.text = spb.append(commodity.vendor.maxDiscount.convertToRedColor()).append("元")
        binding.imgVendorIcon.setImageResource(R.drawable.android)

    }
}