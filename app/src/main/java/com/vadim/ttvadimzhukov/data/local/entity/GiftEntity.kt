package com.vadim.ttvadimzhukov.data.local.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.vadim.ttvadimzhukov.utils.RestConstants.JsonKeys.ID
import com.vadim.ttvadimzhukov.utils.RestConstants.JsonKeys.NAME
import com.vadim.ttvadimzhukov.utils.RestConstants.JsonKeys.PREVIEW
import com.vadim.ttvadimzhukov.utils.RestConstants.JsonKeys.SOURCE
import com.vadim.ttvadimzhukov.utils.RestConstants.JsonKeys.TYPE
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GiftEntity(
    @SerializedName(ID) var id: Int,
    @SerializedName(NAME) val name: String,
    @SerializedName(TYPE) val type: String,
    @SerializedName(PREVIEW) val preview: String,
    @SerializedName(SOURCE) val source: String
) : Parcelable