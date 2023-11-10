package com.daineey.vita_log

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.daineey.vita_log.database.DatabaseHelper
import com.daineey.vita_log.database.DatabaseHelper.Companion.DATABASE_NAME
import com.daineey.vita_log.database.DatabaseHelper.Companion.DATABASE_VERSION
import com.daineey.vita_log.databinding.ActivityMainBinding
import com.daineey.vita_log.ui.search.SearchFragment
import com.google.android.material.bottomsheet.BottomSheetDialog

// Navigation Component 방식으로 완전 변경
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // NavController 초기화
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

        // BottomNavigationView와 NavController 연결
        NavigationUI.setupWithNavController(binding.navigationView, navController)

        val bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetView)

        binding.mainFab.setOnClickListener {
            bottomSheetDialog.show()
        }

        binding.navigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.my -> {
                    navController.navigate(R.id.myFragment)
                    true
                }

                else -> false
            }
        }

        binding.headerLayout.actionChat.setOnClickListener {
            Log.i("MainActivity.kt", "actionChat OnClickListener 호출됨.")
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

        binding.headerLayout.actionSearch.setOnClickListener {
            if (binding.headerLayout.searchEditText.visibility == View.GONE) {
                binding.headerLayout.logoImage.visibility = View.GONE
                binding.headerLayout.actionChat.visibility = View.GONE
                binding.headerLayout.searchEditText.visibility = View.VISIBLE

                // 포커스 주기
                binding.headerLayout.searchEditText.requestFocus()
                // 키보드 올리기
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(
                    binding.headerLayout.searchEditText,
                    InputMethodManager.SHOW_IMPLICIT
                )

                binding.headerLayout.searchEditText.setOnEditorActionListener { v, actionId, event ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        val searchText = v.text.toString()

                        // Fragment에 데이터 넘겨주기
                        val fragment = SearchFragment.newInstance(searchText)
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, fragment)
                            .commit()

                        // 키보드 숨기기
                        val imm =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(v.windowToken, 0)

                        true
                    } else {
                        false
                    }
                }
            } else {
                binding.headerLayout.logoImage.visibility = View.VISIBLE
                binding.headerLayout.actionChat.visibility = View.VISIBLE
                binding.headerLayout.searchEditText.visibility = View.GONE
            }
        }

//      This will trigger the database creation and initialization
        val dbHelper = DatabaseHelper(this, DATABASE_NAME, null, DATABASE_VERSION)
        Log.d("Database", "Data inserted: $dbHelper")
    }

//    override fun onSupportNavigateUp() = findNavController(R.id.mainFragment).navigateUp()

    /**
     * @depreciate
     * private fun setFragment(tag: String, fragment: Fragment) {
     *         val manager: FragmentManager = supportFragmentManager
     *         val fragTransaction = manager.beginTransaction()
     *
     *         // All existing fragments are hidden first.
     *         for (existingFragment in manager.fragments) {
     *             fragTransaction.hide(existingFragment)
     *         }
     *
     *         var fragment = manager.findFragmentByTag(tag)
     *
     *         if (fragment == null){
     *             fragment = when (tag) {
     *                 TAG_HOME -> HomeFragment()
     *                 TAG_SEARCH -> com.daineey.vita_log.ui.search.SearchFragment()
     *                 TAG_MY -> com.daineey.vita_log.ui.my.MyFragment()
     *                 else -> throw IllegalArgumentException("Unexpected tag: $tag")
     *             }
     *             fragTransaction.add(R.id.fragmentContainer, fragment, tag)
     *         } else {
     *             fragTransaction.show(fragment)
     *         }
     *
     *         fragTransaction.commitAllowingStateLoss()
     *     }
     * */

}