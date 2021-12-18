package com.colderz.project_bacu_app.presentation.ui.cards_screen.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.colderz.project_bacu_app.R
import com.colderz.project_bacu_app.databinding.DialogEditGoalLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditGoalDialogFragment : BottomSheetDialogFragment() {
    private val viewModel: EditGoalViewModel by viewModels()

    private var _binding: DialogEditGoalLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogEditGoalLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
    }

    private fun initBinding() {
        binding.apply {
            dialogEditGoalCancel.setOnClickListener { this@EditGoalDialogFragment.dismiss() }
        }
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
}
