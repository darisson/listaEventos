package com.example.eventssicredi.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.eventssicredi.R

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.no_image_available)
        .into(this)
}