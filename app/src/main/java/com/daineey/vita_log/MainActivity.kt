package com.daineey.vita_log

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.daineey.vita_log.databinding.ActivityMainBinding
import com.daineey.vita_log.ui.home.HomeFragment


private const val TAG_HOME = "home_fragment"
private const val TAG_SEARCH = "search_fragment"
private const val TAG_CHAT = "chat_fragment"

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(TAG_HOME, HomeFragment())

        binding.navigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> setFragment(TAG_HOME, HomeFragment())
                R.id.search_photo-> setFragment(TAG_SEARCH, com.daineey.vita_log.ui.search.SearchFragment())
                R.id.chat-> setFragment(TAG_CHAT, com.daineey.vita_log.ui.chat.ChatFragment())
            }
            true
        }
    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        // All existing fragments are hidden first.
        for (existingFragment in manager.fragments) {
            fragTransaction.hide(existingFragment)
        }

        var fragment = manager.findFragmentByTag(tag)

        if (fragment == null){
            fragment = when (tag) {
                TAG_HOME -> HomeFragment()
                TAG_SEARCH -> com.daineey.vita_log.ui.search.SearchFragment()
                TAG_CHAT -> com.daineey.vita_log.ui.chat.ChatFragment()
                else -> throw IllegalArgumentException("Unexpected tag: $tag")
            }
            fragTransaction.add(R.id.fragmentContainer, fragment, tag)
        } else {
            fragTransaction.show(fragment)
        }

        fragTransaction.commitAllowingStateLoss()
    }

}