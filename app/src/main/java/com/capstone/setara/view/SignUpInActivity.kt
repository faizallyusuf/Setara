package com.capstone.setara.view

import android.animation.ObjectAnimator
import android.animation.AnimatorSet
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.capstone.setara.MainActivity
import com.capstone.setara.databinding.ActivitySignUpInBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        playAnimation()
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun playAnimation() {
        val signInAnim = ObjectAnimator.ofFloat(binding.btnSignIn, View.ALPHA, 0f, 1f).setDuration(1000)
        val signUpAnim = ObjectAnimator.ofFloat(binding.btnSignUp, View.ALPHA, 0f, 1f).setDuration(1000)
        val backgroundAnim = ObjectAnimator.ofFloat(binding.root, View.ALPHA, 0f, 1f).setDuration(1000)
        val asset3Anim = ObjectAnimator.ofFloat(binding.asset2, View.TRANSLATION_X, -100f, 100f).apply {
            duration = 1000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }

        AnimatorSet().apply {
            playSequentially(
                backgroundAnim,
                signInAnim,
                signUpAnim,
                asset3Anim
            )
            start()
        }
    }
}
