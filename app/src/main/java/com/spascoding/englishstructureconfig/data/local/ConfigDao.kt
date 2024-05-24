package com.spascoding.englishstructureconfig.data.local

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spascoding.englishstructureconfig.domain.repository.model.ConfigItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ConfigDao {

    @Delete
    suspend fun delete(configItem: ConfigItem)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(configItem: ConfigItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(configItem: List<ConfigItem>)

    @Query("SELECT * FROM config_items WHERE config=:config")
    fun getConfiguration(config: String): Flow<List<ConfigItem>>

    @Query("SELECT * FROM config_items WHERE config=:config")
    fun getConfigurationSync(config: String): List<ConfigItem>
}