package com.example.prepotency.main.home

import com.example.prepotency.app.http.observer.HttpDefaultObserver
import com.example.prepotency.app.http.helper.RetrofitHelper
import com.example.prepotency.bean.result.TopClassResult
import com.example.prepotency.app.base.presenter.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TobPresenter(view: TopContract.View): BasePresenter<TopContract.View>(view)
    , TopContract.Presenter<TopContract.View>{
    override fun topClass() {
        RetrofitHelper.getApiService()
            .topClass()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : HttpDefaultObserver<MutableList<TopClassResult>>(){
                override fun disposable(d: Disposable) {
                    addSubscribe(d)
                }
                override fun onSuccess(t: MutableList<TopClassResult>) {
                    view?.showList(t)
                }

                override fun onError(errorMsg: String) {
                    view?.showError(errorMsg)
                }
            })
    }
}