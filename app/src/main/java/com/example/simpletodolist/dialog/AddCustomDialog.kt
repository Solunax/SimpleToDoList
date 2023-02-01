package com.example.simpletodolist.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.simpletodolist.databinding.AddDialogBinding

class AddCustomDialog(context: Context, mInterface : AddCustomInterface) : Dialog(context), TimePickerInterface {
    private var customDialogInterface : AddCustomInterface = mInterface
    private lateinit var binding : AddDialogBinding
    var hour : Int? = null
    var minute : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)

        val ok = binding.add
        val cancel = binding.cancel
        val timeSet = binding.timeSet
        val inputText = binding.ToDoString


        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        timeSet.setOnClickListener {
            val timePickDialog = TimePickerDialog(context, this)
            timePickDialog.show()
        }

        ok.setOnClickListener {
            val text = inputText.text.toString()
            if(text.isEmpty()){
                Toast.makeText(context, "내용이 비어있습니다.", Toast.LENGTH_SHORT).show()
            }else if(hour == null || minute == null){
                Toast.makeText(context, "시간을 설정하세요.", Toast.LENGTH_SHORT).show()
            }else{
                customDialogInterface.onOkClicked(text, hour!!, minute!!)
                dismiss()
            }
        }

        cancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onPositive(h: Int, m: Int) {
        hour = h
        minute = m
    }
}