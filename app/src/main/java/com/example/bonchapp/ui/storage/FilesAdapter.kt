package com.example.bonchapp.ui.storage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.bonchapp.R
import kotlinx.android.synthetic.main.item_file.view.*

class FilesAdapter(private val storageFragment: StorageFragment): RecyclerView.Adapter<FilesAdapter.FilesHolder>() {

    inner class FilesHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val liveData = storageFragment.presenter.filesList.value

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_file, parent, false)
        return FilesHolder(view)

    }

    override fun getItemCount(): Int {
        return liveData?.size ?: 1
    }

    override fun onBindViewHolder(holder: FilesHolder, position: Int) {
        holder.itemView.run {
            item_file_title.text = liveData!![position].author
            item_file_author.text = liveData[position].author
            //TODO: fix presenter methods
            this.setOnLongClickListener { it ->
                val popUp = PopupMenu(this.context!!, it)
                popUp.inflate(R.menu.file_menu)

                popUp.setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.add_to_my_files -> storageFragment.presenter.addFileToMy()
                        R.id.delete_from_my_files -> {
                            storageFragment.presenter.deleteFileFromMy()
                            onItemDismiss(position)
                        }
                    }
                    true
                }
                popUp.show()
                true
            }
            item_file_download.setOnClickListener {
                storageFragment.presenter.downloadFile()
            }
        }
    }

    private fun onItemDismiss(position: Int) {
        liveData!!.removeAt(position)
        notifyItemRemoved(position)
    }
}