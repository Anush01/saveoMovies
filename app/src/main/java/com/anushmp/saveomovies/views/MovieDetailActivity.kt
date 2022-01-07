package com.anushmp.saveomovies.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.anushmp.saveomovies.R
import com.anushmp.saveomovies.datamodels.remotesources.responseclasses.Results
import com.anushmp.saveomovies.views.adapters.OnTapListner
import com.bumptech.glide.Glide
import org.jetbrains.anko.find

class MovieDetailActivity : AppCompatActivity(){

    lateinit var imageView: ImageView
    lateinit var moviename: TextView
    lateinit var moviedescription: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        window.enterTransition = null

        imageView = findViewById(R.id.moviedetailposter)
        moviename = findViewById(R.id.moviename)
        moviedescription = findViewById(R.id.moviedesc)

        var intent = intent
        var urlpath = intent.getStringExtra("url")

        val movienametext = intent.getStringExtra("moviename")
        val moviedesctext = intent.getStringExtra("description")

        moviename.text = movienametext
        moviedescription.text = moviedesctext

        var url = "https://image.tmdb.org/t/p/w500/"
        url += urlpath

        Glide.with(this)
            .load(url)
            .into(imageView)


    }


}