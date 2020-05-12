package com.example.prepotency.main.home.container

import com.example.prepotency.app.base.presenter.BasePresenter
import com.example.prepotency.app.http.helper.RetrofitHelper
import com.example.prepotency.app.http.observer.HttpDefaultObserver
import com.example.prepotency.bean.result.HotResult
import com.example.prepotency.bean.result.SlideShowResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(view: HomeContract.View) : BasePresenter<HomeContract.View>(view) ,HomeContract.Presenter<HomeContract.View> {
    override fun hot(page: Int) {
        RetrofitHelper.getApiService()
            .hot(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (object : HttpDefaultObserver<HotResult>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: HotResult) {
                    view?.showHot(t)
                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)
                }

            })
    }


    override fun slideShow(pid: Int) {
        RetrofitHelper.getApiService()
            .slideShow(pid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (object : HttpDefaultObserver<List<SlideShowResult>>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }
                override fun onSuccess(t: List<SlideShowResult>) {
                    view?.showSlideShow(t)
                }
                override fun onError(errorMsg: String) {
                }
            })
    }

    override fun homeAd() {

    }


}