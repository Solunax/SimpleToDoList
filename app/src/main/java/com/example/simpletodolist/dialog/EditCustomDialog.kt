package com.example.simpletodolist.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.simpletodolist.databinding.EditDialogBinding

class EditCustomDialog(context : Context, mInterface:EditCustomInterface, var hour : Int, var minute : Int) : Dialog(context), TimePickerInterface{
    private var customDialogInterface : EditCustomInterface = mInterface
    private lateinit var binding : EditDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)

        val ok = binding.edit
        val cancel = binding.cancel
        val editText = binding.ToDoString
        val timeSet = binding.timeSet

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        timeSet.setOnClickListener {
            val timePickerDialog = TimePickerDialog(context, this)
            timePickerDialog.show()
        }

        ok.setOnClickListener {
            val text = editText.text.toString()
            if(text.isEmpty()){
                Toast.makeText(context, "내용이 비어있습니다.", Toast.LENGTH_SHORT).show()
            }else{
                customDialogInterface.onClicked(text, hour, minute)
                dismiss()
            }
        }

        cancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onPositive(hour: Int, minute: Int) {
        this.hour = hour
        this.minute = minute
    }
}