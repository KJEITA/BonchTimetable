package com.example.bonchapp.presentation.ui.timetable.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.View.OnTouchListener
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.bonchapp.R
import com.example.bonchapp.presentation.App
import com.example.bonchapp.presentation.presenter.timetable.ITimetablePresenter
import com.example.bonchapp.presentation.ui.timetable.calendar.CalendarViewPagerAdapter
import com.example.bonchapp.presentation.ui.timetable.calendar.CollapseCalendarView
import com.example.bonchapp.presentation.ui.timetable.calendar.manager.CalendarManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_timetable.*
import org.joda.time.DateTime
import org.joda.time.LocalDate
import javax.inject.Inject


class TimetableFragment : Fragment(), ITimetableView {

    @Inject
    lateinit var presenter: ITimetablePresenter
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var root: View
    var date = DateTime.now()

    var calendarViewPagerAdapter = CalendarViewPagerAdapter()

    init {
        App.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_timetable, container, false)
        presenter.attachView(this)

        initSelectTypeTimetable()

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().nav_view.visibility = View.VISIBLE

        presenter.firstLoad()
        initCalender()
        initViewPager()
    }

    private fun initViewPager() {
        viewPagerAdapter = ViewPagerAdapter(this)

        viewPager2.adapter = viewPagerAdapter
        viewPager2.setCurrentItem(500, false)

    }

    override fun setDatee(dateTime: DateTime){
        date = dateTime
    }

    private fun initCalender() {
//        calendarViewPagerAdapter = CalendarViewPagerAdapter()
//
//        calendarPager.adapter = calendarViewPagerAdapter
//        calendarPager.setCurrentItem(500, false)


        val calendarView = root.findViewById<CollapseCalendarView>(R.id.calendar)
        val manager = CalendarManager(
            date.toLocalDate(),
            CalendarManager.State.WEEK,
            LocalDate.now().minusYears(5),
            LocalDate.now().plusYears(5)
        )
        calendarView.init(manager)

        val textMonth = root.findViewById<TextView>(R.id.month)
        textMonth.text =
            resources.getStringArray(R.array.Months)[(manager.activeMonth.monthOfYear ?: 1) - 1]

        calendarView.setListener { date: LocalDate? ->
            val int = DateTime.now().weekOfWeekyear-date!!.weekOfWeekyear
            viewPager2.currentItem = 500 - int
            presenter.scrollDayRV(500 - int, date.dayOfWeek)
        }


        calendarView.setOnTouchListener(object : OnSwipeTouchListener(requireContext()) {

            override fun onSwipeTop() {
                super.onSwipeTop()
            }

            override fun onSwipeBottom() {
                super.onSwipeBottom()
            }

            override fun onSwipeLeft() {
                super.onSwipeLeft()
                manager.next()
                calendarView.populateLayout()
                textMonth.text =
                    resources.getStringArray(R.array.Months)[(manager.activeMonth.monthOfYear
                        ?: 1) - 1]
            }

            override fun onSwipeRight() {
                super.onSwipeRight()
                manager.prev()
                calendarView.populateLayout()
                textMonth.text =
                    resources.getStringArray(R.array.Months)[(manager.activeMonth.monthOfYear
                        ?: 1) - 1]
            }
        })


//        val calendar = root.findViewById<CollapsibleCalendar>(R.id.calendar)
//

//
//        var currentWeek = DateTime.now().weekOfWeekyear
//
//        calendar.setCalendarListener(object : CollapsibleCalendar.CalendarListener {
//            override fun onDaySelect() {
//                val day = calendar.selectedDay
//                val dt = DateTime(day!!.year, day.month + 1, day.day, 0, 0)
//
//                val recyclerViewDay = root.findViewById<RecyclerView>(R.id.timeTable_recyclerView)
//
//                if (dt.weekOfWeekyear != currentWeek) {
//                    currentWeek = dt.dayOfWeek
//                }
//
//                recyclerViewDay.scrollToPosition(dt.dayOfWeek - 1)
//            }
//
//            override fun onItemClick(v: View) {}
//            override fun onClickListener() {
//            }
//
//            override fun onDataUpdate() {}
//            override fun onDayChanged() {
//            }
//
//            override fun onMonthChange() {
//                textMonth.text = resources.getStringArray(R.array.Months)[(calendar.month ?: 1)]
//            }
//
//            override fun onWeekChange(position: Int) {}
//        })
    }

    private fun initSelectTypeTimetable() {
        val button = root.findViewById<ImageView>(R.id.filter)
        button.setOnClickListener {
            presenter.navigateToSelectType()
        }
    }

    override fun hideKeyboard() {
        val context: Context = requireContext().applicationContext
        val imm =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(root.windowToken, 0)
    }

    override fun showName(s: String) {
        val name = root.findViewById<TextView>(R.id.nameGroup)
        name.text = s
    }

    open class OnSwipeTouchListener(ctx: Context) : OnTouchListener {

        private val gestureDetector: GestureDetector

        companion object {

            private val SWIPE_THRESHOLD = 100
            private val SWIPE_VELOCITY_THRESHOLD = 100
        }

        init {
            gestureDetector = GestureDetector(ctx, GestureListener())
        }

        override fun onTouch(v: View, event: MotionEvent): Boolean {
            return gestureDetector.onTouchEvent(event)
        }

        private inner class GestureListener : SimpleOnGestureListener() {


            override fun onDown(e: MotionEvent): Boolean {
                return true
            }

            override fun onFling(
                e1: MotionEvent,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                var result = false
                try {
                    val diffY = e2.y - e1.y
                    val diffX = e2.x - e1.x
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight()
                            } else {
                                onSwipeLeft()
                            }
                            result = true
                        }
                    } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom()
                        } else {
                            onSwipeTop()
                        }
                        result = true
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }

                return result
            }


        }

        open fun onSwipeRight() {}

        open fun onSwipeLeft() {}

        open fun onSwipeTop() {}

        open fun onSwipeBottom() {}
    }
}