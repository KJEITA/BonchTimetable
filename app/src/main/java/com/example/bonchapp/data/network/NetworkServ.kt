package com.example.bonchapp.data.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.bonchapp.data.api.NetworkService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration

@RequiresApi(Build.VERSION_CODES.O)
object NetworkServ {
    val TABLE_API: NetworkService
    private val retrofit: Retrofit

    init{
        val okHttpClient = OkHttpClient.Builder().callTimeout(Duration.ofSeconds(30)).readTimeout(Duration.ofSeconds(30)).connectTimeout(Duration.ofSeconds(30)).build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://delta-axis.me")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        TABLE_API = retrofit.create(
            NetworkService::class.java)
    }
}
