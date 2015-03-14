package com.nectar.thesun;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.nectar.thesun.library.ConnectionDetector;
import com.nectar.thesun.library.MyConstants;
import com.nectar.thesun.library.NewsListDatabase;

import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSItem;
import org.mcsoxford.rss.RSSReader;
import org.mcsoxford.rss.RSSReaderException;

import java.util.Calendar;
import java.util.Random;

public class SplashScreen extends Activity {

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 3000;
	private static NotificationManager sysservice;
	private static Context activity;
	final loadquote lq = new loadquote();

	ConnectionDetector cd;
	Boolean done = false;
	String Quote;
	private String QuoteTitle;

	class loadquote extends AsyncTask<Object, Object, Object> {

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub

			done = (Boolean) result;
			if (done) {
                NewsListDatabase db = new NewsListDatabase(activity);
                MyConstants.homenews = db.getNews();
				Intent i = new Intent(SplashScreen.this, MainActivity.class);
				i.putExtra("quote", Quote);
				i.putExtra("quotetitle", QuoteTitle);
				startActivity(i);
				finish();
			}
			super.onPostExecute(result);
		}

		@Override
		protected Boolean doInBackground(Object... params) {
			// TODO Auto-generated method stub
			RSSReader reader = new RSSReader();
			RSSFeed feed;
			String uri = MyConstants.quotesfeedurl;
			try {
				feed = reader.load(uri);
				if (feed.getItems().size() > 0) {

					int no = feed.getItems().size();
					Random rd = new Random(Calendar.getInstance()
							.getTimeInMillis());
					int pos = rd.nextInt() % no;
					if (pos < 0)
						pos = pos * -1;
					RSSItem item = feed.getItems().get(pos);
					Quote = item.getDescription();
					QuoteTitle = item.getTitle();
					return true;
				}

			} catch (RSSReaderException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
				// TODO: handle exception
			}
			return false;

		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		sysservice = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		activity = getApplicationContext();

        setalarm();

		if (lq.getStatus() != Status.RUNNING) {
			cd = new ConnectionDetector(getApplicationContext());
			if (cd.isConnectingToInternet()) {
				SPLASH_TIME_OUT = 10000;
                lq.execute();


			} else {
				if (lq != null) {
					if (lq.getStatus() != Status.RUNNING)
						lq.cancel(true);
				}
				Intent i = new Intent(SplashScreen.this, MainActivity.class);
				startActivity(i);
				finish();
				return;
			}
		}

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				lq.cancel(true);
				if (!done) {
					Intent i = new Intent(SplashScreen.this, MainActivity.class);
					startActivity(i);
					finish();
				}
			}
		}, SPLASH_TIME_OUT);
	}


    void setalarm()
    {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.MONTH, 6);
        calendar.set(Calendar.YEAR, 2013);
        calendar.set(Calendar.DAY_OF_MONTH, 13);

        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 48);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM,Calendar.PM);

        Intent myIntent = new Intent(SplashScreen.this, UpdateReceiver.class);
        PendingIntent  pendingIntent = PendingIntent.getBroadcast(SplashScreen.this, 0, myIntent,0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                AlarmManager.INTERVAL_HALF_HOUR,
                AlarmManager.INTERVAL_HALF_HOUR, pendingIntent);
    }


    public static void notifybreaking() {
		if (activity != null) {
			NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
					activity).setSmallIcon(R.drawable.ic_launcher)
					.setContentTitle("Breaking News")
					.setContentText("don't miss the latest news around you!")
            .setSound(Uri.parse("android.resource://com.nectar.thesun/raw/sound"));

			Intent resultIntent = new Intent(activity, MainActivity.class);
			TaskStackBuilder stackBuilder = TaskStackBuilder.create(activity);
			stackBuilder.addParentStack(MainActivity.class);
			stackBuilder.addNextIntent(resultIntent);
			PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
					0, PendingIntent.FLAG_UPDATE_CURRENT);
			mBuilder.setContentIntent(resultPendingIntent);
			
			NotificationManager mNotificationManager = (NotificationManager) sysservice;
			int mId = 1;
			//mBuilder.setSound(sound, streamType);
			// mId allows you to update the notification later on.
			mNotificationManager.notify(mId, mBuilder.build());
		}
	}
}