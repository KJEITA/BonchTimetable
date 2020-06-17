package com.example.bonchapp.presentation.ui.timetable.main

import com.example.bonchapp.pojo.SubjectDTO
import org.joda.time.DateTime

interface ITimetableView {
    fun setTimetable(timetable: ArrayList<SubjectDTO>, date: DateTime)
    fun closeFragment()
    fun showSelectGroupFragment()
    fun showSelectTutorFragment()
    fun showName(s:String)
}