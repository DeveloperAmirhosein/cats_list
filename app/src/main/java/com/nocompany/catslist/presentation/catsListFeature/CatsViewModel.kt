package com.nocompany.catslist.presentation.catsListFeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.nocompany.catslist.domain.BookmarkCatsUseCase
import com.nocompany.catslist.domain.GetCatsUseCase
import com.nocompany.catslist.presentation.mapper.asDomainCatModel
import com.nocompany.catslist.presentation.mapper.asPresentationCatModel
import com.nocompany.catslist.presentation.model.CatModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase,
    private val bookmarkUseCase: BookmarkCatsUseCase,
) : ViewModel() {
    val cats = getCatsUseCase()
        .map { pagingData ->
            pagingData.map { it.asPresentationCatModel() }
        }
        .cachedIn(viewModelScope)


    fun bookmark(catModel: CatModel, bookMark: Boolean) {
        viewModelScope.launch {
            bookmarkUseCase(catModel.asDomainCatModel(), bookMark)
        }
    }
}