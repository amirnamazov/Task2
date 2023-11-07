package com.example.task2.bottom_nav

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.task2.base.BaseFragment
import com.example.task2.databinding.FragmentDBinding
import com.google.android.material.tabs.TabLayoutMediator

class FragmentD : BaseFragment<FragmentDBinding>(FragmentDBinding :: inflate) {

    private val listFrag by lazy { listOf("Tab 1" to FragmentC(), "Tab 2" to FragmentE()) }

    private val stateAdapter by lazy {
        object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = listFrag.size

            override fun createFragment(p: Int): Fragment = listFrag.map { it.second }[p]
        }
    }

    private val tabLayoutMediator by lazy {
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, i ->
            tab.text = listFrag.map { it.first }[i]
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewPager2.adapter = stateAdapter
        tabLayoutMediator.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        tabLayoutMediator.detach()
    }
}