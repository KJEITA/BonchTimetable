package com.example.bonchapp.data.api

import com.example.bonchapp.domain.entities.*
import com.example.bonchapp.pojo.SubjectDTO
import com.example.bonchapp.router.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkService {


    // EVENTS
    @GET("/api/timetable/group")
    fun getGroups(@Header("Authorization") token: String = "Token ${User.token.value}"
    ): Call<ArrayList<ArrayList<String>>>

    @GET("/api/timetable/group")
    fun getEvents(@Header("Authorization") token: String = "Token ${User.token.value}"
    ): Call<List<Event>>

    @GET("/api/timetable/group")
    fun getNews(@Header("Authorization") token: String = "Token ${User.token.value}"
    ): Call<ArrayList<ArrayList<String>>>


    //TimeTable
    //@FormUrlEncoded
    @POST("/api/timetable")
    fun getTimeTable(
        //@Header("Accept") accept: String = "application/json",
        @Header("Authorization") token: String = "Token ${User.token.value}",
        @Body body: RequestTimeTable?
    ): Call<ArrayList<SubjectDTO>>

    @GET("/api/timetable/tutor/long")
    fun getTutors(
        @Header("Authorization") token: String = "Token ${User.token.value}"
    ): Call<ArrayList<String>>


    //Auth
    @POST("/api/login")
    fun getToken(
        @Header("Accept") accept: String = "application/json",
        @Body body: Auth?
    ): Call<Token>

    //Messages
    @GET("/api/messages")
    fun getMessages(@Header("Authorization") token: String ="Token ${User.token.value}"
    ): Call<ArrayList<Message>>

    @POST("/api/messages")
    fun sendMessage(
        @Header("Authorization") token: String = "Token ${User.token.value}",
        @Body body: MessageBody
    ): Call<Messages>

    @GET("/api/user/account")
    fun getUserInfo(
        @Header("Authorization") token: String = "Token ${User.token.value}"
    ): Call<AccountDTO>

    @GET("/api/user/debt")
    fun getDebt(
        @Header("Authorization") token: String = "Token ${User.token.value}"
    ): Call<ArrayList<DebtDTO>>

    @GET("/api/user/history")
    fun getHistoryElectives(
        @Header("Authorization") token: String = "Token ${User.token.value}"
    ): Call<ArrayList<ElectiveDTO>>

    @GET("/api/user/mark")
    fun getMark(
        @Header("Authorization") token: String = "Token ${User.token.value}"
    ): Call<ArrayList<MarkDTO>>

}
