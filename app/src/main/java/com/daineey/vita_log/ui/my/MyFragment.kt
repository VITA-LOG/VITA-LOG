package com.daineey.vita_log.ui.my

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.daineey.vita_log.ChatActivity
import com.daineey.vita_log.R
import com.daineey.vita_log.databinding.FragmentMyBinding
import com.daineey.vita_log.my.AdvertiseActivity
import com.daineey.vita_log.my.ContactActivity
import com.daineey.vita_log.my.DeveloperActivity
import com.daineey.vita_log.my.IntroActivity
import com.daineey.vita_log.my.LicenseActivity
import com.daineey.vita_log.my.PrivacyActivity
import com.daineey.vita_log.my.QuestionActivity
import com.daineey.vita_log.my.SponsorActivity
import com.daineey.vita_log.my.TeenagerActivity
import com.daineey.vita_log.my.TermsActivity

/**
 * A simple [Fragment] subclass.
 * Use the [MyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyFragment : Fragment() {

    private var _binding: FragmentMyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val MyViewModel =
            ViewModelProvider(this).get(MyViewModel::class.java)

        _binding = FragmentMyBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button1 = view?.findViewById<Button>(R.id.question)
        button1?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, QuestionActivity::class.java)
                startActivity(intent)
            }
        }
        val button2 = view?.findViewById<Button>(R.id.contact)
        button2?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, ContactActivity::class.java)
                startActivity(intent)
            }
        }
        val button3 = view?.findViewById<Button>(R.id.terms)
        button3?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, TermsActivity::class.java)
                startActivity(intent)
            }
        }
        val button4 = view?.findViewById<Button>(R.id.privacy)
        button4?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, PrivacyActivity::class.java)
                startActivity(intent)
            }
        }
        val button5 = view?.findViewById<Button>(R.id.teenager)
        button5?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, TeenagerActivity::class.java)
                startActivity(intent)
            }
        }
        val button6 = view?.findViewById<Button>(R.id.license)
        button6?.setOnClickListener {
            activity?.let{
                val intent = Intent(context, LicenseActivity::class.java)
                startActivity(intent)
            }
        }
        val button7 = view?.findViewById<Button>(R.id.intro)
        button7?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, IntroActivity::class.java)
                startActivity(intent)
            }
        }
        val button8 = view?.findViewById<Button>(R.id.developer)
        button8?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, DeveloperActivity::class.java)
                startActivity(intent)
            }
        }
        val button9 = view?.findViewById<Button>(R.id.advertise)
        button9?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, AdvertiseActivity::class.java)
                startActivity(intent)
            }
        }
        val button10 = view?.findViewById<Button>(R.id.sponsor)
        button10?.setOnClickListener {
            activity?.let {
                val intent = Intent(context, SponsorActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}