package com.spascoding.englishstructureconfig.data.providers

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import com.spascoding.englishstructureconfig.data.local.ConfigDatabase
import com.spascoding.englishstructureconfig.di.AppEntryPoint
import com.spascoding.englishstructureconfig.domain.repository.model.ConfigItem
import dagger.hilt.android.EntryPointAccessors
import org.json.JSONObject
import javax.inject.Inject

class ConfigProvider : ContentProvider() {

    private val TAG = "ConfigProvider"

    @Inject
    lateinit var database: ConfigDatabase

    override fun onCreate(): Boolean {
        val appContext = context?.applicationContext ?: throw IllegalStateException("Context cannot be null")
        val hiltEntryPoint = EntryPointAccessors.fromApplication(appContext, AppEntryPoint::class.java)
        database = hiltEntryPoint.getDatabase()
        return true
    }

    override fun query(
        uri: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor {
        return database.dao.getConfigurationSync().toCursor()
    }

    private fun List<ConfigItem>.toCursor(): MatrixCursor {
        val json = toJson()
        val matrixCursor = MatrixCursor(arrayOf("json"))
        matrixCursor.addRow(arrayOf(json))
        return matrixCursor
    }

    private fun List<ConfigItem>.toJson(): String {
        val jsonObject = JSONObject()
        for ((config, key, value) in this) {
            jsonObject.put(key, value)
        }
        return jsonObject.toString()
    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        throw UnsupportedOperationException("Not supported")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        throw UnsupportedOperationException("Not supported")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        throw UnsupportedOperationException("Not supported")
    }
}