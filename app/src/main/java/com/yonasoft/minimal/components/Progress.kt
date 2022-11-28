package com.yonasoft.minimal.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yonasoft.minimal.ui.theme.Blue1

@Composable
fun CircularProgress(rowModifier: Modifier, indicatorModifier: Modifier, color:Color, strokeWidth:Dp) {
    Row(
        modifier = rowModifier,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = indicatorModifier,
            color = color,
            strokeWidth = strokeWidth,
        )
    }
}