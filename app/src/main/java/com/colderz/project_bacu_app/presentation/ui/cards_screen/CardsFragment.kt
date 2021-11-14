package com.colderz.project_bacu_app.presentation.ui.cards_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.colderz.project_bacu_app.R
import com.colderz.project_bacu_app.databinding.FragmentCardsBinding
import com.colderz.project_bacu_app.presentation.ui.cards_screen.adapters.CardPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class CardsFragment : Fragment() {
    private val TAG: String = CardsFragment::class.java.simpleName
    private lateinit var adapter: CardPagerAdapter

    private lateinit var viewPager: ViewPager2
    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!

    private var images: MutableList<Int> = mutableListOf(
        R.drawable.card_bacu,
        R.drawable.card_bacu,
        R.drawable.card_bacu,
        R.drawable.card_bacu,
        R.drawable.card_bacu
    )

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
        configureViewPager()

        binding.fragmentCardAddButton.setOnClickListener {
            findNavController().navigate(R.id.action_CardsFragment_to_AddNewGoalDialog)
        }
    }

    private fun configureViewPager() {
        viewPager = binding.imageView
        adapter = CardPagerAdapter(images)
        viewPager.adapter = adapter
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}