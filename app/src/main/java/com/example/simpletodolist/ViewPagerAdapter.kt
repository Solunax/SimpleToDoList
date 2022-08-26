package com.example.simpletodolist

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.simpletodolist.fragment.CalendarFragment
import com.example.simpletodolist.fragment.CheckedToDoFragment
import com.example.simpletodolist.fragment.ToDoFragment

class ViewPagerAdapter(fragmentManager:FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ToDoFragment()
            1 -> CalendarFragment()
            2 -> CheckedToDoFragment()
            else -> ToDoFragment()
        }
    }
}