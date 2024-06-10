package com.spascoding.localconfig.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.spascoding.localconfig.data.local.ConfigDatabase
import com.spascoding.localconfig.domain.use_case.database.ConfigurationUseCases
import com.spascoding.localconfig.domain.use_case.database.DeleteConfigurationUseCase
import com.spascoding.localconfig.domain.use_case.database.GetAllConfigNamesUseCase
import com.spascoding.localconfig.domain.use_case.database.GetConfigurationUseCase
import com.spascoding.localconfig.domain.use_case.database.GetSelectedConfigurationUseCase
import com.spascoding.localconfig.domain.use_case.database.SelectConfigurationUseCase
import com.spascoding.localconfig.domain.use_case.database.SyncConfigurationFromFirebaseUseCase
import com.spascoding.localconfig.domain.use_case.database.UpsertConfigurationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideEnglishStructureDatabase(@ApplicationContext context: Context): ConfigDatabase {
        return Room.databaseBuilder(
            context,
            ConfigDatabase::class.java, "config-database-db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideConfigurationUseCase(configDatabase: ConfigDatabase): ConfigurationUseCases {
        return ConfigurationUseCases(
            getConfigurationUseCase = GetConfigurationUseCase(configDatabase),
            syncConfigurationFromFirebaseUseCase = SyncConfigurationFromFirebaseUseCase(configDatabase),
            upsertConfigurationUseCase = UpsertConfigurationUseCase(configDatabase),
            selectConfigurationUseCase = SelectConfigurationUseCase(configDatabase),
            getSelectedConfigurationUseCase = GetSelectedConfigurationUseCase(configDatabase),
            getAllConfigNamesUseCase = GetAllConfigNamesUseCase(configDatabase),
            deleteSelectedConfigurationUseCase = DeleteConfigurationUseCase(configDatabase),
        )
    }
}