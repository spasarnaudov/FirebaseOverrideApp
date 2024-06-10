package com.spascoding.englishstructureconfig.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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

@Composable
fun InputDialog(
    title: String,
    text: String = "",
    setShowDialog: (Boolean) -> Unit,
    setValue: (String) -> Unit,
) {
    val txtField = remember { mutableStateOf(text) }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    value = txtField.value,
                    onValueChange = {
                        txtField.value = it
                    },
                    label = { Text(title) },
                )

                Spacer(modifier = Modifier.height(20.dp))

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
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