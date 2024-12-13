package com.capstone.setara.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.capstone.setara.R
import com.capstone.setara.remote.ApiConfig
import com.capstone.setara.remote.JobRecommendationRequest
import kotlinx.coroutines.launch

class RecommendationFragment : Fragment() {

    private lateinit var disabilityRadioGroup: RadioGroup
    private lateinit var ageRadioGroup: RadioGroup
    private lateinit var experienceRadioGroup: RadioGroup
    private lateinit var citySpinner: Spinner
    private lateinit var submitButton: Button
    private lateinit var jobResultsTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recommendation, container, false)

        // Inisialisasi komponen UI
        disabilityRadioGroup = view.findViewById(R.id.disabilityRadioGroup)
        ageRadioGroup = view.findViewById(R.id.ageRadioGroup)
        experienceRadioGroup = view.findViewById(R.id.experienceRadioGroup)
        citySpinner = view.findViewById(R.id.citySpinner)
        submitButton = view.findViewById(R.id.submitButton)
        jobResultsTextView = view.findViewById(R.id.jobResultsTextView)

        submitButton.setOnClickListener {
            submitData()
        }

        return view
    }

    private fun submitData() {
        // Ambil input dari UI
        val selectedDisability = when (disabilityRadioGroup.checkedRadioButtonId) {
            R.id.daksaRadioButton -> "Daksa"
            R.id.netraRadioButton -> "Netra"
            R.id.runguRadioButton -> "Rungu"
            else -> ""
        }

        val selectedAge = when (ageRadioGroup.checkedRadioButtonId) {
            R.id.RadioButton1 -> "17-24"
            R.id.RadioButton2 -> "25-30"
            R.id.RadioButton3 -> "31-35"
            else -> ""
        }

        val selectedExperience = when (experienceRadioGroup.checkedRadioButtonId) {
            R.id.exp0To2RadioButton -> "0-2"
            R.id.exp3To5RadioButton -> "3-5"
            R.id.expMoreThan6RadioButton -> "6"
            else -> ""
        }

        val selectedCity = citySpinner.selectedItem.toString()

        // Validasi input
        if (selectedDisability.isEmpty() || selectedAge.isEmpty() || selectedExperience.isEmpty()) {
            Toast.makeText(requireContext(), "Harap lengkapi semua input.", Toast.LENGTH_SHORT).show()
            return
        }

        // Tampilkan loading
        jobResultsTextView.text = "Loading..."

        // Gunakan Coroutine untuk mengirim data
        lifecycleScope.launch {
            try {
                val apiService = ApiConfig.getApiService()
                val request = JobRecommendationRequest(
                    disability = selectedDisability,
                    age = selectedAge,
                    experience = selectedExperience,
                    city = selectedCity
                )

                // Kirim data dan terima respons
                val response = apiService.sendJobRecommendation(request)

                // Tampilkan hasil respons
                jobResultsTextView.text = "Job : ${response.message}"
            } catch (e: Exception) {
                jobResultsTextView.text = "Error occurred : ${e.message}"
                Log.e("RecommendationFragment", "Error: ${e.message}", e)
            }
        }
    }
}