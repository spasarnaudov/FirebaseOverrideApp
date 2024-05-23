package com.spascoding.englishstructureconfig.domain.repository.model

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.remoteConfig
import org.json.JSONObject

object SharedPreferenceConfig {
    private fun getSelectedPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences("EnglishStructureConfigAppPrefs", Context.MODE_PRIVATE)
    }

    private fun clear(context: Context): Boolean {
        return getSelectedPrefs(context).edit().clear().commit()
    }

    fun toJson(context: Context): String {
        val prefs = getSelectedPrefs(context)
        val allEntries = prefs.all
        val jsonObject = JSONObject()
        if (allEntries != null) {
            for ((key, value) in allEntries) {
                jsonObject.put(key, value)
            }
        }
        return jsonObject.toString()
    }

    fun getParameters(context: Context): MutableMap<String, String> {
        val map: MutableMap<String, String> = mutableMapOf()
        getSelectedPrefs(context).all.forEach { entry ->
            map[entry.key] = entry.value.toString()
        }
        return map
    }

    fun editParameter(context: Context, key: String, value: String): Boolean {
        return getSelectedPrefs(context).edit().putString(key, value).commit()
    }

    fun syncFirebase(
        context: Context,
        getMap: (MutableMap<String, String>) -> Unit
    ) {
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (clear(context)) {
                val map: MutableMap<String, String> = mutableMapOf()
                remoteConfig.all.forEach { entry ->
                    if (getSelectedPrefs(context).edit().putString(entry.key, entry.value.asString()).commit()) {
                        map[entry.key] = entry.value.asString()
                    }
                }
                getMap.invoke(map)
            }
        }
    }

}