package com.nocompany.catslist.presentation.features.catsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.nocompany.catslist.domain.usecase.BookmarkCatUseCase
import com.nocompany.catslist.domain.usecase.GetBookmarkedCatsUseCase
import com.nocompany.catslist.domain.usecase.GetCatsUseCase
import com.nocompany.catslist.presentation.mapper.asDomainCatModel
import com.nocompany.catslist.presentation.model.CatModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
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
            CatModel(
                cat.id,
                cat.url,
                cat.width,
                cat.height,
                isBookmarked = cat.id in bookmarkedCats.map {
                    it.id
                })
        }
    }


    fun bookmark(catModel: CatModel, bookMark: Boolean) {
        viewModelScope.launch {
            bookmarkUseCase(catModel.asDomainCatModel(), bookMark)
        }
    }
}