package com.example.prepotency.main.home.container

import com.example.prepotency.app.base.viewstratum.presentation.IVPresentation
import com.example.prepotency.bean.result.HotResult
import com.zs.wanandroid.base.IBasePresenter

interface ClassContract{

    interface View: IVPresentation {
        fun showResult(hotResult: HotResult)
    }

    interface Presenter<T>: IBasePresenter<View> {
        fun classGoods(map: MutableMap<String, Int?>)
    }

}