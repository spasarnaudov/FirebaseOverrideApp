package com.spascoding.englishstructureconfig.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.spascoding.englishstructureconfig.domain.repository.GetValueType
import com.spascoding.englishstructureconfig.domain.repository.ValueType

@Composable
fun CustomDialog(
    parameter: String,
    value: String,
    setShowDialog: (Boolean) -> Unit,
    setValue: (String) -> Unit,
) {
    val txtField = remember { mutableStateOf(value) }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = parameter,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(20.dp))

                val valueType = GetValueType(value).get()
                Log.d("asdfg", valueType.name)
                when (valueType) {
                    ValueType.STRING -> {
                        StringInputField(txtField.value) {
                            txtField.value = it
                        }
                    }

                    ValueType.INT -> {
                        IntInputField(txtField.value) {
                            txtField.value = it
                        }
                    }

                    ValueType.BOOLEAN -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = txtField.value == "true",
                                onClick = { txtField.value = "true" }
                            )
                            Text("true")
                            RadioButton(
                                selected = txtField.value == "false",
                                onClick = { txtField.value = "false" }
                            )
                            Text("false")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if (txtField.value.isEmpty()) {
                            return@Button
                        }
                        setValue(txtField.value)
                        setShowDialog(false)
                    },
                ) {
                    Text(text = "Done")
                }
            }
        }
    }
}