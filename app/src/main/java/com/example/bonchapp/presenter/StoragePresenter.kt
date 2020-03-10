package com.example.bonchapp.presenter

import com.example.bonchapp.R
import com.example.bonchapp.ui.storage.StorageFragment
import kotlinx.android.synthetic.main.fragment_storage.view.*

class StoragePresenter(val context: StorageFragment) {

    var data = mutableListOf("Мой Файл 1", "Мой Файл 2", "Мой Файл 3", "Мой Файл 4", "Мой Файл 5", "Мой Файл 6", "Мой Файл 7")

    fun onCreate() {

    }

    fun onSpinnerSelectedItemUpdated(itemPosition: Int) {
        when (itemPosition) {
            R.id.my_file_btn -> data = mutableListOf("Мой Файл 1", "Мой Файл 2", "Мой Файл 3", "Мой Файл 4", "Мой Файл 5", "Мой Файл 6", "Мой Файл 7")
            R.id.group_file_btn -> data = mutableListOf("Файл группы 1", "Файл группы 2", "Файл группы 3", "Файл группы 4", "Файл группы 5", "Файл группы 6", "Файл группы 7")
            R.id.lib_file_btn -> data = mutableListOf("Файл библиотека 1", "Файл библиотека 2", "Файл библиотека 3", "Файл библиотека 4", "Файл библиотека 5", "Файл библиотека 6", "Файл библиотека 7")
            R.id.bonch_file_btn -> data = mutableListOf("bonchfile 1", "bonchfile 2", "bonchfile 3", "bonchfile 4", "bonchfile 5", "bonchfile 6", "bonchfile 7")
        }
        context.updateAdapter(data)
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