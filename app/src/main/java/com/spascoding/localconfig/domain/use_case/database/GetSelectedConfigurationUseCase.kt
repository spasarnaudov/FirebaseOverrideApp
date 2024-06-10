package com.spascoding.localconfig.domain.use_case.database

import com.spascoding.localconfig.data.local.ConfigDatabase
import com.spascoding.localconfig.domain.repository.model.SelectedConfig
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedConfigurationUseCase @Inject constructor(
    private val repository: ConfigDatabase
) {
    operator fun invoke(): Flow<SelectedConfig> {
        return repository.dao.getSelectedConfigFlow()
    }
}