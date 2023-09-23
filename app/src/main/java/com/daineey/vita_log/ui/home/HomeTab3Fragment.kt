package com.daineey.vita_log.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.daineey.vita_log.R
import com.daineey.vita_log.databinding.FragmentHometab3Binding
import java.net.URL


class HomeTab3Fragment : Fragment() {

    private var _binding: FragmentHometab3Binding? = null

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

        _binding = FragmentHometab3Binding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val button1 = view?.findViewById<ImageView>(R.id.article1)
        button1?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://n.news.naver.com/mnews/article/346/0000063272?sid=103"))
            startActivity(intent)
        }
        val button2 = view?.findViewById<ImageView>(R.id.article2)
        button2?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://n.news.naver.com/mnews/article/296/0000067793?sid=103"))
            startActivity(intent)
        }
        val button3 = view?.findViewById<ImageView>(R.id.article3)
        button3?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://n.news.naver.com/mnews/article/346/0000055055?sid=103"))
            startActivity(intent)
        }
        val button4 = view?.findViewById<ImageView>(R.id.article4)
        button4?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://n.news.naver.com/mnews/article/346/0000053099?sid=103"))
            startActivity(intent)
        }
        val button5 = view?.findViewById<ImageView>(R.id.article5)
        button5?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://n.news.naver.com/mnews/article/296/0000051760?sid=103"))
            startActivity(intent)
        }
        val button6 = view?.findViewById<ImageView>(R.id.article6)
        button6?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://n.news.naver.com/mnews/article/296/0000051200?sid=103"))
            startActivity(intent)
        }
        val button7 = view?.findViewById<ImageView>(R.id.article7)
        button7?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://n.news.naver.com/mnews/article/586/0000027716?sid=103"))
            startActivity(intent)
        }
        val button8 = view?.findViewById<ImageView>(R.id.article8)
        button8?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://n.news.naver.com/mnews/article/346/0000029747?sid=103"))
            startActivity(intent)
        }
        val button9 = view?.findViewById<ImageView>(R.id.article9)
        button9?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://n.news.naver.com/mnews/article/014/0004346095?sid=103"))
            startActivity(intent)
        }
        val button10 = view?.findViewById<ImageView>(R.id.article10)
        button10?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://n.news.naver.com/mnews/article/346/0000030609?sid=103"))
            startActivity(intent)
        }
        val button11 = view?.findViewById<ImageView>(R.id.article11)
        button11?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://n.news.naver.com/mnews/article/346/0000027710?sid=103"))
            startActivity(intent)
        }
        val button12 = view?.findViewById<ImageView>(R.id.article12)
        button12?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://n.news.naver.com/mnews/article/022/0003388677?sid=103"))
            startActivity(intent)
        }
        val button13 = view?.findViewById<ImageView>(R.id.article13)
        button13?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://n.news.naver.com/mnews/article/296/0000041918?sid=103"))
            startActivity(intent)
        }
        val button14 = view?.findViewById<ImageView>(R.id.article14)
        button14?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://n.news.naver.com/mnews/article/014/0004255696?sid=103"))
            startActivity(intent)
        }
        val button15 = view?.findViewById<ImageView>(R.id.article15)
        button15?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://n.news.naver.com/mnews/article/469/0000389195?sid=103"))
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}