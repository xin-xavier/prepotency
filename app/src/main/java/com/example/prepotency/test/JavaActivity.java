package com.example.prepotency.test;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.prepotency.R;
import com.example.prepotency.app.base.viewstratum.activity.SimpleActivty;

public class JavaActivity extends SimpleActivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

    }

    @Override
    public void init() {
        //ProgressDialog.show(context, null, "正在连接服务器...", true, true);

    }

}
