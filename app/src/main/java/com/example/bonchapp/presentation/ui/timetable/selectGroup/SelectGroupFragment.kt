package com.example.bonchapp.presentation.ui.timetable.selectGroup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bonchapp.R
import com.example.bonchapp.presentation.App
import com.example.bonchapp.presentation.presenter.timetable.ITimetableGroupPresenter
import javax.inject.Inject

class SelectGroupFragment() : Fragment(),
    ITimetableGroupView {

    lateinit var groupsListAdapter: SelectGroupAdapter
    lateinit var arrSubjects: List<ArrayList<String>>
    lateinit var root: View

    @Inject
    lateinit var presenter: ITimetableGroupPresenter

    init {
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_select_group, container, false)
        presenter.attachView(this)

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadList()

        initSearchField(root)
        initBackButton(root)
        initRecyclerView(root)

    }

    private fun initRecyclerView(root: View) {
        groupsListAdapter =
            SelectGroupAdapter(
                root.context
            )
        val recyclerView = root.findViewById<RecyclerView>(R.id.rv_selectGroup)
        recyclerView.layoutManager = LinearLayoutManager(root.context)
        recyclerView.adapter = groupsListAdapter
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

    override fun setList(list: ArrayList<ArrayList<String>>){
        arrSubjects = list
        groupsListAdapter.setGroups(arrSubjects)
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

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val context: Context = requireContext().applicationContext
        val imm =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(root.windowToken, 0)
    }
}
