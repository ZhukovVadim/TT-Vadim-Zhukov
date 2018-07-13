package com.vadim.ttvadimzhukov.ui.gift.fragments

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.vadim.ttvadimzhukov.R
import com.vadim.ttvadimzhukov.adapters.GiftsAdapter
import com.vadim.ttvadimzhukov.base.BaseFragment
import com.vadim.ttvadimzhukov.data.local.entity.GiftEntity
import com.vadim.ttvadimzhukov.ui.gift.presenters.GiftsListFragmentPresenter
import com.vadim.ttvadimzhukov.ui.gift.views.GiftsListFragmentView
import com.vadim.ttvadimzhukov.ui.main.activities.MainActivity
import com.vadim.ttvadimzhukov.utils.helpers.ThemeHelper
import kotlinx.android.synthetic.main.fragment_gifts_list.rvGifts

private const val ARG_CATEGORY_NAME = "ARG_CATEGORY_NAME"

class GiftsListFragment : BaseFragment(), GiftsListFragmentView {

    @InjectPresenter lateinit var presenter: GiftsListFragmentPresenter
    override val layoutId = R.layout.fragment_gifts_list
    private val categoryName by lazy { arguments!!.getString(ARG_CATEGORY_NAME) }
    private val adapter by lazy { GiftsAdapter(Glide.with(context!!), ::onGiftClickEvent) }

    @ProvidePresenter
    fun providePresenter() = GiftsListFragmentPresenter(categoryName)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        ThemeHelper.setRecyclerViewStyle(rvGifts)
        rvGifts.adapter = adapter
    }

    private fun onGiftClickEvent(giftEntity: GiftEntity) {
        (activity as? MainActivity)?.showGiftFragment(giftEntity)
    }

    override fun setGiftsToList(list: List<GiftEntity>) {
        adapter.setList(ArrayList(list))
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.setToolbarTitle(categoryName)
    }

    companion object {
        const val TAG = "GiftsListFragment"
        fun newInstance(categoryName: String): GiftsListFragment {
            val fragment = GiftsListFragment()
            val args = Bundle()
            args.putString(ARG_CATEGORY_NAME, categoryName)
            fragment.arguments = args
            return fragment
        }
    }
}
