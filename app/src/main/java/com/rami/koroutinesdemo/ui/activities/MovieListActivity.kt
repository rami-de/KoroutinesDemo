package com.rami.koroutinesdemo.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.rami.koroutinesdemo.DemoApplication
import com.rami.koroutinesdemo.R
import com.rami.koroutinesdemo.presentation.MovieListPresenter
import com.rami.koroutinesdemo.presentation.MovieListView
import com.rami.koroutinesdemo.ui.adapters.MovieListAdapter
import com.rami.koroutinesdemo.ui.adapters.MovieListNavigationListener
import com.rami.koroutinesdemo.ui.models.SimpleMovieItem
import javax.inject.Inject

class MovieListActivity : AppCompatActivity(), MovieListView, MovieListNavigationListener {

    private lateinit var moviesRecycler: RecyclerView

    @Inject
    lateinit var adapter: MovieListAdapter

    @Inject
    lateinit var presenter: MovieListPresenter

    lateinit var loader: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as DemoApplication).appComponent.inject(this)
        setContentView(R.layout.activity_main)
        title = "Popular Movies"
        presenter.setView(this)

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        moviesRecycler = findViewById(R.id.movies_recycler)
        loader = findViewById(R.id.lottie_loader)
        moviesRecycler.layoutManager = layoutManager
        moviesRecycler.adapter = adapter
        adapter.listener = this
    }

    override fun onResume() {
        super.onResume()
        presenter.onResumed()
    }

    override fun showLoader() {
        loader.visibility = VISIBLE
        loader.playAnimation()
    }

    override fun hideLoader() {
        loader.pauseAnimation()
        loader.visibility = GONE
    }

    override fun updateData(movies: List<SimpleMovieItem>) {
        adapter.updateItems(movies)
    }

    override fun onMovieItemPressed(movieId: Int) {
        presenter.onMovieItemClicked(movieId)
        Toast.makeText(this, "Movie pressed: $movieId", Toast.LENGTH_SHORT).show()
    }

    override fun hideMovies() {
        moviesRecycler.visibility = GONE
    }

    override fun showMovies() {
        moviesRecycler.visibility = VISIBLE
    }

    override fun navigateToMovieDetails(movieId: Int) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("MOVIE_ID", movieId)
        startActivity(intent)
    }

    override fun disableListClicks() {
        adapter.isClickable = false
    }

    override fun enableListClicks() {
        adapter.isClickable = true
    }
}
