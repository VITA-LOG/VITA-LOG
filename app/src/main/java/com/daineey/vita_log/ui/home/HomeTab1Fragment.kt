package com.daineey.vita_log.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.daineey.vita_log.R
import com.daineey.vita_log.SupplementActivity
import com.daineey.vita_log.databinding.FragmentHometab1Binding
import com.daineey.vita_log.my.QuestionActivity

class HomeTab1Fragment : Fragment() {

    private var _binding: FragmentHometab1Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val HomeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHometab1Binding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val button1 = view?.findViewById<ImageButton>(R.id.gut_health)
        button1?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, SupplementActivity::class.java)
                startActivity(intent)
            }
        }
        val button2 = view?.findViewById<ImageButton>(R.id.junlipsun)
        button2?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, SupplementActivity::class.java)
                startActivity(intent)
            }
        }
        val button3 = view?.findViewById<ImageButton>(R.id.daily_energy)
        button3?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, SupplementActivity::class.java)
                startActivity(intent)
            }
        }
        val button4 = view?.findViewById<ImageButton>(R.id.sleep)
        button4?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, SupplementActivity::class.java)
                startActivity(intent)
            }
        }
        val button5 = view?.findViewById<ImageButton>(R.id.exercise_supplement)
        button5?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, SupplementActivity::class.java)
                startActivity(intent)
            }
        }
        val button6 = view?.findViewById<ImageButton>(R.id.skin_care)
        button6?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, SupplementActivity::class.java)
                startActivity(intent)
            }
        }
        val button7 = view?.findViewById<ImageButton>(R.id.eyes)
        button7?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, SupplementActivity::class.java)
                startActivity(intent)
            }
        }
        val button8 = view?.findViewById<ImageButton>(R.id.liver)
        button8?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, SupplementActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}