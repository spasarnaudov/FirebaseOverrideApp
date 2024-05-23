package com.spascoding.englishstructureconfig.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.spascoding.englishstructureconfig.constants.Padding
import com.spascoding.englishstructureconfig.constants.RoundCorner

@Composable
fun BorderedListElement(
    onClickItem: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val localDensity = LocalDensity.current
    var heightIs by remember {
        mutableStateOf(0.dp)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = Padding.MEDIUM,
                bottom = Padding.MEDIUM,
                end = Padding.MEDIUM,
            )
            .background(
                MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                RoundedCornerShape(RoundCorner.MEDIUM)
            )
            .border(
                1.dp,
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(RoundCorner.MEDIUM)
            )
            .clip(RoundedCornerShape(RoundCorner.MEDIUM))
            .clickable(
                enabled = true,
                onClick = {
                    onClickItem?.invoke()
                }
            )
            .onGloballyPositioned { coordinates ->
                heightIs = with(localDensity) { coordinates.size.height.toDp() }
            },
    ) {
        Box(
            modifier = Modifier
                .height(heightIs)
                .width(Padding.SMALL)
                .background(
                    MaterialTheme.colorScheme.primary,
                )
        )
        content.invoke(this)
    }
}