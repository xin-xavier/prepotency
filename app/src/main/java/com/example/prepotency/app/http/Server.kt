package com.example.prepotency.app.http

import com.example.prepotency.bean.BaseData
import com.example.prepotency.bean.result.HotResult
import com.example.prepotency.bean.result.TopClassResult
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Server {

    //头部分类
    @POST("v3/home/topClass")
    fun topClass(): Observable<BaseData<MutableList<TopClassResult>>>

    //  分类商品
    @FormUrlEncoded
    @POST("v3/home/classGoods")
    fun classGoods(@FieldMap map: MutableMap<String, Int?>): Observable<BaseData<HotResult>>

}