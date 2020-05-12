package com.example.prepotency.app.http

import com.example.prepotency.app.api.ConstantPool.Companion.PAGE
import com.example.prepotency.app.api.ConstantPool.Companion.PID
import com.example.prepotency.bean.BaseData
import com.example.prepotency.bean.result.*
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Server {

    //头部分类
    @POST("v3/home/topClass")
    fun topClass(): Observable<BaseData<MutableList<TopClassResult>>>

    //  分类商品
    @POST("v3/home/classGoods")
    @FormUrlEncoded
    fun classGoods(@FieldMap map: MutableMap<String, Int?>): Observable<BaseData<HotResult>>

    //  分类商品
    @POST("v3/home/hot")
    @FormUrlEncoded
    fun hot(@Field(PAGE) page : Int): Observable<BaseData<HotResult>>

    // 首页广告 https://api.chengmeiyoupin.com/v3/home/ad
    @POST("v3/home/ad")
    fun homeAd(): Observable<BaseData<List<HomeAdResult>>>

    // 版本更新
    @POST("v3/home/release")
    @FormUrlEncoded
    fun versionUpdating(@FieldMap stringObjectMap: Map<String, String>): Observable<BaseData<VersionUpdatingResult>>

    //首页轮播图
    @POST("v3/home/slideShow")
    @FormUrlEncoded
    fun slideShow(@Field(PID) pid : Int): Observable<BaseData<List<SlideShowResult>>>

}