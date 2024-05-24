package com.spascoding.englishstructureconfig.presentation.config_screen

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.spascoding.englishstructureconfig.data.local.ConfigDatabase
import com.spascoding.englishstructureconfig.domain.repository.model.ConfigItem
import com.spascoding.englishstructureconfig.domain.repository.model.SharedPreferenceConfig
import com.spascoding.englishstructureconfig.domain.use_case.database.GetConfigurationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfigScreenViewModel @Inject constructor(
    val context: Context,
    private val configDatabase: ConfigDatabase,
    private val configurationUseCase: GetConfigurationUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(ConfigScreenState())
    val state: State<ConfigScreenState> = _state

    init {
        syncPreferences()
    }

    fun syncFirebase() {
        SharedPreferenceConfig.syncFirebase {
            GlobalScope.launch(Dispatchers.IO) {
                configDatabase.dao.add(it.toList())
            }

//            _state.value = state.value.copy(
//                config = it,
//            )
        }
    }

    fun syncPreferences() {
        val map = SharedPreferenceConfig.getParameters(context)
        _state.value = state.value.copy(
            config = map,
        )
    }

    fun setConfig(key: String, value: String) {
        if (SharedPreferenceConfig.editParameter(context, key, value)) {
            state.value.config[key] = value
            _state.value = state.value.copy(
                config = state.value.config,
            )
        }
    }

    fun getConfiguration(): Flow<List<ConfigItem>> {
        return configurationUseCase.invoke("main")
    }

}