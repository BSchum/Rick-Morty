package com.ynov.kotlin.rickmorty.presentation.episodeList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.model.RMEpisode
import io.reactivex.annotations.NonNull
import kotlinx.android.synthetic.main.view_episode_list_item.view.*

class EpisodesListAdapter : RecyclerView.Adapter<EpisodesListAdapter.ViewHolder>() {
    private var episodes: MutableList<RMEpisode> = mutableListOf()
    private lateinit var context: Context;
    var onClickCallBack: (id: Int) -> Unit = {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_episode_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return episodes.size
    }
    fun updateList(episodes: List<RMEpisode> ){
        this.episodes.clear()
        this.episodes.addAll(episodes)
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.episodes.get(position), onClickCallBack)
    }

    class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(rmEpisode: RMEpisode, onClickCallBack: (id: Int)-> Unit){
            itemView.episodeNameField.text = rmEpisode.name
            itemView.episodeAirDateField.text = rmEpisode.air_date
            itemView.episodeField.text = rmEpisode.episode
        }
    }
}