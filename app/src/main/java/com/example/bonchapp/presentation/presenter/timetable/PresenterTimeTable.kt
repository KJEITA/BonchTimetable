package com.example.bonchapp.presentation.presenter.timetable

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bonchapp.presentation.MainContract
import com.example.bonchapp.data.ModelTimetable
import com.example.bonchapp.domain.entities.RequestTimeTable
import com.example.bonchapp.domain.entities.RequestTimeTableDTO
import com.example.bonchapp.pojo.SubjectDTO
import org.joda.time.DateTime
import org.joda.time.DateTimeConstants
import org.joda.time.format.DateTimeFormat
import kotlin.collections.ArrayList


class PresenterTimeTable(fr: Fragment, view: MainContract.ITimeTableView) :
    MainContract.ITimeTablePresenter {
    private val mView = view
    private val mModel = ModelTimetable()

    private val fragment = fr

    private var name = ""
    private var type = "group"

    lateinit var timetable: List<SubjectDTO>
    lateinit var groupsList: List<ArrayList<String>>
    lateinit var tutorsList: ArrayList<String>

    //var activeday: String
    var currentDate = DateTime()

    val dtf = DateTimeFormat.forPattern("dd-MM-yyyy");


    init {
        //val sdf = SimpleDateFormat("dd-MM-yyyy")
        //activeday = sdf.format(java.util.Date())

        loadSavedNameGroup()

        if (name != "")
            switchDayTimetable("*")
    }

    override fun switchDayTimetable(command: String) {
        if (name != "") {
            val start: DateTime
            val end: DateTime

            if (command.equals("+")) {
                currentDate.plusWeeks(1)
            } else if (command.equals("-")) {
                currentDate.minusWeeks(1)
            }

            start = currentDate.dayOfWeek().setCopy(DateTimeConstants.MONDAY)
            end = currentDate.dayOfWeek().setCopy(DateTimeConstants.SUNDAY)

            val body = RequestTimeTable(dtf.print(start), dtf.print(end), name, type)


            val datesWeeks = arrayListOf<String>(
                dtf.print(currentDate.dayOfWeek().setCopy(DateTimeConstants.MONDAY)),
                dtf.print(currentDate.dayOfWeek().setCopy(DateTimeConstants.TUESDAY)),
                dtf.print(currentDate.dayOfWeek().setCopy(DateTimeConstants.WEDNESDAY)),
                dtf.print(currentDate.dayOfWeek().setCopy(DateTimeConstants.THURSDAY)),
                dtf.print(currentDate.dayOfWeek().setCopy(DateTimeConstants.FRIDAY)),
                dtf.print(currentDate.dayOfWeek().setCopy(DateTimeConstants.SATURDAY)),
                dtf.print(currentDate.dayOfWeek().setCopy(DateTimeConstants.SUNDAY))
            )


            mModel.loadTimetable(body).observe(fragment.viewLifecycleOwner, Observer {
                timetable = it

                mView.showTimetable(timetable, datesWeeks)

                /*if (timetable.size != 0) {
                    mView.setWithoutClassesVisibility(false)
                } else
                    mView.setWithoutClassesVisibility(true)
                */
            })
        }
    }

    fun switchDayTimetable(dt: DateTime) {
        currentDate = dt
        switchDayTimetable("*")
    }

    override fun updateGroupsList() {
        mModel.getGroups().observe(fragment.viewLifecycleOwner, Observer {
            groupsList = it
            mView.showGroupsList(groupsList)
        })
    }

    override fun updateTutorsList() {
        mModel.getTutors().observe(fragment.viewLifecycleOwner, Observer {
            tutorsList = it
            mView.showTutorsList(tutorsList)
        })
    }

    override fun switchTimetable(type: String) {
        this.type = type
        fragment.activity?.onBackPressed()

        if (type == "group")
            mView.showSelectGroupFragment()
        else if (type == "tutor")
            mView.showSelectProfessorFragment()
        else
            switchDayTimetable("*")

    }

    override fun switchGroup(name: String, type: String) {
        this.name = name
        this.type = type

        switchDayTimetable("*")

        mView.setNameGroup(name)

        fragment.activity?.onBackPressed()
    }

    fun loadSavedNameGroup() {
        mModel.loadSavedNameGroup(name)

        name = "ИКПИ-84"


        if (name != "") {
            mView.setNameGroup(name)
        }
    }
}