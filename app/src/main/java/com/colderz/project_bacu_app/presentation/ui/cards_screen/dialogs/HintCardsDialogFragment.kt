package com.colderz.project_bacu_app.presentation.ui.cards_screen.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.colderz.project_bacu_app.R
import com.colderz.project_bacu_app.databinding.DialogCardsHintLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HintCardsDialogFragment : BottomSheetDialogFragment() {

    private var _binding: DialogCardsHintLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCardsHintLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
}