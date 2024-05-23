package com.spascoding.englishstructureconfig.presentation.config_screen

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.spascoding.englishstructureconfig.domain.repository.model.SharedPreferenceConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConfigScreenViewModel @Inject constructor(
    val context: Context
) : ViewModel() {

    private val _state = mutableStateOf(ConfigScreenState())
    val state: State<ConfigScreenState> = _state

    init {
        syncPreferences()
    }

    fun syncFirebase() {
        SharedPreferenceConfig.syncFirebase(context) {
            _state.value = state.value.copy(
                config = it,
            )
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

}