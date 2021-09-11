package com.bicubictwice.composedemoapp.ui.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onSearchClick: (String) -> Unit,
) {
    var searchFieldValue by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    fun done() {
        if (searchFieldValue.isNotBlank()) {
            onSearchClick(searchFieldValue)
            searchFieldValue = ""
        }
        focusManager.clearFocus()
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(1f),
            enabled = enabled,
            label = { Text("Google Books API query") },
            singleLine = true,
            maxLines = 1,
            value = searchFieldValue,
            onValueChange = { searchFieldValue = it },
            keyboardActions = KeyboardActions(onDone = { done() }),
        )

        IconButton(
            enabled = enabled && searchFieldValue.isNotBlank(),
            onClick = { done() },
        ) {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = "")
        }
    }
}
