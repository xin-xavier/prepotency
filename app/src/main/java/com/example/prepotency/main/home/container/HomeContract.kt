package com.example.prepotency.main.home.container

import com.example.prepotency.app.base.viewstratum.presentation.IVPresentation
import com.example.prepotency.bean.result.HomeAdResult
import com.example.prepotency.bean.result.HotResult
import com.example.prepotency.bean.result.SlideShowResult
import com.zs.wanandroid.base.IBasePresenter

interface HomeContract{

    interface View: IVPresentation {
        fun showSlideShow(slideList : List<SlideShowResult>)
        fun showAd(adList : List<HomeAdResult>)
        fun showHot(hotResult: HotResult)
    }

    interface Presenter<T>: IBasePresenter<View> {
        fun slideShow(pid: Int)
        fun homeAd()
        fun hot(page: Int)
    }

}