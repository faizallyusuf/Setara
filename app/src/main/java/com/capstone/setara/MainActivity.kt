package com.capstone.setara

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.capstone.setara.databinding.ActivityMainBinding
import com.capstone.setara.fragments.HomeFragment
import com.capstone.setara.fragments.RecommendationFragment
import com.capstone.setara.fragments.SettingsFragment
import com.capstone.setara.ui.fragment.AssistFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private var isScrollUp = false  // To track scroll direction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView)

        // Set default fragment to HomeFragment
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Set BottomNavigationView listener
        setupBottomNavigation()

        // Add scroll listener for hiding/showing BottomNavigationView
        setupScrollListener()
    }

    // Ubah visibilitas menjadi public
    fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
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

    // Set up RecyclerView scroll listener to hide/show BottomNavigationView
    private fun setupScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 && !isScrollUp) {
                    // User scrolls down, hide BottomNavigationView
                    binding.bottomNavigation.visibility = View.GONE
                    isScrollUp = true
                } else if (dy < 0 && isScrollUp) {
                    // User scrolls up, show BottomNavigationView
                    binding.bottomNavigation.visibility = View.VISIBLE
                    isScrollUp = false
                }
            }
        })
    }
}
