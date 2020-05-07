package com.example.prepotency.app.http.url

interface ConstantUrl {
    companion object {
        const val DEBUG = true
        // url 版本
        const val V = "v1/"
        const val VV = "v2/"
        const val VVV = "v3/"
        const val BASE_URL_DEBUG = "https://api.chengmeiyoupin.com/"
        const val BASE_URL_RELEASE = "https://api.chengmeiyouxuan.com/"
        const val BASE_URL_SERVER_DEBUG = "https://api.server.chengmeiyoupin.com/"
        const val BASE_URL_SERVER_RELEASE = "https://api.server.chengmeiyouxuan.com/"
        // 连接
        const val WS_DEBUG = "ws://connector.chengmeiyoupin.com:9503"
        const val WS_RELEASE = "wss://chat.chengmeiyouxuan.com:"
        const val H5_URL_DEBUG = "https://h5.chengmeiyoupin.com/"
        const val H5_URL_RELEASE = "https://h5.chengmeiyouxuan.com/"
        const val QiNiuYun_DNS = "https://upload.chengmeiyouxuan.com/"
        // 测试 WS
        const val TEST_WS = "ws://echo.websocket.org"
    }
}