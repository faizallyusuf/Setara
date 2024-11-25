package com.capstone.setara.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.setara.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        playAnimation()

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Akun berhasil dibuat! Silakan login.", Toast.LENGTH_LONG).show()

                                firebaseAuth.signOut()

                                val intent = Intent(this, SignInActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "Gagal membuat akun: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun playAnimation() {
        val asset1Anim = ObjectAnimator.ofFloat(binding.asset1, View.ALPHA, 0f, 1f).setDuration(1000)

        val welcomeAnim = ObjectAnimator.ofFloat(binding.textWelcome, View.ALPHA, 0f, 1f).setDuration(1000)
        val emailAnim = ObjectAnimator.ofFloat(binding.emailLayout, View.ALPHA, 0f, 1f).setDuration(1000)
        val passwordAnim = ObjectAnimator.ofFloat(binding.passwordLayout, View.ALPHA, 0f, 1f).setDuration(1000)
        val confirmPassAnim = ObjectAnimator.ofFloat(binding.confirmPasswordLayout, View.ALPHA, 0f, 1f).setDuration(1000)
        val buttonAnim = ObjectAnimator.ofFloat(binding.button, View.ALPHA, 0f, 1f).setDuration(1000)
        val signInTextAnim = ObjectAnimator.ofFloat(binding.textView, View.ALPHA, 0f, 1f).setDuration(1000)

        AnimatorSet().apply {
            playSequentially(
                welcomeAnim,
                emailAnim,
                passwordAnim,
                confirmPassAnim,
                buttonAnim,
                signInTextAnim
            )
            startDelay = 300
            start()
        }

        asset1Anim.start()
    }
}
