package com.vadim.ttvadimzhukov.utils.helpers

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.applySchedulers(): Observable<T> {
    return subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
}