package com.anushmp.saveomovies.views.adapters.paginationutils

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.anushmp.saveomovies.R
import com.anushmp.saveomovies.datamodels.remotesources.responseclasses.Results
import com.anushmp.saveomovies.views.adapters.OnTapListner
import com.bumptech.glide.Glide

class MovieViewHolder(val view:View, val onTapListner: OnTapListner): RecyclerView.ViewHolder(view) {

    private val poster:ImageView = view.findViewById(R.id.poster)

    fun setData(results: Results){
        poster.setOnClickListener {
            onTapListner.onTap(results, view)
        }

        var url = "https://image.tmdb.org/t/p/w500/"
        url += results.poster_path

        Glide.with(view.context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(poster)

    }

}