package com.spascoding.englishstructureconfig.di

import com.spascoding.englishstructureconfig.data.local.ConfigDatabase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface AppEntryPoint {
    fun getDatabase(): ConfigDatabase
}