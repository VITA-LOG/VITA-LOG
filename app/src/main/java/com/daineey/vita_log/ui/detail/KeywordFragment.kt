package com.daineey.vita_log.ui.detail

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daineey.vita_log.R
import com.daineey.vita_log.database.Supplement
import com.daineey.vita_log.databinding.FragmentKeywordBinding

data class keywordRequiredData(
    val keyword: String,
    val imageSrc: String,
    val companyName: String,
    val supplementName: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(keyword)
        parcel.writeString(imageSrc)
        parcel.writeString(companyName)
        parcel.writeString(supplementName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<keywordRequiredData> {
        override fun createFromParcel(parcel: Parcel): keywordRequiredData {
            return keywordRequiredData(parcel)
        }

        override fun newArray(size: Int): Array<keywordRequiredData?> {
            return arrayOfNulls(size)
        }
    }
}

class KeywordFragment : Fragment() {
    private var _binding: FragmentKeywordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKeywordBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val supplementsData = arguments?.let {
            KeywordFragmentArgs.fromBundle(it).supplementsData?.toList() ?: listOf()
        }?: listOf()

        if (supplementsData.isNotEmpty()) {
            binding.headerKeyword.text = supplementsData[0].keyword
        }

        // 어댑터 설정
        val adapter = KeywordAdapter(supplementsData) { clickedSupplement ->
            // 여기서 클릭한 아이템에 대한 처리를 합니다.
            navigateToSupplementDetail(clickedSupplement)
        }
        val recyclerView: RecyclerView = view.findViewById(R.id.keyword_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    fun navigateToSupplementDetail(clickedSupplement: keywordRequiredData) {
        val supplementDetailFragment = SupplementDetail()
        val args = Bundle()
        args.putString("supplementName", clickedSupplement.supplementName)
        supplementDetailFragment.arguments = args

        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, supplementDetailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}