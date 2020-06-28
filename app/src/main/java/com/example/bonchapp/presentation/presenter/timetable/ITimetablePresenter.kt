package com.example.bonchapp.presentation.presenter.timetable

import com.example.bonchapp.pojo.SubjectDTO
import com.example.bonchapp.presentation.ui.timetable.calendar.manager.CalendarManager
import com.example.bonchapp.presentation.ui.timetable.main.ITimetableView
import com.example.bonchapp.presentation.ui.timetable.main.TimetableViewPagerFragment
import org.joda.time.DateTime

interface ITimetablePresenter {
    fun addPager(p:TimetableViewPagerFragment)
    fun deletePager(p:TimetableViewPagerFragment)
    fun getAttachView(): ITimetableView
    fun attachView(viewMain: ITimetableView)
    fun loadTimetable(fragment: TimetableViewPagerFragment, date: DateTime, callback: (data: ArrayList<SubjectDTO>?) -> Unit)
    fun switchType(type: String)
    fun switchName(name:String)
    fun closeFragment()
    fun returnOriginal()
    fun navigateToCabinet(cabinet: String)
    fun navigateToSelectType()
    fun firstLoad()
    fun reloadPagers()
    fun scrollDayRV(pos:Int, posRV:Int)
    }