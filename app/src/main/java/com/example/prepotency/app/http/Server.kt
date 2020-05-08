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

    /////////////////////////////////////////////////////////////////////////////////
    //
    //  直播接口
    //
    /////////////////////////////////////////////////////////////////////////////////
    /**
     * @param map FieldMap
     * @return BaseData
     * @method 提交认证
     * @url https://live.chengmeiyoupin.com/api/anchor/artificial_attest
     */
    @POST("api/anchor/artificial_attest")
    @FormUrlEncoded
    fun artificial_attest(@FieldMap map: Map<String, String>): Observable<BaseData<Any>>

}