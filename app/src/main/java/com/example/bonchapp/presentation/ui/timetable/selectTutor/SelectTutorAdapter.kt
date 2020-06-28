package com.example.bonchapp.presentation.ui.timetable.selectTutor


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bonchapp.R
import com.example.bonchapp.presentation.App
import com.example.bonchapp.presentation.presenter.timetable.ITimetablePresenter
import javax.inject.Inject

class SelectTutorAdapter(val context: Context) :
    RecyclerView.Adapter<SelectTutorPostHolder>() {

    var subject = ArrayList<String>()

    fun setGroups(groupList: ArrayList<String>) {
        this.subject.clear()
        this.subject.addAll(groupList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectTutorPostHolder {
        return SelectTutorPostHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_group, parent, false)
        )
    }

    override fun getItemCount(): Int = subject.size


    override fun onBindViewHolder(holder: SelectTutorPostHolder, position: Int) {
        holder.bind(subject[position], position)
    }

}

class SelectTutorPostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageSrc = itemView.findViewById<ImageView>(R.id.faculty_icon)
    val textName = itemView.findViewById<TextView>(R.id.item_group_nameGroup)

    @Inject
    lateinit var presenter: ITimetablePresenter

    lateinit var group: String

    init {
        App.appComponent.inject(this)
    }

    fun bind(groupLGr: String, pos: Int) {

        textName.text = groupLGr

        if (pos % 2 != 0)
            itemView.setBackgroundColor(itemView.resources.getColor(R.color.colorItemGrey))
        else
            itemView.setBackgroundColor(itemView.resources.getColor(R.color.colorItemWhite))

        itemView.setOnClickListener {
            presenter.switchName(groupLGr)
            //presenter.loadTimetable()
            presenter.reloadPagers()
            presenter.closeFragment()
        }
    }
}
