package com.example.bonchapp.presentation.ui.profile.elective

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bonchapp.R
import com.example.bonchapp.domain.entities.DebtDTO
import com.example.bonchapp.domain.entities.ElectiveDTO
import com.example.bonchapp.presentation.App
import com.example.bonchapp.presentation.presenter.profile.electives.IProfileElectivesPresenter
import com.example.bonchapp.presentation.ui.profile.debt.DebtAdapter
import javax.inject.Inject

class ProfileElectivesFragment : Fragment(),
    IProfileElectivesView {

    @Inject
    lateinit var presenter: IProfileElectivesPresenter

    lateinit var adapter: ElectiveAdapter

    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_profile_electives, container, false)

        return root
    }

    init {
<<<<<<< HEAD
        //App.appComponent.inject(this)
=======
        App.appComponent.inject(this)
>>>>>>> timeTable
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
        presenter.attachView(this)
        presenter.updateData()    }

    fun initRV() {
        adapter = ElectiveAdapter(
            root.context,
            this
        )
        val recyclerViewDay = root.findViewById<RecyclerView>(R.id.profileRV)
        recyclerViewDay.layoutManager = LinearLayoutManager(root.context)
        recyclerViewDay.adapter = adapter
    }

    override fun setData(list: ArrayList<ElectiveDTO>) {
        adapter.setData(list)
    }

    override fun hideImg(b:Boolean){
        root.findViewById<View>(R.id.profilEmptyImage).visibility = if(b) View.VISIBLE else View.INVISIBLE
        root.findViewById<View>(R.id.profileRV).visibility = if(!b) View.VISIBLE else View.INVISIBLE
    }
}