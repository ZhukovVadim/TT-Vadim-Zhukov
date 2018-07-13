package com.vadim.ttvadimzhukov.utils

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.vadim.ttvadimzhukov.data.local.entity.GiftEntity
import org.json.JSONArray

class DataBaseConverter {
    @TypeConverter
    fun giftEntityToJson(entity: GiftEntity): String = Gson().toJson(entity)

    @TypeConverter
    fun giftEntityFromJson(json: String): GiftEntity = Gson().fromJson(json, GiftEntity::class.java)

    @TypeConverter
    fun giftEntityToJsonArray(entityList: List<GiftEntity>): String {
        val jsonArray = JSONArray()
        entityList.forEach { jsonArray.put(Gson().toJson(it)) }
        return jsonArray.toString()
    }

    @TypeConverter
    fun giftEntityFromJsonArray(jsonArray: String): List<GiftEntity> {
        val result = ArrayList<GiftEntity>()
        val jsonArrayResult = JSONArray(jsonArray)
        for (i in 0 until jsonArrayResult.length())
            result.add(Gson().fromJson(jsonArrayResult[i].toString(), GiftEntity::class.java))
        return result
    }

}