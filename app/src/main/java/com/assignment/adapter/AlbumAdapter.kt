package com.assignment.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.assignment.R
import com.assignment.model.Album

class AlbumAdapter( val listAlbum : ArrayList<Album>?) : RecyclerView.Adapter<AlbumAdapter.AlbumVH>() {

    fun ViewGroup.inflate(layout: Int) : View{
        return LayoutInflater.from(context).inflate(layout,this, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumVH {
        return  AlbumVH(parent.inflate(R.layout.item_album))
    }

    override fun getItemCount(): Int {
            return listAlbum?.size ?: 0
    }

    override fun onBindViewHolder(holder: AlbumVH, position: Int) {
       val title = listAlbum?.get(position)?.title?.capitalize()
       val index = position.inc()

        holder.titleAlbum.text = "$index. $title"

    }

    fun getAlbums(): ArrayList<Album>? {
        return listAlbum
    }


    inner class AlbumVH : RecyclerView.ViewHolder{
        val  titleAlbum : TextView
        constructor(itemView : View) : super(itemView) {
            titleAlbum = itemView.findViewById(R.id.textView)
        }
    }
}