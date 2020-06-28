package com.example.bonchapp.presentation.ui.timetable.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bonchapp.R
import com.example.bonchapp.presentation.App
import com.example.bonchapp.presentation.presenter.timetable.ITimetablePresenter
import kotlinx.android.synthetic.main.fragment_timetable_viewpager.*
import org.joda.time.DateTime
import javax.inject.Inject

class TimetableViewPagerFragment() : Fragment() {

    @Inject
    lateinit var presenter: ITimetablePresenter

    lateinit var myDate: DateTime

    var myPos = 0

    lateinit var dayTimeTableAdapter: DayTimeTableAdapter

    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_timetable_viewpager, container, false)

        presenter.addPager(this)

        return root
    }

    init {
        App.appComponent.inject(this)
    }

    fun newInstance(num: Int): TimetableViewPagerFragment {
        val args = Bundle()
        args.putInt("num", num)
        val f = TimetableViewPagerFragment()
        f.arguments = args
        return f
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        loadTimetable()
    }

//    fun updateTimetable(timetable: ArrayList<SubjectDTO>, date: DateTime) {
//        dayTimeTableAdapter.setList(timetable, date)
//    }

    private fun setDate(addWeeks: Int): DateTime {
        var date = DateTime()
        date = date.plusWeeks(addWeeks)
        return date
    }

    fun loadTimetable() {
        presenter.loadTimetable(this, myDate,
            callback = {
                //fragment.updateTimetable(it!!, date)
                dayTimeTableAdapter.setList(it!!, myDate)
            }
        )
    }

    private fun initRecyclerView() {
        dayTimeTableAdapter =
            DayTimeTableAdapter(
                root.context,
                this
            )
        val recyclerViewDay = root.findViewById<RecyclerView>(R.id.timeTable_recyclerView)
        recyclerViewDay.layoutManager = LinearLayoutManager(root.context)
        recyclerViewDay.adapter = dayTimeTableAdapter
        recyclerViewDay.smoothScrollToPosition(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val i = requireArguments().getInt("num")
        myDate = setDate(i)
        myPos = i + 500
    }

    override fun onDestroyView() {
        presenter.deletePager(this)
        super.onDestroyView()
    }

    fun scrollRV(posRV:Int){
        timeTable_recyclerView.smoothScrollToPosition(posRV)
    }
}