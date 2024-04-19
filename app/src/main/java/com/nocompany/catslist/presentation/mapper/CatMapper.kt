package com.nocompany.catslist.presentation.mapper

import com.nocompany.catslist.domain.model.Cat
import com.nocompany.catslist.presentation.model.CatModel

fun Cat.asPresentationCatModel(): CatModel = CatModel(id, url, width, height)