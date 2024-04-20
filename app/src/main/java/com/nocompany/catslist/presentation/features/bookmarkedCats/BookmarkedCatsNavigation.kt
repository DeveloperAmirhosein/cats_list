package com.nocompany.catslist.presentation.features.bookmarkedCats

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val BOOKMARKED_CATS_ROUTE = "bookmarked_cats_route"


fun NavController.navigateToBookmarkedCats() = navigate(BOOKMARKED_CATS_ROUTE)

fun NavGraphBuilder.bookmarkedCatsScreen() {
    composable(
        route = BOOKMARKED_CATS_ROUTE,
    ) {
        BookmarkedCatsRoute()
    }
}