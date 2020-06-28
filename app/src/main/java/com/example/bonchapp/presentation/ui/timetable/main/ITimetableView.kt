package com.example.bonchapp.presentation.ui.timetable.main

import com.example.bonchapp.pojo.SubjectDTO
import com.example.bonchapp.presentation.ui.timetable.calendar.manager.CalendarManager
import org.joda.time.DateTime

interface ITimetableView {
    fun hideKeyboard()
    fun showName(s:String)
    fun setDatee(dateTime: DateTime)
}