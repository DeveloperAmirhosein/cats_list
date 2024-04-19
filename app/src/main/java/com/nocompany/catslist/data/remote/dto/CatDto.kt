package com.nocompany.catslist.data.remote.dto

import com.nocompany.catslist.domain.model.Cat
import com.squareup.moshi.Json

data class CatDto(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "width") val width: Long,
    @field:Json(name = "height") val height: Long,
)

fun CatDto.asExternalModel(): Cat {
    return Cat(id, url, width, height)
}