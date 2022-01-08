package br.com.mmdevelopment.cleannotes.util

import br.com.mmdevelopment.cleannotes.data.local.entity.NoteEntity
import br.com.mmdevelopment.cleannotes.domain.model.Note
import io.mockk.MockKAnnotations

object TestData {

    fun Any.initMockkAnnotations() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    val NOTE =
        Note(
            id = 0,
            title = "Title",
            description = "Description",
            date = "01/01/2022",
            time = "14:00"
        )

    val ENTITY_NOTE =
        NoteEntity(
            id = 0,
            title = "Entity Title",
            description = "Entity Description",
            date = "02/02/2022",
            time = "08:00"
        )
}