package com.vadim.ttvadimzhukov.ui.gift.presenters

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v4.content.FileProvider
import com.arellomobile.mvp.InjectViewState
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.vadim.ttvadimzhukov.App
import com.vadim.ttvadimzhukov.BuildConfig
import com.vadim.ttvadimzhukov.R
import com.vadim.ttvadimzhukov.base.BasePresenter
import com.vadim.ttvadimzhukov.data.local.entity.GiftEntity
import com.vadim.ttvadimzhukov.ui.gift.views.GiftFragmentView
import java.io.File
import java.io.FileOutputStream


@InjectViewState
class GiftFragmentPresenter : BasePresenter<GiftFragmentView>() {

    override fun inject() = App.appComponent.inject(this)
    private var canShare = true

    fun setGift(giftEntity: GiftEntity) {
        viewState.setGiftImage(BuildConfig.BASE_URL + giftEntity.source)
        viewState.setGiftName(giftEntity.name)
        viewState.setGiftType(giftEntity.type)
    }

    fun shareGift(context: Context, giftEntity: GiftEntity) {
        viewState.showProgress()
        val type = giftEntity.type
        if (canShare) {
            canShare = false
            Glide.with(context).asBitmap().load(BuildConfig.BASE_URL + giftEntity.source)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        val contentUri = getContentUri(context, resource, type)
                        val shareIntent = Intent()
                        shareIntent.action = Intent.ACTION_SEND
                        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        shareIntent.setDataAndType(contentUri, context.contentResolver.getType(contentUri))
                        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
                        context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.choose_app)))
                        viewState.hideProgress()
                        canShare = true
                    }
                })
        }
    }

    private fun getContentUri(context: Context, resource: Bitmap, type: String): Uri {
        val cachePath = File(context.cacheDir, "images")
        cachePath.mkdirs()
        val stream = FileOutputStream(cachePath.path + "/image.$type")
        resource.compress(Bitmap.CompressFormat.PNG, 100, stream)
        stream.close()
        val imagePath = File(context.cacheDir, "images")
        val newFile = File(imagePath, "image.$type")
        return FileProvider.getUriForFile(context, "com.vadim.ttvadimzhukov.fileprovider", newFile)
    }
}