package com.example.simpletodolist.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.simpletodolist.databinding.TimePickerDialogBinding
import kotlin.math.min

class TimePickerDialog(context : Context, mInterface : TimePickerInterface) : Dialog(context) {
    private var customInterface : TimePickerInterface = mInterface
    private lateinit var binding : TimePickerDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TimePickerDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)

        val timePicker = binding.timePicker
        val ok = binding.yes
        val no = binding.no

        var hour : Int? = null
        var minute : Int? = null

        timePicker.setOnTimeChangedListener { _, h, m ->
            hour = h
            minute = m
        }

        ok.setOnClickListener {
            if(hour != null && minute != null){
                customInterface.onPositive(hour!!, minute!!)
                dismiss()
            }else
                Toast.makeText(context, "시간을 설정하세요.", Toast.LENGTH_SHORT).show()
        }

        no.setOnClickListener {
            dismiss()
        }
    }

}