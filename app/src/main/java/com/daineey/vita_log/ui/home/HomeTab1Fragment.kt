package com.daineey.vita_log.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.daineey.vita_log.R
import com.daineey.vita_log.database.DatabaseCRUD
import com.daineey.vita_log.database.DatabaseHelper
import com.daineey.vita_log.database.DatabaseHelper.Companion.DATABASE_NAME
import com.daineey.vita_log.database.DatabaseHelper.Companion.DATABASE_VERSION
import com.daineey.vita_log.databinding.FragmentHometab1Binding
import com.daineey.vita_log.ui.detail.keywordRequiredData

class HomeTab1Fragment() : Fragment() {
    private var _binding: FragmentHometab1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHometab1Binding.inflate(inflater, container, false)
        val view = inflater.inflate(R.layout.fragment_hometab1, container, false)

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        healthKeyWordListener(view)
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
                val navController = view.findNavController()

                if (supplements.isNotEmpty()) {
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
                } else {
                    Toast.makeText(context, "값이 없습니다.", LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // For Memory Leaking Prevent
        _binding = null
    }
}

/** @sample `+` 버튼을 누르면 아이템이 등록됨
 *
 * private fun showPopupWidget() {
 *         val builder = AlertDialog.Builder(requireContext())
 *         builder.setTitle("내 건강키워드")
 *         val input = EditText(requireContext())
 *         input.hint = "키워드를 입력하세요"
 *
 *         builder.setView(input)
 *         builder.setPositiveButton("등록") { dialog, _ ->
 *             val keyword = input.text.toString()
 *             if (keyword.isNotBlank()) {
 *                 addKeywordToFlexbox(keyword, R.id.flexboxLayout)
 *             }
 *             dialog.dismiss()
 *         }
 *         builder.setNegativeButton("취소") { dialog, _ ->
 *             dialog.dismiss()
 *         }
 *         builder.show()
 *     }
 *     private fun addKeywordToFlexbox(keyword: String, flexboxLayoutId: Int) {
 *         // 키워드 추가 로직
 *         val flexboxLayout = view?.findViewById<FlexboxLayout>(flexboxLayoutId)
 *
 *         val keywordButton = Button(requireContext()).apply {
 *             text = keyword
 *             setBackgroundResource(R.drawable.elipse_keyword_button)
 *             setTextColor(Color.parseColor("#FFFFFF"))
 *             textSize = 16f
 *
 *             // Health Keyword Purple Design Setting
 *             layoutParams = FlexboxLayout.LayoutParams(81.dpToPx(), 81.dpToPx()).apply {
 *                 setMargins(10.dpToPx(), 10.dpToPx(), 10.dpToPx(), 10.dpToPx())
 *             }
 *
 *             setOnClickListener {
 *                 // TODO: 버튼을 클락하면 상세페이지로 이동" -> Hard Coding
 *                 Log.i("OnClickListener", "건강정보 클릭!")
 *             }
 *         }
 *
 *         // + 임시 제거
 *         val plusButton = flexboxLayout?.findViewById<Button>(R.id.plus_button)
 *         flexboxLayout?.removeView(plusButton)
 *
 *         // 키워드 버튼 추가
 *         flexboxLayout?.addView(keywordButton)
 *
 *         // + 버튼을 다시 추가하여 마지막 아이템으로 유지
 *         plusButton?.let { flexboxLayout.addView(it) }
 *      }
 *
 *     private fun Int.dpToPx(): Int {
 *         val density = Resources.getSystem().displayMetrics.density
 *         return (this * density).toInt()
 *     }
 *     */