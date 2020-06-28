package com.example.bonchapp.domain.di

import com.example.bonchapp.data.api.NetworkService
import com.example.bonchapp.data.repository.TimetableRepository
import com.example.bonchapp.domain.interactors.profile.IProfileInteractor
import com.example.bonchapp.domain.interactors.timetable.ITimetableInteractor
import com.example.bonchapp.domain.interactors.timetable.TimetableInteractor
import com.example.bonchapp.domain.repository.ITimetableRepository
import com.example.bonchapp.presentation.presenter.timetable.*
import com.example.bonchapp.router.MainRouter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TimetableModule {

    @Provides
    @Singleton
    fun providePresenter(
        timetableInteractor: ITimetableInteractor,
        profileInteractor: IProfileInteractor,
        router: MainRouter
    ): ITimetablePresenter {
        return TimetablePresenter(
            timetableInteractor,
            profileInteractor,
            router
        )
    }

    @Provides
    @Singleton
    fun provideGroupPresenter(timetableInteractor: ITimetableInteractor): ITimetableGroupPresenter {
        return TimetableGroupPresenter(
            timetableInteractor
        )
    }

    @Provides
    @Singleton
    fun provideTutorPresenter(timetableInteractor: ITimetableInteractor): ITimetableTutorPresenter {
        return TimetableTutorPresenter(
            timetableInteractor
        )
    }

    @Provides
    @Singleton
    fun provideInteractor(repository: ITimetableRepository): ITimetableInteractor {
        return TimetableInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideRepository(networkService: NetworkService): ITimetableRepository {
        return TimetableRepository(networkService)
    }
}