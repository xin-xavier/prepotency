package com.example.prepotency.app.http.helper;

import com.example.prepotency.app.http.Server;

/**
 * des 实例化Retrofit,获取ApiService
 *
 * @author zs
 * @date 2020-03-05
 */
public class RetrofitHelper {
    static {
        api= RetrofitFactory.apiFactory().create(Server.class);
        apiService =RetrofitFactory.apiServerFactory().create(Server.class);
        scalarsApiService =RetrofitFactory.scalarsApiServerFactory().create(Server.class);
        // xavier Deprecated
        //liveApiService =RetrofitFactory.liveApiFactory().create(Server.class);
    }
    private RetrofitHelper(){}
    private static Server api;
    private static Server apiService;
    private static Server scalarsApiService;
    //private static Server liveApiService;
    public static Server getApi(){ return api; }
    public static Server getApiService(){ return apiService; }
    public static Server getScalarsApiServer(){ return scalarsApiService; }
    //public static Server getLiveApiService(){ return liveApiService; }
}
