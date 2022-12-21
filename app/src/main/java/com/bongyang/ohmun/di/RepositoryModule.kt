package com.bongyang.ohmun.di

import android.content.Context
import com.bongyang.ohmun.data.repository.DataStoreOperationsImpl
import com.bongyang.ohmun.data.repository.MovieRepository
import com.bongyang.ohmun.domain.repository.DataStoreOperations
import com.bongyang.ohmun.domain.use_cases.UseCases
import com.bongyang.ohmun.domain.use_cases.get_all_movies.GetAllMoviesUseCase
import com.bongyang.ohmun.domain.use_cases.get_selected_movie.GetSelectedMovieUseCase
import com.bongyang.ohmun.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.bongyang.ohmun.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.bongyang.ohmun.domain.use_cases.search_movies.SearchMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: MovieRepository): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllMoviesUseCase = GetAllMoviesUseCase(repository),
            searchMoviesUseCase = SearchMoviesUseCase(repository),
            getSelectedMovieUseCase = GetSelectedMovieUseCase(repository)
        )
    }
}