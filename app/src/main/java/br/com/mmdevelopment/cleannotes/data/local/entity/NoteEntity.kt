package br.com.mmdevelopment.cleannotes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @PrimaryKey (autoGenerate = true) val id: Int,
    val title: String,
    val description: String?,
    val date: String?,
    val time: String?
)