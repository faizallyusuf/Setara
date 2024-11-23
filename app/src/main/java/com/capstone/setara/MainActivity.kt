package com.capstone.setara

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.capstone.setara.databinding.ActivityMainBinding
import com.capstone.setara.fragments.AssistFragment
import com.capstone.setara.fragments.HomeFragment
import com.capstone.setara.fragments.RecommendationFragment
import com.capstone.setara.fragments.SettingsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set default fragment to HomeFragment
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Set BottomNavigationView listener
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> { // ID untuk Home
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.recommendation -> { // ID untuk Recommendation
                    replaceFragment(RecommendationFragment())
                    true
                }
                R.id.assist -> { // ID untuk Assist
                    replaceFragment(AssistFragment())
                    true
                }
                R.id.settings -> { // ID untuk Settings
                    replaceFragment(SettingsFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}
