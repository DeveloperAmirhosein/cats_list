package com.nocompany.catslist.presentation.mapper

import com.nocompany.catslist.domain.model.BookmarkableCat
import com.nocompany.catslist.presentation.model.CatModel

fun BookmarkableCat.asPresentationCatModel(): CatModel =
    CatModel(cat.id, cat.url, cat.width, cat.height, isBookmarked)