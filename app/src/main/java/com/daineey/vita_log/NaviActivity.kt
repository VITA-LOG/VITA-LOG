package com.daineey.vita_log

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.daineey.vita_log.databinding.ActivityNaviBinding
import com.daineey.vita_log.ui.home.HomeFragment


private const val TAG_HOME = "home_fragment"
private const val TAG_PROFILE = "profile_fragment"
private const val TAG_SEARCH = "search_fragment"
private const val TAG_CHAT = "chat_fragment"
private const val TAG_MY = "my_fragment"


class NaviActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNaviBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNaviBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setFragment(TAG_HOME, HomeFragment())

        binding.navigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> setFragment(TAG_HOME, com.daineey.vita_log.ui.home.HomeFragment())
                R.id.profile -> setFragment(TAG_PROFILE, com.daineey.vita_log.ui.profile.ProfileFragment())
                R.id.search_photo-> setFragment(TAG_SEARCH, com.daineey.vita_log.ui.search.SearchFragment())
                R.id.chat-> setFragment(TAG_CHAT, com.daineey.vita_log.ui.chat.ChatFragment())
                R.id.my-> setFragment(TAG_MY, com.daineey.vita_log.ui.my.MyFragment())
            }
            true
        }




    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        val home = manager.findFragmentByTag(TAG_HOME)
        val profile = manager.findFragmentByTag(TAG_PROFILE)
        val search = manager.findFragmentByTag(TAG_SEARCH)
        val chat = manager.findFragmentByTag(TAG_CHAT)
        val myPage = manager.findFragmentByTag(TAG_MY)

        if (home != null){
            fragTransaction.hide(home)
        }

        if (profile != null){
            fragTransaction.hide(profile)
        }

        if (search != null) {
            fragTransaction.hide(search)
        }

        if (chat != null) {
            fragTransaction.hide(chat)
        }

        if (myPage != null) {
            fragTransaction.hide(myPage)
        }

        if (tag == TAG_HOME) {
            if (home!=null){
                fragTransaction.show(home)
            }
        }
        else if (tag == TAG_PROFILE) {
            if (profile != null) {
                fragTransaction.show(profile)
            }
        }
        else if (tag == TAG_SEARCH) {
            if (search != null) {
                fragTransaction.show(search)
            }
        }
        else if (tag == TAG_CHAT) {
            if (chat != null) {
                fragTransaction.show(chat)
            }
        }
        else if (tag == TAG_MY){
            if (myPage != null){
                fragTransaction.show(myPage)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }


}