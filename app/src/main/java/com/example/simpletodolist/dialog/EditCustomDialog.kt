package com.example.simpletodolist.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.simpletodolist.databinding.EditDialogBinding

class EditCustomDialog(context : Context, mInterface:EditCustomInterface) : Dialog(context){
    private var customDialogInterface : EditCustomInterface = mInterface
    private lateinit var binding : EditDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)

        val ok = binding.edit
        val cancel = binding.cancel
        val editText = binding.ToDoString

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        ok.setOnClickListener {
            val text = editText.text.toString()
            if(text.isEmpty()){
                Toast.makeText(context, "내용이 비어있습니다.", Toast.LENGTH_SHORT).show()
            }else{
                customDialogInterface.onClicked(text)
                dismiss()
            }
        }

        cancel.setOnClickListener {
            dismiss()
        }
    }
}