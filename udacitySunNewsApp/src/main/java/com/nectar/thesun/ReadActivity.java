package com.nectar.thesun;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nectar.thesun.library.MyConstants;
import com.nectar.thesun.library.MyTextView;
import com.nectar.thesun.library.News;
import com.nectar.thesun.library.NewsListDatabase;
import com.nectar.thesun.library.SessionManagement;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReadActivity extends ActionBarActivity {

	private int pos;
	private int listname;
	private News NEWS;
	private Bundle si;
	private ArrayList<News> NEWSLIST;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		si = savedInstanceState;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read);
		Intent i = getIntent();
		final MyTextView header = (MyTextView) findViewById(R.id.newsheader);
		final MyTextView news = (MyTextView) findViewById(R.id.news);
		final MyTextView nextread = (MyTextView) findViewById(R.id.myTextViewnextread);
		final MyTextView date = (MyTextView) findViewById(R.id.date);
		
	//	ImageView share = (ImageView) findViewById(R.id.ic_action_share);
		//ImageView fav = (ImageView) findViewById(R.id.ic_action_fav);
		LinearLayout bottomread = (LinearLayout) findViewById(R.id.bottomread);


//		fav.setOnClickListener(new OnClickListener() {
//
//			private boolean found = false;
//
//			@Override
//			public void onClick(View arg0) {
//				if (MyConstants.favoritenews != null) {
//					for (int j = 0; j < MyConstants.favoritenews.size(); j++) {
//						if (NEWS.pageid == MyConstants.favoritenews.get(j).pageid) {
//							found = true;
//						}
//					}
//					if (!found) {
//						MyConstants.favoritenews.add(NEWS);
//						SessionManagement sm = new SessionManagement(
//								getApplicationContext());
//						sm.addFavorite(NEWS);
//						Toast.makeText(getApplicationContext(),
//								"This News has been added to your Favorites!",
//								Toast.LENGTH_SHORT).show();
//					} else {
//						Toast.makeText(
//								getApplicationContext(),
//								"This News is already added to your Favorites!",
//								Toast.LENGTH_SHORT).show();
//					}
//				}
//			}
//		});
//
		pos = i.getIntExtra("position", -1);
		listname = i.getIntExtra("listname", -1);

		switch (listname) {
		case 0:
            NewsListDatabase nldb = new NewsListDatabase(getApplicationContext());
			NEWSLIST = MyConstants.homenews;
			NEWS = nldb.getNewsItem(pos);

			break;
		case 1:
			NEWSLIST = MyConstants.breakingnews;
			NEWS = MyConstants.breakingnews.get(pos);

			break;
		case 2:
			NEWSLIST = MyConstants.nationalnews;
			NEWS = MyConstants.nationalnews.get(pos);

			break;
		case 3:
			NEWSLIST = MyConstants.politicsnews;
			NEWS = MyConstants.politicsnews.get(pos);

			break;
		case 4:
			NEWSLIST = MyConstants.businessnews;
			NEWS = MyConstants.businessnews.get(pos);

			break;
		case 5:
			NEWSLIST = MyConstants.sportsnews;
			NEWS = MyConstants.sportsnews.get(pos);

			break;
		case 6:
			NEWSLIST = MyConstants.editorialnews;
			NEWS = MyConstants.editorialnews.get(pos);

			break;
		case 7:
			NEWSLIST = MyConstants.entertainmentnews;
			NEWS = MyConstants.entertainmentnews.get(pos);

			break;
		case 8:
			NEWSLIST = MyConstants.featuresnews;
			NEWS = MyConstants.featuresnews.get(pos);

			break;
		case 9:
			NEWSLIST = MyConstants.colunmnnews;
			NEWS = MyConstants.colunmnnews.get(pos);

			break;

		case 10:
			NEWSLIST = MyConstants.opinionnews;
			NEWS = MyConstants.opinionnews.get(pos);

			break;

		case 11:
			NEWSLIST = MyConstants.favoritenews;
			NEWS = MyConstants.favoritenews.get(pos);

			break;
		default:
			break;
		}
		ImageView img = (ImageView) findViewById(R.id.imageViewslider);
		Picasso.with(getApplicationContext()).load(NEWS.imageuri)
				.placeholder(R.drawable.imgload).into(img);
		String dt = NEWS.datetime;
		dt = (dt.substring(0, 10) + ", " + dt.substring(24, 28));
		date.setText(dt);
		String prn = NEWS.news.substring(NEWS.news.indexOf("<p>")+3);		
		news.setText(Html.fromHtml(prn));
		header.setText(NEWS.title);
		
		nextread.setText(NEWSLIST.get((pos+1)% (NEWSLIST.size())).title);
