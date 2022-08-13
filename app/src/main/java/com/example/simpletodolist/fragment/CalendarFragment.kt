package com.example.simpletodolist.fragment

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
import com.example.simpletodolist.databinding.CalendarFragmentBinding
import com.example.simpletodolist.dialog.AddCustomDialog
import com.example.simpletodolist.dialog.AddCustomInterface
import com.example.simpletodolist.recycler.ToDoRecyclerAdapter
import com.example.simpletodolist.room.ToDo
import java.text.SimpleDateFormat
import java.util.*

class CalendarFragment : Fragment(), AddCustomInterface {
    private var binding : CalendarFragmentBinding? = null
    private val viewModel : ViewModel by viewModels()
    private val recyclerAdapter : ToDoRecyclerAdapter by lazy { ToDoRecyclerAdapter(viewModel) }

    private var year = 0
    private var month = 0
    private var date = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CalendarFragmentBinding.inflate(inflater, container, false)

        val calendar = binding!!.calendarView
        val recyclerView = binding!!.calendarRecycler
        val floatingButton = binding!!.dateTodoAdd
        recyclerAdapter.setHasStableIds(true)
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        recyclerView.adapter = recyclerAdapter

        calendar.setOnDateChangeListener { _, year, month, date ->
            this.year = year
            this.month = month + 1
            this.date = date

            val dateString = "$year/${month + 1}/$date"
            Log.d("CA", dateString)
            viewModel.getDateToDo(dateString)
        }

        viewModel.readData.observe(viewLifecycleOwner) {
            Log.d("DEB", "READ OBSERVE")
            viewModel.getDateToDo("$year/$month/$date")
        }

        viewModel.dateToDoData.observe(viewLifecycleOwner) {
            Log.d("DEB", "DATE OBSERVE")
            recyclerAdapter.setData(it)
        }

        floatingButton.setOnClickListener {
            if(year == 0)
                Toast.makeText(activity, "날짜를 선택하세요", Toast.LENGTH_SHORT).show()
            else{
                val addDialog = AddCustomDialog(activity!!, this)
                addDialog.show()
            }
        }

        return binding!!.root
    }

    override fun onOkClicked(text: String) {
        val date = "$year/$month/$date"
        val toDo = ToDo(0, text, date, false)

        viewModel.addToDo(toDo)
        Toast.makeText(activity, "추가되었습니다", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}