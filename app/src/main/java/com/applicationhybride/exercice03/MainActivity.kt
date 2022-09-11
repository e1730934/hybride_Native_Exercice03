package com.applicationhybride.exercice03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson


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
                val data: List<Artist> = Gson().fromJson(it, Array<Artist>::class.java).toList()
                this.recyclerView.adapter = ArtistAdapter(data)
            }
        ) { error ->
            error.printStackTrace()
        }
        queue.add(r)

    }

}
