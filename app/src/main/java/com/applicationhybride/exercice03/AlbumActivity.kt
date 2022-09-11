package com.applicationhybride.exercice03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class AlbumActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    data class Genre (

        var genreId : Int,
        var nom     : String,

    )
    data class Artiste (

        var artisteId : Int,
        var nom       : String,

    )
    data class Album (

        var albumId       : Int,
        var titre         : String,
        var anneeParution : Int,
        var prix          : Double,
        var cover         : String,
        var artiste       : Artiste,
        var genre         : Genre,

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        val artisteIdReceived = intent.getIntExtra("artisteId", 0)


        this.recyclerView = findViewById(R.id.rvAlbums)
        this.recyclerView.layoutManager = LinearLayoutManager(this)

        this.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        val queue = Volley.newRequestQueue(this)
        val url = "https://musicstoreapi.herokuapp.com/albums/"

        val request = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener{
                val data: List<Album> = Gson().fromJson(it, Array<Album>::class.java).toList()

                // filter data to get only albums with the artisteId = artisteIdReceived
                val filteredData = data.filter { album -> album.artiste.artisteId == artisteIdReceived }
//                Toast.makeText(this, filteredData.map { album -> album.titre }.toString(), Toast.LENGTH_LONG).show()
                this.recyclerView.adapter = AlbumAdapter(filteredData)
            }
        ) { error ->
            Log.e("AlbumActivity", error.toString())
        }
        queue.add(request)


    }


}
