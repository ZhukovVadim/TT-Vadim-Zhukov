package com.vadim.ttvadimzhukov.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.vadim.ttvadimzhukov.BuildConfig
import com.vadim.ttvadimzhukov.R
import com.vadim.ttvadimzhukov.base.BaseAdapter
import com.vadim.ttvadimzhukov.data.local.entity.CategoryEntity
import com.vadim.ttvadimzhukov.utils.helpers.ThemeHelper
import kotlinx.android.synthetic.main.item_category.view.ivCategory
import kotlinx.android.synthetic.main.item_category.view.tvCategoryName

private const val ITEM_LAYOUT_ID = R.layout.item_category

class CategoriesAdapter(
    private val glide: RequestManager,
    private val onItemClickEvent: (categoryEntity: CategoryEntity) -> Unit
) : BaseAdapter<CategoryEntity, CategoriesAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(ITEM_LAYOUT_ID, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list[position]
        with(holder.itemView) {
            tvCategoryName.text = item.name
            setImage(ivCategory, item)
            setOnClickListener { onItemClickEvent(item) }
        }
    }

    private fun setImage(ivCategory: ImageView, item: CategoryEntity) {
        if (item.gitsList.isNotEmpty())
            glide.setDefaultRequestOptions(ThemeHelper.getGlideOptions(CircleCrop()))
                .load(BuildConfig.BASE_URL + item.gitsList.first().preview)
                .into(ivCategory)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

}