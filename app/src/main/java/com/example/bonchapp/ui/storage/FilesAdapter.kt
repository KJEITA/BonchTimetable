package com.example.bonchapp.ui.storage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.bonchapp.R
import kotlinx.android.synthetic.main.item_file.view.*

class FilesAdapter(val aContext: StorageFragment, var data: MutableList<String>): RecyclerView.Adapter<FilesAdapter.FilesHolder>() {

    inner class FilesHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_file, parent, false)
        return FilesHolder(view)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FilesHolder, position: Int) {
        holder.itemView.run {
            item_file_title.text = data[position]
            //TODO: fix presenter methods
            this.setOnLongClickListener { it ->
                val popUp = PopupMenu(this.context!!, it)
                popUp.inflate(R.menu.file_menu)

                popUp.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.add_to_my_files -> aContext.presenter.addFileToMy()
                        R.id.delete_from_my_files -> aContext.presenter.deleteFileFromMy()
                    }

                    true
                }
                popUp.show()
                true
            }
            item_file_download.setOnClickListener {
                aContext.presenter.downloadFile()
            }
        }
    }

    fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }
}