package com.bicubictwice.composedemoapp.ui.search.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorItem(
    modifier: Modifier = Modifier,
    text: String,
    onRetry: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp,
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(Modifier.width(4.dp))
            OutlinedButton(onClick = onRetry) { Text("Retry") }
            Spacer(Modifier.width(16.dp))
            Text(
                text = text,
                color = MaterialTheme.colors.error,
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic,
            )
        }
    }
}
