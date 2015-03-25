package com.nectar.thesun;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSItem;
import org.mcsoxford.rss.RSSReader;
import org.mcsoxford.rss.RSSReaderException;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import com.nectar.thesun.library.ConnectionDetector;
import com.nectar.thesun.library.MyConstants;
import com.nectar.thesun.library.News;
import com.nectar.thesun.library.NewsListDatabase;
import com.nectar.thesun.library.SessionManagement;
import com.nectar.thesun.library.Urls;

public class PageSlidingTabStripFragment extends Fragment {

	static WeakReference<loadNews> loadnewsAsyncTask;
	private static int position;
	private static ConnectionDetector cd;
	private static Boolean done;
	public static loadNews ln = null;
    private static boolean apphasupdates;
    private boolean connecting = false;
    private static Context mcontext;
	public static final String TAG = PageSlidingTabStripFragment.class
			.getSimpleName();

	public static PageSlidingTabStripFragment newInstance() {
		return new PageSlidingTabStripFragment();
	}
    NewsListDatabase db;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);

        if(MyConstants.homenews == null) {
            MyConstants.homenews = new ArrayList<>();

        }
		if (!isAsyncTaskPendingOrRunning())
		{
            mcontext = getActivity().getApplicationContext();
            loadNewsNow(getActivity());

		}
		
	}

	public void loadNewsNow(FragmentActivity fragmentActivity) {
		if (ln == null) {
			ln = new loadNews(PageSlidingTabStripFragment.this);
			this.loadnewsAsyncTask = new WeakReference<PageSlidingTabStripFragment.loadNews>(
					ln);
			ln.execute(fragmentActivity);
		}
	}

	static boolean isAsyncTaskPendingOrRunning() {
		return PageSlidingTabStripFragment.loadnewsAsyncTask != null
				&& PageSlidingTabStripFragment.loadnewsAsyncTask.get() != null
				&& !PageSlidingTabStripFragment.loadnewsAsyncTask.get().getStatus()
						.equals(Status.FINISHED);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        connecting = false;
		cd = new ConnectionDetector(getActivity());
		if (cd.isConnectingToInternet()) {
			connecting = true;
			return inflater.inflate(R.layout.pager, container, false);
		} else {
			return inflater.inflate(R.layout.nointernet, container, false);
		}
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		if (connecting) {
			PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view
					.findViewById(R.id.tabs);
			ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
			MyPagerAdapter adapter = new MyPagerAdapter(
					getChildFragmentManager());
			pager.setAdapter(adapter);
			tabs.setViewPager(pager);
		
		}

	}

    public class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
		}

		private final String[] TITLES = { "Home", "Breaking News", "Favorites"};
		private Fragment fragment;

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			return super.instantiateItem(container, position);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
			// TODO Auto-generated method stub
			super.restoreState(state, loader);
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public Fragment getItem(int position) {

			switch (position) {
			case 0:
				fragment = (Fragment) Fragment.instantiate(getActivity(), HomeFragment.class.getName());
				break;
			case 1:
				fragment = (Fragment) Fragment.instantiate(getActivity(), BreakingFragment.class.getName());
			break;
			case 2:
				fragment = (Fragment) Fragment.instantiate(getActivity(), FavoritesFragment.class.getName());
			default:
				break;
			}
		
			return fragment;
		}

	}

 static	class  loadNews extends AsyncTask<Object, Object, Object> {

		private WeakReference<PageSlidingTabStripFragment> fragmentWeakRef;
		private int callcounter;


     public loadNews(PageSlidingTabStripFragment pageSlidingTabStripFragment) {
			this.fragmentWeakRef = new WeakReference<PageSlidingTabStripFragment>(
					pageSlidingTabStripFragment);
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			if (this.fragmentWeakRef.get() != null) {
				done = (Boolean) result;
				if (done) {
					
					HomeFragment.Setup();
					BreakingFragment.Setup();
					
				}
				
			}
			
			super.onPostExecute(result);
		}

		
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			if (!done) {
				ln.cancel(true);
				callcounter++;
				if(callcounter<=3)
					ln.execute();
			}
			
			super.onCancelled();
			
		}

     @Override
     protected void onPreExecute() {
         super.onPreExecute();
         if(MyConstants.homenews != null) {

             HomeFragment.Setup();
         }
     }

     @Override
		protected Boolean doInBackground(Object... arg0) {
			// TODO Auto-generated method stub
			cd = new ConnectionDetector((Activity)arg0[0]);
            NewsListDatabase db = new NewsListDatabase(mcontext);
			UserFunctions uf = new UserFunctions();
			ArrayList<Integer> pageids = new ArrayList<Integer>();
			RSSReader reader = new RSSReader();
			RSSFeed feed, feed2;
			String uri1 = MyConstants.breakingnewsfeedul;
			String uri2 = MyConstants.homenewsfeedul;
			News news;
			try {
				if (cd.isConnectingToInternet()) {
					
					// BREAKING NEWS

					feed = reader.load(uri1);
					feed2 = reader.load(uri2);
					int sz1 = feed.getItems().size();
					int sz2 = feed2.getItems().size();
					if (sz1 > 0) {
						for (int j = 0; j < sz1; j++) {
							RSSItem item = feed.getItems().get(j);
							String newss = item.getContent();
							String title = item.getTitle();
							String link = item.getLink().toString();
							String pageidS = link.replace(
									"http://sunnewsonline.com/new/?p=", "");
							int pageid = Integer.valueOf(pageidS);
							pageids.add(pageid);
							String datetime = item.getPubDate().toString();
							String category = item.getCategories().get(0);
							String description = item.getDescription().toString();
							Document jsoup = Jsoup.parse(description);
							Document jsoupcontent = Jsoup.parse(newss);
							Elements elem = jsoup.getElementsByTag("img");
							String imageuri="http://sunnewsonline.com/new/wp-content/uploads/2014/11/43d401609ffb6398c05e945dbaf8b8aa1-150x150.jpg";
							for (Element elm : elem) {
								
								 imageuri = elm.attr("src");
							}
							jsoupcontent.removeClass("attachment-thumbnail wp-post-image");
							newss = jsoupcontent.toString();
							
						
							String ext =imageuri.substring(imageuri.length()-4, imageuri.length());
							String image = imageuri.substring(0, imageuri.length()-12);

							if (!category.contentEquals("Sun Girl")) {
								if(category.contentEquals("Back Page / Columns"))
								{
									image = imageuri.substring(0, imageuri.length()-11);
									title = title + ": "+ item.getCategories().get(1);
								}
								imageuri = image + ext;
								news = new News(newss, title, link, imageuri, "",
										datetime, category, pageid, description);
								MyConstants.breakingnews.add(news);

							}
						}
						Urls urls = new Urls();
						urls.pageid = new ArrayList<Integer>();
						urls.pageid = pageids;
						urls.imageurls = new ArrayList<String>();
					
					}
					if (sz2 > 0) {
						for (int j = 0; j < sz2; j++) {
							RSSItem item = feed2.getItems().get(j);
							String newss = item.getContent();
							String title = item.getTitle();
							String link = item.getLink().toString();
							String pageidS = link.replace(
									"http://sunnewsonline.com/new/?p=", "");
							int pageid = Integer.valueOf(pageidS);
							pageids.add(pageid);
							String datetime = item.getPubDate().toString();
							String category = item.getCategories().get(0);
							String description = item.getDescription().toString();
							Document jsoup = Jsoup.parse(description);
							Document jsoupcontent = Jsoup.parse(newss);
							Elements elem = jsoup.getElementsByTag("img");
							
							String imageuri="http://sunnewsonline.com/new/wp-content/uploads/2014/11/43d401609ffb6398c05e945dbaf8b8aa1-150x150.jpg";
							for (Element elm : elem) {
								
								 imageuri = elm.attr("src");
							}
							jsoupcontent.removeClass("attachment-thumbnail wp-post-image");
							newss = jsoupcontent.toString();
						    String ext =imageuri.substring(imageuri.length()-4, imageuri.length());
							String image = imageuri.substring(0, imageuri.length()-12);
							
							if (!category.contentEquals("Sun Girl")) {
								if(category.contentEquals("Back Page / Columns"))
								{
									image = imageuri.substring(0, imageuri.length()-11);
									title = title + ": "+ item.getCategories().get(1);
								}
								imageuri = image + ext;
								news = new News(newss, title, link, imageuri, "",
										datetime, category, pageid, description);
								MyConstants.homenews.add(news);

							}
						}

						SessionManagement sm = new SessionManagement((Activity)arg0[0]);
						try {
							MyConstants.favoritenews = sm.getfavs();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        sm.addNews("BREAKING", MyConstants.breakingnews);
                        sm.addNews("HOME", MyConstants.homenews);
                        try {
                            db.addNews(MyConstants.homenews);
                        }
                        catch (Exception e)
                        {
                            return true;
                        }

						return true;
					}
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
}
