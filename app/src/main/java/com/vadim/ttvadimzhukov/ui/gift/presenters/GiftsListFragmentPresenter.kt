package com.vadim.ttvadimzhukov.ui.gift.presenters

import com.arellomobile.mvp.InjectViewState
import com.vadim.ttvadimzhukov.App
import com.vadim.ttvadimzhukov.base.BasePresenter
import com.vadim.ttvadimzhukov.ui.gift.views.GiftsListFragmentView

@InjectViewState
class GiftsListFragmentPresenter(var categoryName: String) : BasePresenter<GiftsListFragmentView>() {

    override fun inject() = App.appComponent.inject(this)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        fetchGiftsList(categoryName)
    }

    fun fetchGiftsList(categoryName: String){
        dataManager.getCategory(categoryName)?.let {
            viewState.setGiftsToList(it.gitsList)
        }
    }

}