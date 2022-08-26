package com.example.simpletodolist.recycler

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletodolist.ViewModel
import com.example.simpletodolist.databinding.RecyclerItemBinding
import com.example.simpletodolist.dialog.EditCustomDialog
import com.example.simpletodolist.dialog.EditCustomInterface
import com.example.simpletodolist.room.ToDo

class ToDoRecyclerAdapter(private val toDoViewModel : ViewModel) : RecyclerView.Adapter<ToDoRecyclerAdapter.TodoViewHolder>(){
    private var doList = emptyList<ToDo>()

    class TodoViewHolder(private val binding : RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root), EditCustomInterface{
        private lateinit var toDoOrigin : ToDo
        private lateinit var viewModel : ViewModel

        fun bind(toDo : ToDo, viewModel : ViewModel){
            binding.todo = toDo
            toDoOrigin = toDo
            this.viewModel = viewModel

            binding.checkbox.setOnCheckedChangeListener { _, checked ->
                if(binding.checkbox.isPressed){
                    val updateToDo = ToDo(toDoOrigin.index, toDoOrigin.name, toDoOrigin.year, toDoOrigin.month, toDoOrigin.date, checked)
                    viewModel.editToDo(updateToDo)
                }
            }

            binding.doEdit.setOnClickListener{
                val editDialog = EditCustomDialog(binding.doEdit.context, this)
                editDialog.show()
            }

            binding.doDelete.setOnClickListener {
                viewModel.deleteToDo(toDo)
                Toast.makeText(binding.root.context, "삭제 했습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onClicked(text: String) {
            val updateToDo = ToDo(toDoOrigin.index, text, toDoOrigin.year, toDoOrigin.month, toDoOrigin.date, toDoOrigin.check)
            viewModel.editToDo(updateToDo)
            Toast.makeText(binding.root.context, "수정했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(doList[position], toDoViewModel)
    }

    override fun getItemCount(): Int {
        return doList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(toDo : List<ToDo>){
        doList = toDo
        notifyDataSetChanged()
        Log.d("DEB Recycle", "$doList")
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}