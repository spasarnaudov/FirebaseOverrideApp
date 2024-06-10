package com.spascoding.localconfig.domain.use_case.database

import com.spascoding.localconfig.data.local.ConfigDatabase
import com.spascoding.localconfig.domain.repository.model.ConfigItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpsertConfigurationUseCase @Inject constructor(
    private val repository: ConfigDatabase
) {

    operator fun invoke(configItem: ConfigItem) {
        GlobalScope.launch(Dispatchers.IO) {
            repository.dao.upsert(configItem)
        }
    }

}