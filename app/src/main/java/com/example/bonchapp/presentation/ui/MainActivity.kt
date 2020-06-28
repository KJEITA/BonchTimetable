package com.example.bonchapp.presentation.ui

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.preference.PreferenceManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.bonchapp.R
import com.example.bonchapp.router.MainRouter
import com.example.bonchapp.router.User
import com.example.bonchapp.domain.entities.Token
import com.example.bonchapp.presentation.App
import com.example.bonchapp.router.Constants
import com.example.bonchapp.router.MainCoordinator
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    lateinit var preferences: SharedPreferences
    lateinit var locale: Locale
    lateinit var lang: String


    @Inject
    lateinit var router: MainRouter

    init {
        App.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navController = findNavController(R.id.nav_host_fragment)
        router.navController = navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_event,
            R.id.navigation_navgut,
            R.id.navigation_timetable,
            R.id.navigation_storage,
            R.id.navigation_profile
        ))
        navView.setupWithNavController(navController)

        updateConfiguration()



        val sharedPreferences = getSharedPreferences(Constants.APP_PREFERENCE, Context.MODE_PRIVATE)
        if(sharedPreferences.contains(Constants.TOKEN)) {
            User.addToken(Token(sharedPreferences.getString(Constants.TOKEN, "")!!))
            MainCoordinator.navigateToTimetable(this)
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, null)
    }

    fun updateConfiguration() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        lang = preferences.getString("lang", "ru")!!
        if (lang.equals("default")) {
            lang = resources.configuration.locale.country
        }
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, null)
    }

}
