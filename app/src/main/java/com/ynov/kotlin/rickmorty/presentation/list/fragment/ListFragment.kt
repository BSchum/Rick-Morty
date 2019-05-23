package com.ynov.kotlin.rickmorty.presentation.list.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.model.RMCharacter
import com.ynov.kotlin.rickmorty.presentation.CharactersListAdapter
import com.ynov.kotlin.rickmorty.presentation.DetailActivity
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.CharacterListViewModel
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.Nullable
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment :Fragment() {

    lateinit var viewModel: CharacterListViewModel
    private lateinit var charactersListAdapter: CharactersListAdapter;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        charactersListAdapter = CharactersListAdapter()
        fragment_list_recycler_view.layoutManager = LinearLayoutManager(context)
        fragment_list_recycler_view.adapter = charactersListAdapter
        charactersListAdapter.onClickCallBack = {
            var intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }
        swipe_refresh_layout.setOnRefreshListener {
            viewModel.Refresh()
        }
        viewModel = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)
        viewModel.characterListLiveData.observe(this, Observer {
            charactersListAdapter.updateList(it)
            swipe_refresh_layout.isRefreshing = false
        })

        viewModel.errorLiveData.observe(this, Observer {
            Snackbar.make(view, "Erreur lors du chargement des characters", Snackbar.LENGTH_SHORT).show()
            swipe_refresh_layout.isRefreshing = false
        })
    }
}