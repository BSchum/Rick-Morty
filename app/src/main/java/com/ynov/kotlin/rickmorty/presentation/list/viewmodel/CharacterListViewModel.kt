package com.ynov.kotlin.rickmorty.presentation.list.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.model.RMCharacter
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterListViewModel : ViewModel() {
    var characterListLiveData : MutableLiveData<List<RMCharacter>> = MutableLiveData()
    init {
        Refresh()
    }

    fun Refresh(onSuccessCallBack: () -> Unit = {}){
        RMApplication.app.repo
            .retrieveCharacterList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    onSuccessCallBack()
                    characterListLiveData.postValue(it)
                },
                onError = {
                    Log.e("ERROR", "", it)
                }
            )
    }
}