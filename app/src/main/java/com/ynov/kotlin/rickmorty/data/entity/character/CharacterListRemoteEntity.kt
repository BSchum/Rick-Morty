package com.ynov.kotlin.rickmorty.data.entity.character

data class CharacterListRemoteEntity(
    val info: Info,
    val results: List<CharacterRemoteEntity>
)