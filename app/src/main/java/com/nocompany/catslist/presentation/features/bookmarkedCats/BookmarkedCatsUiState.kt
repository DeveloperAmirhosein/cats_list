package com.nocompany.catslist.presentation.features.bookmarkedCats

import com.nocompany.catslist.domain.model.Cat

sealed interface BookmarkedCatsUiState {
    data class Success(val list: List<Cat>) : BookmarkedCatsUiState
    data object LoadFailed : BookmarkedCatsUiState
    data object Loading : BookmarkedCatsUiState
}