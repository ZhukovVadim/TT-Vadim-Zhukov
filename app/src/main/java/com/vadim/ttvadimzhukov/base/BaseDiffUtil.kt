package com.vadim.ttvadimzhukov.base

import android.support.v7.util.DiffUtil

class BaseDiffUtil<E>(private val oldList: List<E>, private val newList: List<E>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]!! == newList[newItemPosition]
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]!! == newList[newItemPosition]
    }
}