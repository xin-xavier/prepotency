package com.example.prepotency.app.http.observer

import android.util.Log
import com.example.prepotency.bean.BaseData
import com.example.prepotency.app.AppManager
import com.example.prepotency.app.http.HttpfinishCallback
import com.example.prepotency.app.http.helper.BusinessHttpException
import com.google.gson.JsonParseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import java.lang.reflect.ParameterizedType
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * des 给Response脱壳,对服务器错误统一处理
 *
 * @author zs
 * @date 2020-03-13
 */
abstract class HttpDefaultObserver<T> : Observer<BaseData<T>>, HttpfinishCallback<T> {

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        disposable(d)
    }

    override fun onNext(t: BaseData<T>) {
        if (t.code==200) {
            if (t.result==null){
                try {
                    Log.i("genericSuperclass","onNext: "+ System.currentTimeMillis())
                    val tClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
                    t.result = tClass.newInstance()
                }catch (e : ClassCastException){
                    e.printStackTrace()
                }
            }
            t.result?.let { onSuccess(it) }

        }
        //code!=0代表业务出错，进行过滤
        else{
            filterCode(t.msg,t.code)
        }
    }

    override fun onError(e: Throwable) {
        // Log.i("Throwable","onError: "+ System.currentTimeMillis())
        // com.example.zs_wan_android I/Throwable: onError: 1587277284516
        // 调用 onError() 标志事件结束 所以只会运行一次
        val errorMsg = if (e is UnknownHostException) {
            "网络异常"
        } else if (e is JSONException || e is JsonParseException) {
            "数据异常"
        } else if (e is SocketTimeoutException) {
            "连接超时"
        } else if (e is ConnectException) {
            "连接错误"
        } else if (e is BusinessHttpException){
            e.businessMessage
            //Log.i("BusinessHttpException","onError: "+ e.businessMessage)
            // com.example.zs_wan_android I/BusinessHttpException: onError: 请先登录！
        } else{
            "未知错误"
        }
        e.printStackTrace()
        onError(errorMsg)
    }

    private fun filterCode(msg: String, code: Int) {
        when (code) {
            //登录失败
            -1001 -> {
                AppManager.resetUser()
                onError(
                    BusinessHttpException(
                        msg,
                        code
                    )
                )
            }
            //未知code,将errorMsg封装成异常,由onError()处理
            else -> onError(
                BusinessHttpException(
                    msg,
                    code
                )
            )
        }
    }

}