package com.spascoding.englishstructureconfig.domain.use_case.database

import com.spascoding.englishstructureconfig.data.local.ConfigDatabase
import com.spascoding.englishstructureconfig.domain.repository.model.ConfigItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetConfigurationUseCase @Inject constructor(
    private val repository: ConfigDatabase
) {

    operator fun invoke(config: String): Flow<List<ConfigItem>> {
        return repository.dao.getConfiguration(config)
    }

}