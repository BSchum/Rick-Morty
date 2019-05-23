package com.ynov.kotlin.rickmorty.presentation.episodeList.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.extensions.DoSnackBar
import com.ynov.kotlin.rickmorty.presentation.episodeList.EpisodesListAdapter
import com.ynov.kotlin.rickmorty.presentation.episodeList.viewModel.EpisodeListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
class  EpisodeListFragment : Fragment(){

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
        swipe_refresh_layout.setOnRefreshListener {
            viewModel.Refresh()
        }
        viewModel = ViewModelProviders.of(this).get(EpisodeListViewModel::class.java)
        viewModel.episodeListLiveData.observe(this, Observer {
            episodesListAdapter.updateList(it)
            swipe_refresh_layout.isRefreshing = false
        })

        viewModel.errorLiveData.observe(this, Observer {
            view.DoSnackBar("Erreur lors de la récupération des episodes")
            swipe_refresh_layout.isRefreshing = false
        })
    }
}