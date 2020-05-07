package com.example.prepotency.app;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

public class VibrateUtils {
    /**
     * 让手机振动milliseconds毫秒
     */
    public static void vibrate(Context context, long milliseconds) {
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        if(vib!=null && vib.hasVibrator()) { //判断手机硬件是否有振动器
            vib.vibrate(milliseconds);
        }
    }

    /**
     * 让手机以我们自己设定的pattern[]模式振动
     * long pattern[] = {1000, 20000, 10000, 10000, 30000};
     */
    public static void vibrate(Context context, long[] pattern,int repeat){
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        if(vib!=null && vib.hasVibrator()){
            vib.vibrate(pattern,repeat);
        }
    }

    /**
     * 取消震动
     */
    public static void virateCancle(Context context){
        //关闭震动
        Vibrator vib = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE);
        if(vib!=null){
            vib.cancel();
        }
    }
}
