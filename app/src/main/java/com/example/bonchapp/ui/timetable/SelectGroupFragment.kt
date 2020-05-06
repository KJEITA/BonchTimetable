package com.example.bonchapp.ui.timetable

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.SearchView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bonchapp.R
import com.example.bonchapp.model.pojo.GroupDTO
import com.example.bonchapp.ui.adapters.SelectGroupAdapter
import kotlinx.android.synthetic.main.fragment_timetable.*

class SelectGroupFragment() : Fragment() {

    lateinit var groupsListAdapter: SelectGroupAdapter
    lateinit var arrSubjects: List<ArrayList<String>>
    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_select_group, container, false)

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(root)
        initSearchField(root)
        initBackButton(root)

    }

    private fun initRecyclerView(root: View) {
        groupsListAdapter = SelectGroupAdapter(root.context)
        val recyclerView = root.findViewById<RecyclerView>(R.id.rv_selectGroup)
        recyclerView.layoutManager = LinearLayoutManager(root.context)
        recyclerView.adapter = groupsListAdapter

        //timeTable_recyclerView.apply {
          //  groupsListAdapter
        //}
    }

    private fun initSearchField(root: View) {
        val textSearch = root.findViewById<SearchView>(R.id.search_field)
        textSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                findInArray(newText.toString())
                return false
            }
        })
    }

    private fun initBackButton(root: View) {
        val btn = root.findViewById<ImageButton>(R.id.backButton)
        btn.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun findInArray(str: String) {

        if (str == "")
            groupsListAdapter.setGroups(arrSubjects)
        else {
            val arr: ArrayList<ArrayList<String>> = arrayListOf()

            arrSubjects.forEach {
                if (it[1].contains(str, true)) {
                    arr.add(it)
                }
            }

            groupsListAdapter.setGroups(arr)
        }
    }
}
