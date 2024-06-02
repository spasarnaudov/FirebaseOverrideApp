package com.spascoding.englishstructureconfig.presentation.config_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spascoding.englishstructureconfig.constants.Padding
import com.spascoding.englishstructureconfig.domain.repository.model.ConfigItem
import com.spascoding.englishstructureconfig.presentation.components.BorderedListElement
import com.spascoding.englishstructureconfig.presentation.components.CustomDialog

@Composable
fun ConfigScreen(
    viewModel: ConfigScreenViewModel = hiltViewModel()
) {
    val configItems by viewModel.getConfiguration().collectAsState(initial = emptyList())

    val showDialog = remember { mutableStateOf(false) }
    val configItemTemp = remember { mutableStateOf(ConfigItem("", "", "")) }

    if (showDialog.value)
        CustomDialog(
            parameter = configItemTemp.value.parameter,
            value = configItemTemp.value.value,
            setShowDialog = {
                showDialog.value = it
            }
        ) {
            if (it.isNotBlank()) {
                viewModel.setParameter(configItemTemp.value.copy(value = it))
            }
        }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.padding(vertical = 8.dp),
            onClick = {
                viewModel.syncFirebase()
            }) {
            Text(text = "Load from Firebase")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            configItems.forEach { configItem ->
                item {
                    BorderedListElement(
                        onClickItem = {
                            showDialog.value = true
                            configItemTemp.value = configItem
                        }
                    ) {
                        Column(
                            modifier = Modifier.padding(Padding.MEDIUM)
                        ) {
                            Text(
                                modifier = Modifier.padding(start = Padding.SMALL),
                                text = configItem.parameter,
                                fontWeight = FontWeight.Bold,
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(start = Padding.SMALL),
                                    text = configItem.value,
                                )
                                Icon(
                                    modifier = Modifier,
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}