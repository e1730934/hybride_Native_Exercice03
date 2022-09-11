package com.applicationhybride.exercice03

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AlbumAdapter(private val listAlbums: List<AlbumActivity.Album>) :
    RecyclerView.Adapter<AlbumAdapter.RecyclerViewViewHolder>() {

    class RecyclerViewViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.album_view_item, parent, false)
        return RecyclerViewViewHolder(view)
    }

    override fun getItemCount() = this.listAlbums.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        val imgViewAlbum =  holder.view.findViewById<ImageView>(R.id.imgViewAlbumPicture)
        Picasso.get().load(this.listAlbums[position].cover).into(imgViewAlbum)

        holder.view.findViewById<TextView>(R.id.tvAlbumTitle).text = this.listAlbums[position].titre
        holder.view.findViewById<TextView>(R.id.tvAlbumYear).text = this.listAlbums[position].anneeParution.toString()
        holder.view.findViewById<TextView>(R.id.tvAlbumPrice).text = this.listAlbums[position].prix.toString() + "$"

    }


}
