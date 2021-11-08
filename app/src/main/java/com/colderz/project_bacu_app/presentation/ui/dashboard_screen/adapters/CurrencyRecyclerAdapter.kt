package com.colderz.project_bacu_app.presentation.ui.dashboard_screen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.colderz.project_bacu_app.databinding.CurrencyCardLayoutBinding

class CurrencyRecyclerAdapter(
    private val items: List<Double?>,
    private val listOfsAbbr: Array<String>,
    private val listsOfNames: Array<String>
) :
    RecyclerView.Adapter<CurrencyRecyclerAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencyRecyclerAdapter.CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CurrencyCardLayoutBinding.inflate(inflater)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) =
        holder.bind(
            items[position],
            listsOfNames[position],
            listOfsAbbr[position]
        )

    override fun getItemCount(): Int = items.size

    inner class CurrencyViewHolder(val binding: CurrencyCardLayoutBinding) :
        ViewHolder(binding.root) {
        fun bind(item: Double?, currencyName: String, currencyAbbr: String) {
            val builder = StringBuilder()
            builder.append(item.toString()).append(currencyAbbr)
            binding.currencyName.text = currencyName
            binding.currencyRate.text = builder
        }

    }
}
