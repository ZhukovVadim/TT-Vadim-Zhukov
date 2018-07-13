package com.vadim.ttvadimzhukov.base

import android.support.v7.widget.RecyclerView
import android.support.v7.util.DiffUtil


abstract class BaseAdapter <E, H: RecyclerView.ViewHolder> : RecyclerView.Adapter<H>() {

    protected val list = ArrayList<E>()

    override fun getItemCount() = list.size

    fun setList(newList: ArrayList<E>){
        val diffUtil = BaseDiffUtil(list, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        list.clear()
        list.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getItem(position: Int) = list[position]

    fun getAllItems() = list

    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyDataSetChanged()
    }
}