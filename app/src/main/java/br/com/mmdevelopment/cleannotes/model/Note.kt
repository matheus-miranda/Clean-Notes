package br.com.mmdevelopment.cleannotes.model

data class Note(
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val id: Int = 0
)