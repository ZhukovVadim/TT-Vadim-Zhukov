package com.vadim.ttvadimzhukov.data

import com.vadim.ttvadimzhukov.data.local.database.DataBase
import com.vadim.ttvadimzhukov.data.local.entity.CategoryEntity
import com.vadim.ttvadimzhukov.data.remote.TTApi


class DataManager(private val restApi: TTApi, private val db: DataBase) {

    //REST--------------------------------------------------------------------------------------------------------------
    fun fetchWelcome() = restApi.fetchWelcome()

    fun fetchGift(giftId: Int) = restApi.fetchGift(giftId)

    fun fetchJs(path: String) = restApi.fetchJs(path)
    //------------------------------------------------------------------------------------------------------------------
    //Data Base---------------------------------------------------------------------------------------------------------
    fun saveAllCategories(categoriesList: List<CategoryEntity>) =
        categoriesList.forEach { db.getCategoryDao().insert(it) }

    fun saveCategory(categoryEntity: CategoryEntity) = db.getCategoryDao().insert(categoryEntity)

    fun getAllCategories() = db.getCategoryDao().getAll()

    fun getCategory(categoryName: String) = db.getCategoryDao().getCategory(categoryName)
    //------------------------------------------------------------------------------------------------------------------
}