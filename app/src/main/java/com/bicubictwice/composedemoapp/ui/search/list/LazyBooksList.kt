package com.bicubictwice.composedemoapp.ui.search.list

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import com.bicubictwice.composedemoapp.ui.search.BookViewEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LazyBooksList(
    modifier: Modifier = Modifier,
    header: @Composable (modifier: Modifier) -> Unit,
    lazyPagingItems: LazyPagingItems<BookViewEntity>?,
) {
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val isScrollTopButtonVisible by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex > 3 }
    }

    var scrolledY = 0f
    var previousOffset = 0
    var height = 0

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = lazyListState,
    ) {

        item {
            header(
                modifier = Modifier
                    .onGloballyPositioned {
                        height = it.size.height
                    }
                    .graphicsLayer {
                        scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                        translationY = scrolledY * 0.2f
                        alpha = 1f - ((scrolledY * 1.2f) / height.coerceAtLeast(1)).coerceIn(0f, 1f)
                        previousOffset = lazyListState.firstVisibleItemScrollOffset
                    }
            )
        }

        lazyPagingItems?.let {

            items(
                count = lazyPagingItems.itemCount,
                key = null,
                contentType = lazyPagingItems.itemContentType { null },
            ) { index ->
                val item = lazyPagingItems[index]!!
                BookItem(
                    item = item,
                    onOpenLink = { link ->
                        ContextCompat.startActivity(
                            context,
                            Intent(Intent.ACTION_VIEW, Uri.parse(link)),
                            null
                        )
                    },
                )
            }

            it.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            LoadingItem(modifier = Modifier.padding(vertical = 20.dp))
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val e = it.loadState.refresh as LoadState.Error
                        item {
                            ErrorItem(
                                text = "${e.error.message}",
                                onRetry = { retry() },
                            )
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            LoadingItem(modifier = Modifier.padding(vertical = 10.dp))
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val e = it.loadState.append as LoadState.Error
                        item {
                            ErrorItem(
                                text = "${e.error.message}",
                                onRetry = { retry() },
                            )
                        }
                    }
                }
            }
        }
    }

    if (isScrollTopButtonVisible) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd,
        ) {
            ScrollTopButton(
                onClick = {
                    scope.launch {
                        delay(150L)
                        lazyListState.animateScrollToItem(0)
                    }
                }
            )
        }
    }
}

@Composable
private fun ScrollTopButton(
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .alpha(0.7f)
            .padding(16.dp),
        shape = RoundedCornerShape(percent = 50),
        elevation = 4.dp,
    ) {
        IconButton(onClick = onClick) {
            Icon(
                modifier = Modifier.scale(1.2f),
                imageVector = Icons.Rounded.ExpandLess,
                tint = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                contentDescription = null,
            )
        }
    }
}
