package com.example.bonchapp.presenter

import android.animation.ObjectAnimator
import android.view.View
import com.example.bonchapp.R
import com.example.bonchapp.ui.event.FullEventFragment
import com.google.android.material.snackbar.Snackbar

class FullEventPresenter(val context: FullEventFragment) {

    fun onRegButtonPress(view: View) {
        //registration()
        startRegButtonAnimation(view)
        val snack = Snackbar.make(view.parent as View, R.string.reg_success, Snackbar.LENGTH_SHORT)
        snack.show()
    }

    private fun startRegButtonAnimation(view: View){
        ObjectAnimator.ofFloat(view, "translationY", -125f).apply {
            duration = 200
            start()
        }
        ObjectAnimator.ofFloat(view, "translationY", 0f).apply {
            startDelay = 3000
            duration = 300
            start()
        }
    }
    private fun registration(){
        TODO("request to server")
    }

    fun onCreate() {
        //setDataToView()
    }

    private fun setDataToView() {
        TODO("request data and set them to view")
    }
}