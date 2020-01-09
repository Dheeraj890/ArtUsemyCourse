package com.example.artudemydevelopment.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.artudemydevelopment.R

fun getProgresDrawable(context: Context):CircularProgressDrawable {


    return CircularProgressDrawable(context).apply {


        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

    fun ImageView.loadImage(uri:String?,progressDrawable: CircularProgressDrawable){

        val options=RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher)
            Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(uri)
                .into(this)
    }



@BindingAdapter("android:imageUrl")
fun loadImage(view:ImageView,url:String?){

    view.loadImage(url, getProgresDrawable(view.context))


}

@BindingAdapter("android:colorset")
fun setColor(view:View,color:Int){


    view.setBackgroundColor(color)

}



