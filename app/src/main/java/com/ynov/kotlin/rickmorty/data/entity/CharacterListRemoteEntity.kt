package com.ynov.kotlin.rickmorty.data.entity

data class CharacterListRemoteEntity(
    val info: Info,
    val results: List<CharacterRemoteEntity>
)