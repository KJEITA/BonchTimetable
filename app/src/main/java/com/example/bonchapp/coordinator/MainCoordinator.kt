package com.example.bonchapp.coordinator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bonchapp.R
import com.example.bonchapp.ui.event.FullEventFragment

object MainCoordinator {

    fun navigateToFullEvent(fragment: Fragment, eventId: Int) {
        fragment.arguments = Bundle().apply { putInt(Keys.FULL_EVENT_ID, eventId) }
        fragment.activity!!.supportFragmentManager.beginTransaction().add(FullEventFragment(), null)
            .commit()
    }

    fun navigateToAddEvent(fragment: Fragment) {
        fragment.findNavController().navigate(R.id.action_navigation_event_to_addEvent)
    }

    //Function for open navigator fragment with showing cabinet
    //Accepts cabinet number as argument. Example: 552/4; 522/4/1; 122 (for college)
    fun showCabinetInNavigator(context: Fragment, cabinet: String) {
        val bundle = Bundle()
        bundle.putString("cabinet", cabinet)
        context.findNavController().navigate(R.id.navigation_navgut, bundle)
    }
}