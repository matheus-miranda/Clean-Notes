package br.com.mmdevelopment.cleannotes.data.mapper

import br.com.mmdevelopment.cleannotes.data.local.entity.NoteEntity
import br.com.mmdevelopment.cleannotes.domain.model.Note

interface NoteEntityMapper {
    fun toDomain(entity: NoteEntity): Note
    fun toEntity(domain: Note): NoteEntity
    fun toDomainList(entityList: List<NoteEntity>) = entityList.map { toDomain(it) }
    fun toEntityList(domainList: List<Note>) = domainList.map { toEntity(it) }
}