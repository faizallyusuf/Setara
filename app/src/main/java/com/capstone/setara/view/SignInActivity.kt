package com.capstone.setara.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import com.capstone.setara.MainActivity
import com.capstone.setara.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        setupAnimations()

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                if (pass.length >= 8) {
                    firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password must be at least 8 characters!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (firebaseAuth.currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupAnimations() {
        val asset1Anim = ObjectAnimator.ofFloat(binding.asset1, View.ALPHA, 0f, 1f).apply {
            duration = 800
        }
        val asset2Anim = ObjectAnimator.ofFloat(binding.asset2, View.TRANSLATION_X, -100f, 100f).apply {
            duration = 1000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }

        val textWelcomeAnim = ObjectAnimator.ofFloat(binding.textWelcome, View.ALPHA, 0f, 1f).apply {
            duration = 600
        }
        val emailLayoutAnim = ObjectAnimator.ofFloat(binding.emailLayout, View.ALPHA, 0f, 1f).apply {
            duration = 600
        }
        val passwordLayoutAnim = ObjectAnimator.ofFloat(binding.passwordLayout, View.ALPHA, 0f, 1f).apply {
            duration = 600
        }
        val buttonAnim = ObjectAnimator.ofFloat(binding.button, View.ALPHA, 0f, 1f).apply {
            duration = 600
        }
        val textViewAnim = ObjectAnimator.ofFloat(binding.textView, View.ALPHA, 0f, 1f).apply {
            duration = 600
        }

        asset1Anim.start()

        asset2Anim.start()

        AnimatorSet().apply {
            playSequentially(
                textWelcomeAnim,
                emailLayoutAnim,
                passwordLayoutAnim,
                buttonAnim,
                textViewAnim
            )
            startDelay = 300
        }.start()
    }
}
