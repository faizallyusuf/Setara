package com.capstone.setara.view

import android.animation.ObjectAnimator
import android.animation.AnimatorSet
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone.setara.databinding.ActivitySignUpInBinding

class SignUpInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol Sign In
        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        // Tombol Sign Up
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // Panggil fungsi untuk menambahkan animasi
        playAnimation()
    }

    private fun playAnimation() {
        // Animasi untuk SignIn button
        val signInAnim = ObjectAnimator.ofFloat(binding.btnSignIn, View.ALPHA, 0f, 1f).setDuration(1000)

        // Animasi untuk SignUp button
        val signUpAnim = ObjectAnimator.ofFloat(binding.btnSignUp, View.ALPHA, 0f, 1f).setDuration(1000)

        // Animasi untuk background atau gambar lainnya (jika ada)
        val backgroundAnim = ObjectAnimator.ofFloat(binding.root, View.ALPHA, 0f, 1f).setDuration(1000)

        // Animasi untuk asset3 (Gambar yang bergerak dari kiri ke kanan)
        val asset3Anim = ObjectAnimator.ofFloat(binding.asset2, View.TRANSLATION_X, -100f, 100f).apply {
            duration = 1000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }

        // Gabungkan animasi agar berjalan secara berurutan
        AnimatorSet().apply {
            playSequentially(
                backgroundAnim,
                signInAnim,
                signUpAnim,
                asset3Anim // Menambahkan animasi asset3Anim
            )
            start()
        }
    }
}
