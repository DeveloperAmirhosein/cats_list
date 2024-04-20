package com.nocompany.catslist.presentation.features.catsListFeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.nocompany.catslist.domain.usecase.BookmarkCatUseCase
import com.nocompany.catslist.domain.usecase.GetBookmarkedCatsUseCase
import com.nocompany.catslist.domain.usecase.GetCatsUseCase
import com.nocompany.catslist.domain.model.BookmarkableCat
import com.nocompany.catslist.presentation.mapper.asDomainCatModel
import com.nocompany.catslist.presentation.mapper.asPresentationCatModel
import com.nocompany.catslist.presentation.model.CatModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase,
    private val bookmarkUseCase: BookmarkCatUseCase,
    private val getBookmarkedCatsUseCase: GetBookmarkedCatsUseCase,
) : ViewModel() {

    // because paging data needs using "viewModelScope" these two
    // useCase can not be combined into another use case
    val cats = combine(
        getCatsUseCase().cachedIn(viewModelScope),
        getBookmarkedCatsUseCase()
    ) { pagingCats, bookmarkedCats ->
        pagingCats.map { cat ->
            BookmarkableCat(
                cat,
                isBookmarked = cat.id in bookmarkedCats.map {
                    it.id
                })
        }
    }.map { pagingData ->
        pagingData.map { it.asPresentationCatModel() }
    }


    fun bookmark(catModel: CatModel, bookMark: Boolean) {
        viewModelScope.launch {
            bookmarkUseCase(catModel.asDomainCatModel(), bookMark)
        }
    }
}