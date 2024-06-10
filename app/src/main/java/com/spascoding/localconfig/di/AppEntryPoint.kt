package com.spascoding.localconfig.di

import com.spascoding.localconfig.data.local.ConfigDatabase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface AppEntryPoint {
    fun getDatabase(): ConfigDatabase
}