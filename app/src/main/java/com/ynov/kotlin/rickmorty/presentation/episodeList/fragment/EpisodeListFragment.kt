package com.ynov.kotlin.rickmorty.presentation.episodeList.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.DetailActivity
import com.ynov.kotlin.rickmorty.presentation.episodeList.EpisodesListAdapter
import com.ynov.kotlin.rickmorty.presentation.episodeList.viewModel.EpisodeListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class EpisodeListFragment : Fragment(){

    lateinit var viewModel: EpisodeListViewModel

    private lateinit var episodesListAdapter: EpisodesListAdapter;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        episodesListAdapter = EpisodesListAdapter()
        fragment_list_recycler_view.layoutManager = LinearLayoutManager(requireContext())
        fragment_list_recycler_view.adapter = episodesListAdapter
        //episodesListAdapter.onClickCallBack = {
        //    var intent = Intent(context, DetailActivity::class.java)
        //    intent.putExtra("id", it)
        //    startActivity(intent)
        //}
        viewModel = ViewModelProviders.of(this).get(EpisodeListViewModel::class.java)
        viewModel.episodeListLiveData.observe(this, Observer {
            episodesListAdapter.updateList(it)
        })
    }
}