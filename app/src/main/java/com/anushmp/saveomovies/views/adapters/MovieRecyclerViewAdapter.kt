package com.anushmp.saveomovies.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.anushmp.saveomovies.R
import com.anushmp.saveomovies.datamodels.remotesources.responseclasses.Results
import com.anushmp.saveomovies.views.adapters.paginationutils.MovieViewHolder

class MovieRecyclerViewAdapter(val onTapListner: OnTapListner):PagingDataAdapter<Results,MovieViewHolder>(diffUtilCallback) {

    companion object{

        val diffUtilCallback = object: DiffUtil.ItemCallback<Results>() {
            override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
                return oldItem == newItem
            }

        }

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val results = getItem(position)

//        if(results!=null){
//            holder.setData(results)
//        }

        results?.let {
            holder.setData(it)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        var view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.popular_movies_recyclerview_item_layout,parent,false)

        return MovieViewHolder(view,onTapListner)
    }

}