package com.daineey.vita_log.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daineey.vita_log.database.DatabaseCRUD
import com.daineey.vita_log.database.DatabaseHelper
import com.daineey.vita_log.database.Supplement
import com.daineey.vita_log.databinding.FragmentHometab2Binding
import com.daineey.vita_log.ui.detail.SupplementAdapter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

class HomeTab2Fragment : Fragment() {

    private var _binding: FragmentHometab2Binding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonLoadMore: Button
    private var allSupplements = listOf<Supplement>()
    private lateinit var databaseCRUD: DatabaseCRUD
    private lateinit var sharedPreferences: SharedPreferences
    private var displayedSupplements = mutableListOf<Supplement>()
    private val gson by lazy { Gson() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        databaseCRUD = DatabaseCRUD(
            DatabaseHelper(
                context,
                DatabaseHelper.DATABASE_NAME,
                null,
                DatabaseHelper.DATABASE_VERSION
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHometab2Binding.inflate(inflater, container, false)
        val view = binding.root

        recyclerView = binding.recyclerRanking
        buttonLoadMore = binding.buttonLoadMore

        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

        loadRandomSupplements()

        binding.buttonLoadMore.setOnClickListener {
            displayAllSupplements()
        }

        return view
    }

    private fun loadRandomSupplements() {
        val savedRandomListJson = sharedPreferences.getString("randomList_10", null)

        if (savedRandomListJson == null) {
            displayedSupplements = databaseCRUD.getRandomLank(sharedPreferences, 10).toMutableList()
        } else {
            val type = object : TypeToken<List<Supplement>>() {}.type
            displayedSupplements = Gson().fromJson(savedRandomListJson, type)
        }

        updateListView(displayedSupplements.take(10))
    }

    private fun displayAllSupplements() {
        val savedFullListJson = sharedPreferences.getString("randomList_40", null)
        if (savedFullListJson == null) {
            displayedSupplements = databaseCRUD.getRandomLank(sharedPreferences, 40).toMutableList()
        } else {
            val type = object : TypeToken<List<Supplement>>() {}.type
            displayedSupplements = gson.fromJson(savedFullListJson, type)
        }

        updateListView(displayedSupplements)
        buttonLoadMore.visibility = View.GONE
    }

    private fun updateListView(supplements: List<Supplement>) {
        Log.i("updateListViewMethod", displayedSupplements.toString())
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = SupplementAdapter(requireContext(), displayedSupplements)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}