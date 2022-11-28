package com.yonasoft.minimal.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun CircularProgress(boxModifier: Modifier, alignment: Alignment, indicatorModifier: Modifier, color:Color, strokeWidth:Dp) {
    Box(
        modifier = boxModifier ,
        contentAlignment = alignment
    ) {
        CircularProgressIndicator(
            modifier = indicatorModifier,
            color = color,
            strokeWidth = strokeWidth,
        )
    }
}