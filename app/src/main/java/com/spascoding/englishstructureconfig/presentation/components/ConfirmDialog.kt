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
fun ConfirmDialog(
    text: String,
    setShowDialog: (Boolean) -> Unit,
    onConfirm: () -> Unit,
) {
    val txtField = remember { mutableStateOf(text) }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = text,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        if (txtField.value.isEmpty()) {
                            return@Button
                        }
                        onConfirm()
                        setShowDialog(false)
                    },
                ) {
                    Text(text = "Yes")
                }
            }
        }
    }
}