package com.capstone.setara.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.capstone.setara.R

class RecommendationFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var ageRadioGroup: RadioGroup
    private lateinit var daksaCheckBox: CheckBox
    private lateinit var netraCheckBox: CheckBox
    private lateinit var runguCheckBox: CheckBox
    private lateinit var exp0To2CheckBox: CheckBox
    private lateinit var exp3To5CheckBox: CheckBox
    private lateinit var expMoreThan6CheckBox: CheckBox
    private lateinit var citySpinner: Spinner
    private lateinit var submitButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommendation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize components
        nameEditText = view.findViewById(R.id.nameEditText)
        genderRadioGroup = view.findViewById(R.id.genderRadioGroup)
        ageRadioGroup = view.findViewById(R.id.ageRadioGroup)
        daksaCheckBox = view.findViewById(R.id.daksaCheckBox)
        netraCheckBox = view.findViewById(R.id.netraCheckBox)
        runguCheckBox = view.findViewById(R.id.runguCheckBox)
        exp0To2CheckBox = view.findViewById(R.id.exp0To2CheckBox)
        exp3To5CheckBox = view.findViewById(R.id.exp3To5CheckBox)
        expMoreThan6CheckBox = view.findViewById(R.id.expMoreThan6CheckBox)
        citySpinner = view.findViewById(R.id.citySpinner)
        submitButton = view.findViewById(R.id.submitButton)

        val citySpinner: Spinner = view.findViewById(R.id.citySpinner)
        val cities = resources.getStringArray(R.array.city_array)  // Mengambil string-array dari strings.xml
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, cities)
        citySpinner.adapter = adapter


        // Set up click listener for the submit button
        submitButton.setOnClickListener {
            handleSubmit()
        }
    }

    private fun handleSubmit() {
        // Get name
        val name = nameEditText.text.toString().trim()
        if (name.isEmpty()) {
            Toast.makeText(requireContext(), "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
            return
        }

        // Get gender
        val selectedGenderId = genderRadioGroup.checkedRadioButtonId
        val gender = if (selectedGenderId != -1) {
            view?.findViewById<RadioButton>(selectedGenderId)?.text.toString()
        } else {
            "Not selected"
        }

        // Get age
        val selectedAgeId = ageRadioGroup.checkedRadioButtonId
        val age = if (selectedAgeId != -1) {
            view?.findViewById<RadioButton>(selectedAgeId)?.text.toString()
        } else {
            "Not selected"
        }

        // Get city
        val city = citySpinner.selectedItem.toString()
        if (city == "Pilih kota") {
            Toast.makeText(requireContext(), "Silakan pilih kota", Toast.LENGTH_SHORT).show()
            return
        }

        // Get disabilities
        val disabilities = mutableListOf<String>()
        if (daksaCheckBox.isChecked) disabilities.add("Daksa")
        if (netraCheckBox.isChecked) disabilities.add("Netra")
        if (runguCheckBox.isChecked) disabilities.add("Rungu")

        // Get work experience
        val experiences = mutableListOf<String>()
        if (exp0To2CheckBox.isChecked) experiences.add("0-2")
        if (exp3To5CheckBox.isChecked) experiences.add("3-5")
        if (expMoreThan6CheckBox.isChecked) experiences.add(">6")

        // Display data
        val result = """
           Name: $name
           Gender: $gender
           Age: $age
           City: $city
           Disability: ${if (disabilities.isEmpty()) "None" else disabilities.joinToString(", ")}
           Experience: ${if (experiences.isEmpty()) "None" else experiences.joinToString(", ")}
       """.trimIndent()

        Toast.makeText(requireContext(), result, Toast.LENGTH_LONG).show()
    }
}
