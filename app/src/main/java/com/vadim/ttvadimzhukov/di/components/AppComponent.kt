package com.vadim.ttvadimzhukov.di.components

import com.vadim.ttvadimzhukov.base.BaseActivity
import com.vadim.ttvadimzhukov.base.BaseFragment
import com.vadim.ttvadimzhukov.di.module.AppModule
import com.vadim.ttvadimzhukov.ui.category.presenters.CategoriesListFragmentPresenter
import com.vadim.ttvadimzhukov.ui.gift.presenters.GiftFragmentPresenter
import com.vadim.ttvadimzhukov.ui.gift.presenters.GiftsListFragmentPresenter
import com.vadim.ttvadimzhukov.ui.main.presenters.MainActivityPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    fun inject(baseActivity: BaseActivity)

    fun inject(baseFragment: BaseFragment)

    fun inject(presenter: MainActivityPresenter)

    fun inject(presenter: CategoriesListFragmentPresenter)

    fun inject(presenter: GiftsListFragmentPresenter)

    fun inject(presenter: GiftFragmentPresenter)

}