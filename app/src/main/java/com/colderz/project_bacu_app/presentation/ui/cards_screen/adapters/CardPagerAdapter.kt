package com.colderz.project_bacu_app.presentation.ui.cards_screen.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.colderz.project_bacu_app.R
import com.colderz.project_bacu_app.common.binding_adapters.setTouchListener
import com.colderz.project_bacu_app.databinding.CardInfoItemBinding
import com.colderz.project_bacu_app.presentation.ui.cards_screen.CardsViewModel

class CardPagerAdapter(private val items: List<Int>, private val viewModel: CardsViewModel) :
    RecyclerView.Adapter<CardPagerAdapter.ViewHolder>() {
    var position: Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardInfoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
        this.position = position
    }

    override fun getItemCount(): Int = items.size

    fun getCurrentPosition(): Int = this.position

    inner class ViewHolder(val binding: CardInfoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("ClickableViewAccessibility")
        fun bind(item: Int, position: Int) {
            binding.imageView.setBackgroundResource(item)
            if (position == items.size - 1) {
                binding.imageView.setBackgroundResource(R.drawable.card_add)
                binding.imageView.setTouchListener(true)
                binding.imageView.setTouchListener(false)
                binding.imageView.setOnClickListener { viewModel.goToAddGoalDialog() }
            }
        }

    }

}