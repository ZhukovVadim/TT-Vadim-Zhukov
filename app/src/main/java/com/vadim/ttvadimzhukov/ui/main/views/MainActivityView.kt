package com.vadim.ttvadimzhukov.ui.main.views

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.vadim.ttvadimzhukov.data.local.entity.GiftEntity

interface MainActivityView : MvpView{

    @StateStrategyType(SkipStrategy::class)
    fun showProgress()

    @StateStrategyType(SkipStrategy::class)
    fun hideProgress()

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(@StringRes stringId: Int)

    fun showGiftFragment(giftEntity: GiftEntity, addToBackStack: Boolean  = true)

    fun showCategoriesFragment()
}
