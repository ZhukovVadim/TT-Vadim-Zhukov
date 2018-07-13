package com.vadim.ttvadimzhukov.utils

import com.airbnb.deeplinkdispatch.DeepLinkHandler

import android.app.Activity
import android.os.Bundle

@DeepLinkHandler(AppDeepLinkModule::class)
class DeepLinkActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DeepLinkDelegate(AppDeepLinkModuleLoader()).dispatchFrom(this)
        finish()
    }
}
