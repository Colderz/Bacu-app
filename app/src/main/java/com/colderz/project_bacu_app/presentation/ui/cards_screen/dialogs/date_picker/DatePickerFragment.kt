package com.colderz.project_bacu_app.presentation.ui.cards_screen.dialogs.date_picker

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import java.text.SimpleDateFormat
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private var calendar = Calendar.getInstance()

    private val requestKey = "REQUEST_KEY"
    private val selectedDate = "SELECTED_DATE"

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //default date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        //return new DatePickerDialog instance
        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        val selectedDate = SimpleDateFormat("dd-MM-yyyy", Locale.GERMANY).format(calendar.time)

        val selectedDataBundle = Bundle()
        selectedDataBundle.putString(this.selectedDate, selectedDate)

        setFragmentResult(requestKey, selectedDataBundle)
    }
}