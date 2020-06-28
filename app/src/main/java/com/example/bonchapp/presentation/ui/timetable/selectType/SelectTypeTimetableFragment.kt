package com.example.bonchapp.presentation.ui.timetable.selectType

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bonchapp.R
import com.example.bonchapp.domain.entities.Message
import com.example.bonchapp.presentation.App
import com.example.bonchapp.presentation.presenter.timetable.ITimetablePresenter
import com.example.bonchapp.router.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class SelectTypeTimetableFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var presenter: ITimetablePresenter

    var b = false

    init {
        App.appComponent.inject(this)
    }


    lateinit var root:View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_type_timetable, container, false)

        arguments?.let {
            b = it.getSerializable(Constants.TYPE_TIMETABLE) as Boolean
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnInit()
    }

    private fun btnInit(){
        val btn_select_group = root.findViewById<View>(R.id.btn_select_group)
        val btn_select_professor = root.findViewById<View>(R.id.btn_select_tutor)
        val btn_select_exam = root.findViewById<View>(R.id.btn_exams)
        val btn_select_elective = root.findViewById<View>(R.id.btn_elective)
        val btn_select_original = root.findViewById<View>(R.id.btn_original)

        btn_select_group.setOnClickListener {
            presenter.switchType("group")
        }

        btn_select_professor.setOnClickListener {
            presenter.switchType("tutor")
        }

        btn_select_exam.setOnClickListener {
            presenter.switchType("exam")
        }

        btn_select_elective.setOnClickListener {
            presenter.switchType("user_id")
        }

        btn_select_original.setOnClickListener {
            presenter.returnOriginal()
        }

        if (b)
            btn_select_original.visibility = View.VISIBLE
        else
            btn_select_original.visibility = View.GONE
    }
}
