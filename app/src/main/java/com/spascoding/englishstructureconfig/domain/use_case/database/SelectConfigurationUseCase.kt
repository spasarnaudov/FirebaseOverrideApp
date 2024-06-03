package com.spascoding.englishstructureconfig.domain.use_case.database

import com.spascoding.englishstructureconfig.data.local.ConfigDatabase
import com.spascoding.englishstructureconfig.domain.repository.model.SelectedConfig
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SelectConfigurationUseCase @Inject constructor(
    private val repository: ConfigDatabase
) {

    @OptIn(DelicateCoroutinesApi::class)
    operator fun invoke(selectedConfig: SelectedConfig) {
        GlobalScope.launch(Dispatchers.IO) {
            repository.dao.insertSelectedConfig(selectedConfig)
        }
    }

}