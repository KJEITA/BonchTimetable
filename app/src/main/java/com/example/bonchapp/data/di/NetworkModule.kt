package com.example.bonchapp.data.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.bonchapp.data.api.NetworkService
import com.example.bonchapp.data.network.NetworkServ
import com.example.bonchapp.router.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import javax.inject.Singleton

@Module
class NetworkModule {

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun provideNetworkService(): NetworkService {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(Duration.ofSeconds(120))
            .callTimeout(Duration.ofSeconds(120))
            .readTimeout(Duration.ofSeconds(120))
            .writeTimeout(Duration.ofSeconds(120))
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.HOST)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }

}
