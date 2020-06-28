package com.example.bonchapp.presentation.presenter.auth

import android.util.Log
import android.view.View
import com.example.bonchapp.domain.entities.Token
import com.example.bonchapp.domain.interactors.auth.IAuthInteractor
import com.example.bonchapp.presentation.ui.authorization.IAuthView
import com.example.bonchapp.router.Constants
import com.example.bonchapp.router.MainRouter
import com.example.bonchapp.router.User
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class AuthPresenter @Inject constructor(val interactor: IAuthInteractor, val router: MainRouter): IAuthPresenter {

    lateinit var view: IAuthView

    private fun signIn(email: String, pass: String) {

        if (email.isEmpty() or pass.isEmpty()) view.showError("Bad Input")
        else {
            interactor.login(User.create(email, pass)) { token: Token?, s: String? ->
                if (token != null) signInSuccess(token)
                else signInError(s)
            }
        }
//        else {
//            repository.logIn(User.create(email, pass)) {
//                User.addToken(it)
//                Log.d("Auth", "Token: ${it.value}")
//                //Toast.makeText(fragment.context, it.value, Toast.LENGTH_LONG).show()
//                saveTokenToPreference(it.value)
//                router.navigateToTimetable()
//            }
//        }
    }

    private fun signInSuccess(token: Token){
        User.addToken(token)
        Log.d("Auth", "Token: ${token.value}")
        saveTokenToPreference(token.value)
        router.navigateToTimetable()
    }

    private fun signInError(message: String?){
        val mess = message ?: "Непредвиденная ошибка"
        view.showError(mess)
    }

    private fun saveTokenToPreference(token: String){
        view.getSharedPref().edit().putString(Constants.TOKEN, token).apply()
    }

    override fun getAttachView(): IAuthView {
        return view
    }

    override fun attachView(view: IAuthView) {
        this.view = view
    }

    override fun onSignInClick(email: String, pass: String) {
        signIn(email, pass)
    }
}