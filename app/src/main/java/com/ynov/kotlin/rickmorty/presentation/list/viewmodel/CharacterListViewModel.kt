package com.ynov.kotlin.rickmorty.presentation.list.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ynov.kotlin.rickmorty.data.model.RMCharacter
import com.ynov.kotlin.rickmorty.presentation.DisposableViewModel
import com.ynov.kotlin.rickmorty.presentation.RMApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class CharacterListViewModel : DisposableViewModel() {
    var characterListLiveData : MutableLiveData<List<RMCharacter>> = MutableLiveData()
    var errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    init {
        Refresh()
    }

    fun Refresh(){
        this.AddToDisposableContainer(
            RMApplication.app.repo
            .retrieveCharacterList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    characterListLiveData.postValue(it)
                },
                onError = {
                    Log.e("ERROR", "", it)
                    errorLiveData.postValue(it)
                }
            )
        )
    }
}