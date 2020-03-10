package com.example.bonchapp.ui.storage

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bonchapp.R
import com.example.bonchapp.presenter.StoragePresenter
import kotlinx.android.synthetic.main.fragment_storage.*

class StorageFragment : Fragment() {

    val presenter = StoragePresenter(this)

    private lateinit var filesRecycler: RecyclerView
    private lateinit var filesSearch: SearchView

    private lateinit var filesSpinnerTitle: TextView
    private lateinit var filesSpinner: LinearLayout
    private lateinit var filesDropdownList: CardView
    private lateinit var currentTypeFileTW: TextView

    private lateinit var myFileBtn: Button
    private lateinit var groupFileBtn: Button
    private lateinit var libFileBtn: Button
    private lateinit var bonchFileBtn: Button

    private val touchListener = object: View.OnTouchListener {
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            if (event!!.actionIndex >= 0 && filesDropdownList.visibility == View.VISIBLE) {
                filesDropdownList.visibility = View.GONE
            }
            return true
        }

    }

    private lateinit var recyclerAdapter: FilesAdapter

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_storage, container, false)
        root.setOnTouchListener(touchListener)
        initView(root)
        setClickers()

        return root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onCreate()
        initRecycler()
    }

    fun initView(view: View) {
        filesSpinner = view.findViewById(R.id.files_spinner)
        filesRecycler = view.findViewById(R.id.files_recycler)
        filesSearch = view.findViewById(R.id.files_search)
        filesDropdownList = view.findViewById(R.id.file_spinner_drowdown_list)

        filesSpinnerTitle = view.findViewById(R.id.file_spinner_title)
        currentTypeFileTW = view.findViewById(R.id.current_type_file)

        //spinner buttons
        myFileBtn = view.findViewById(R.id.my_file_btn)
        groupFileBtn = view.findViewById(R.id.group_file_btn)
        libFileBtn = view.findViewById(R.id.lib_file_btn)
        bonchFileBtn = view.findViewById(R.id.bonch_file_btn)
    }


    fun initRecycler() {
        recyclerAdapter = FilesAdapter(this, presenter.data)
        filesRecycler.layoutManager = LinearLayoutManager(this.context!!)
        filesRecycler.adapter = recyclerAdapter
    }

    fun updateAdapter(newData: MutableList<String>) {
        recyclerAdapter.data = newData
        filesRecycler.adapter!!.notifyDataSetChanged()
    }

    private fun setClickers() {
        filesSpinner.setOnClickListener {
            filesDropdownList.visibility = View.VISIBLE
        }

        bonchFileBtn.setOnClickListener { onSpinnerSelectedItemUpdated(bonchFileBtn) }
        myFileBtn.setOnClickListener { onSpinnerSelectedItemUpdated(myFileBtn) }
        libFileBtn.setOnClickListener { onSpinnerSelectedItemUpdated(libFileBtn) }
        groupFileBtn.setOnClickListener { onSpinnerSelectedItemUpdated(groupFileBtn) }
    }

    private fun onSpinnerSelectedItemUpdated(button: Button) {
        presenter.onSpinnerSelectedItemUpdated(button.id)

        filesSpinnerTitle.text = button.text
        currentTypeFileTW.text = button.text

        filesDropdownList.visibility = View.GONE
    }
}
