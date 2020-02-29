package com.example.bonchapp.ui.timetable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bonchapp.MainContract
import com.example.bonchapp.R
import com.example.bonchapp.ui.adapters.SelectGroupAdapter

class SelectGroupFragment() : Fragment() {

    lateinit var groupsListAdapter:SelectGroupAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_switch_group, container, false)

        initRecyclerView(root)

        return root
    }

    fun initRecyclerView(root:View){
        groupsListAdapter = SelectGroupAdapter(root.context)
        val recyclerViewDay = root.findViewById<RecyclerView>(R.id.rv_selectGroup)
        recyclerViewDay.layoutManager = LinearLayoutManager(root.context)
        recyclerViewDay.adapter = groupsListAdapter
    }

}
