package tw.personal.jehuty.fsdation.ui.commodity.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import tw.personal.jehuty.fsdation.ui.commodity.CommodityListFragment

class CommodityPagerAdapter (
    private val fa: FragmentActivity,
    private val tabsName: List<String>): FragmentStateAdapter(fa){

    override fun createFragment(position: Int): Fragment = CommodityListFragment(position)

    override fun getItemCount(): Int = tabsName.size

}