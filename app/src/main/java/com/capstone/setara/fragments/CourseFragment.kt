package com.capstone.setara.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.setara.databinding.FragmentCourseBinding

class CourseFragment : Fragment() {

    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout dengan ViewBinding
        _binding = FragmentCourseBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set Text Programmatically
        binding.tvTimeSpent.text = "Time spent on the course\n31 Hr"

        // Handle Button Click
        binding.btnStart.setOnClickListener {
            // Tambahkan logika untuk "Start" button
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
