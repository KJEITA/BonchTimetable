package com.example.bonchapp.ui.event.main

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.example.bonchapp.ui.event.favorite.FavoriteEventFragment
import com.example.bonchapp.ui.event.my.MyEventFragment

class EventPagerAdapter(val fragment: Fragment): FragmentPagerAdapter(fragment.fragmentManager!!) {
    private val tabs = arrayOf("Мероприятия", "Избранное", "Мои события")
    override fun getItem(position: Int): Fragment {
        return when(position){
            0-> {
                Log.d("Lol", "start 0")
                MainEventFragment()
            }
            1-> {
                Log.d("Lol", "start 1")
                FavoriteEventFragment()
            }
            2 -> {
                Log.d("Lol", "start 2")
                MyEventFragment()
            }
            else -> {
                Log.d("Lol", "start else")
                MainEventFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position]
    }

    override fun getCount(): Int {
        return tabs.size
    }
}