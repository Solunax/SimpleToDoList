package com.example.simpletodolist.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.simpletodolist.databinding.AddDialogBinding

class AddCustomDialog(context: Context, mInterface : AddCustomInterface) : Dialog(context) {
    private var customDialogInterface : AddCustomInterface = mInterface
    private lateinit var binding : AddDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)

        val ok = binding.add
        val cancel = binding.cancel
        val inputText = binding.ToDoString

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        ok.setOnClickListener {
            val text = inputText.text.toString()
            if(text.isEmpty()){
                Toast.makeText(context, "내용이 비어있습니다.", Toast.LENGTH_SHORT).show()
            }else{
                customDialogInterface.onOkClicked(text)
                dismiss()
            }
        }

        cancel.setOnClickListener {
            dismiss()
        }
    }
}