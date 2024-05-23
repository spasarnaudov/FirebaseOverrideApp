package com.spascoding.englishstructureconfig

import android.app.Application
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EnglishStructureConfigApplication: Application() {

    val TAG = "EnglishStructureConfigApplication"

    override fun onCreate() {
        super.onCreate()
        setupFirebaseRemoteConfig()
    }

    private fun setupFirebaseRemoteConfig() {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600 // Fetch every hour for production build
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        // Fetch and activate the values
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val updated = task.result
                // Log or handle the successful fetching and activation of remote configs
                Log.d(TAG, "Remote config values were updated: $updated")
            } else {
                // Log or handle the failure to fetch and activate
                Log.e(TAG, "Failed to fetch and activate remote config")
            }
            printParameters(remoteConfig.all)
        }
    }

    private fun printParameters(map: Map<String, FirebaseRemoteConfigValue>) {
        map.forEach { entry ->
            Log.d(TAG, "${entry.key}: ${entry.value.asString()}.")
        }
    }

}