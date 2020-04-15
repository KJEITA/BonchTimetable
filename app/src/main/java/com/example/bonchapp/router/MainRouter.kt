package com.example.bonchapp.router

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bonchapp.R
import com.example.bonchapp.ui.event.FullEventFragment
import com.example.bonchapp.util.Keys

class MainRouter {

    fun navigateToFullEvent(fragment: Fragment, eventId: Int) {
        val bundle = Bundle().apply { putInt(Keys.FULL_EVENT_ID, eventId) }
        fragment.activity!!.supportFragmentManager.beginTransaction().add(FullEventFragment().apply { arguments = bundle }, null)
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