package com.nocompany.catslist.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatModel(
    val id: String,
    val url: String,
    val width: Long,
    val height: Long,
    val isBookmarked: Boolean,
) : Parcelable