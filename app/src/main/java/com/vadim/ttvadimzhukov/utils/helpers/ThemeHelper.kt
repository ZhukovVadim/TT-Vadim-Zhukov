package com.vadim.ttvadimzhukov.utils.helpers

import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.ColorRes
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.RequestOptions
import com.vadim.ttvadimzhukov.R
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator

private const val ITEM_ANIMATION_DURATION = 300L

object ThemeHelper {

    fun getColorAccent(context: Context) =
        getColorFromTheme(context, R.attr.colorAccent)

    fun getColorPrimary(context: Context) =
        getColorFromTheme(context, R.attr.colorPrimary)

    fun getColorPrimaryDark(context: Context) =
        getColorFromTheme(context, R.attr.colorPrimaryDark)

    fun getColorFromTheme(context: Context, @AttrRes attr: Int): Int {
        return getColor(context, getColorIdFromTheme(context, attr))
    }

    fun getColorIdFromTheme(context: Context, @AttrRes attrRes: Int): Int {
        val color = TypedValue()
        context.theme.resolveAttribute(attrRes, color, true)
        return color.resourceId
    }

    fun getColor(context: Context, @ColorRes colorRes: Int) = CC.getColor(context, colorRes)

    fun setSwipeRefreshLayoutStyle(srl: SwipeRefreshLayout) {
        val context = srl.context
        srl.setColorSchemeColors(
            getColorAccent(context),
            getColorPrimary(context),
            getColorPrimaryDark(context)
        )
    }

    fun setRecyclerViewStyle(recyclerView: RecyclerView, orientation: Int = LinearLayoutManager.VERTICAL) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, orientation, false)
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, orientation)
        dividerItemDecoration.setDrawable(CC.getDrawable(recyclerView.context, R.drawable.divider)!!)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.itemAnimator = SlideInLeftAnimator().apply {
            addDuration = ITEM_ANIMATION_DURATION
            removeDuration = ITEM_ANIMATION_DURATION
            changeDuration = ITEM_ANIMATION_DURATION
            moveDuration = ITEM_ANIMATION_DURATION
        }
    }

    fun getGlideOptions(transformation: BitmapTransformation? = null) =
        RequestOptions().apply {
            placeholder(R.drawable.image_placeholder)
            transformation?.let { transform(it) }
        }

}