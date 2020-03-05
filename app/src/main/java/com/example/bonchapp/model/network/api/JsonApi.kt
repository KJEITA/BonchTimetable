package com.example.bonchapp.model.network.api

import retrofit2.Call
import retrofit2.http.GET

interface JsonApi {
    @GET("/api/groups")
    fun getGroups(): Call<ArrayList<String>>
}