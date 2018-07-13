package com.vadim.ttvadimzhukov.ui.gift.views

import com.arellomobile.mvp.MvpView

interface GiftFragmentView : MvpView {

    fun setGiftName(name: String)

    fun setGiftType(type: String)

    fun setGiftImage(url: String)

    fun showProgress()

    fun hideProgress()

}
