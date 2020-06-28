package com.example.bonchapp.router

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.bonchapp.R
import com.example.bonchapp.presentation.ui.event.FullEventFragment
import com.example.bonchapp.presentation.ui.event.my.ApplicationEventFragment
import com.example.bonchapp.presentation.ui.timetable.selectType.SelectTypeTimetableFragment
import kotlinx.android.synthetic.main.activity_main.*

object MainCoordinator {

    fun navigateToFullEvent(fragment: Fragment, eventId: Int) {
        val bundle = Bundle().apply { putInt(Constants.FULL_EVENT_ID, eventId) }
        fragment.requireActivity().supportFragmentManager.beginTransaction().add(FullEventFragment.fragment.apply { arguments = bundle }, null)
            .commit()
    }

    fun navigateToCalendarEvent(fragment: Fragment) {
        fragment.requireActivity().supportFragmentManager.beginTransaction().add(R.id.myEventContainer, ApplicationEventFragment(), null)
            .commit()
    }

    fun navigateToOrgInfo(fragment: Fragment){
        val fr = FullEventFragment.fragment
        fragment.requireActivity().findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_orgInfo)
        fragment.requireActivity().supportFragmentManager.beginTransaction().remove(fr).commitNow()
        Log.d("Coordinator", "Delete")

    }


    fun navigateToSettings(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.action_navigation_profile_to_SettingsFragment)
    }

    //Function for open navigator fragment with showing cabinet
    //Accepts cabinet number as argument. Example: 552/4; 522/4/1; 122 (for college)

    fun showCabinetInNavigator(context: Fragment, cabinet: String) {
        val bundle = Bundle()
        bundle.putString("cabinet", cabinet)
        context.findNavController().navigate(R.id.navigation_navgut, bundle)
    }


    /*fun showSwitchGroupFragment() {
        selectGroupFragment = SelectGroupFragment()
        activity!!.supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment ,
            selectGroupFragment, null).addToBackStack("HZ").commit()

        mPresenter.updateGroupsList()
    }*/

    fun navigateToSelectGroup(context: Fragment) {
        context.findNavController().navigate(R.id.action_timetableFragment_to_selectGroupFragment)
    }

    fun navigateToSelectTypeTimetable(context: Fragment, b: Boolean){
        context.requireActivity().supportFragmentManager.beginTransaction().add(

            SelectTypeTimetableFragment(), null
        ).addToBackStack(null).commit()
    }
    fun navigateToTimetable(context: Fragment) {
        context.requireActivity().nav_view.visibility = View.VISIBLE
        context.findNavController().navigate(R.id.action_navigation_authorization_to_timetableFragment)
    }
    fun navigateToTimetable(context: Activity) {
        context.nav_view.visibility = View.VISIBLE
        context.findNavController(R.id.nav_host_fragment).navigate(R.id.action_navigation_authorization_to_timetableFragment)
    }
}