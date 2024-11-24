package com.capstone.setara.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.capstone.setara.R

class AboutUsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        // Cari ID dari layout XML
        val aboutUsTitle = findViewById<TextView>(R.id.aboutUsTitle)
        val aboutUsDescription = findViewById<TextView>(R.id.aboutUsDescription)

        // Set konten judul dan deskripsi
        aboutUsTitle.text = getString(R.string.about_us_title)
        aboutUsDescription.text = getString(R.string.about_us_description)
    }
}
