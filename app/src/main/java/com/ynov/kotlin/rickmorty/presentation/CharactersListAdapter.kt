package com.ynov.kotlin.rickmorty.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.model.RMCharacter
import io.reactivex.annotations.NonNull
import kotlinx.android.synthetic.main.view_character_list_item.view.*
import java.net.URI

class CharactersListAdapter : RecyclerView.Adapter<CharactersListAdapter.ViewHolder>() {
    private var characters: MutableList<RMCharacter> = mutableListOf()
    private lateinit var context: Context;
    lateinit var onClickCallBack: (id: Int) -> Unit
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_character_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return characters.size
    }
    fun updateList(characters: List<RMCharacter>){
        this.characters.clear()
        this.characters.addAll(characters)
        notifyDataSetChanged()
}
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.characters.get(position), onClickCallBack)
    }

    class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(rmCharacter: RMCharacter, onClickCallBack: (id: Int)-> Unit){
            itemView.nameField.text = rmCharacter.name
            Picasso.get().load(rmCharacter.url).into(itemView.imageField)
            itemView.setOnClickListener {
                onClickCallBack(rmCharacter.id)
            }
        }
    }


}