package com.nocompany.catslist.data.mapper

import com.nocompany.catslist.data.local.entities.CatEntity
import com.nocompany.catslist.data.remote.dto.CatDto
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

fun CatDto.asExternalModel(): Cat {
    return Cat(id, url, width, height)
}