package com.example.simpletodolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpletodolist.ViewModel
import com.example.simpletodolist.databinding.CheckedTodoFragementBinding
import com.example.simpletodolist.recycler.ToDoRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckedToDoFragment : Fragment() {
    private var binding : CheckedTodoFragementBinding? = null
    private val viewModel : ViewModel by viewModels()
    private val recyclerAdapter : ToDoRecyclerAdapter by lazy { ToDoRecyclerAdapter(viewModel)}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CheckedTodoFragementBinding.inflate(inflater, container, false)

        val recyclerView = binding!!.recyclerView
        recyclerAdapter.setHasStableIds(true)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = recyclerAdapter

        viewModel.checkedData.observe(viewLifecycleOwner){
            recyclerAdapter.setData(it)
        }

        return binding!!.root
    }
}