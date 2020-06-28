package com.example.bonchapp.presentation.ui.timetable.selectGroup

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

class SelectGroupAdapter(val context: Context) :
    RecyclerView.Adapter<SelectGroupPostHolder>() {

    var subject = ArrayList<ArrayList<String>>()

    fun setGroups(groupList: List<ArrayList<String>>) {
        this.subject.clear()
        this.subject.addAll(groupList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectGroupPostHolder {
        return SelectGroupPostHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_group, parent, false)
        )
    }

    override fun getItemCount(): Int = subject.size


    override fun onBindViewHolder(holder: SelectGroupPostHolder, position: Int) {
        holder.bind(subject[position], position)
    }

}

class SelectGroupPostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageSrc = itemView.findViewById<ImageView>(R.id.faculty_icon)
    val textName = itemView.findViewById<TextView>(R.id.item_group_nameGroup)
    lateinit var group: String

    @Inject
    lateinit var presenter: ITimetablePresenter

    init {
        App.appComponent.inject(this)
    }

    fun bind(groupLGr: ArrayList<String>, pos: Int) {

        group = groupLGr[1]
        when {
            groupLGr[0] == "РТС" -> {
                imageSrc.setImageResource(R.drawable.rts)
            }
            groupLGr[0] == "ИС и Т" -> {
                imageSrc.setImageResource(R.drawable.isit)
            }
            groupLGr[0] == "ИКСС" -> {
                imageSrc.setImageResource(R.drawable.ikss_pushka)
            }
            groupLGr[0] == "ЦЭУБИ" -> {
                imageSrc.setImageResource(R.drawable.ceubi)
            }
            groupLGr[0] == "ГФ" -> {
                imageSrc.setImageResource(R.drawable.gf)
            }
            else -> {
                imageSrc.setImageResource(R.drawable.empty)
            }
        }
        textName.text = group

        val background: Int

        if (pos % 2 != 0)
            background = itemView.resources.getColor(R.color.colorItemGrey)
        else
            background = itemView.resources.getColor(R.color.colorItemWhite)

        itemView.setBackgroundColor(background)

        itemView.setOnClickListener {
            presenter.switchName(group)
            presenter.reloadPagers()
            presenter.closeFragment()
        }
    }
}
