package br.com.mmdevelopment.cleannotes.domain.model

import br.com.mmdevelopment.cleannotes.util.TestData
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class NoteTest {

    @Test
    fun `instantiate model`() {
        val note = TestData.NOTE

        assertThat(note.id).isEqualTo(0)
        assertThat(note.title).isEqualTo("Title")
        assertThat(note.description).isEqualTo("Description")
        assertThat(note.date).isEqualTo("01/01/2022")
        assertThat(note.time).isEqualTo("14:00")
    }
}