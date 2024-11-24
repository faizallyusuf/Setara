package com.capstone.setara.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.setara.R
import com.capstone.setara.adapter.AssistAdapter
import com.capstone.setara.databinding.FragmentAssistBinding
import com.capstone.setara.model.AssistItem

class AssistFragment : Fragment(R.layout.fragment_assist) {

    private lateinit var binding: FragmentAssistBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inisialisasi view binding
        binding = FragmentAssistBinding.inflate(inflater, container, false)

        // Data untuk RecyclerView
        val assistItems = listOf(
            AssistItem(R.drawable.gpt, "Chat GPT", "Capable of generating human-like text on context and past conversations.", "https://chat.openai.com"),
            AssistItem(R.drawable.sendsteps, "Sendsteps", "AI Presentation Maker: Create presentation 10x faster.", "https://www.sendsteps.ai"),
            AssistItem(R.drawable.gencraft, "Gencraft", "The world's most powerful AI photo and video generation engine.", "https://www.gencraft.com"),
            AssistItem(R.drawable.simplified, "Simplified", "Generate 50+ types of copy in seconds with AI Writer.", "https://www.simplified.com")
        )


        // Setup RecyclerView dengan Adapter
        val adapter = AssistAdapter(assistItems) { item ->
            // Menangani klik item, bisa membuka detail atau aksi lainnya
            Toast.makeText(context, "${item.title} clicked!", Toast.LENGTH_SHORT).show()

            // Buka link di browser saat tombol diklik
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
            startActivity(intent)
        }


        // Setup RecyclerView
        binding.rvAssist.layoutManager = LinearLayoutManager(context)
        binding.rvAssist.adapter = adapter

        return binding.root
    }
}
