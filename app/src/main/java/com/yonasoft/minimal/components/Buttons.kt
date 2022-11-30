package com.yonasoft.minimal.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yonasoft.minimal.ui.theme.Blue1

@Composable
fun MiniButton(onClick:() -> Unit, text:String) {
    Card(
        modifier = Modifier
            .width(60.dp)
            .clickable {onClick() },
        backgroundColor = Blue1,
        shape = CircleShape,
        elevation = 12.dp
    ) { Text(text = text, textAlign = TextAlign.Center) }
}