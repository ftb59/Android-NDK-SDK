package cn.bjtu.power.powerservice;

/**
 * Created by ftb on 14-12-23.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


public class PowerService extends Service {
    private IPowerServiceImpl service;

    @Override
    public void onCreate() {
        super.onCreate();
        this.service = new IPowerServiceImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return this.service;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        this.service = null;
        super.onDestroy();
    }
}