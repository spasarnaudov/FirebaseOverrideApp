package com.spascoding.englishstructureconfig.domain.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selected_config")
data class SelectedConfig(
    @PrimaryKey val id: Int = 1, // Assuming there will only be one selected configuration
    val config: String
)
