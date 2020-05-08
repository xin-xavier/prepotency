package com.example.prepotency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prepotency.app.api.ConstantPool.Companion.ID_CARD
import com.example.prepotency.app.api.ConstantPool.Companion.ID_CARD_POSITIVE
import com.example.prepotency.app.api.ConstantPool.Companion.ID_CARD_REVERSE
import com.example.prepotency.app.api.ConstantPool.Companion.NAME
import com.example.prepotency.app.api.ConstantPool.Companion.PHONE
import com.example.prepotency.app.http.helper.RetrofitHelper
import com.example.prepotency.app.http.observer.HttpDefaultObserver
import com.example.prepotency.utils.RxUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val map= mutableMapOf(
            NAME to "",
            ID_CARD to "",
            ID_CARD_POSITIVE to "",
            ID_CARD_REVERSE to "",
            PHONE to ""
        )
        artificialAttest.setOnClickListener {
            RetrofitHelper.getLiveApiService().artificial_attest(map)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(object : HttpDefaultObserver<Any>(){
                    override fun disposable(d: Disposable) {
                    }
                    override fun onSuccess(t: Any) {
                    }
                    override fun onError(errorMsg: String) {
                    }
                })

        }
    }
}
