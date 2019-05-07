package me.kevincampos.policies.util

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import me.kevincampos.domain.model.Exchange
import me.kevincampos.policies.ExchangeAdapter

@BindingAdapter("visibleUnless")
fun bindVisibleIf(view: View, mustGone: Boolean) {
    view.visibility = if (mustGone) {
        GONE
    } else {
        VISIBLE
    }
}

@BindingAdapter("goneUnless")
fun bindGoneIf(view: View, mustVisible: Boolean) {
    view.visibility = if (mustVisible) {
        VISIBLE
    } else {
        GONE
    }
}

@BindingAdapter(
    "imageUrl",
    "imagePlaceholder",
    "circleCropImage",
    "crossFadeImage",
    "overrideImageWidth",
    "overrideImageHeight",
    "imageLoadListener",
    requireAll = false
)
fun bindImage(
    imageView: ImageView,
    imageUrl: String?,
    placeholder: Int? = null,
    circleCrop: Boolean? = false,
    crossFade: Boolean? = false,
    overrideWidth: Int? = null,
    overrideHeight: Int? = null,
    listener: RequestListener<Drawable>?
) {
    if (imageUrl == null) return
    var request = GlideApp.with(imageView.context).load(imageUrl)
    if (placeholder != null) {
        request = request.placeholder(placeholder).error(placeholder)
    }
    if (circleCrop == true) {
        request = request.circleCrop()
    }
    if (crossFade == true) {
        request = request.transition(DrawableTransitionOptions.withCrossFade())
    }
    if (overrideWidth != null && overrideHeight != null) {
        request = request.override(overrideWidth, overrideHeight)
    }
    if (listener != null) {
        request = request.listener(listener)
    }
    request.into(imageView)
}

@BindingAdapter("exchanges")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: List<Exchange>) {
    if (recyclerView.adapter is ExchangeAdapter) {
        (recyclerView.adapter as ExchangeAdapter).swap(items)
    }
}