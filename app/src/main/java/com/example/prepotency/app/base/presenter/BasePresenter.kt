package com.example.prepotency.app.base.presenter

import com.example.prepotency.app.base.viewstratum.presentation.IVPresentation
import com.example.prepotency.app.base.viewstratum.presentation.UIPresentation
import com.zs.wanandroid.base.IBasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<V : IVPresentation>(view: V) :
    IBasePresenter<V> {

    protected var view: V? = view
    private var compositeDisposable: CompositeDisposable? = null

    init {
        compositeDisposable = CompositeDisposable()
    }

    override fun onCreate() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onDestroy() {
        compositeDisposable?.clear()
    }

    protected fun addSubscribe(disposable: Disposable) {
        compositeDisposable?.add(disposable)
    }

}