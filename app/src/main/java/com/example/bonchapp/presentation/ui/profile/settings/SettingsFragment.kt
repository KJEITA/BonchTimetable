package com.example.bonchapp.presentation.ui.profile.settings

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bonchapp.presentation.ui.MainActivity
import com.example.bonchapp.R
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*

class SettingsFragment : Fragment() {

lateinit var root:View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_settings, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exit.setOnClickListener {
            val dialog = LogoutDialogFragment()
            fragmentManager?.let { it1 -> dialog.show(it1, "custom")
            }

        }
        back.setOnClickListener {
            requireActivity().onBackPressed()
        }

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(root.context)
        var lang = sharedPreferences.getString("lang", "ru")!!

        when(lang){
            "ru" -> lngRUSSIA.isChecked = true
            "en" -> lngEnglish.isChecked = true
        }

        lngEnglish.setOnClickListener {
            sharedPreferences.edit().putString("lang", "en").apply()
            requireActivity().recreate()

        }

        lngRUSSIA.setOnClickListener {
            sharedPreferences.edit().putString("lang", "ru").apply()
            requireActivity().recreate()
        }
    }
}
