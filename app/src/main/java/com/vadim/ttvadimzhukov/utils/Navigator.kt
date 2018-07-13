package com.vadim.ttvadimzhukov.utils

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

object Navigator {

    fun replaceFragment(
        activity: AppCompatActivity, @IdRes containerId: Int, fragment: Fragment, tag: String? = null,
        backStack: Boolean = false
    ) {
        activity.supportFragmentManager
            .beginTransaction()
            .apply {
                if (tag != null) replace(containerId, fragment, tag)
                else replace(containerId, fragment)
                if(backStack) addToBackStack(tag)
            }
            .commit()
    }
}