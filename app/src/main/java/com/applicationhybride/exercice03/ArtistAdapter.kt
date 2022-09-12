package com.applicationhybride.exercice03

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArtistAdapter(private val listeArtistes: MutableList<Artist>) :
    RecyclerView.Adapter<ArtistAdapter.RecyclerViewViewHolder>() {

    class RecyclerViewViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.artistes_view_item, parent, false)
        return RecyclerViewViewHolder(view)
    }

    override fun getItemCount() = this.listeArtistes.size

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.tvNomArtist).text = this.listeArtistes[position].nom
        holder.view.setOnClickListener {
            val intent = Intent(holder.view.context, AlbumActivity::class.java)
            intent.putExtra("artisteId", this.listeArtistes[position].artisteId)
            holder.view.context.startActivity(intent)
        }
    }

}
