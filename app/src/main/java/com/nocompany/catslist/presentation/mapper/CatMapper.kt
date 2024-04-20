package com.nocompany.catslist.presentation.mapper

import com.nocompany.catslist.domain.model.Cat
import com.nocompany.catslist.presentation.model.CatModel


fun CatModel.asDomainCatModel(): Cat =
    Cat(id, url, width, height)