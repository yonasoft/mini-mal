package com.yonasoft.minimal.components


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yonasoft.minimal.ui.theme.Blue2


@Composable
fun SearchTextField(
    //Todo: Text parameter
    onSearch: (String) -> Unit
) {
    //TODO: Move to view model
    var text by remember { mutableStateOf("") }

        TextField(

            value = text,
            textStyle = TextStyle.Default.copy(fontSize = 14.sp, textAlign = TextAlign.Center),
            onValueChange = { text = it },
            modifier = Modifier
                .padding(8.dp)
                ,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                cursorColor = Color.Black,
                disabledLabelColor = Blue2,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(16.dp),
            placeholder = {
                Text(text = "Search...", fontSize = 14.sp, textAlign = TextAlign.Center)
            },
            maxLines = 1,
            trailingIcon = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(text)
                }
            )
        )
}