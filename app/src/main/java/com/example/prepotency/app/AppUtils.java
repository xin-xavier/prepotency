package com.example.prepotency.app;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class AppUtils {

    private static volatile AppUtils singleton;

    private AppUtils() {
    }

    public static AppUtils getInstance() {
        if (singleton == null) {
            synchronized (AppUtils.class) {
                if (singleton == null) {
                    singleton = new AppUtils();
                }
            }
        }
        return singleton;
    }

    /**
     * 校验某个服务是否还存在
     */
    public boolean isServiceRunning(Context context, String serviceName){
        // 校验服务是否还存在
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        if(am!=null) {
            List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(Integer.MAX_VALUE);
            for (ActivityManager.RunningServiceInfo info : services) {
                // 得到所有正在运行的服务的名称
                String name = info.service.getClassName();
                Log.i("ServiceRunning", "isServiceRunning: "+name);
                if (serviceName.equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }



}