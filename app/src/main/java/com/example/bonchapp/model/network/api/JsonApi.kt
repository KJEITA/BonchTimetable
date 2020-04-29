package com.example.bonchapp.model.network.api

import com.example.bonchapp.model.pojo.Auth
import retrofit2.Call
import com.example.bonchapp.model.pojo.RequestTimeTableDTO
import com.example.bonchapp.model.pojo.RequestTutorsDTO
import com.example.bonchapp.pojo.SubjectDTO
import retrofit2.http.*


interface JsonApi {
    @GET("/groups")
    fun getGroups(): Call<ArrayList<String>>

    //@FormUrlEncoded
    @POST("/timetable")
    fun getTimeTable(
        @Header("Accept") accept: String = "application/json",
        @Body body: RequestTimeTableDTO?
    ): Call<ArrayList<SubjectDTO>>

    @GET("/tutors")
    fun getTutors(
        @Header("Accept") accept: String = "application/json",
        @Body body: RequestTutorsDTO?
    ): Call<ArrayList<SubjectDTO>>


}