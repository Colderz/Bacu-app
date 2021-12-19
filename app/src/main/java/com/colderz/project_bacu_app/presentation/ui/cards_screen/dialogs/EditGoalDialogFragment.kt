package com.colderz.project_bacu_app.presentation.ui.cards_screen.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.colderz.project_bacu_app.R
import com.colderz.project_bacu_app.databinding.DialogEditGoalLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditGoalDialogFragment : BottomSheetDialogFragment() {
    private val viewModel: EditGoalViewModel by viewModels()

    private var _binding: DialogEditGoalLayoutBinding? = null
    private val binding get() = _binding!!

    private val args: EditGoalDialogFragmentArgs by navArgs()
    private var tempBalance = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogEditGoalLayoutBinding.inflate(inflater, container, false)
        initBinding()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewModel.updateTemporaryBalance.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { amount ->
                if(tempBalance + amount >= args.goalInfo.amountGoal.toInt()) {
                    tempBalance = args.goalInfo.amountGoal.toInt()
                } else {
                    tempBalance += amount
                }
                binding.balanceOfGoal = tempBalance.toString()
            }
        })
        viewModel.endOfEdit.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                this.dismiss()
            }
        })
    }

    private fun initBinding() {
        binding.apply {
            dialogEditGoalCancel.setOnClickListener { this@EditGoalDialogFragment.dismiss() }
            editGoalViewModel = viewModel
            goal = args.goalInfo
            tempBalance = args.goalInfo.balance.toInt()
            balanceOfGoal = tempBalance.toString()
        }
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
}
