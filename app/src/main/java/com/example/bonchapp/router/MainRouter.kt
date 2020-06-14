package com.example.bonchapp.router

import android.os.Bundle
import androidx.navigation.NavController
import com.example.bonchapp.R
import com.example.bonchapp.domain.entities.Message
import javax.inject.Singleton

class MainRouter {
    lateinit var navController: NavController

    fun navigateToFullEvent(eventId: Int) {
        val bundle = Bundle()
        bundle.putInt(Constants.FULL_EVENT_ID, eventId)
        navController.navigate(R.id.action_navigation_event_to_fullEventFragment)
       // MainCoordinator.navigateToFullEvent( bundle)
    }


    fun navigateToCabinet(cabinet: String) {
        val bundle = Bundle()
        bundle.putString(Constants.CABINET_BUNDLE_ID, cabinet)
        navController.navigate(R.id.navigation_navgut, bundle)
    }

    fun navigateToTimetable() {
        //context.requireActivity().nav_view.visibility = View.VISIBLE
        navController.navigate(R.id.action_navigation_authorization_to_navigation_timetable)
    }

    fun navigateToSendMessage() {
        navController.navigate(R.id.action_navigation_storage_to_sendMessageFragment)
    }

    fun navigateToFullMessage(message: Message){
        val bundle = Bundle()
        bundle.putSerializable(Constants.FULL_MESSAGE, message)
        navController.navigate(R.id.action_navigation_storage_to_fullMassageFragment, bundle)
    }

}