package br.com.mmdevelopment.cleannotes.data.mapper

import br.com.mmdevelopment.cleannotes.data.local.entity.NoteEntity
import br.com.mmdevelopment.cleannotes.domain.model.Note

class NoteEntityMapperImpl : NoteEntityMapper {
    override fun toDomain(entity: NoteEntity) = Note(
        id = entity.id,
        title = entity.title,
        description = entity.description,
        date = entity.date,
        time = entity.time
    )

    override fun toEntity(domain: Note) = NoteEntity(
        id = domain.id,
        title = domain.title,
        description = domain.description,
        date = domain.date,
        time = domain.time
    )
}