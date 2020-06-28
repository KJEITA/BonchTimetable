package com.example.bonchapp.domain.di

import com.example.bonchapp.router.MainRouter
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule {
    @Provides
    @Singleton
    fun provideRouter(): MainRouter{
        return MainRouter()
    }
}
