package com.ynov.kotlin.rickmorty.data.entity.episode

import com.ynov.kotlin.rickmorty.data.entity.character.Info

data class EpisodeListRemoteEntity(
    val info: Info,
    val results: List<EpisodeRemoteEntity>
)