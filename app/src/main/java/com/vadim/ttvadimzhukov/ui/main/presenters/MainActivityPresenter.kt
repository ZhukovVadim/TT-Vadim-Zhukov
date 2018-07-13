package com.vadim.ttvadimzhukov.ui.main.presenters

import com.arellomobile.mvp.InjectViewState
import com.vadim.ttvadimzhukov.App
import com.vadim.ttvadimzhukov.R
import com.vadim.ttvadimzhukov.base.BasePresenter
import com.vadim.ttvadimzhukov.data.local.entity.GiftEntity
import com.vadim.ttvadimzhukov.ui.main.views.MainActivityView
import com.vadim.ttvadimzhukov.utils.RestConstants
import com.vadim.ttvadimzhukov.utils.RestConstants.RESPONSE.RESPONSE_200
import com.vadim.ttvadimzhukov.utils.helpers.applySchedulers
import retrofit2.Response

@InjectViewState
class MainActivityPresenter : BasePresenter<MainActivityView>() {

    override fun inject() = App.appComponent.inject(this)

    fun fetchGift(giftId: Int) {
        addToComposite(
            dataManager.fetchGift(giftId)
                .applySchedulers()
                .subscribe(::onCompleteFetchGift, ::onErrorFetchGift)
        )
    }

    private fun onCompleteFetchGift(response: Response<GiftEntity>) {
        viewState.hideProgress()
        when(response.code()){
            RESPONSE_200 -> viewState.showGiftFragment(response.body()!!, false)
            else ->{
                viewState.showMessage(R.string.error_message)
                viewState.showCategoriesFragment()
            }
        }
    }

    private fun onErrorFetchGift(throwable: Throwable) {
        throwable.printStackTrace()
        viewState.hideProgress()
        viewState.showMessage(R.string.error_message)
        viewState.showCategoriesFragment()
    }

}