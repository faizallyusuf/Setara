package com.capstone.setara.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.setara.databinding.ItemAssistBinding
import com.capstone.setara.model.AssistItem

class AssistAdapter(
    private val listData: List<AssistItem>,
    private val onClick: (AssistItem) -> Unit // Callback saat item diklik
) : RecyclerView.Adapter<AssistAdapter.AssistViewHolder>() {

    // ViewHolder untuk mengelola view item
    class AssistViewHolder(private val binding: ItemAssistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AssistItem, onClick: (AssistItem) -> Unit) {
            binding.icon.setImageResource(data.iconRes)
            binding.title.text = data.title
            binding.description.text = data.description
            binding.buttonGo.setOnClickListener {
                onClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssistViewHolder {
        val binding = ItemAssistBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AssistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AssistViewHolder, position: Int) {
        holder.bind(listData[position], onClick)
    }

    override fun getItemCount(): Int = listData.size
}
