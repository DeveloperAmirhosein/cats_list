package com.nocompany.catslist.presentation.catsListFeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nocompany.catslist.domain.GetCatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(private val getCatsUseCase: GetCatsUseCase) : ViewModel() {
    val cats = getCatsUseCase().cachedIn(viewModelScope)
}