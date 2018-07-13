package com.vadim.ttvadimzhukov.ui.category.views

import android.support.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.vadim.ttvadimzhukov.data.local.entity.CategoryEntity
import okhttp3.ResponseBody

interface CategoriesListFragmentView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showProgress()

    @StateStrategyType(SkipStrategy::class)
    fun hideProgress()

    fun setItemsToList(list: List<CategoryEntity>)

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(@StringRes stringId: Int)

    @StateStrategyType(SkipStrategy::class)
    fun setJs(code: ResponseBody)
}
