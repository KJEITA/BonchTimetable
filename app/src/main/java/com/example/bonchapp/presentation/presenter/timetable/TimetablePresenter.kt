package com.example.bonchapp.presentation.presenter.timetable

import com.example.bonchapp.domain.interactors.timetable.ITimetableInteractor
import com.example.bonchapp.presentation.ui.timetable.main.ITimetableView
import com.example.bonchapp.router.MainRouter
import org.joda.time.DateTime
import com.example.bonchapp.domain.entities.RequestTimeTable
import com.example.bonchapp.domain.interactors.profile.IProfileInteractor
import com.example.bonchapp.pojo.SubjectDTO
import com.example.bonchapp.presentation.ui.timetable.main.TimetableViewPagerFragment
import org.joda.time.DateTimeConstants
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class TimetablePresenter @Inject constructor(
    private val interactor: ITimetableInteractor, private val profileInteractor: IProfileInteractor,
    val router: MainRouter
) : ITimetablePresenter {

    private val dtf = DateTimeFormat.forPattern("dd-MM-yyyy");

    private var originalName = ""
    private var originalType = "group"
    private var name = ""
    private var type = "group"

    private lateinit var viewMain: ITimetableView

    private var btn_origin = false

    private val pagers = arrayListOf<TimetableViewPagerFragment>()

    init {
        getUserInfo()
    }

    override fun getAttachView(): ITimetableView {
        return viewMain
    }

    override fun attachView(viewMain: ITimetableView) {
        this.viewMain = viewMain
    }

    override fun addPager(p: TimetableViewPagerFragment) {
        pagers.add(p)
    }

    override fun deletePager(p: TimetableViewPagerFragment) {
        pagers.remove(p)
    }

    private fun getUserInfo(
        fragment: TimetableViewPagerFragment,
        date: DateTime,
        callback: (data: ArrayList<SubjectDTO>?) -> Unit
    ) {
        profileInteractor.getUserInfo(
            callback = {
                if (it != null) {
                    originalName = it.group
                    switchName(it.group)
                    loadTimetable(fragment, date, callback)
                }
            }
        )
    }

    private fun getUserInfo() {
        profileInteractor.getUserInfo(
            callback = {
                if (it != null) {
                    originalName = it.group
                    if (this::viewMain.isInitialized)
                        switchName(it.group)
                    else
                        name = it.group
                }
            }
        )
    }

    override fun scrollDayRV(pos:Int, posRV:Int){
        pagers.forEach {
            if(pos == it.myPos)
                it.scrollRV(posRV)
        }
    }

    override fun firstLoad() {
        if (!name.equals("")){
            switchName(name)
            if (!pagers.isEmpty())
            viewMain.setDatee(pagers[0].myDate)
    }}

    override fun reloadPagers() {
        pagers.forEach {
            it.loadTimetable()
        }
    }

    override fun loadTimetable(
        fragment: TimetableViewPagerFragment,
        date: DateTime,
        callback: (data: ArrayList<SubjectDTO>?) -> Unit
    ) {
        if (name != "") {
            val start: DateTime = date.dayOfWeek().setCopy(DateTimeConstants.MONDAY)
            val end: DateTime = date.dayOfWeek().setCopy(DateTimeConstants.SUNDAY)

            val body = RequestTimeTable(dtf.print(start), dtf.print(end), name, type)

            interactor.getTimetable(body,
                callback = {
                    callback(it)
                }
            )
        } else {
            getUserInfo(fragment, date, callback)
        }
    }


    override fun switchType(type: String) {
        this.type = type

        if (type == "group")
            router.navigateToSelectGroup()
        else if (type == "tutor")
            router.navigateToSelectTutor()
        else if (type == "exam" || type == "user_id") {
            btn_origin = true
            reloadPagers()
            closeFragment()
        }
    }

    override fun switchName(name: String) {
        this.name = name
        viewMain.showName(name)

        btn_origin = !this.name.equals(originalName)
    }

    override fun returnOriginal() {
        type = originalType
        switchName(originalName)

        reloadPagers()
        closeFragment()
    }

    override fun closeFragment() {
        router.navController.popBackStack()
        viewMain.hideKeyboard()
    }

    override fun navigateToCabinet(cabinet: String) {
        router.navigateToCabinet(cabinet)
    }

    override fun navigateToSelectType() {
        router.navigateToSelectType(btn_origin)
    }
}