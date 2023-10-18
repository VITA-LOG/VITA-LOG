package com.daineey.vita_log.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.daineey.vita_log.ViewPagerAdapter
import com.daineey.vita_log.databinding.FragmentMainhomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// 메뉴바와 HomeTab1 묶어주는 역할
class MainHomeFragment: Fragment() {

    private var _binding: FragmentMainhomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainhomeBinding.inflate(inflater, container, false)

        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout

        // Adapter에 프래그먼트 추가
        val adapter = ViewPagerAdapter(requireActivity())
        adapter.addFragment(HomeTab1Fragment())
        adapter.addFragment(HomeTab2Fragment())
        adapter.addFragment(HomeTab3Fragment())
        viewPager.adapter = adapter

        // TabLayout과 ViewPager2를 연결
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "맞춤 영양제"
                1 -> "영양제 랭킹"
                2 -> "건강 컨텐츠"
                else -> null
            }
        }.attach()

        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // For Memory Leaking Prevent
        _binding = null
    }
}