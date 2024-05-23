package com.spascoding.englishstructureconfig.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.spascoding.englishstructureconfig.presentation.config_screen.ConfigScreen
import com.spascoding.englishstructureconfig.presentation.theme.EnglishStructureConfigTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfigActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglishStructureConfigTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConfigScreen()
                }
            }
        }
    }
}