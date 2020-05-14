package com.example.prepotency.main.home.container

import com.example.prepotency.app.base.viewstratum.presentation.IVPresentation
import com.example.prepotency.bean.result.*
import com.zs.wanandroid.base.IBasePresenter

interface HomeContract {

    interface View : IVPresentation {
        fun showSlideShow(slideList: List<SlideShowResult>)
        fun showAd(adList: List<HomeAdResult>)
        fun showSubClass(subclassList : List<SubClassResult>)
        fun showChoiceShop(choiceLists : List<List<ChoiceShopResult>>)
        fun showHot(hotResult: HotResult)
    }

    interface Presenter<T> : IBasePresenter<View> {
        fun slideShow(pid: Int)
        fun homeAd()
        fun subClass(pid: Int)
        fun choiceShop()
        fun hot(page: Int)
    }

}