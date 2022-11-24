package com.example.zd7_sizov

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_DATE = "time"
class TimePickerFragment : DialogFragment() {
    interface Callbacks {
        fun onTimeSelected(time: Date)
    }
 @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val timeListener =TimePickerDialog.OnTimeSetListener { _: TimePicker, hour: Int, minute: Int ->
                val resultTime: Date = GregorianCalendar(0, 0, 0, hour, minute).time
                targetFragment?.let { fragment ->
                    (fragment as Callbacks).onTimeSelected(resultTime)
                }
            }
        val time = arguments?.getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = time
        val initialHour = calendar.get(Calendar.HOUR)
        val initialMinute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(requireContext(), timeListener, initialHour, initialMinute, true)
    }

    companion object {
        fun newInstance(time: Date): TimePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, time)
            }
            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }
}