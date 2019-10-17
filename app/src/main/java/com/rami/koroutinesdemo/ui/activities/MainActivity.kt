package com.rami.koroutinesdemo.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rami.koroutinesdemo.DemoApplication
import com.rami.koroutinesdemo.R
import com.rami.koroutinesdemo.data.repository.MovieRepository
import com.rami.koroutinesdemo.ui.adapters.MovieListAdapter
import com.rami.koroutinesdemo.ui.models.SimpleMovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var moviesRecycler: RecyclerView

    @Inject
    lateinit var adapter: MovieListAdapter

    @Inject
    lateinit var repository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as DemoApplication).appComponent.inject(this)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        moviesRecycler = findViewById(R.id.movies_recycler)
        moviesRecycler.layoutManager = layoutManager
        moviesRecycler.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        GlobalScope.launch(Dispatchers.Main) {
            val movies = repository.getPopularMovies()
            val movieItems = mutableListOf<SimpleMovieItem>()
            movies.forEach {
                movieItems.add(SimpleMovieItem(it.title, it.posterUrl, it.rating, it.releaseYear))
            }
            Log.d("MainActivity", movies.toString())
            adapter.updateItems(movieItems)
        }
    }
}
