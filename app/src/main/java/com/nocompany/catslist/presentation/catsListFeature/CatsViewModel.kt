package com.nocompany.catslist.presentation.catsListFeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.nocompany.catslist.domain.GetCatsUseCase
import com.nocompany.catslist.presentation.mapper.asPresentationCatModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(private val getCatsUseCase: GetCatsUseCase) : ViewModel() {
    val cats = getCatsUseCase()
        .map { pagingData ->
            pagingData.map { it.asPresentationCatModel() }
        }
        .cachedIn(viewModelScope)
}