package com.example.prepotency.main.home.container

import com.example.prepotency.app.base.presenter.BasePresenter
import com.example.prepotency.app.http.helper.RetrofitHelper
import com.example.prepotency.app.http.observer.HttpDefaultObserver
import com.example.prepotency.bean.result.HotResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ClassPresenter(view: ClassContract.View) : BasePresenter<ClassContract.View>(view) ,ClassContract.Presenter<ClassContract.View> {
    override fun classGoods(map: MutableMap<String, Int?>) {
        RetrofitHelper.getApiService()
            .classGoods(map)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (object : HttpDefaultObserver<HotResult>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }

                override fun onSuccess(t: HotResult) {
                    view?.showResult(t)
                }

                override fun onError(errorMsg: String) {
                    view?.onError(errorMsg)
                }

            })
    }
}