package com.nectar.thesun;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nectar.thesun.R;
import com.nectar.thesun.library.ConnectionDetector;
import com.nectar.thesun.library.MyConstants;
import com.nectar.thesun.library.MyConstants.categories;
import com.nectar.thesun.library.News;
import com.nectar.thesun.library.NewsListAdapter;
import com.nectar.thesun.library.Urls;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mcsoxford.rss.RSSFeed;
import org.mcsoxford.rss.RSSItem;
import org.mcsoxford.rss.RSSReader;
import org.mcsoxford.rss.RSSReaderException;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class OtherPagesFragment extends Fragment {
	public static final String ARG_PLANET_NUMBER = "planet_number";
	private final int national = 1;
	private final int politics = 2;
	private final int business = 3;
	private final int sports = 4;
	private final int editorial = 5;
	private final int entertainment = 6;
	private final int features = 7;
	private final int opinion = 8;
	private final int columns = 9;
	private final int eyewitness = 10;
	private final int marketsummary = 11;
	private final int about = 12;
	private final int setting = 13;
	private ProgressBar spinner;
	private ListView ll;
	private String uri;
	private WeakReference<loadNews> loadnewsAsyncTask;
	loadNews ln = null;
	private View headerView;
	public ArrayList<News> NEWS;

	public OtherPagesFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		headerView = new View(getActivity());
		ConnectionDetector cd = new ConnectionDetector(getActivity());
		int i = getArguments().getInt(ARG_PLANET_NUMBER);
		View rootView = null;
		if (!cd.isConnectingToInternet() && i != about) {
			rootView = inflater.inflate(R.layout.nointernet, container, false);
			return rootView;
		}
		headerView = inflater.inflate(R.layout.defaultlistheader, null);
		switch (i) {
		case national:
			rootView = inflater.inflate(R.layout.newslistfragment, container,
					false);
			 spinner = (ProgressBar) rootView.findViewById(R.id.spinner);
			 ll = new ListView(getActivity());
			 ll = (ListView)rootView.findViewById(R.id.listView);
			 uri = MyConstants.nationalnewsfeedul;
			loadNews(national);

			return rootView;
		case politics:
			rootView = inflater.inflate(R.layout.newslistfragment, container,
					false);
			spinner = (ProgressBar) rootView.findViewById(R.id.spinner);
			ll = new ListView(getActivity());
			ll = (ListView) rootView.findViewById(R.id.listView);
			uri = MyConstants.poiticsnewsfeedul;
			loadNews(politics);
			return rootView;
		case business:
			rootView = inflater.inflate(R.layout.newslistfragment, container,
					false);
			spinner = (ProgressBar) rootView.findViewById(R.id.spinner);
			ll = new ListView(getActivity());
			ll = (ListView) rootView.findViewById(R.id.listView);
			uri = MyConstants.businessnewsfeedul;
			loadNews(business);
			return rootView;
		case sports:
			rootView = inflater.inflate(R.layout.newslistfragment, container,
					false);
			spinner = (ProgressBar) rootView.findViewById(R.id.spinner);
			ll = new ListView(getActivity());
			ll = (ListView) rootView.findViewById(R.id.listView);
			uri = MyConstants.sportsnewsfeedul;
			loadNews(sports);
			return rootView;
		case editorial:
			rootView = inflater.inflate(R.layout.newslistfragment, container,
					false);
			spinner = (ProgressBar) rootView.findViewById(R.id.spinner);
			ll = new ListView(getActivity());
			ll = (ListView) rootView.findViewById(R.id.listView);
			uri = MyConstants.editorialnewsfeedul;
			loadNews(editorial);
			return rootView;
		case entertainment:
			rootView = inflater.inflate(R.layout.newslistfragment, container,
					false);
			spinner = (ProgressBar) rootView.findViewById(R.id.spinner);
			ll = new ListView(getActivity());
			ll = (ListView) rootView.findViewById(R.id.listView);
			uri = MyConstants.entertainmentnewsfeedul;
			loadNews(entertainment);
			return rootView;
		case features:
			rootView = inflater.inflate(R.layout.newslistfragment, container,
					false);
			spinner = (ProgressBar) rootView.findViewById(R.id.spinner);
			ll = new ListView(getActivity());
			ll = (ListView) rootView.findViewById(R.id.listView);
			uri = MyConstants.featuresnewsfeedul;
			loadNews(features);
			return rootView;
		case opinion:
			rootView = inflater.inflate(R.layout.newslistfragment, container,
					false);
			spinner = (ProgressBar) rootView.findViewById(R.id.spinner);
			ll = new ListView(getActivity());
			ll = (ListView) rootView.findViewById(R.id.listView);
			uri = MyConstants.opinionnewsfeedul;
			loadNews(opinion);
			return rootView;
		case columns:
			rootView = inflater.inflate(R.layout.newslistfragment, container,
					false);
			spinner = (ProgressBar) rootView.findViewById(R.id.spinner);
			ll = new ListView(getActivity());
			ll = (ListView) rootView.findViewById(R.id.listView);
			uri = MyConstants.columsnewsfeedul;
			loadNews(columns);
			return rootView;
	
		case about:
			rootView = inflater.inflate(R.layout.about, container, false);
			return rootView;
		default:
			return rootView;
		}
	}

	private void loadNews(int pos) {
		if (ln == null) {
			ln = new loadNews(OtherPagesFragment.this, pos);
			this.loadnewsAsyncTask = new WeakReference<OtherPagesFragment.loadNews>(
					ln);

			ln.execute();
		}

	}

	class loadNews extends AsyncTask<Object, Object, Object> {

		private WeakReference<OtherPagesFragment> fragmentWeakRef;
		private boolean done;
		int pos;

		public loadNews(OtherPagesFragment otherpagesfragment, int position) {
			this.fragmentWeakRef = new WeakReference<OtherPagesFragment>(
					otherpagesfragment);
			pos = position;
		}

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub

			if (this.fragmentWeakRef.get() != null) {
				done = (Boolean) result;
				if (done) {
					Setup();
				}
			}

			super.onPostExecute(result);
		}

		@Override
		protected Boolean doInBackground(Object... arg0) {
			// TODO Auto-generated method stub
			ConnectionDetector cd = new ConnectionDetector(getActivity());
			UserFunctions uf = new UserFunctions();
			ArrayList<Integer> pageids = new ArrayList<Integer>();
			RSSReader reader = new RSSReader();
			RSSFeed feed;
			News news;
			try {
				if (cd.isConnectingToInternet()) {
					feed = reader.load(uri);
					int sz = feed.getItems().size();
					if (sz > 0) {
						NEWS = new ArrayList<News>(sz);
						for (int j = 0; j < sz; j++) {
							RSSItem item = feed.getItems().get(j);
							String newss = item.getContent();
							newss=  newss.replace("150", "0");
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
							Elements elem = jsoup.getElementsByTag("img");
							String imageuri="http://sunnewsonline.com/new/wp-content/uploads/2014/11/43d401609ffb6398c05e945dbaf8b8aa1-150x150.jpg";
							for (Element elm : elem) {
								 imageuri = elm.attr("src");
							}
							String ext =imageuri.substring(imageuri.length()-4, imageuri.length());
							String image = imageuri.substring(0, imageuri.length()-12);
							imageuri = image + ext;
							
							if (!category.contentEquals("Sun Girl")) {

								news = new News(newss, title, link, imageuri, "",
										datetime, category, pageid, description);

								NEWS.add(news);

							}
						}
						Urls urls = new Urls();
						urls.pageid = new ArrayList<Integer>();
						urls.pageid = pageids;
//						urls.imageurls = new ArrayList<String>();
//						uf.getImageUrl(urls);
//						for (int i = 0; i < NEWS.size(); i++) {
//							NEWS.get(i).imageuri = urls.imageurls.get(i);
//						}

						switch (pos) {
						case national:
							MyConstants.nationalnews = NEWS;
							break;
						case politics:
							MyConstants.politicsnews = NEWS;
							break;
						case business:
							MyConstants.businessnews = NEWS;
							break;
						case opinion:
							MyConstants.opinionnews = NEWS;
							break;
						case editorial:
							MyConstants.editorialnews = NEWS;
							break;
						case columns:
							MyConstants.colunmnnews = NEWS;
							break;
						case sports:
							MyConstants.sportsnews = NEWS;
							break;
						case entertainment:
							MyConstants.entertainmentnews = NEWS;
							break;
						case features:
							MyConstants.featuresnews = NEWS;
							break;
						default:
							break;
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

		public void Setup() {
			// TODO Auto-generated method stub
			spinner.setVisibility(View.INVISIBLE);
			TextView tv = (TextView) headerView
					.findViewById(R.id.textviewcategory);
			
			ImageView imgvhome = (ImageView) headerView
					.findViewById(R.id.imageViewslider);
			int no = NEWS.size();
			Random rd = new Random(Calendar.getInstance()
					.getTimeInMillis());
			int poss = rd.nextInt() % no;
			if (poss < 0)
				poss = poss * -1;
			tv.setText(NEWS.get(poss).category);
			Picasso.with(getActivity()).load(NEWS.get(pos).imageuri).placeholder(R.drawable.imgload).into(imgvhome);
			ll.addHeaderView(headerView);
			switch (pos) {
			case national:
				ll.setAdapter(new NewsListAdapter(getActivity(), NEWS,
						getResources(), categories.national));
				break;
			case politics:
				ll.setAdapter(new NewsListAdapter(getActivity(), NEWS,
						getResources(), categories.politics));
				break;
			case business:
				ll.setAdapter(new NewsListAdapter(getActivity(), NEWS,
						getResources(), categories.business));
				break;
			case opinion:
				ll.setAdapter(new NewsListAdapter(getActivity(), NEWS,
						getResources(), categories.opinion));
				break;
			case editorial:
				ll.setAdapter(new NewsListAdapter(getActivity(), NEWS,
						getResources(), categories.editorial));
				break;
			case columns:
				ll.setAdapter(new NewsListAdapter(getActivity(), NEWS,
						getResources(), categories.column));
				break;
			case sports:
				ll.setAdapter(new NewsListAdapter(getActivity(), NEWS,
						getResources(), categories.sports));
				break;
			case entertainment:
				ll.setAdapter(new NewsListAdapter(getActivity(), NEWS,
						getResources(), categories.entertainment));
				break;
			case features:
				ll.setAdapter(new NewsListAdapter(getActivity(), NEWS,
						getResources(), categories.features));
				break;

			default:

			}
		}
	}

}