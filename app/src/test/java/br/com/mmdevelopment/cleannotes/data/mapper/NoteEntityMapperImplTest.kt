package br.com.mmdevelopment.cleannotes.data.mapper

import br.com.mmdevelopment.cleannotes.util.TestData.ENTITY_NOTE
import br.com.mmdevelopment.cleannotes.util.TestData.NOTE
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class NoteEntityMapperImplTest {

    private val mapper = NoteEntityMapperImpl()

    @Test
    fun toDomain() {
        val expectedNote = NOTE

        val actualNote = mapper.toDomain(ENTITY_NOTE)

        assertThat(actualNote).isInstanceOf(expectedNote::class.java)
    }

    @Test
    fun toEntity() {
        val expectedNote = ENTITY_NOTE

        val actualNote = mapper.toEntity(NOTE)

        assertThat(actualNote).isInstanceOf(expectedNote::class.java)
    }

    @Test
    fun toDomainList() {
        val expectedNotes = listOf(NOTE)

        val actualNotes = mapper.toDomainList(listOf(ENTITY_NOTE))

        assertThat(actualNotes[0]).isInstanceOf(expectedNotes[0]::class.java)
    }

    @Test
    fun toEntityList() {
        val expectedNotes = listOf(ENTITY_NOTE)

        val actualNotes = mapper.toEntityList(listOf(NOTE))

        assertThat(expectedNotes[0]).isInstanceOf(actualNotes[0]::class.java)
    }
}