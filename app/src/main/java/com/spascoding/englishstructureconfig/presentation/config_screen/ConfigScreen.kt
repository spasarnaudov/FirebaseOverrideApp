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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spascoding.englishstructureconfig.constants.Padding
import com.spascoding.englishstructureconfig.presentation.components.BorderedListElement
import com.spascoding.englishstructureconfig.presentation.components.CustomDialog

@Composable
fun ConfigScreen(
    viewModel: ConfigScreenViewModel = hiltViewModel()
) {
    val showDialog = remember { mutableStateOf(false) }
    val rememberParameter = remember { mutableStateOf("") }
    val rememberValue = remember { mutableStateOf("") }

    if (showDialog.value)
        CustomDialog(
            parameter = rememberParameter.value,
            value = rememberValue.value,
            setShowDialog = {
                showDialog.value = it
            }
        ) {
            if (it.isNotBlank()) {
                viewModel.setConfig(rememberParameter.value, it)
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
            viewModel.state.value.config.forEach { (parameter, value) ->
                item {
                    BorderedListElement(
                        onClickItem = {
                            showDialog.value = true
                            rememberParameter.value = parameter
                            rememberValue.value = value
                        }
                    ) {
                        Column(
                            modifier = Modifier.padding(Padding.MEDIUM)
                        ) {
                            Text(
                                modifier = Modifier.padding(start = Padding.SMALL),
                                text = parameter,
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
                                    text = value,
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