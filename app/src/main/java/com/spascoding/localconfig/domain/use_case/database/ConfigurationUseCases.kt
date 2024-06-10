package com.spascoding.localconfig.domain.use_case.database

data class ConfigurationUseCases(
    val getConfigurationUseCase: GetConfigurationUseCase,
    val syncConfigurationFromFirebaseUseCase: SyncConfigurationFromFirebaseUseCase,
    val upsertConfigurationUseCase: UpsertConfigurationUseCase,
    val selectConfigurationUseCase: SelectConfigurationUseCase,
    val getSelectedConfigurationUseCase: GetSelectedConfigurationUseCase,
    val getAllConfigNamesUseCase: GetAllConfigNamesUseCase,
    val deleteSelectedConfigurationUseCase: DeleteConfigurationUseCase,
)