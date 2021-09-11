package com.bicubictwice.composedemoapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.bicubictwice.composedemoapp.data.repository.api.GoogleBooksRepositoryApi
import com.bicubictwice.composedemoapp.data.source.api.SearchFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val googleBooksRepositoryApi: GoogleBooksRepositoryApi,
) : ViewModel() {

    private val _searchPager = MutableStateFlow<Pager<Int, BookViewEntity>?>(null)
    val searchPager: StateFlow<Pager<Int, BookViewEntity>?> = _searchPager

    fun search(query: String) {
        _searchPager.value = Pager(
            PagingConfig(
                pageSize = LOAD_PAGE_SIZE,
                initialLoadSize = LOAD_PAGE_SIZE,
            )
        ) {
            BooksPagingSource(
                query,
                SearchFilter.FULL,
                googleBooksRepositoryApi,
            )
        }
    }

    fun resetSearch() {
        _searchPager.value = null
    }

    companion object {
        private const val LOAD_PAGE_SIZE = 20
    }
}
