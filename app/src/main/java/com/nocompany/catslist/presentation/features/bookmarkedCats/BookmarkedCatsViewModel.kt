package com.nocompany.catslist.presentation.features.bookmarkedCats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nocompany.catslist.common.Result
import com.nocompany.catslist.common.asResult
import com.nocompany.catslist.domain.usecase.GetBookmarkedCatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BookmarkedCatsViewModel @Inject constructor(
    getBookmarkedCatsUseCase: GetBookmarkedCatsUseCase,
) : ViewModel() {
    val bookmarkedCats: StateFlow<BookmarkedCatsUiState> =
        getBookmarkedCatsUseCase().asResult().map { result ->
            when (result) {
                is Result.Success -> {
                    BookmarkedCatsUiState.Success(result.data)
                }

                is Result.Loading -> {
                    BookmarkedCatsUiState.Loading
                }

                is Result.Error -> {
                    BookmarkedCatsUiState.LoadFailed
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = BookmarkedCatsUiState.Loading,
        )

}