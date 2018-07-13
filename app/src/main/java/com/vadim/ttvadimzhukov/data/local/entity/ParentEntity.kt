package com.vadim.ttvadimzhukov.data.local.entity

import com.google.gson.annotations.SerializedName
import com.vadim.ttvadimzhukov.utils.RestConstants.JsonKeys.CATALOG
import com.vadim.ttvadimzhukov.utils.RestConstants.JsonKeys.WELCOME

data class ParentEntity(
    @SerializedName(WELCOME) val welcome: String,
    @SerializedName(CATALOG) val categoryList: List<CategoryEntity>
)