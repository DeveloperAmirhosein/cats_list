package com.nocompany.catslist.presentation.catsListFeature

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.nocompany.catslist.domain.model.Cat

@Composable
fun CatsListRoute(viewModel: CatsViewModel = hiltViewModel()) {
    val cats = viewModel.cats.collectAsLazyPagingItems()
    CatsListScreen(cats = cats)
}

@Composable
fun CatsListScreen(
    cats: LazyPagingItems<Cat>
) {
}