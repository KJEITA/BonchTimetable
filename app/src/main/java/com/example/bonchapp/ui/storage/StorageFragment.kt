package com.example.bonchapp.ui.storage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bonchapp.R
import com.example.bonchapp.presenter.StoragePresenter

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

    lateinit var recyclerAdapter: FilesAdapter

    private lateinit var noFileView: LinearLayout

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_storage, container, false)

        initView(root)
//      initSearch()

        return root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onCreate()
        initRecycler()
        setClickers()
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

        noFileView = view.findViewById(R.id.no_files_view)
    }


    fun initRecycler() {
        recyclerAdapter = FilesAdapter(this)
        filesRecycler.layoutManager = LinearLayoutManager(this.context!!)
        filesRecycler.adapter = recyclerAdapter
    }

    fun updateAdapter() {
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

//    fun initSearch() {
//        filesSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                recyclerAdapter.filter(query!!.toLowerCase().trim())
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                recyclerAdapter.filter(newText!!.toLowerCase().trim())
//                return true
//            }
//
//        })
//    }

    fun onEmptyFileList() {
        filesRecycler.visibility = View.GONE
        noFileView.visibility = View.VISIBLE
    }

    fun onNonEmptyFileList() {
        filesRecycler.visibility = View.VISIBLE
        noFileView.visibility = View.GONE
    }
}
