package com.example.bonchapp.presentation.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bonchapp.presentation.ui.profile.debt.ProfileDebtFragment
<<<<<<< HEAD
import com.example.bonchapp.presentation.ui.profile.electives.ProfileElectivesFragment
=======
import com.example.bonchapp.presentation.ui.profile.elective.ProfileElectivesFragment
>>>>>>> timeTable
import com.example.bonchapp.presentation.ui.profile.files.ProfileFileFragment
import com.example.bonchapp.presentation.ui.profile.mark.ProfileMarkFragment

class ProfilePagerAdapter(val fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment = when(position) {
        0 -> ProfileDebtFragment()
        1 -> ProfileMarkFragment()
        2 -> ProfileFileFragment()
        else -> ProfileElectivesFragment()
    }
}
