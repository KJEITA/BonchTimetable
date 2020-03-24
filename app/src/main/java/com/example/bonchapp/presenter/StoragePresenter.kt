package com.example.bonchapp.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bonchapp.R
import com.example.bonchapp.model.pojo.File
import com.example.bonchapp.ui.storage.StorageFragment
import kotlinx.android.synthetic.main.fragment_storage.view.*

class StoragePresenter(val context: StorageFragment) {

    private val testData = MutableLiveData<ArrayList<File>>()

    var filesList: LiveData<ArrayList<File>> = testData

    fun onCreate() {
        testData.value = arrayListOf()
    }

    fun onSpinnerSelectedItemUpdated(itemPosition: Int) {
        when (itemPosition) {
            R.id.my_file_btn -> testData.value!!.clear()
            R.id.group_file_btn -> testData.value = arrayListOf(File("Файл группы1", "Автор1"), File("Файл группы2", "Автор2"))
            R.id.lib_file_btn -> testData.value = arrayListOf(File("Файл библиотека 1", "Автор"), File("Файл библиотека 3", "Автор2"))
            R.id.bonch_file_btn -> testData.value = arrayListOf(File("bonchfile 1", "bonchfile 2"))
        }
        context.updateAdapter()
    }

    fun addFileToMy() {
        //TODO: add file
    }

    fun deleteFileFromMy() {
        //TODO: delete file
    }

    fun downloadFile() {
        //TODO: download file
    }
}