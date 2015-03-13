package com.nectar.thesun;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import com.nectar.thesun.library.ConnectionDetector;
import com.nectar.thesun.library.MyConstants;
import com.nectar.thesun.library.News;
import com.nectar.thesun.library.SessionManagement;

import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSItem;
import org.mcsoxford.rss.RSSReader;
import org.mcsoxford.rss.RSSReaderException;

import java.util.ArrayList;

/**
 * Created by victor on 3/11/15.
 */
public class UpdateService extends Service {


    private Handler mHandler = new Handler();


    private static SyncAdapter sSyncAdapter = null;
    // Object to use as a thread-safe lock
    private static final Object sSyncAdapterLock = new Object();
    /*
     * Instantiate the sync adapter object.
     */
    @Override
    public void onCreate() {
        /*
         * Create the sync adapter as a singleton.
         * Set the sync adapter as syncable
         * Disallow parallel syncs
         */

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
        if(cd.isConnectingToInternet())

        {

            synchronized (sSyncAdapterLock) {
                if (sSyncAdapter == null) {
                    sSyncAdapter = new SyncAdapter(getApplicationContext(), true);
                }
            }

        }

       // SplashScreen.notifybreaking();
        return super.onStartCommand(intent, flags, startId);
    }

//    public void runchekingthread() throws Exception{
//        new Thread() {
//            @Override
//            public void run() {
//
//            }
//        }.start();
//    }


}
