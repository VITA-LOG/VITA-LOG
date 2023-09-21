package com.daineey.vita_log.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Intent
import android.widget.ImageButton
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.daineey.vita_log.ChatActivity
import com.daineey.vita_log.R
import com.daineey.vita_log.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: FragmentStateAdapter


    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view: View = binding.root

        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)

        adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 3

            override fun createFragment(position: Int): Fragment {
                return when(position) {
                    0 -> HomeTab1Fragment()
                    1 -> HomeTab2Fragment()
                    else -> HomeTab3Fragment()
                }
            }
        }

        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "맞춤 영양제"
                1 -> "영양제 랭킹"
                else -> "건강 컨텐츠"
            }
        }.attach()

        val chat_button = view?.findViewById<ImageButton>(R.id.action_chat)
        chat_button?.setOnClickListener {
            activity?.let{
                val intent = Intent(context, ChatActivity::class.java)
                startActivity(intent)
            }
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}