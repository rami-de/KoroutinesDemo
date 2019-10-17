package com.rami.koroutinesdemo.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.rami.koroutinesdemo.R

class MovieCardView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    val poster: ImageView
    val title: TextView
    val year: TextView
    val rating: TextView

    init {
        inflate(context, R.layout.movie_card_view_layout, this)
        poster = findViewById(R.id.image)
        title = findViewById(R.id.title)
        year = findViewById(R.id.release_year)
        rating = findViewById(R.id.rating)
    }
}