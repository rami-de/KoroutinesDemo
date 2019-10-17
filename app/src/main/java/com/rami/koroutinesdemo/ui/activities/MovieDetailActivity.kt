package com.rami.koroutinesdemo.ui.activities

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airbnb.lottie.LottieAnimationView
import com.rami.koroutinesdemo.DemoApplication
import com.rami.koroutinesdemo.R
import com.rami.koroutinesdemo.presentation.DetailScreenState
import com.rami.koroutinesdemo.presentation.MovieDetailViewModel
import com.rami.koroutinesdemo.presentation.di.ViewModelFactory
import com.rami.koroutinesdemo.ui.models.DetailMovieItem
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var bigPoster: ImageView
    private lateinit var smallPoster: ImageView
    private lateinit var title: TextView
    private lateinit var attributes: TextView
    private lateinit var description: TextView
    private lateinit var rating: TextView
    private lateinit var loader: LottieAnimationView
    private lateinit var errorView: LottieAnimationView

    @Inject lateinit var factory: ViewModelFactory
    private lateinit var viewModel: MovieDetailViewModel

    @Inject lateinit var picasso: Picasso

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as DemoApplication).appComponent.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(MovieDetailViewModel::class.java)
        setContentView(R.layout.activity_details)

        bigPoster = findViewById(R.id.big_poster)
        smallPoster = findViewById(R.id.small_poster)
        title = findViewById(R.id.title)
        attributes = findViewById(R.id.attributes)
        description = findViewById(R.id.description)
        rating = findViewById(R.id.rating)
        loader = findViewById(R.id.loader)
        errorView = findViewById(R.id.error_view)

        initObservers()
        viewModel.init(intent.getIntExtra("MOVIE_ID", 0))
    }

    fun initObservers() {
        viewModel.getState().observe(this, Observer(::onStateChanged))
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResumed()
    }

    fun onStateChanged(state: DetailScreenState) {
        when (state) {
            DetailScreenState.Loading -> hideViewsAndShowLoader()
            DetailScreenState.Error -> hideViewsAndShowError()
            is DetailScreenState.BigPosterAvailable -> showBigPoster(state.url)
            is DetailScreenState.SmallPosterAvailable -> showSmallPoster(state.url)
            is DetailScreenState.Ready -> showMovieDetails(state.movie)
        }
    }

    fun hideViewsAndShowLoader() {
        hideViews()
        loader.visibility = VISIBLE
        loader.playAnimation()
    }

    fun hideViewsAndShowError() {
        hideViews()
        showError()
    }

    fun showBigPoster(url: String) {
        picasso.load(url)
            .fit()
            .centerCrop()
            .into(bigPoster)
    }

    fun showSmallPoster(url: String) {
        picasso.load(url)
            .fit()
            .centerCrop()
            .into(smallPoster)
    }

    fun showMovieDetails(movie: DetailMovieItem) {
        loader.visibility = GONE
        loader.pauseAnimation()
        attributes.text = getString(R.string.movie_attributes, movie.releaseYear, movie.runTime, movie.genres)
        title.text = movie.title
        rating.text = "${movie.rating}/10"
        description.text = movie.description
        showViews()
    }

    fun showViews() {
        bigPoster.visibility = VISIBLE
        smallPoster.visibility = VISIBLE
        title.visibility = VISIBLE
        attributes.visibility = VISIBLE
        rating.visibility = VISIBLE
        description.visibility = VISIBLE
    }

    fun hideViews() {
        bigPoster.visibility = GONE
        smallPoster.visibility = GONE
        title.visibility = GONE
        attributes.visibility = GONE
        rating.visibility = GONE
        description.visibility = GONE
    }

    fun showError() {
        errorView.visibility = VISIBLE
        errorView.playAnimation()
    }
}