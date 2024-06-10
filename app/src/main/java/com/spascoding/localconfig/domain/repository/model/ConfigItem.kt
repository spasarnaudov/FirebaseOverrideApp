package com.spascoding.localconfig.domain.repository.model

import androidx.room.Entity

@Entity(tableName = "config_items", primaryKeys = ["config", "parameter"])
data class ConfigItem(
    val config: String,
    val parameter: String,
    val value: String,
)