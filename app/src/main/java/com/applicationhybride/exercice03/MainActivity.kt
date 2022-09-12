package com.applicationhybride.exercice03

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    private val data= mutableListOf<Artist>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.recyclerView = findViewById(R.id.rvArtistes)
        this.recyclerView.layoutManager = LinearLayoutManager(this)
        this.recyclerView.adapter = ArtistAdapter(data)

        val queue = Volley.newRequestQueue(this@MainActivity)
        val url = "https://musicstoreapi.herokuapp.com/artistes"

        val r = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener{
                val fetchedData = Gson().fromJson(it, Array<Artist>::class.java).toList()
                data.addAll(fetchedData)
                this.recyclerView.adapter?.notifyDataSetChanged()
            }
        ) { error ->
            error.printStackTrace()
        }
        queue.add(r)

    }

}
