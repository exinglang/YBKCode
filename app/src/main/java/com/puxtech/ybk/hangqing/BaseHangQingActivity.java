package com.puxtech.ybk.hangqing;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import com.puxtech.ybk.BaseActivity;
import com.puxtech.ybk.MyApplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fanshuo on 16/4/27.
 */
public class BaseHangQingActivity extends BaseActivity {

    protected MyApplication myApplication;
    protected Context context;

    protected List<AsyncTask<Void, Void, Boolean>> mAsyncTasks = new ArrayList<AsyncTask<Void, Void, Boolean>>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApplication = (MyApplication) getApplicationContext();
        this.context = this;
    }

    protected void putAsyncTask(AsyncTask<Void, Void, Boolean> asyncTask) {
        mAsyncTasks.add(asyncTask.execute());
    }

    protected void clearAsyncTask() {
        Iterator<AsyncTask<Void, Void, Boolean>> iterator = mAsyncTasks
                .iterator();
        while (iterator.hasNext()) {
            AsyncTask<Void, Void, Boolean> asyncTask = iterator.next();
            if (asyncTask != null && !asyncTask.isCancelled()) {
                asyncTask.cancel(true);
            }
        }
        mAsyncTasks.clear();
    }



    @Override
    public void onDestroy() {
        clearAsyncTask();
        super.onDestroy();
    }

}
