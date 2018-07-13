package com.vadim.ttvadimzhukov.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.vadim.ttvadimzhukov.App

abstract class BaseFragment : MvpAppCompatFragment() {

    abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        App.appComponent.inject(this)
        return inflater.inflate(layoutId, container, false)
    }
}