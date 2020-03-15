package com.example.bonchapp.ui.event.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bonchapp.R
import com.example.bonchapp.presenter.EventPresenter
import kotlinx.android.synthetic.main.fragment_main_event.*


class MainEventFragment : Fragment() {

    val presenter = EventPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onViewCreate()
        initRecycler()
        initSearch()
        initFab()
    }

    private fun initRecycler() {
        eventRecyclerView.apply {
            adapter =
                EventAdapter(this@MainEventFragment)
            layoutManager = LinearLayoutManager(context)
        }
        presenter.testData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                (eventRecyclerView.adapter as EventAdapter).setData(
                    presenter.testData.value ?: arrayListOf()
                )
                eventRecyclerView.adapter?.notifyDataSetChanged()
            })
    }

    private fun initSearch() {
        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.onSearchQueryUpdate(eventRecyclerView, newText)
                return true
            }
        })
    }

    private fun initFab(){
        addEventFab.setOnClickListener{
            presenter.onFabClick()

        }
    }
}
