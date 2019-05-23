package com.ynov.kotlin.rickmorty.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.DoSnackBar(text: String){
    Snackbar.make(this, text, Snackbar.LENGTH_SHORT).show()
}