//		share.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				Intent sharingIntent = new Intent(
//						android.content.Intent.ACTION_SEND);
//				sharingIntent.setType("text/plain");
//				sharingIntent.putExtra(Intent.EXTRA_TITLE,
//						NEWSLIST.get(pos).title);
//				sharingIntent.putExtra(Intent.EXTRA_TEXT, NEWSLIST.get(pos).link);
//
//				startActivity(sharingIntent);
//
//			}
//		});
		nextread.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			//	Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
				pos = pos + 1;
				NEWS = NEWSLIST.get(pos % NEWSLIST.size());
				ImageView img = (ImageView) findViewById(R.id.imageViewslider);
				Picasso.with(getApplicationContext()).load(NEWS.imageuri)
						.placeholder(R.drawable.imgload).into(img);
				String dt = NEWS.datetime;
				dt = (dt.substring(0, 10) + ", " + dt.substring(
						dt.length() - 5, dt.length()));
				
				date.setText(dt);
				String prn = NEWS.news.substring(NEWS.news.indexOf("<p>")+3);		
				news.setText(Html.fromHtml(prn));
				header.setText(NEWS.title);
				nextread.setText(NEWSLIST.get((pos+1)% (NEWSLIST.size())).title);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater a = getMenuInflater();
		a.inflate(R.menu.read, menu);
		boolean found = false;
//		if (MyConstants.favoritenews != null) {
//			for (int j = 0; j < MyConstants.favoritenews.size(); j++) {
//				if (NEWS.pageid == MyConstants.favoritenews.get(j).pageid) {
//					found = true;
//				}
//			}
//			if (found) {
//				menu.getItem(0).setIcon(
//						getResources().getDrawable(
//								R.drawable.ic_action_toggle_star_i));
//			}
//		}
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(
			MenuItem item) {
		// TODO Auto-generated method stub

		boolean found = false;
		switch (item.getItemId()) {
		case R.id.action_share:
			Intent sharingIntent = new Intent(
					android.content.Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			sharingIntent.putExtra(Intent.EXTRA_TITLE,
					"Get The Latest news by downloading the Sun News App");
			sharingIntent.putExtra(Intent.EXTRA_TEXT, MyConstants.appurl);

			startActivity(sharingIntent);
			break;
		case R.id.action_favs:
			if(MyConstants.favoritenews == null)
				{
					MyConstants.favoritenews = new ArrayList<News>();
					MyConstants.favoritenews.add(NEWS);
					SessionManagement sm = new SessionManagement(
							getApplicationContext());
					if(sm.addFavorite(NEWS))
					{
					Toast.makeText(getApplicationContext(),
							"This News has been added to your Favorites!",
							Toast.LENGTH_SHORT).show();
					}
					item.setIcon(
						getResources().getDrawable(
							R.drawable.ic_action_toggle_star_i));
				}
			else  {
				for (int j = 0; j < MyConstants.favoritenews.size(); j++) {
					if (NEWS.pageid == MyConstants.favoritenews.get(j).pageid) {
						found = true;
					}
				}
				if (!found) {
					MyConstants.favoritenews.add(NEWS);
					SessionManagement sm = new SessionManagement(
							getApplicationContext());
					if(sm.addFavorite(NEWS))
					{
					Toast.makeText(getApplicationContext(),
							"This News has been added to your Favorites!",
							Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(getApplicationContext(),
							"This News is already added to your Favorites!",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
