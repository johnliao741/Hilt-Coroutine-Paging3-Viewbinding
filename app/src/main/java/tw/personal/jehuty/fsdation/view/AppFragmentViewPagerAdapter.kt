package tw.personal.jehuty.fsdation.view

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import tw.personal.jehuty.fsdation.ui.AppFragment

class AppFragmentViewPagerAdapter(
    fragment: Fragment,
    private val pagerFragments: Array<AppFragment>
) : FragmentStateAdapter(fragment.childFragmentManager, fragment.viewLifecycleOwner.lifecycle) {
    override fun getItemCount(): Int = pagerFragments.size

    override fun createFragment(position: Int): Fragment = pagerFragments[position]
}