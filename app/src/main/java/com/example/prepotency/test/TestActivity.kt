package com.example.prepotency.test

import android.os.Bundle
import com.example.prepotency.R
import com.example.prepotency.app.api.ConstantPool.Companion.ID_CARD
import com.example.prepotency.app.api.ConstantPool.Companion.ID_CARD_POSITIVE
import com.example.prepotency.app.api.ConstantPool.Companion.ID_CARD_REVERSE
import com.example.prepotency.app.api.ConstantPool.Companion.NAME
import com.example.prepotency.app.api.ConstantPool.Companion.PHONE
import com.example.prepotency.app.base.viewstratum.activity.SimpleDecorViewActivity
import com.example.prepotency.app.http.helper.RetrofitHelper
import com.example.prepotency.app.http.observer.HttpDefaultObserver
import com.example.prepotency.utils.RxUtils
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : SimpleDecorViewActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    override fun init() {
    }

    override fun onPrepare() {

    }
}
