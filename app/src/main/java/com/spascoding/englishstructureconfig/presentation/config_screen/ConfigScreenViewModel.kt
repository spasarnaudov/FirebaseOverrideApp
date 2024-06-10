package com.spascoding.englishstructureconfig.presentation.config_screen

import androidx.lifecycle.ViewModel
import com.spascoding.englishstructureconfig.domain.repository.model.ConfigItem
import com.spascoding.englishstructureconfig.domain.repository.model.SelectedConfig
import com.spascoding.englishstructureconfig.domain.use_case.database.ConfigurationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ConfigScreenViewModel @Inject constructor(
    private val configurationUseCases: ConfigurationUseCases,
) : ViewModel() {

    fun syncFirebase() {
        configurationUseCases.syncConfigurationFromFirebaseUseCase.invoke()
    }

    fun setParameter(configItem: ConfigItem) {
        configurationUseCases.upsertConfigurationUseCase(configItem)
    }

    fun getConfiguration(): Flow<List<ConfigItem>> {
        return configurationUseCases.getConfigurationUseCase.invoke()
    }

    fun getConfigurationFlow(): Flow<SelectedConfig> {
        return configurationUseCases.getSelectedConfigurationUseCase.invoke()
    }

    fun getAllConfigNamesUseCase(): Flow<List<String>> {
        return configurationUseCases.getAllConfigNamesUseCase.invoke()
    }

    fun selectConfiguration(name: String) {
        configurationUseCases.selectConfigurationUseCase.invoke(SelectedConfig(config = name))
    }

}