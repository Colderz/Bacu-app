package com.colderz.project_bacu_app.presentation.ui.cards_screen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.colderz.project_bacu_app.common.binding_adapters.setTouchListener
import com.colderz.project_bacu_app.data.database.model.FinanceGoalEntity
import com.colderz.project_bacu_app.databinding.CardInfoItemBinding
import com.colderz.project_bacu_app.presentation.ui.cards_screen.CardsViewModel

class CardPagerAdapter(
    private val items: List<FinanceGoalEntity>?,
    private val viewModel: CardsViewModel
) :
    RecyclerView.Adapter<CardPagerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardInfoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items?.get(position))
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    inner class ViewHolder(val binding: CardInfoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FinanceGoalEntity?) {
            binding.apply {
                goal = item
                viewModel = this@CardPagerAdapter.viewModel
            }
        }

    }

}