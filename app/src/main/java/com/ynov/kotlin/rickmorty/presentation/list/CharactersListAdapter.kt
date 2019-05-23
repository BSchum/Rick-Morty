package com.ynov.kotlin.rickmorty.presentation.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.model.RMCharacter
import io.reactivex.annotations.NonNull
import kotlinx.android.synthetic.main.view_character_list_item.view.*

class CharactersListAdapter : RecyclerView.Adapter<CharactersListAdapter.ViewHolder>() {
    private var characters: MutableList<RMCharacter> = mutableListOf()
    var onClickCallBack : ((id: Int) -> Unit)? = null
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
        fun bind(rmCharacter: RMCharacter, onClickCallBack: ((id: Int)-> Unit)?){
            itemView.nameField.text = rmCharacter.name
            Picasso.get().load(rmCharacter.url).into(itemView.imageField)
            itemView.setOnClickListener {
                onClickCallBack?.let { callBack -> callBack(rmCharacter.id) }
            }
        }
    }


}