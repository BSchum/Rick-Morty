package com.ynov.kotlin.rickmorty.data.entity.episode

data class EpisodeRemoteEntity(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)