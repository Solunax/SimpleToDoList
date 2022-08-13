package com.example.simpletodolist.fragment

import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpletodolist.ViewModel
import com.example.simpletodolist.databinding.TodoFragmentBinding
import com.example.simpletodolist.dialog.AddCustomDialog
import com.example.simpletodolist.dialog.AddCustomInterface
import com.example.simpletodolist.recycler.ToDoRecyclerAdapter
import com.example.simpletodolist.room.ToDo

class ToDoFragment : Fragment(), AddCustomInterface {
    private var binding : TodoFragmentBinding? = null
    private val viewModel : ViewModel by viewModels()
    private val recyclerAdapter : ToDoRecyclerAdapter by lazy { ToDoRecyclerAdapter(viewModel) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TodoFragmentBinding.inflate(inflater, container, false)

        val floatingButton = binding!!.todoAdd
        val recyclerView = binding!!.recyclerView
        recyclerAdapter.setHasStableIds(true)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = recyclerAdapter

        viewModel.readData.observe(viewLifecycleOwner){
            recyclerAdapter.setData(it)
        }

        floatingButton.setOnClickListener {
            val addDialog = AddCustomDialog(activity!!, this)
            addDialog.show()
        }

        return binding!!.root
    }

    override fun onOkClicked(text: String) {
        val calendar = Calendar.getInstance()
        val dateString = "${calendar.get(Calendar.YEAR)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.DATE)}"

        val toDo = ToDo(0, text, dateString, false)
        viewModel.addToDo(toDo)
        Toast.makeText(activity, "추가되었습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}