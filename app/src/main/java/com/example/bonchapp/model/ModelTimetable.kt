package com.example.bonchapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bonchapp.MainContract
import com.example.bonchapp.model.network.NetworkService
import com.example.bonchapp.model.pojo.GroupDTO
import com.example.bonchapp.model.pojo.RequestTimeTableDTO
import com.example.bonchapp.model.pojo.RequestTutorsDTO
import com.example.bonchapp.pojo.SubjectDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelTimetable() : MainContract.ITimeTableModel {

    override fun loadTimetable(body: RequestTimeTableDTO): LiveData<ArrayList<SubjectDTO>> {

        val data = MutableLiveData<ArrayList<SubjectDTO>>()
        NetworkService
            .TABLE_API
            .getTimeTable(body = body)
            .enqueue(object : Callback<ArrayList<SubjectDTO>> {
                override fun onResponse(
                    call: Call<ArrayList<SubjectDTO>>,
                    resp: Response<ArrayList<SubjectDTO>>
                ) {
                    Log.d("LoL", "Good")
                    data.value = resp.body()
                        ?: arrayListOf(SubjectDTO())
                }

                override fun onFailure(call: Call<ArrayList<SubjectDTO>>, t: Throwable) {
                    Log.d("LoL", t.localizedMessage ?: "Error!")

                }
            })

        return data
    }

    override fun getGroups(): LiveData<ArrayList<ArrayList<String>>>  {

        val data = MutableLiveData<ArrayList<ArrayList<String>>>()

        NetworkService
            .TABLE_API
            .getGroups()
            .enqueue(object : Callback<ArrayList<ArrayList<String>>> {
                override fun onResponse(
                    call: Call<ArrayList<ArrayList<String>>>,
                    resp: Response<ArrayList<ArrayList<String>>>
                ){
                    Log.d("Test", "Good")
                    data.value = resp.body()

                }

                override fun onFailure(call: Call<ArrayList<ArrayList<String>>>, t: Throwable) {
                    Log.d("Test", t.localizedMessage ?: "Error!")

                }
            })

        return data
    }

    override fun getTutors(): LiveData<ArrayList<String>>  {

        val data = MutableLiveData<ArrayList<String>>()

        NetworkService
            .TABLE_API
            .getTutors()
            .enqueue(object : Callback<ArrayList<String>> {
                override fun onResponse(
                    call: Call<ArrayList<String>>,
                    resp: Response<ArrayList<String>>
                ){
                    Log.d("Test", "Good")
                    data.value = resp.body() ?: arrayListOf("Error!", "LOL", "statr")

                }

                override fun onFailure(call: Call<ArrayList<String>>, t: Throwable) {
                    Log.d("Test", t.localizedMessage ?: "Error!")

                }
            })

        return data
    }

    fun loadSavedNameGroup(name:String){

    }
}