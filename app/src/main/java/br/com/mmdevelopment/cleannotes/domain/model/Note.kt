package br.com.mmdevelopment.cleannotes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    var id: Int = 0,
    val title: String,
    val description: String?,
    val date: String?,
    val time: String?
) : Parcelable