package com.spascoding.englishstructureconfig.domain.repository

enum class ValueType {
    STRING,
    INT,
    BOOLEAN
}

class GetValueType(private val value: String) {

    fun get(): ValueType {
        if (isInt(value)) return ValueType.INT
        if (isBoolean(value)) return ValueType.BOOLEAN
        return ValueType.STRING
    }

    private fun isInt(value: String): Boolean {
        return value.toIntOrNull() != null
    }
    private fun isBoolean(value: String): Boolean {
        return value == "true" || value == "false"
    }

}