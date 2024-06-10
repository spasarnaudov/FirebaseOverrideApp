package com.spascoding.localconfig.domain.use_case.database

import com.spascoding.localconfig.data.local.ConfigDatabase
import com.spascoding.localconfig.domain.repository.model.ConfigItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetConfigurationUseCase @Inject constructor(
    private val repository: ConfigDatabase
) {

    operator fun invoke(): Flow<List<ConfigItem>> {
        return repository.dao.getConfiguration()
    }

}