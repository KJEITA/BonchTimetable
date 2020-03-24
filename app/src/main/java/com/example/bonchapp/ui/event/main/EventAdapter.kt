package com.example.bonchapp.ui.event.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.bonchapp.R
import kotlinx.android.synthetic.main.fragment_main_event.*
import kotlinx.android.synthetic.main.item_event.view.*

class EventAdapter(private val eventFragment: MainEventFragment) :
        RecyclerView.Adapter<EventAdapter.ViewHolder>(), Filterable {

    private var data: ArrayList<String>? = arrayListOf()
    private var newData: ArrayList<String> = arrayListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setData(data: ArrayList<String>) {
        this.data = data
        newData = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_event, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.apply {
            eventFragment.eventRecyclerView?.visibility = View.VISIBLE
            titleEventView.text = data?.get(position) ?: "Bad"
            dateEventView.text = data?.get(position) ?: "Bad"

            setOnClickListener {
                eventFragment.presenter.onItemClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 1//TODO: Fix nullability
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResult = FilterResults()
                if (constraint.isNullOrBlank()) {
                    filterResult.count = newData.size
                    filterResult.values = newData

                    return filterResult
                }
                val result = data?.filter {
                    it.contains(constraint, true)
                }
                return filterResult.apply {
                    count = result?.size ?: 0
                    values = result
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                Log.d("ss", "ss")
                data = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }

}