package com.colderz.project_bacu_app.presentation.ui.cards_screen.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.colderz.project_bacu_app.R
import com.colderz.project_bacu_app.databinding.DialogAddNewGoalBinding
import com.colderz.project_bacu_app.presentation.ui.cards_screen.dialogs.date_picker.DatePickerFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewGoalDialogFragment : BottomSheetDialogFragment() {
    private val viewModel: AddNewGoalViewModel by viewModels()

    private var _binding: DialogAddNewGoalBinding? = null
    private val binding get() = _binding!!

    private val requestKey = "REQUEST_KEY"
    private val selectedDate = "SELECTED_DATE"
    private val datePickerTag = "DatePickerFragment"
    private val args: AddNewGoalDialogFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddNewGoalBinding.inflate(inflater, container, false)
        initBinding()
        initObservers()
        updateAddGoalState(AddGoalState.SET_AMOUNT)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.category = args.categoryName
    }

    private fun initObservers() {
        viewModel.navigateToNextStep.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { state ->
                updateAddGoalState(state)
            }
        })
        viewModel.choiceIntervalButton.observe(viewLifecycleOwner, {
            handleIntervalButtons(it)
        })
        viewModel.openCalendarPickerDialog.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let {
                openDatePicker()
            }
        })
    }

    private fun openDatePicker() {
        val datePickerFragment = DatePickerFragment()
        val supportFragmentManager = requireActivity().supportFragmentManager

        supportFragmentManager.setFragmentResultListener(
            requestKey,
            viewLifecycleOwner
        ) { resultKey, bundle ->
            if (resultKey == requestKey) {
                val date = bundle.getString(selectedDate)
                binding.addGoalLayoutDate.dialogAddGoalOpenCalendar.text = date
                viewModel.goalDate = date.toString()
            }
        }
        //show
        datePickerFragment.show(supportFragmentManager, datePickerTag)
    }

    private fun initBinding() {
        binding.apply {
            newGoalViewModel = viewModel
            categoryName = args.categoryName
        }
        handleIntervalButtons(1)
    }

    enum class AddGoalState {
        SET_AMOUNT,
        SET_INTERVAL,
        SET_DATE,
        SET_NAME,
        SET_AMOUNT_INTERVAL,
        SET_GOAL
    }

    private fun updateAddGoalState(state: AddGoalState) {
        binding.apply {
            when (state) {
                AddGoalState.SET_AMOUNT -> {
                    customProgressIndicator.updateProgressView(0)
                }
                AddGoalState.SET_INTERVAL -> {
                    viewModel.goalAmount =
                        addGoalLayoutAmount.addGoalDialogInputAmount.text.toString()
                    customProgressIndicator.updateProgressView(1)
                }
                AddGoalState.SET_DATE -> {
                    customProgressIndicator.updateProgressView(2)
                }
                AddGoalState.SET_NAME -> {
                    customProgressIndicator.updateProgressView(3)
                }
                AddGoalState.SET_AMOUNT_INTERVAL -> {
                    viewModel.goalName =
                        addGoalLayoutName.addGoalDialogInputName.text.toString()
                    customProgressIndicator.updateProgressView(4)
                }
                AddGoalState.SET_GOAL -> {
                    viewModel.apply {
                        goalIntervalAmount = addGoalLayoutAmountInterval.addGoalDialogInputAmountInterval.text.toString()
                        saveGoal()
                    }
                    this@AddNewGoalDialogFragment.dismiss()
                }
            }
            uiState = state
        }
    }

    private fun handleIntervalButtons(choice: Int) {
        binding.intervalChoice = choice
        viewModel.intervalChoice = choice.toString()
    }

    override fun getTheme(): Int {
        return R.style.AppBottomSheetDialogTheme
    }
}