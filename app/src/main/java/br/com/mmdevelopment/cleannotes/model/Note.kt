package br.com.mmdevelopment.cleannotes.model

data class Note(
    val title: String,
    val description: String,
    val date: String,
    val time: String,
    val id: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}