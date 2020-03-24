package com.example.bonchapp.ui.timetable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bonchapp.MainContract
import com.example.bonchapp.R
import com.example.bonchapp.coordinator.MainCoordinator
import com.example.bonchapp.pojo.SubjectDTO
import com.example.bonchapp.presenter.PresenterTimeTable
import com.example.bonchapp.ui.adapters.SelectGroupAdapter
import com.example.bonchapp.ui.adapters.TimetableAdapter
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import kotlinx.android.synthetic.main.fragment_timetable.view.*

lateinit var mPresenter: PresenterTimeTable

class TimetableFragment : Fragment(), MainContract.View {

    lateinit var timeTableAdapter: TimetableAdapter
    lateinit var selectGroupFragment: SelectGroupFragment

    lateinit var root:View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_timetable, container, false)

        initRecyclerView(root)
        initCalender(root)
        initSwitchTimetable(root)

        mPresenter = PresenterTimeTable(this, this)

        return root
    }

    override fun showTimetable(timetable: List<SubjectDTO>) {
        timeTableAdapter.setSubjects(timetable)
    }

    override fun showGroupsList(list: List<String>) {
        selectGroupFragment.groupsListAdapter.setGroups(list)
        selectGroupFragment.arrSubjects = list

    }

    override fun showSwitchProfessorFragment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSwitchGroupFragment() {
        selectGroupFragment = SelectGroupFragment()
        activity!!.supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment ,
            selectGroupFragment, null).addToBackStack("HZ").commit()

        mPresenter.updateGroupsList()

    }

    private fun initRecyclerView(view: View) {
        timeTableAdapter = TimetableAdapter(view.context, this)
        val recyclerViewDay = view.findViewById<RecyclerView>(R.id.timeTable_recyclerView)
        recyclerViewDay.layoutManager = LinearLayoutManager(view.context)
        recyclerViewDay.adapter = timeTableAdapter
    }

    private fun initCalender(view: View) {
        val calendar = view.findViewById<MaterialCalendarView>(R.id.calendar)
        calendar.setTopbarVisible(false)

        val textMonth = view.findViewById<TextView>(R.id.month)

        calendar.setOnDateChangedListener(OnDateSelectedListener { widget, date, selected ->
            val s: String = "${date.year}-${date.month}-${date.day}"
            mPresenter.updateTimetable(s)
        })

        textMonth.text = resources.getStringArray(R.array.Months)[calendar.currentDate.month-1]

        calendar.setOnMonthChangedListener { widget, date ->
            textMonth.text = resources.getStringArray(R.array.Months)[calendar.currentDate.month-1]
        }

    }

    private fun initSwitchTimetable(view: View) {
        val spinner = view.findViewById<TextView>(R.id.spinner)
        val groupName = view.findViewById<TextView>(R.id.groupName)
        val itemSwitchTimeTable = view.findViewById<View>(R.id.item_swithTimeTable)

        val btn_select_group = view.findViewById<View>(R.id.btn_select_group)
        val btn_select_professor = view.findViewById<View>(R.id.btn_select_tutor)


        spinner.setOnClickListener {
            itemSwitchTimeTable.setVisibility(View.VISIBLE)
        }

        groupName.setOnClickListener {
            itemSwitchTimeTable.setVisibility(View.INVISIBLE)
        }

        btn_select_group.setOnClickListener {
            mPresenter.switchTimetable("group")
            itemSwitchTimeTable.setVisibility(View.INVISIBLE)
        }

        btn_select_professor.setOnClickListener {
            //mPresenter.switchTimetable("tutor")
            //itemSwitchTimeTable.setVisibility(View.INVISIBLE)
        }
    }

    override fun setNameGroup(name:String){
        val groupName = root.findViewById<TextView>(R.id.groupName)
        groupName.text = name
    }
}
