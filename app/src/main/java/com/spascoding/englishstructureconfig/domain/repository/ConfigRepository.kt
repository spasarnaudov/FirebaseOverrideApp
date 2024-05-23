package com.spascoding.englishstructureconfig.domain.repository

import kotlinx.coroutines.flow.Flow

data class ConfigItem(val key: String, val value: String)

interface ConfigRepository {
    fun getConfigItems(): Flow<List<ConfigItem>>
}