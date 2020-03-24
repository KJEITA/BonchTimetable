package com.example.bonchapp.ui.adapters

import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.bonchapp.R
import com.example.bonchapp.coordinator.MainCoordinator
import com.example.bonchapp.pojo.SubjectDTO


class TimetableAdapter(val context: Context, fragment: Fragment) :
    RecyclerView.Adapter<TimetablePostHolder>() {

    var subject = ArrayList<SubjectDTO>()

    val fragment = fragment

    fun setSubjects(subjectList: List<SubjectDTO>) {
        this.subject.clear()
        this.subject.addAll(subjectList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimetablePostHolder {
        return TimetablePostHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_subject, parent, false), fragment
        )
    }

    override fun getItemCount(): Int = subject.size

    override fun onBindViewHolder(holder: TimetablePostHolder, position: Int) {
        holder.bind(subject[position])
    }

}

class TimetablePostHolder(itemView: View, fragment: Fragment) : RecyclerView.ViewHolder(itemView) {
    val textTime = itemView.findViewById<TextView>(R.id.subject_time)
    val textName = itemView.findViewById<TextView>(R.id.subject_name)
    val textType = itemView.findViewById<TextView>(R.id.subject_type)
    val textProfessor = itemView.findViewById<TextView>(R.id.subject_professor)
    val textCabinet = itemView.findViewById<TextView>(R.id.subject_cabinet)

    val fragment = fragment

    fun bind(subject: SubjectDTO) {
        textTime.text = subject.time
        textName.text = subject.subject
        textType.text = subject.subject_type
        textProfessor.text = subject.tutor
        //textCabinet.text = subject.place

        if (subject.place != null) {
            val content = SpannableString(subject.place)
            content.setSpan(UnderlineSpan(), 0, content.length, 0)
            textCabinet.setText(content)
        }
        else
            textCabinet.setText("")


        textCabinet.setOnClickListener {
            MainCoordinator.showCabinetInNavigator(fragment, stringer(subject.place))
        }


    }

    fun stringer(str: String): String {
        if (str.indexOf("Б22") != -1) {
            return str.replace("; Б22", "")
        } else if (str.indexOf("БМ20") != -1) {
            return str.replace("; БМ20", "")
        }
        return ""
    }
}