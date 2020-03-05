package com.example.bonchapp.ui.timetable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bonchapp.R

class TimetableFragment : Fragment() {

    private lateinit var timetableViewModel: TimetableViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        timetableViewModel =
                ViewModelProviders.of(this).get(TimetableViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_timetable, container, false)
        val textView: TextView = root.findViewById(R.id.text_timetable)
        timetableViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
