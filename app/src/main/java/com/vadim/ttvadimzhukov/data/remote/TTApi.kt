package com.vadim.ttvadimzhukov.data.remote

import com.vadim.ttvadimzhukov.data.local.entity.GiftEntity
import com.vadim.ttvadimzhukov.data.local.entity.ParentEntity
import com.vadim.ttvadimzhukov.utils.RestConstants.Get.GET_GIFT
import com.vadim.ttvadimzhukov.utils.RestConstants.Get.GET_JS
import com.vadim.ttvadimzhukov.utils.RestConstants.Get.GET_WELCOME
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TTApi {

    @GET(GET_WELCOME)
    fun fetchWelcome(): Observable<Response<ParentEntity>>

    @GET(GET_GIFT)
    fun fetchGift(@Path("id") giftId: Int): Observable<Response<GiftEntity>>

    @GET(GET_JS)
    fun fetchJs(@Path(value = "path", encoded = true) path: String): Observable<Response<ResponseBody>>
}