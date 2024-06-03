package com.spascoding.englishstructureconfig.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spascoding.englishstructureconfig.domain.repository.model.ConfigItem
import com.spascoding.englishstructureconfig.domain.repository.model.SelectedConfig

@Database(
    entities = [ConfigItem::class, SelectedConfig::class],
    version = 1,
)
abstract class ConfigDatabase: RoomDatabase() {
    abstract val dao: ConfigDao
}