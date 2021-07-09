package ai.andromeda.nordstarter.extensions

import ai.andromeda.nordstarter.R
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions

fun AppCompatImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .transition(withCrossFade())
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_loading_image)
                .error(R.drawable.ic_broken_image)
        )
        .into(this)
}

fun AppCompatImageView.loadCircularImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .transition(withCrossFade())
        .apply(
            RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.ic_loading_image)
                .error(R.drawable.ic_broken_image)
        )
        .into(this)
}