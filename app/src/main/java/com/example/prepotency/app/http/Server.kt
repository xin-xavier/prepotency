package com.example.prepotency.app.http

import com.example.prepotency.BaseData
import com.example.prepotency.bean.result.TopClassResult
import io.reactivex.Observable
import retrofit2.http.POST

interface Server {

    //头部分类
    @POST("v3/home/topClass")
    fun topClass(): Observable<BaseData<MutableList<TopClassResult>>>

}