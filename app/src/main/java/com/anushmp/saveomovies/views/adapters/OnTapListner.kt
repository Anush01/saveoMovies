package com.anushmp.saveomovies.views.adapters

import android.view.View
import com.anushmp.saveomovies.datamodels.remotesources.responseclasses.Results

interface OnTapListner {

    fun onTap(result: Results?, view:View)
}