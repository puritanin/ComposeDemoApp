package com.bicubictwice.composedemoapp.ui.search.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.OpenInNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.bicubictwice.composedemoapp.ui.search.BookViewEntity

@Composable
fun BookItem(
    modifier: Modifier = Modifier,
    item: BookViewEntity,
    onOpenLink: (String) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp,
    ) {

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp),
        ) {

            Row(
                verticalAlignment = Alignment.Top,
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = item.thumbnailLink,
                    ),
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = "",
                    modifier = Modifier
                        .width(60.dp)
                        .height(100.dp),
                )

                Spacer(Modifier.width(16.dp))

                Column {

                    Text(
                        text = item.title,
                        fontSize = 14.sp,
                    )

                    if (item.authors.isNotEmpty()) {
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = item.authors,
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Italic,
                        )
                    }

                    Spacer(Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        if (item.infoLink.isNotEmpty()) {
                            IconButton(onClick = { onOpenLink(item.infoLink) }) {
                                Icon(
                                    imageVector = Icons.Rounded.OpenInNew,
                                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                                    contentDescription = null,
                                )
                            }
                        }

                        Spacer(Modifier.weight(1f))
                        Text(
                            text = item.secondaryInfo,
                            fontSize = 12.sp,
                        )
                    }
                }
            }
        }
    }
}
