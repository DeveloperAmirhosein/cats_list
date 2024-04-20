package com.nocompany.catslist.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatEntity(
    @PrimaryKey
    val id: String,
    val url: String,
    val width: Long,
    val height: Long,
)