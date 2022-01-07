package com.anushmp.saveomovies.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anushmp.saveomovies.R
import com.anushmp.saveomovies.datamodels.remotesources.responseclasses.Results
import com.anushmp.saveomovies.repositories.MovieDataRepository
import com.anushmp.saveomovies.viewmodels.MovieDataFactory
import com.anushmp.saveomovies.viewmodels.MoviesViewModel
import com.anushmp.saveomovies.views.adapters.MovieRecyclerViewAdapter
import com.anushmp.saveomovies.views.adapters.OnTapListner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularMoviesActivity : AppCompatActivity(), OnTapListner {

    lateinit var popularmoviesRecyclerView: RecyclerView
    lateinit var recyclerViewAdapter: MovieRecyclerViewAdapter
    lateinit var repository: MovieDataRepository
    lateinit var popularMoviesViewModel: MoviesViewModel
    lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        popularmoviesRecyclerView = findViewById(R.id.popularmoviesRecyclerView)

        recyclerViewAdapter = MovieRecyclerViewAdapter(this)
        gridLayoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL,false)
        repository = MovieDataRepository()

        popularmoviesRecyclerView.adapter = recyclerViewAdapter
        popularmoviesRecyclerView.layoutManager = gridLayoutManager



        window.exitTransition = null




        popularMoviesViewModel =
            ViewModelProvider(this, MovieDataFactory(repository))[MoviesViewModel::class.java]


        popularMoviesViewModel.getPopularMovesAsLiveData().observe(this) {

            CoroutineScope(Dispatchers.IO).launch {
                Log.d("CoroutineScope", "error here")
                try {
                    recyclerViewAdapter.submitData(it)
                } catch (e: Exception) {

                    launch(Dispatchers.Main) {
                        Toast.makeText(
                            this@PopularMoviesActivity,
                            "No internet Connection Available",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
            }

        }





    }

    override fun onTap(result: Results?, view:View) {

        val imageView = view.findViewById<ImageView>(R.id.poster)

        val intent:Intent = Intent(this,MovieDetailActivity::class.java)
        var options:ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            imageView,
            ViewCompat.getTransitionName(imageView)!!
        )

        intent.putExtra("url",result!!.poster_path)
        intent.putExtra("moviename", result.title)
        intent.putExtra("description", result.overview)

        startActivity(intent,options.toBundle())



    }
}