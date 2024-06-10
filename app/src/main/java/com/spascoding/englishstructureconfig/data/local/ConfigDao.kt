package com.spascoding.englishstructureconfig.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.spascoding.englishstructureconfig.domain.repository.model.ConfigItem
import com.spascoding.englishstructureconfig.domain.repository.model.SelectedConfig
import kotlinx.coroutines.flow.Flow

const val GET_PARAMETERS = "SELECT ci.* FROM config_items ci JOIN selected_config sc ON ci.config = sc.config WHERE sc.id = 1"

@Dao
interface ConfigDao {

    @Delete
    suspend fun delete(configItem: ConfigItem)

    @Upsert
    suspend fun upsert(configItem: ConfigItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(configItem: List<ConfigItem>)

    @Query(GET_PARAMETERS)
    fun getConfiguration(): Flow<List<ConfigItem>>

    @Query(GET_PARAMETERS)
    fun getConfigurationSync(): List<ConfigItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSelectedConfig(selectedConfig: SelectedConfig)

    @Query("SELECT * FROM selected_config WHERE id = 1")
    suspend fun getSelectedConfig(): SelectedConfig?

    @Query("SELECT * FROM selected_config WHERE id = 1")
    fun getSelectedConfigFlow(): Flow<SelectedConfig>

    @Query("SELECT DISTINCT config FROM config_items")
    fun getAllConfigConfigNames(): Flow<List<String>>
}