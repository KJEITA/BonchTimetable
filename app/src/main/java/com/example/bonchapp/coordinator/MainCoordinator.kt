package com.example.bonchapp.coordinator

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bonchapp.R

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
}