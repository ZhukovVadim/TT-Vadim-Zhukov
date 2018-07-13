package com.vadim.ttvadimzhukov.ui.category.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.pawegio.kandroid.runDelayed
import com.pawegio.kandroid.toast
import com.vadim.ttvadimzhukov.R
import com.vadim.ttvadimzhukov.adapters.CategoriesAdapter
import com.vadim.ttvadimzhukov.base.BaseFragment
import com.vadim.ttvadimzhukov.data.local.entity.CategoryEntity
import com.vadim.ttvadimzhukov.ui.category.presenters.CategoriesListFragmentPresenter
import com.vadim.ttvadimzhukov.ui.category.views.CategoriesListFragmentView
import com.vadim.ttvadimzhukov.ui.main.activities.MainActivity
import com.vadim.ttvadimzhukov.utils.helpers.ThemeHelper
import kotlinx.android.synthetic.main.fragment_categories.rvCategories
import kotlinx.android.synthetic.main.fragment_categories.srlCategories
import kotlinx.android.synthetic.main.fragment_categories.wvWelcome
import okhttp3.ResponseBody


class CategoriesListFragment : BaseFragment(), CategoriesListFragmentView {

    @InjectPresenter lateinit var presenter: CategoriesListFragmentPresenter
    override val layoutId = R.layout.fragment_categories
    private val adapter by lazy { CategoriesAdapter(Glide.with(this), ::onCategoryClickEvent) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setListeners()
    }

    private fun initRecyclerView() {
        ThemeHelper.setRecyclerViewStyle(rvCategories)
        ThemeHelper.setSwipeRefreshLayoutStyle(srlCategories)
        rvCategories.adapter = adapter
    }

    private fun setListeners() {
        srlCategories.setOnRefreshListener { presenter.fetchWelcome() }
    }

    private fun onCategoryClickEvent(categoryEntity: CategoryEntity) {
        (activity as? MainActivity)?.openGiftsListFragment(categoryEntity.name)
    }

    override fun setJs(code: ResponseBody) {
        wvWelcome.visibility = View.VISIBLE
        wvWelcome.settings.javaScriptEnabled = true
        wvWelcome.webChromeClient = WebChromeClient()
        wvWelcome.setBackgroundColor(Color.TRANSPARENT)
        val html = "<html><body style=\"background-color:transparent;\">  </body></html>"
        val mime = "text/html"
        val encoding = "utf-8"
        wvWelcome.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                view.loadUrl("javascript:" + code.string())
                runDelayed(10000) { view.visibility = View.GONE }
            }
        }
        wvWelcome.loadDataWithBaseURL(null, html, mime, encoding, null)
    }

    override fun showProgress() {
        srlCategories.isRefreshing = true
    }

    override fun hideProgress() {
        srlCategories.isRefreshing = false
    }

    override fun showMessage(stringId: Int) {
        toast(stringId)
    }

    override fun setItemsToList(list: List<CategoryEntity>) = adapter.setList(ArrayList(list))

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.setToolbarTitle(R.string.categories)
    }

    companion object {
        const val TAG = "CategoriesListFragment"
        fun newInstance(): CategoriesListFragment {
            val fragment = CategoriesListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
