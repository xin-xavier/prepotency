package com.example.prepotency.main.home

import com.example.prepotency.app.base.viewstratum.presentation.IVPresentation
import com.example.prepotency.app.base.viewstratum.presentation.UIPresentation
import com.example.prepotency.bean.result.TopClassResult
import com.zs.wanandroid.base.IBasePresenter

interface TopContract {

    interface View:IVPresentation{
        fun showList(list:MutableList<TopClassResult>)
    }

    interface Presenter<T>: IBasePresenter<View> {
        fun topClass()
    }
}