package com.vadim.ttvadimzhukov.data.local.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.provider.MediaStore.Video.VideoColumns.CATEGORY
import com.google.gson.annotations.SerializedName
import com.vadim.ttvadimzhukov.utils.RestConstants.JsonKeys.LIST
import com.vadim.ttvadimzhukov.utils.RestConstants.JsonKeys.NAME

@Entity(tableName = CATEGORY)
data class CategoryEntity (
    @SerializedName(NAME) @PrimaryKey() val name: String,
    @SerializedName(LIST) var gitsList: List<GiftEntity>
)