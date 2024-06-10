package com.spascoding.localconfig.domain.use_case.database

import com.spascoding.localconfig.data.local.ConfigDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllConfigNamesUseCase @Inject constructor(
    private val repository: ConfigDatabase
) {
    operator fun invoke(): Flow<List<String>> {
        return repository.dao.getAllConfigConfigNames()
    }
}