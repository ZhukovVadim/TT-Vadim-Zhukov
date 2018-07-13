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
import com.vadim.ttvadimzhukov.data.local.entity.GiftEntity
import com.vadim.ttvadimzhukov.utils.helpers.ThemeHelper
import kotlinx.android.synthetic.main.item_gift.view.ivGift
import kotlinx.android.synthetic.main.item_gift.view.tvGiftName
import kotlinx.android.synthetic.main.item_gift.view.tvGiftType

private const val ITEM_LAYOUT_ID = R.layout.item_gift

class GiftsAdapter(
    private val glide: RequestManager,
    private val onItemClickEvent: (categoryEntity: GiftEntity) -> Unit
) : BaseAdapter<GiftEntity, GiftsAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(ITEM_LAYOUT_ID, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list[position]
        with(holder.itemView) {
            tvGiftName.text = context.getString(R.string.name,item.name)
            tvGiftType.text = context.getString(R.string.type, item.type)
            glide.setDefaultRequestOptions(ThemeHelper.getGlideOptions(CircleCrop()))
                .load(BuildConfig.BASE_URL + item.preview)
                .into(ivGift)
            setOnClickListener { onItemClickEvent(item) }
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

}