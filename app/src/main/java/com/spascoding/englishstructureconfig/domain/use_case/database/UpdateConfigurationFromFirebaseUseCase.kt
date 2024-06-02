package com.spascoding.englishstructureconfig.domain.use_case.database

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.remoteConfig
import com.spascoding.englishstructureconfig.data.local.ConfigDatabase
import com.spascoding.englishstructureconfig.domain.repository.model.ConfigItem
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpdateConfigurationFromFirebaseUseCase @Inject constructor(
    private val repository: ConfigDatabase
) {
    @OptIn(DelicateCoroutinesApi::class)
    operator fun invoke(config: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val remoteConfig = Firebase.remoteConfig
            remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
                val list: MutableList<ConfigItem> = mutableListOf()
                remoteConfig.all.forEach { entry ->
                    list.add(
                        ConfigItem(
                            config = config,
                            parameter = entry.key,
                            value = entry.value.asString(),
                        )
                    )
                }
                GlobalScope.launch(Dispatchers.IO) {
                    repository.dao.add(list.toList())
                }
            }
        }
    }
}