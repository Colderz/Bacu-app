package com.colderz.project_bacu_app.presentation.ui.dashboard_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.colderz.project_bacu_app.R
import com.colderz.project_bacu_app.common.Resource
import com.colderz.project_bacu_app.databinding.FragmentDashboardBinding
import com.colderz.project_bacu_app.presentation.ui.dashboard_screen.adapters.CurrencyRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private val TAG: String = DashboardFragment::class.java.simpleName

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: CurrencyRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        linearLayoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = linearLayoutManager
        setupApiObserver()
        initLiveDataObserver()
    }

    private fun initLiveDataObserver() {

    }

    private fun setupApiObserver() {
        viewModel.getCurrencies()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.currenciesFlow.collect { result ->
                when (result) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.recyclerView.visibility = View.VISIBLE
                        adapter = CurrencyRecyclerAdapter(
                            viewModel.convert(result.data!!.rates),
                            resources.getStringArray(R.array.currency_codes),
                            resources.getStringArray(R.array.cuurency_names)
                        )
                        binding.textDateTest.text = result.data.rates[0].toString()
                        binding.recyclerView.adapter = adapter
                        result.data.date.also { binding.fragmentDashboardDateCurrencies.text = it }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.INVISIBLE
                        binding.textDateTest.text = result.message
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.recyclerView.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}