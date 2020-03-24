package com.example.bonchapp.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.bonchapp.coordinator.MainCoordinator
import com.example.bonchapp.model.repository.TestRep
import com.example.bonchapp.ui.event.main.EventAdapter
import com.example.bonchapp.ui.event.main.MainEventFragment

class EventPresenter(val context: MainEventFragment) {

    private val _testData =
        MutableLiveData<ArrayList<String>>().apply { value = arrayListOf("Load!") }

    var testData: LiveData<ArrayList<String>> = _testData

    fun onItemClick(pos: Int) {
        MainCoordinator.navigateToFullEvent(context, pos)
    }

    fun onViewCreate() {
        TestRep.getGroups(_testData)
    }

    fun onSearchQueryUpdate(
        eventRecyclerView: RecyclerView,
        query: String?
    ) {
        (eventRecyclerView.adapter as EventAdapter).filter.filter(query)
    }

    fun onFabClick() {
        MainCoordinator.navigateToAddEvent(context)
    }

}