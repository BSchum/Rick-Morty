package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.CharacterRemoteEntity
import com.ynov.kotlin.rickmorty.data.model.RMCharacter
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

}