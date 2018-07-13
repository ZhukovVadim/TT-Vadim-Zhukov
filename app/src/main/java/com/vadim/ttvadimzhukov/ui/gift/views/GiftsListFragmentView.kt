package com.vadim.ttvadimzhukov.ui.gift.views

import com.arellomobile.mvp.MvpView
import com.vadim.ttvadimzhukov.data.local.entity.GiftEntity

interface GiftsListFragmentView : MvpView{

    fun setGiftsToList(list: List<GiftEntity>)

}
