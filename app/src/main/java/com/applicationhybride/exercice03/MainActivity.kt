package com.applicationhybride.exercice03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

data class ListArtistes(val artists: List<Artist>)

data class Artist(
    val artisteId: Int,
    val nom: String,
)

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.recyclerView = findViewById(R.id.rvArtistes)
        this.recyclerView.layoutManager = LinearLayoutManager(this)

        val queue = Volley.newRequestQueue(this@MainActivity)
        val url = "https://musicstoreapi.herokuapp.com/artistes"

        val r = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener{

                // get data from array of objects
                val data: List<Artist> = Gson().fromJson(it, Array<Artist>::class.java).toList()
                // set adapter
                val nomArtistes = data.map { it.nom }
                this.recyclerView.adapter = ArtistAdapter(nomArtistes)
                Toast.makeText(this, "Réponse reçue", Toast.LENGTH_SHORT).show()
            }
        ) { error ->
            error.printStackTrace()
        }
        queue.add(r)

    }

}
