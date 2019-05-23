package com.ynov.kotlin.rickmorty.presentation

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class DisposableViewModel : ViewModel() {
    protected var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
    }

    fun AddToDisposableContainer(disposable: Disposable){
        compositeDisposable.add(disposable)
    }
}

