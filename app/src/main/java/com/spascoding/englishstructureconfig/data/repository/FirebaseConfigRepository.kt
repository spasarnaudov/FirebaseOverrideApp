package com.spascoding.englishstructureconfig.data.repository

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.remoteConfig
import com.spascoding.englishstructureconfig.domain.repository.ConfigItem
import com.spascoding.englishstructureconfig.domain.repository.ConfigRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FirebaseConfigRepository : ConfigRepository {

    private val itemsFlow = MutableStateFlow<List<ConfigItem>>(emptyList())

    init {
        fetchConfigItems()
    }

    override fun getConfigItems(): Flow<List<ConfigItem>> {
        return itemsFlow
    }

    private fun fetchConfigItems() {
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val items: MutableList<ConfigItem> = mutableListOf()
                remoteConfig.all.forEach { entry ->
                    items.add(ConfigItem(entry.key, entry.value.asString()))
                }
                itemsFlow.value = items
            } else {
                // Handle error
            }
        }
    }
}