package com.capstone.setara.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.capstone.setara.R
import com.capstone.setara.databinding.FragmentHomeBinding
import com.capstone.setara.MainActivity
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment_home
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Ambil referensi ke TextView
        val tvWelcome: TextView = binding.tvWelcome
        val tvSubTitle: TextView = binding.tvSubTitle
        val tvEmail: TextView = binding.tvEmail

        // Ambil user saat ini dari FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        // Update teks sambutan dengan email pengguna
        val welcomeText = "Welcome"
        tvWelcome.text = welcomeText

        val subTitleText = getString(R.string.discover)
        tvSubTitle.text = subTitleText

        val emailText = if (currentUser != null) {
            currentUser.email ?: "User"
        } else {
            "Guest"
        }

        // Set email ke TextView
        tvEmail.text = emailText

        // Aksi tombol Explore
        binding.btnExplore.setOnClickListener {
            // Ganti fragment ke RecommendationFragment menggunakan metode replaceFragment dari MainActivity
            (activity as MainActivity).replaceFragment(RecommendationFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
