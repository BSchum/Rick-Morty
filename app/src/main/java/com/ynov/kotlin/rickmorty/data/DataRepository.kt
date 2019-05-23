package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.model.RMCharacter
import com.ynov.kotlin.rickmorty.data.model.RMEpisode
import io.reactivex.Single

class DataRepository(private val apiManager : ApiManager , private val cacheManager: CacheManager){
    fun retrieveCharacterList(): Single<List<RMCharacter>> {
        return Single.defer<List<RMCharacter>>{
            if(cacheManager.characterList.isEmpty()){
                apiManager.retrieveCharacterList().map {
                    return@map it.map{ characterRemoteEntity ->
                        RMCharacter(characterRemoteEntity.id, characterRemoteEntity.name, characterRemoteEntity.image)
                    }
                }.doAfterSuccess{
                    cacheManager.characterList = it
                }
            }
            else {
                Single.just(cacheManager.characterList)
            }
        }
    }

    fun retrieveCharacter(id: Int): Single<RMCharacter> =
            apiManager.retrieveCharacter(id).map { characterRemoteEntity ->
                RMCharacter(characterRemoteEntity.id ,characterRemoteEntity.name, characterRemoteEntity.image)
            }

    fun retrieveEpisodeList(): Single<List<RMEpisode>> {
        return Single.defer<List<RMEpisode>>{
            if(cacheManager.episodeList.isEmpty()){
                apiManager.retrieveEpisodeList().map {
                    return@map it.map{ episodeRemoteEntity ->
                        RMEpisode(episodeRemoteEntity.id, episodeRemoteEntity.name, episodeRemoteEntity.air_date, episodeRemoteEntity.episode)
                    }
                }.doAfterSuccess{
                    cacheManager.episodeList = it
                }
            }
            else {
                Single.just(cacheManager.episodeList)
            }
        }
    }

}