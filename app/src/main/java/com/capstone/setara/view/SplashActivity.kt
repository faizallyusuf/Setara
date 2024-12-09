package com.capstone.setara.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.capstone.setara.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    companion object {
        const val SPLASH_SCREEN_DELAY_MILLIS = 3000L // 3 seconds
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Use the constant variable for the delay
        Handler(Looper.getMainLooper()).postDelayed({
            // Pindah ke SignUpInActivity
            val intent = Intent(this, SignUpInActivity::class.java)
            startActivity(intent)
            finish() // Mengakhiri SplashActivity agar tidak bisa kembali dengan tombol back
        }, SPLASH_SCREEN_DELAY_MILLIS)
    }
}
