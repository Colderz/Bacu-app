package com.colderz.project_bacu_app.presentation.ui.cards_screen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.colderz.project_bacu_app.databinding.CardInfoItemBinding

class CardPagerAdapter(private val items: List<Int>) : RecyclerView.Adapter<CardPagerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardInfoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: CardInfoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int) {
            binding.imageView.setBackgroundResource(item)
        }
    }

}