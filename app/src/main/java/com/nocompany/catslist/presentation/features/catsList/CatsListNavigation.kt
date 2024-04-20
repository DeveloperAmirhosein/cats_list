package com.nocompany.catslist.presentation.features.catsList

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val CATS_LIST_ROUTE = "cats_list_route"


fun NavController.navigateToCatsList(navOptions: NavOptions) = navigate(CATS_LIST_ROUTE)

fun NavGraphBuilder.catsListScreen(onBookmarkButtonClick: () -> Unit) {
    composable(
        route = CATS_LIST_ROUTE,
    ) {
        CatsListRoute(onBookmarkButtonClick)
    }
}