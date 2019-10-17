package com.rami.koroutinesdemo.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rami.koroutinesdemo.DemoApplication
import com.rami.koroutinesdemo.R
import com.rami.koroutinesdemo.ui.adapters.MovieListAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var moviesRecycler: RecyclerView

    @Inject
    lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as DemoApplication).appComponent.inject(this)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        moviesRecycler = findViewById(R.id.movies_recycler)
        moviesRecycler.layoutManager = layoutManager
        moviesRecycler.adapter = adapter
    }
}
