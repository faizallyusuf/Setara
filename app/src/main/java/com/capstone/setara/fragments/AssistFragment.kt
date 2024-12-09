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

        binding = FragmentAssistBinding.inflate(inflater, container, false)

        val assistItems = listOf(
            AssistItem(R.drawable.gpt, "Chat GPT",
                "Chat GPT by OpenAI is an advanced AI model that can generate human-like text. It's widely used for content creation, conversation simulation, and answering questions, making it a versatile tool for a range of applications.",
                "https://chat.openai.com"
            ),
            AssistItem(R.drawable.sendsteps, "Sendsteps",
                "Sendsteps is an AI-powered presentation tool that helps users create professional presentations quickly. By automating slide creation, it enables users to design high-quality presentations in a fraction of the time.",
                "https://www.sendsteps.ai"
            ),
            AssistItem(R.drawable.gencraft, "Gencraft",
                "Gencraft is a cutting-edge platform that uses AI to generate images and videos from text descriptions. It's especially valuable for content creators, designers, and marketers who need high-quality visuals quickly.",
                "https://www.gencraft.com"
            ),
            AssistItem(R.drawable.simplified, "Simplified",
                "Simplified is an AI-driven tool that helps users create over 50 types of copy in seconds. From marketing content to social media posts, it simplifies the content creation process, making it faster and more efficient.",
                "https://www.simplified.com"
            )
        )

        val adapter = AssistAdapter(assistItems) { item ->

            Toast.makeText(context, "${item.title} clicked!", Toast.LENGTH_SHORT).show()

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
            startActivity(intent)
        }


        // Setup RecyclerView
        binding.rvAssist.layoutManager = LinearLayoutManager(context)
        binding.rvAssist.adapter = adapter

        return binding.root
    }
}
