package com.bongyang.ohmun.di

import android.content.Context
import androidx.room.Room
import com.bongyang.ohmun.data.local.OhmunDatabase
import com.bongyang.ohmun.data.repository.LocalDataSourceImpl
import com.bongyang.ohmun.domain.repository.LocalDataSource
import com.bongyang.ohmun.util.Constants.OHMUN_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): OhmunDatabase {
        return Room.databaseBuilder(
            context,
            OhmunDatabase::class.java,
            OHMUN_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        ohmunDatabase: OhmunDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            ohmunDatabase = ohmunDatabase
        )
    }
}