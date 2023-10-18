package com.daineey.vita_log.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.daineey.vita_log.R
import com.daineey.vita_log.database.DatabaseCRUD
import com.daineey.vita_log.database.DatabaseHelper
import com.daineey.vita_log.database.DatabaseHelper.Companion.DATABASE_NAME
import com.daineey.vita_log.databinding.FragmentSearchBinding
import com.daineey.vita_log.ui.detail.SupplementDetail

/**
 * A simple [Fragment] subclass.
 * Use the [MyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SearchAdapter

    companion object {
        private const val ARG_SEARCH_TEXT = "search_text"

        fun newInstance(searchText: String): SearchFragment {
            Log.i("SEARCH TEXT", searchText)
            val fragment = SearchFragment()
            val args = Bundle()
            args.putString(ARG_SEARCH_TEXT, searchText)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSearchBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        val searchText = arguments?.getString(ARG_SEARCH_TEXT)
        binding.keyword = searchText.plus(" 에 대한 검색결과")

        // 추가적인 binding 설정이 필요한 경우 여기에 구현하면 됩니다.
        val databaseCRUD = DatabaseCRUD(
            DatabaseHelper(
                requireContext(), DATABASE_NAME, null,
                DatabaseHelper.DATABASE_VERSION
            )
        ) // DatabaseHelper 초기화
        val supplements = databaseCRUD.searchForSupplements(searchText)
        Log.i("Search supplements", supplements.toString())

        if (supplements.isNotEmpty()) {
            adapter = SearchAdapter(supplements) { clickedSupplement ->
                val supplementDetailFragment = SupplementDetail()
                val args = Bundle()
                args.putString("supplementName", clickedSupplement.name)
                supplementDetailFragment.arguments = args

                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainer, supplementDetailFragment) // R.id.fragment_container는 프래그먼트를 보여줄 공간의 ID입니다.
                transaction.addToBackStack(null) // 이전 프래그먼트로 돌아갈 수 있도록 스택에 추가
                transaction.commit()
            }
            binding.searchResultRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.searchResultRecyclerView.adapter = adapter

            val supplements = databaseCRUD.searchForSupplements(searchText)
            adapter.updateData(supplements)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}