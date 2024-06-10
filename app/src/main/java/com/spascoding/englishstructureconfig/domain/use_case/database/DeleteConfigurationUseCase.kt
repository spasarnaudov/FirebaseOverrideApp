package com.spascoding.englishstructureconfig.domain.use_case.database

import com.spascoding.englishstructureconfig.data.local.ConfigDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeleteConfigurationUseCase @Inject constructor(
    private val repository: ConfigDatabase
) {

    @OptIn(DelicateCoroutinesApi::class)
    operator fun invoke() {
        GlobalScope.launch(Dispatchers.IO) {
            repository.dao.deleteConfigAndSelectedConfig()
        }
    }

}