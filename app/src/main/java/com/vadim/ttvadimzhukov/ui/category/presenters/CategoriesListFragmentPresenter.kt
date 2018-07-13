package com.vadim.ttvadimzhukov.ui.category.presenters

import com.arellomobile.mvp.InjectViewState
import com.vadim.ttvadimzhukov.App
import com.vadim.ttvadimzhukov.R
import com.vadim.ttvadimzhukov.base.BasePresenter
import com.vadim.ttvadimzhukov.data.local.entity.CategoryEntity
import com.vadim.ttvadimzhukov.data.local.entity.ParentEntity
import com.vadim.ttvadimzhukov.ui.category.views.CategoriesListFragmentView
import com.vadim.ttvadimzhukov.utils.RestConstants.RESPONSE.RESPONSE_200
import com.vadim.ttvadimzhukov.utils.helpers.applySchedulers
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response


@InjectViewState
class CategoriesListFragmentPresenter : BasePresenter<CategoriesListFragmentView>() {

    override fun inject() = App.appComponent.inject(this)
    private var firstLoad = true

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        fetchWelcome()
    }

    fun fetchWelcome() {
        viewState.showProgress()
        addToComposite(
            dataManager.fetchWelcome()
                .applySchedulers()
                .concatMap(::concatMapFetchWelcome)
                .distinct { it.name }                                           // Отсеевание повторяющихся категорий
                .doOnNext(::distinctGiftsListAndSaveCategoryToDb)               // Отсеевание повторяющихся подарков в категории
                .toList()
                .subscribe(::onCompleteFetchWelcome, ::onErrorFetchWelcome)
        )
    }

    private fun distinctGiftsListAndSaveCategoryToDb(categoryEntity: CategoryEntity) {
        categoryEntity.gitsList = categoryEntity.gitsList.distinctBy { it.id }
        dataManager.saveCategory(categoryEntity)
    }

    private fun concatMapFetchWelcome(response: Response<ParentEntity>): Observable<CategoryEntity> {
        return when (response.code()) {
            RESPONSE_200 -> {
                var welcome = response.body()!!.welcome
                if (welcome[0] == '/') welcome = welcome.substring(1, welcome.length)
                if(firstLoad) {
                    fetchJs(welcome)
                    firstLoad= false
                }
                Observable.fromIterable(response.body()!!.categoryList)
            }
            else -> {
                viewState.showMessage(R.string.error_message)
                Observable.fromIterable(dataManager.getAllCategories())
            }
        }
    }

    private fun onCompleteFetchWelcome(list: List<CategoryEntity>) {
        viewState.hideProgress()
        dataManager.saveAllCategories(list)
        viewState.setItemsToList(list)
    }

    private fun onErrorFetchWelcome(throwable: Throwable) {
        throwable.printStackTrace()
        viewState.hideProgress()
        viewState.setItemsToList(dataManager.getAllCategories())
        viewState.showMessage(R.string.error_message)
    }

    private fun fetchJs(path: String) {
        addToComposite(
            dataManager.fetchJs(path)
                .applySchedulers()
                .subscribe(::onCompleteLoadJs, ::onErrorFetchJs)
        )
    }

    private fun onErrorFetchJs(throwable: Throwable) {
        throwable.printStackTrace()
    }

    private fun onCompleteLoadJs(code: Response<ResponseBody>) {
        viewState.setJs(code.body()!!)
    }

}