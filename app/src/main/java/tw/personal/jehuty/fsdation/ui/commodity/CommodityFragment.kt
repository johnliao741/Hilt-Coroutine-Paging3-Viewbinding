package tw.personal.jehuty.fsdation.ui.commodity

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import tw.personal.jehuty.fsdation.R
import tw.personal.jehuty.fsdation.databinding.CommodityFragmentBinding
import tw.personal.jehuty.fsdation.ui.AppFragment
import tw.personal.jehuty.fsdation.ui.commodity.adapter.CommodityPagerAdapter
import tw.personal.jehuty.fsdation.util.ZoomOutPageTransformer
import tw.personal.jehuty.fsdation.util.viewBinding

@AndroidEntryPoint
class CommodityFragment() : AppFragment(R.layout.commodity_fragment){
    private val binding: CommodityFragmentBinding by viewBinding()
    lateinit var tabsName :List<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabsName = arrayListOf(getString(R.string.collect_commodity),
            getString(R.string.delivery_notice),
        )
        with(binding.viewpager) {
            setPageTransformer(ZoomOutPageTransformer())
            offscreenPageLimit = 1
            isUserInputEnabled = true
            activity?.let { adapter = CommodityPagerAdapter(it,tabsName) }
        }
        TabLayoutMediator(binding.tabLayout,binding.viewpager) { tab, position ->
            tab.text = tabsName[position]
        }.attach()

    }
}