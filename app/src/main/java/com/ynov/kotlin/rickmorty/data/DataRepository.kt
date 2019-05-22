package com.ynov.kotlin.rickmorty.data

import com.ynov.kotlin.rickmorty.data.entity.CharacterRemoteEntity
import com.ynov.kotlin.rickmorty.data.model.RMCharacter
import io.reactivex.Single

class DataRepository(private val apiManager : ApiManager){
    fun retrieveCharacterList(): Single<List<RMCharacter>> =
                apiManager.retrieveCharacterList().map {
                    it.map{
                        characterRemoteEntity ->
                        RMCharacter(characterRemoteEntity.id, characterRemoteEntity.name, characterRemoteEntity.image)
                    }
                }

    fun retrieveCharacter(id: Int): Single<RMCharacter> =
            apiManager.retrieveCharacter(id).map { characterRemoteEntity ->
                RMCharacter(characterRemoteEntity.id ,characterRemoteEntity.name, characterRemoteEntity.image)
            }

}