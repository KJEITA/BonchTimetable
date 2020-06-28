package com.example.bonchapp.presentation.ui.timetable.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bonchapp.R
import com.example.bonchapp.presentation.ui.timetable.calendar.manager.CalendarManager
import org.joda.time.LocalDate

class CalendarViewPagerAdapter : RecyclerView.Adapter<PagerVH>() {
    var b = false

    var arr = arrayListOf<CalendarManager>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_collapse_calendar_view, parent, false))

    override fun getItemCount(): Int = 1000

    override fun onBindViewHolder(holder: PagerVH, position: Int){
        var cm = if(b) CalendarManager(
            LocalDate.now().plusWeeks(position),
            CalendarManager.State.WEEK,
            LocalDate.now().minusYears(5),
            LocalDate.now().plusYears(5)
        )else
            CalendarManager(
                LocalDate.now().plusWeeks(position),
                CalendarManager.State.MONTH,
                LocalDate.now().minusYears(5),
                LocalDate.now().plusYears(5)
            )
        arr.add(cm)
        holder.bind(position,cm)
    }
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView){
    val calendarView = itemView.findViewById<CollapseCalendarView>(R.id.calendar)

    fun bind(position: Int, cm:CalendarManager){

        //cm.pos = position
        calendarView.init(cm)
    }
}