package com.capstone.setara.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.capstone.setara.R

class AboutUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        val textView = findViewById<TextView>(R.id.aboutUsText)
        textView.text = "Ini adalah halaman About Us\n\n" +
                "Aplikasi ini dibuat oleh Tim Setara.\n" +
                "Tujuan aplikasi ini adalah untuk membantu orang dengan disabilitas menemukan pekerjaan."
    }
}
