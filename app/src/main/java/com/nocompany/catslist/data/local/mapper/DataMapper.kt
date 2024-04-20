package com.nocompany.catslist.data.local.mapper

import com.nocompany.catslist.data.local.CatEntity
import com.nocompany.catslist.domain.model.Cat

fun Cat.asEntity(): CatEntity = CatEntity(
    id = id,
    url = url,
    width = width,
    height = height
)

fun CatEntity.asExternalModel(): Cat = Cat(
    id = id,
    url = url,
    width = width,
    height = height
)