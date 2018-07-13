package com.vadim.ttvadimzhukov.ui.gift.fragments

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.vadim.ttvadimzhukov.R
import com.vadim.ttvadimzhukov.base.BaseFragment
import com.vadim.ttvadimzhukov.data.local.entity.GiftEntity
import com.vadim.ttvadimzhukov.ui.gift.presenters.GiftFragmentPresenter
import com.vadim.ttvadimzhukov.ui.gift.views.GiftFragmentView
import com.vadim.ttvadimzhukov.ui.main.activities.MainActivity
import com.vadim.ttvadimzhukov.utils.GlideListener
import com.vadim.ttvadimzhukov.utils.helpers.ThemeHelper
import kotlinx.android.synthetic.main.fragment_gift.btCancel
import kotlinx.android.synthetic.main.fragment_gift.btShare
import kotlinx.android.synthetic.main.fragment_gift.ivGift
import kotlinx.android.synthetic.main.fragment_gift.pbImage
import kotlinx.android.synthetic.main.fragment_gift.tvGiftName
import kotlinx.android.synthetic.main.fragment_gift.tvGiftType


private const val ARG_GIFT_ENTITY = "ARG_GIFT_ENTITY"

class GiftFragment : BaseFragment(), GiftFragmentView {

    @InjectPresenter lateinit var presenter: GiftFragmentPresenter
    override val layoutId = R.layout.fragment_gift
    private val giftEntity by lazy { arguments!!.getParcelable(ARG_GIFT_ENTITY) as GiftEntity }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        presenter.setGift(giftEntity)
    }

    private fun setListeners() {
        btCancel.setOnClickListener { activity!!.onBackPressed() }
        btShare.setOnClickListener { presenter.shareGift(context!!, giftEntity) }
    }


    override fun setGiftName(name: String) {
        tvGiftName.text = getString(R.string.name, name)
    }

    override fun setGiftType(type: String) {
        tvGiftType.text = getString(R.string.type, type)
    }

    override fun setGiftImage(url: String) {
        Glide.with(context!!)
            .setDefaultRequestOptions(ThemeHelper.getGlideOptions())
            .load(url)
            .listener(GlideListener(pbImage))
            .into(ivGift)
    }

    override fun showProgress() {
        pbImage.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbImage.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        (activity as? MainActivity)?.setToolbarTitle(R.string.gift)
    }

    companion object {
        const val TAG = "GiftFragment"
        fun newInstance(giftEntity: GiftEntity): GiftFragment {
            val fragment = GiftFragment()
            val args = Bundle()
            args.putParcelable(ARG_GIFT_ENTITY, giftEntity)
            fragment.arguments = args
            return fragment
        }
    }
}
