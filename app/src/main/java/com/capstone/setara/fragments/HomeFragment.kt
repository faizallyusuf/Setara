package com.capstone.setara.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.setara.R
import com.capstone.setara.view.SignInActivity
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {
    override fun onStart() {
        super.onStart()

        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        // Jika pengguna belum login, langsung arahkan ke SignInActivity dan tutup Activity saat ini
        if (currentUser == null) {
            val intent = Intent(requireContext(), SignInActivity::class.java)
            startActivity(intent)
            requireActivity().finish() // Tutup Activity saat ini (misal, MainActivity) agar tidak bisa kembali
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}
