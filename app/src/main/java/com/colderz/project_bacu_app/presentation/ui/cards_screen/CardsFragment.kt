package com.colderz.project_bacu_app.presentation.ui.cards_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.colderz.project_bacu_app.R
import com.colderz.project_bacu_app.databinding.FragmentCardsBinding
import com.colderz.project_bacu_app.presentation.ui.cards_screen.adapters.CardPagerAdapter
import com.colderz.project_bacu_app.presentation.ui.cards_screen.adapters.HistoricalGoalRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class CardsFragment : Fragment() {
    private val TAG: String = CardsFragment::class.java.simpleName
    private lateinit var pagerAdapter: CardPagerAdapter
    private lateinit var recyclerAdapter: HistoricalGoalRecyclerAdapter

    private lateinit var viewPager: ViewPager2
    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!

    var actualCardsFragmentState = CardsFragmentState.TRANSPORT_PAGE
    private lateinit var linearLayoutManager: LinearLayoutManager

    private val viewModel: CardsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initView()
    }

    private fun initView() {
        initBinding()
        linearLayoutManager = LinearLayoutManager(context)
        binding.fragmentCardRecyclerView.layoutManager = linearLayoutManager
    }

    private fun initBinding() {
        binding.apply {
            viewModel = this@CardsFragment.viewModel
            goalsCategoryState = actualCardsFragmentState
            categoryTitle = getString(R.string.title_transport)
        }
    }

    private fun initObservers() {
        viewModel.navigateToAddGoalDialog.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                val title: String = binding.categoryGoalsTitle.text.toString()
                val action = CardsFragmentDirections.actionCardsfragmentToAddnewgoaldialog(title)
                findNavController().navigate(action)
            }
        })
        viewModel.changeToNextCategory.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                nextGoalsCategory()
            }
        })
        viewModel.changeToPreviousCategory.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                previousGoalsCategory()
            }
        })
        viewModel.allGoalFromDatabase.observe(viewLifecycleOwner, {
            configureViewPager(binding.categoryTitle!!)
            configureRecyclerAdapter(binding.categoryTitle!!)
        })
        viewModel.navigateToHintDialog.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(R.id.action_cardsfragment_to_cardshintdialog)
            }
        })
        viewModel.navigateToEditGoalDialog.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { goal ->
                val action = CardsFragmentDirections.actionCardsfragmentToEditgoaldialog(goal)
                findNavController().navigate(action)
        }
        })
        viewModel.navigateToGoalSuccess.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                findNavController().navigate(R.id.action_cardsfragment_to_successgoaldialog)
            }
        })
    }

    private fun configureRecyclerAdapter(categoryTitle: String) {
        val prepareHistoricalList = viewModel.prepareHistoricalGoalData(categoryTitle)
        recyclerAdapter = HistoricalGoalRecyclerAdapter(prepareHistoricalList)
    }

    private fun configureViewPager(category: String) {
        viewPager = binding.imagesView
        val prepareCategoryList = viewModel.prepareCorrectViewPagerData(category)
        pagerAdapter = CardPagerAdapter(prepareCategoryList, viewModel)
        viewPager.adapter = pagerAdapter
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(8))
        transformer.addTransformer { page, position ->
            val v: Float = 1 - abs(position)
            page.scaleY = 0.8f + v * 0.2f
        }
        viewPager.setPageTransformer(transformer)
        binding.apply {
            if (prepareCategoryList?.isEmpty()!!) {
                fragmentCardNoneGoal.visibility = View.VISIBLE
                imagesView.visibility = View.INVISIBLE
            } else {
                fragmentCardNoneGoal.visibility = View.INVISIBLE
                imagesView.visibility = View.VISIBLE
            }
        }
    }

    fun nextGoalsCategory() {
        when (actualCardsFragmentState) {
            CardsFragmentState.TRANSPORT_PAGE -> {
                actualCardsFragmentState = CardsFragmentState.HOME_PAGE
                binding.categoryTitle = getString(R.string.title_home)
                configureViewPager(getString(R.string.title_home))
            }
            CardsFragmentState.HOME_PAGE -> {
                actualCardsFragmentState = CardsFragmentState.TRAVEL_PAGE
                binding.categoryTitle = getString(R.string.title_travel)
                configureViewPager(getString(R.string.title_travel))
            }
            CardsFragmentState.TRAVEL_PAGE -> {
                actualCardsFragmentState = CardsFragmentState.ELECTRONIC_PAGE
                binding.categoryTitle = getString(R.string.title_electronic)
                configureViewPager(getString(R.string.title_electronic))
            }
            CardsFragmentState.ELECTRONIC_PAGE -> {
                actualCardsFragmentState = CardsFragmentState.GIFTS_PAGE
                binding.categoryTitle = getString(R.string.title_gifts)
                configureViewPager(getString(R.string.title_gifts))
            }
            CardsFragmentState.GIFTS_PAGE -> {
                actualCardsFragmentState = CardsFragmentState.TRANSPORT_PAGE
                binding.categoryTitle = getString(R.string.title_transport)
                configureViewPager(getString(R.string.title_transport))
            }
        }
        binding.goalsCategoryState = actualCardsFragmentState
    }

    fun previousGoalsCategory() {
        when (actualCardsFragmentState) {
            CardsFragmentState.TRANSPORT_PAGE -> {
                actualCardsFragmentState = CardsFragmentState.GIFTS_PAGE
                binding.categoryTitle = getString(R.string.title_gifts)
                configureViewPager(getString(R.string.title_gifts))
            }
            CardsFragmentState.HOME_PAGE -> {
                actualCardsFragmentState = CardsFragmentState.TRANSPORT_PAGE
                binding.categoryTitle = getString(R.string.title_transport)
                configureViewPager(getString(R.string.title_transport))
            }
            CardsFragmentState.TRAVEL_PAGE -> {
                actualCardsFragmentState = CardsFragmentState.HOME_PAGE
                binding.categoryTitle = getString(R.string.title_home)
                configureViewPager(getString(R.string.title_home))
            }
            CardsFragmentState.ELECTRONIC_PAGE -> {
                actualCardsFragmentState = CardsFragmentState.TRAVEL_PAGE
                binding.categoryTitle = getString(R.string.title_travel)
                configureViewPager(getString(R.string.title_travel))
            }
            CardsFragmentState.GIFTS_PAGE -> {
                actualCardsFragmentState = CardsFragmentState.ELECTRONIC_PAGE
                binding.categoryTitle = getString(R.string.title_electronic)
                configureViewPager(getString(R.string.title_electronic))
            }
        }
        binding.apply {
            goalsCategoryState = actualCardsFragmentState
        }
    }

    enum class CardsFragmentState {
        TRANSPORT_PAGE,
        HOME_PAGE,
        TRAVEL_PAGE,
        ELECTRONIC_PAGE,
        GIFTS_PAGE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}