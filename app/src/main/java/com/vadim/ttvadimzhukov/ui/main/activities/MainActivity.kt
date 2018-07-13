package com.vadim.ttvadimzhukov.ui.main.activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import com.airbnb.deeplinkdispatch.DeepLink
import com.arellomobile.mvp.presenter.InjectPresenter
import com.pawegio.kandroid.toast
import com.vadim.ttvadimzhukov.BuildConfig
import com.vadim.ttvadimzhukov.R
import com.vadim.ttvadimzhukov.base.BaseActivity
import com.vadim.ttvadimzhukov.data.local.entity.GiftEntity
import com.vadim.ttvadimzhukov.ui.category.fragments.CategoriesListFragment
import com.vadim.ttvadimzhukov.ui.gift.fragments.GiftFragment
import com.vadim.ttvadimzhukov.ui.gift.fragments.GiftsListFragment
import com.vadim.ttvadimzhukov.ui.main.presenters.MainActivityPresenter
import com.vadim.ttvadimzhukov.ui.main.views.MainActivityView
import com.vadim.ttvadimzhukov.utils.Navigator
import com.vadim.ttvadimzhukov.utils.RestConstants.JsonKeys.ID


private const val CONTANER_ID = R.id.flContainer

@DeepLink("${BuildConfig.BASE_URL}gift/{$ID}")
class MainActivity : BaseActivity(), MainActivityView {

    @InjectPresenter lateinit var presenter: MainActivityPresenter
    override val layoutId = R.layout.activity_main
    private val progressDialog by lazy { ProgressDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.getBooleanExtra(DeepLink.IS_DEEP_LINK, false)) {
            val parameters = intent.extras
            try {
                val giftId = parameters.getString(ID).toInt()
                presenter.fetchGift(giftId)
            }
            catch (e: NumberFormatException){
                toast(R.string.error_message)
                showCategoriesFragment()
            }
        } else {
            showCategoriesFragment()
        }
    }

    override fun showCategoriesFragment() {
        Navigator.replaceFragment(this, CONTANER_ID, CategoriesListFragment.newInstance())
    }

    fun openGiftsListFragment(categoryName: String) {
        Navigator.replaceFragment(
            this,
            CONTANER_ID,
            GiftsListFragment.newInstance(categoryName),
            GiftsListFragment.TAG,
            true
        )
    }

    override fun showGiftFragment(giftEntity: GiftEntity, addToBackStack: Boolean) {
        if (addToBackStack)
            Navigator.replaceFragment(
                this,
                CONTANER_ID,
                GiftFragment.newInstance(giftEntity),
                GiftFragment.TAG,
                true
            )
        else
            Navigator.replaceFragment(this, CONTANER_ID, GiftFragment.newInstance(giftEntity))
    }

    fun setToolbarTitle(@StringRes stringId: Int) {
        supportActionBar?.let { it.title = getString(stringId) }
    }

    fun setToolbarTitle(title: String) {
        supportActionBar?.let { it.title = title }
    }

    override fun showProgress() = progressDialog.show()


    override fun hideProgress() = progressDialog.hide()


    override fun showMessage(stringId: Int) {
        toast(stringId)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
