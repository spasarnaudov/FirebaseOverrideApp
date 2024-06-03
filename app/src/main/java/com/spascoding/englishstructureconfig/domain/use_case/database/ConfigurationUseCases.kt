package com.spascoding.englishstructureconfig.domain.use_case.database

data class ConfigurationUseCases(
    val getConfigurationUseCase: GetConfigurationUseCase,
    val syncConfigurationFromFirebaseUseCase: SyncConfigurationFromFirebaseUseCase,
    val upsertConfigurationUseCase: UpsertConfigurationUseCase,
    val selectConfigurationUseCase: SelectConfigurationUseCase,
)