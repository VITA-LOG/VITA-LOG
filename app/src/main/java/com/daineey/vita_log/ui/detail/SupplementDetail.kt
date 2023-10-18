package com.daineey.vita_log.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.daineey.vita_log.database.DatabaseCRUD
import com.daineey.vita_log.database.DatabaseHelper
import com.daineey.vita_log.database.DatabaseHelper.Companion.DATABASE_NAME
import com.daineey.vita_log.databinding.FragmentSupplementBinding

class SupplementDetail : Fragment() {

    private lateinit var binding: FragmentSupplementBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 바인딩 초기화
        binding = FragmentSupplementBinding.inflate(inflater, container, false)

        val supplementName = arguments?.getString("supplementName")

        val databaseCRUD = DatabaseCRUD(
            DatabaseHelper(requireContext(), DATABASE_NAME, null, DatabaseHelper.DATABASE_VERSION)
        )

        val supplement = databaseCRUD.getSupplementByName(supplementName)
        Log.i("supplement by name one item", supplement.toString())

        val imgResId = binding.supplementImage.context.resources.getIdentifier(
            supplement?.imageName,
            "drawable",
            binding.supplementImage.context.packageName
        )
        if (imgResId != 0) {
            binding.supplementImage.setImageResource(imgResId)
        }
        binding.companyName.text = supplement?.company
        binding.supplementName.text = supplement?.name
        binding.ingredient.text = supplement?.ingredient
        binding.content.text = supplement?.content
        binding.formulation.text = supplement?.formulation
        binding.amount.text = supplement?.amount.toString()

        return binding.root
    }
}
