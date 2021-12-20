package com.colderz.project_bacu_app.presentation.ui.cards_screen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.colderz.project_bacu_app.data.database.model.FinanceGoalEntity
import com.colderz.project_bacu_app.databinding.HistoricalGoalCardLayoutBinding

class HistoricalGoalRecyclerAdapter(
    private val items: List<FinanceGoalEntity>?
) : RecyclerView.Adapter<HistoricalGoalRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HistoricalGoalCardLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items?.get(position))
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    inner class ViewHolder(val binding: HistoricalGoalCardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FinanceGoalEntity?) {
            binding.goal = item
        }
    }
}
