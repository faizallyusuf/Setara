package com.capstone.setara.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.capstone.setara.R
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment_home
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referensi elemen di layout
        val tvWelcome: TextView = view.findViewById(R.id.tvWelcome)

        // Ambil user saat ini dari FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        // Update teks sambutan menggunakan email
        val welcomeText = if (currentUser != null) {
            val userEmail = currentUser.email ?: "User" // Gunakan email jika tersedia
            getString(R.string.welcome_user, userEmail)
        } else {
            getString(R.string.welcome_guest)
        }
        tvWelcome.text = welcomeText
    }
}
