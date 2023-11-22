package com.daineey.vita_log.my

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.daineey.vita_log.MainActivity
import com.daineey.vita_log.R

class DeveloperActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)

        val button1 = findViewById<ImageView>(R.id.logo)
        button1?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/0_z_in?igshid=MzMyNGUyNmU2YQ=="))
            startActivity(intent)
        }
        val button2 = findViewById<ImageView>(R.id.logo1)
        button2?.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/da_ineey?igshid=MzMyNGUyNmU2YQ=="))
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}