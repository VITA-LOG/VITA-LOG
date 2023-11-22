package com.daineey.vita_log.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.daineey.vita_log.R
import com.daineey.vita_log.database.DatabaseCRUD
import com.daineey.vita_log.database.DatabaseHelper
import com.daineey.vita_log.database.DatabaseHelper.Companion.DATABASE_NAME
import com.daineey.vita_log.database.DatabaseHelper.Companion.DATABASE_VERSION
import com.daineey.vita_log.databinding.FragmentHometab1Binding
import com.daineey.vita_log.ui.detail.SupplementDetail
import com.daineey.vita_log.ui.detail.keywordRequiredData

class HomeTab1Fragment() : Fragment() {
    private var _binding: FragmentHometab1Binding? = null
    private val binding get() = _binding!!
    private lateinit var homeFragment: HomeTab1Fragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHometab1Binding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        healthKeyWordListener(view)
        supplementByTag(view)
    }

    private fun healthKeyWordListener(view: View): Unit {
        val dbHelperInstance =
            DatabaseHelper(requireContext(), DATABASE_NAME, null, DATABASE_VERSION)
        val crud = DatabaseCRUD(dbHelperInstance)

        // 건강키워드's id list sort
        val buttons = listOf(
            R.id.intestine_button to "장 건강",
            R.id.prostate_button to "전립선",
            R.id.daily_button to "일상 활력",
            R.id.sleep_button to "수면",
            R.id.exercise_button to "운동 보조제",
            R.id.skin_button to "피부 보조제",
            R.id.liver_button to "간 건강",
            R.id.eye_button to "눈 건강"
        )

        for ((buttonId, keyword) in buttons) {
            val button = view.findViewById<Button>(buttonId)
            button.setOnClickListener {
                Log.d("keyword", keyword)
                Log.d("buttonId", buttonId.toString())
                Log.d("buttons Iterator", buttons.toString())

                val supplements = crud.getSupplementByKeyword(keyword)
                val navController = findNavController()

                val imageSrc = supplements.firstOrNull()?.imageName ?: "default_image_name"
                val supplementName = supplements.firstOrNull()?.name ?: "default_image_name"
                val companyName = supplements.firstOrNull()?.company ?: "default_image_name"

                val action = MainHomeFragmentDirections.actionMainTab1(
                    supplements.map {
                        keywordRequiredData(
                            keyword,
                            it.imageName,
                            it.company,
                            it.name
                        )
                    }.toTypedArray()
                )
                navController.navigate(action)
                Log.i("Destination", view.findNavController().currentDestination.toString())
            }
        }
    }

    private fun supplementByTag(view: View): Unit {
        val items = listOf(
            "item1", "item2", "item3", "item4", "item5", "item6"
        )

        for (itemTag in items) {
            val itemView = view.findViewWithTag<View>(itemTag)
            itemView?.setOnClickListener { clickedView ->
                Log.i("tag", clickedView.tag.toString())
                when (clickedView.tag) {
                    "item1" -> navigateToDetailFragment("LactoBif 5 Probiotics")
                    "item2" -> navigateToDetailFragment("Acidophilus Probiotic Blend")
                    "item3" -> navigateToDetailFragment("AMPK Metabolic Activators")
                    "item4" -> navigateToDetailFragment("ProbioSlim, Weight Loss Essentials")
                    "item5" -> navigateToDetailFragment("Methyl Folate")
                    "item6" -> navigateToDetailFragment("High Potency Vitamin B Complex")
                }
            }
        }
    }

    private fun navigateToDetailFragment(supplementName: String) {
        val supplementDetailFragment = SupplementDetail()
        val args = Bundle()
        args.putString("supplementName", supplementName)
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
