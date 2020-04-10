package com.example.bonchapp.coordinator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bonchapp.R
import com.example.bonchapp.ui.timetable.SelectGroupFragment
import com.example.bonchapp.ui.timetable.SelectTypeTimetableFragment
import com.example.bonchapp.ui.timetable.mPresenter

object MainCoordinator {

    fun navigateToFullEvent(fragment: Fragment, eventId: Int) {
        val bundle = Bundle()
        bundle.putInt("eventId", eventId)
        fragment.findNavController()
            .navigate(R.id.action_navigation_event_to_fullEventFragment, bundle)
    }


    fun navigateToAddEvent(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.action_navigation_event_to_addEvent)
    }

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
        context.findNavController().navigate(R.id.action_navigation_timetable_to_selectGroupFragment)
    }

    fun navigateToSelectTypeTimetable(context: Fragment){
        context.activity!!.supportFragmentManager.beginTransaction().add(
            R.id.nav_host_fragment,
           SelectTypeTimetableFragment(), null
        ).addToBackStack(null).commit()
    }
}