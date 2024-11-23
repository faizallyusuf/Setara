package com.capstone.setara

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.setara.databinding.ActivityMainBinding
import com.capstone.setara.view.SignInActivity
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.auth.FirebaseAuth

lateinit var auth: FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navigationBar: NavigationBarView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Set up Edge to Edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up Logout Button
        val logoutButton: Button = findViewById(R.id.logoutButton)
        logoutButton.setOnClickListener {
            logout()
        }
    }

    // Logout function
    private fun logout() {
        auth.signOut() // Perform the logout from Firebase

        // Redirect to SignInActivity after logout
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish() // Close MainActivity so user cannot go back
    }
}
