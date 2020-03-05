package com.example.bonchapp.ui.navgut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bonchapp.R

class NavgutFragment : Fragment() {

    private lateinit var navgutViewModel: NavgutViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navgutViewModel =
            ViewModelProviders.of(this).get(NavgutViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_navgut, container, false)
        val textView: TextView = root.findViewById(R.id.text_navgut)
        navgutViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
