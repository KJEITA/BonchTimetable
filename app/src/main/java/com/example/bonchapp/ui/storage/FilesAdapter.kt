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

    private val liveData = storageFragment.presenter.testData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_file, parent, false)
        return FilesHolder(view)
    }

    override fun getItemCount(): Int {
        return liveData.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: FilesHolder, position: Int) {
        holder.itemView.run {
            item_file_title.text = liveData.value!![position].author
            item_file_author.text = liveData.value!![position].author
            //TODO: fix presenter methods
            this.setOnLongClickListener { it ->
                val popUp = PopupMenu(this.context!!, it)
                popUp.menu.add(0, 0, 0, resources.getString(R.string.add_to_my_files))
                popUp.menu.add(0, 1, 0, resources.getString(R.string.delete_from_my_file))

                popUp.setOnMenuItemClickListener {
                    when (it.itemId) {
                        0 -> {
                            storageFragment.presenter.addFileToMy()
                        }
                        1 -> {
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

//    fun filter(text: String) {
//        liveData.value!!.clear()
//        if (text.isNotEmpty()) {
//            itemsCopy!!.forEach {
//                if (it.author.toLowerCase().contains(text) || it.title.toLowerCase().contains(text)) {
//                    liveData.value!!.add(it)
//                }
//            }
//        } else {
//            liveData.value!!.addAll(itemsCopy!!)
//        }
//        notifyDataSetChanged()
//    }

    private fun onItemDismiss(position: Int) {
        liveData.value!!.removeAt(position)
        notifyItemRemoved(position)
    }
}