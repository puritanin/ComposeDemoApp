package com.bicubictwice.composedemoapp.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchRequestChip(
    modifier: Modifier = Modifier,
    text: String,
    onCloseClick: () -> Unit,
) {
    Card(
        modifier = modifier.padding(horizontal = 10.dp),
        shape = RoundedCornerShape(percent = 50),
        elevation = 6.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            IconButton(onClick = onCloseClick) {
                Icon(imageVector = Icons.Rounded.Close, contentDescription = "")
            }
            Spacer(Modifier.width(2.dp))
            Text(
                modifier = Modifier.padding(vertical = 6.dp),
                text = text,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.width(14.dp))
        }
    }
}
