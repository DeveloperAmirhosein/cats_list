package com.nocompany.catslist.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatEntity(
    @PrimaryKey(autoGenerate = true) val localId: Int = 0,
    val id: String,
    val url: String,
    val width: Long,
    val height: Long,
)