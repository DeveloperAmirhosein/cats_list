package com.nocompany.catslist.domain.model

data class BookmarkableCat(
    val cat: Cat,
    val isBookmarked: Boolean = false
)