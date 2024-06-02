package com.spascoding.englishstructureconfig.domain.use_case.database

data class ConfigurationUseCases(
    val getConfigurationUseCase: GetConfigurationUseCase,
    val updateConfigurationFromFirebaseUseCase: UpdateConfigurationFromFirebaseUseCase,
    val upsertConfigurationUseCase: UpsertConfigurationUseCase,
)