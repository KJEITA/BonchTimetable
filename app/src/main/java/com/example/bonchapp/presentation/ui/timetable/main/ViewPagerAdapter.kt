package com.example.bonchapp.presentation.ui.timetable.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.PagerAdapter.POSITION_NONE
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    var list = arrayListOf<TimetableViewPagerFragment>()

    fun updateList(list: ArrayList<TimetableViewPagerFragment>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = 1000

    override fun createFragment(position: Int): Fragment =
        TimetableViewPagerFragment(position - 500)
}