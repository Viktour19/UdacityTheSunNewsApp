package com.nectar.thesun;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

import com.nectar.thesun.library.MyConstants;
import com.nectar.thesun.library.News;
import com.nectar.thesun.library.SessionManagement;

import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSItem;
import org.mcsoxford.rss.RSSReader;
import org.mcsoxford.rss.RSSReaderException;

import java.util.ArrayList;

public class SyncAdapter extends AbstractThreadedSyncAdapter {

    private Context mContext = null;
    ContentResolver mContentResolver;
    private Integer pageid;
    private boolean apphasupdates() {
        RSSFeed feed = null, feed2;
        String uri1 = MyConstants.breakingnewsfeedul;
        RSSReader reader = new RSSReader();
        try {
            feed = reader.load(uri1);
        } catch (RSSReaderException e) {
            e.printStackTrace();
            return false;
        }

        int sz1 = feed.getItems().size();

        if (sz1 > 0) {
            RSSItem item = feed.getItems().get(0);
            String link = item.getLink().toString();
            String pageidS = link.replace(
                    "http://sunnewsonline.com/new/?p=", "");
            pageid = Integer.valueOf(pageidS);
        }
        SessionManagement sm = new SessionManagement(mContext);
        ArrayList<News> oldbreaking = sm.getNews("BREAKING");
        int oldid = oldbreaking.get(0).getPageid();
        if(oldid >= pageid)
            return false;

        else
        {
            return true;
        }
    }
    /**
     * Set up the sync adapter
     */
    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContext = context;
        mContentResolver = context.getContentResolver();
    }

    /**
     * Set up the sync adapter. This form of the
     * constructor maintains compatibility with Android 3.0
     * and later platform versions
     */
    public SyncAdapter(
            Context context,
            boolean autoInitialize,
            boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        /*
         * If your app uses a content resolver, get an instance of it
         * from the incoming Context
         */
        mContentResolver = context.getContentResolver();

    }

    @Override
    public void onPerformSync(
            Account account,
            Bundle extras,
            String authority,
            ContentProviderClient provider,
            SyncResult syncResult) {
    /*
     * Put the data transfer code here.
     */
        if(apphasupdates())
            SplashScreen.notifybreaking();

    }
}