package com.ynov.kotlin.rickmorty.presentation.detail.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.detail.viewmodel.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_character_detail.*

class DetailFragment(id: Int) : Fragment() {
    lateinit var viewModel: CharacterViewModel
    var idCharacter: Int = id
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CharacterViewModel::class.java)
        viewModel.retrieveCharacter(idCharacter)
        viewModel.characterLiveData.observe(this, Observer {
            Picasso.get().load(it.url).into(detailImage)
            detailName.text = it.name
        })
    }
}