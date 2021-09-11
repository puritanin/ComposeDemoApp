package com.bicubictwice.composedemoapp.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.bicubictwice.composedemoapp.ui.search.list.LazyBooksList
import com.bicubictwice.composedemoapp.ui.widgets.SearchBar
import com.bicubictwice.composedemoapp.ui.widgets.SearchRequestChip
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchScreenViewModel = viewModel(SearchScreenViewModel::class.java)
) {
    val searchPagerState by viewModel.searchPager.collectAsState()
    val lazyPagingItems = searchPagerState?.flow?.collectAsLazyPagingItems()

    val scope = rememberCoroutineScope()

    var searchBarEnabled by remember { mutableStateOf(true) }
    var searchRequestText by remember { mutableStateOf("") }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        LazyBooksList(
            modifier = Modifier.fillMaxSize(),
            lazyPagingItems = lazyPagingItems,
            header = { headerModifier ->

                Column(
                    modifier = headerModifier,
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    SearchBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        enabled = searchBarEnabled,
                        onSearchClick = {
                            scope.launch {
                                delay(250L)
                                searchBarEnabled = false
                                searchRequestText = it.trim()
                                viewModel.search(searchRequestText)
                            }
                        },
                    )

                    if (searchRequestText.isNotEmpty()) {
                        SearchRequestChip(
                            text = searchRequestText,
                            onCloseClick = {
                                scope.launch {
                                    delay(250L)
                                    viewModel.resetSearch()
                                    searchRequestText = ""
                                    searchBarEnabled = true
                                }
                            },
                        )
                        Spacer(Modifier.height(20.dp))
                    }
                }
            },
        )
    }
}
