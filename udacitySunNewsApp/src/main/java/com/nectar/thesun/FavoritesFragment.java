package com.nectar.thesun;
;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.nectar.thesun.library.MyConstants;
import com.nectar.thesun.library.MyTextView;
import com.nectar.thesun.library.NewsListAdapter;
import com.nectar.thesun.library.SessionManagement;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Random;

public class FavoritesFragment extends Fragment {

	Bundle savedInstance = new Bundle();
	static ListView llhome;
	static ListView llbreaking;
	static ListView llfavs = null;
	ProgressBar spinnerhome, spinnerbreaking, spinnerfavs;
	static View headerView, headerView_i, headerView_ii;
	Boolean done = false;
	private static Resources res;
	private static Activity act;
	private static NewsListAdapter homenewsadapter;
	private static NewsListAdapter breakingnewsadapter;
	private static boolean called = false;
	private static int no = 0;
	private static NewsListAdapter favnewsadapter;
	private SessionManagement sm;



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		res = getResources();
		act = getActivity();
		sm = new SessionManagement(act);
	}

	public static void Setup() {

	
			if (llfavs != null) {
				if(MyConstants.favoritenews != null)
					no  = MyConstants.favoritenews.size();
				if (no != 0) {
					Random rd = new Random(Calendar.getInstance()
							.getTimeInMillis());
					int pos = rd.nextInt() % no;
					if (pos < 0)
						pos = pos * -1;
					final int posit2 = pos;
					headerView_ii = FavoritesFragment.act.getLayoutInflater()
							.inflate(R.layout.homelistheader_ii, null);
					((MyTextView) headerView_ii.findViewById(R.id.newsheader))
							.setText(MyConstants.favoritenews.get(pos).title);
					((LinearLayout) headerView_ii
							.findViewById(R.id.homeheadertop))
							.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									MainActivity sct = (MainActivity) act;
									sct.onItemClick(posit2, 11);
								}
							});
					String dt = MyConstants.favoritenews.get(pos).datetime;
					dt = (dt.substring(0, 10) + ", " + dt.substring(dt.length()-5, dt.length()));
					((MyTextView) headerView_ii.findViewById(R.id.date))
							.setText(dt);
					ImageView imgbreaking = (ImageView) headerView_ii
							.findViewById(R.id.imageViewslider);
					Picasso.with(act)
							.load(MyConstants.favoritenews.get(pos).imageuri)
							.placeholder(R.drawable.imgload).into(imgbreaking);

					llfavs.addHeaderView(headerView_ii);
					favnewsadapter = new NewsListAdapter(act,
							MyConstants.favoritenews, res,
							MyConstants.categories.favorite);
					llfavs.setAdapter(favnewsadapter);
					View v = (View) llfavs.getParent();
					v.findViewById(R.id.spinner).setVisibility(View.INVISIBLE);
				}
			}
	

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		MyConstants.favoritenews = sm.getfavs();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = null;
		 if(sm.getfavs() != null)
			 MyConstants.favoritenews = sm.getfavs();	
		if(MyConstants.favoritenews == null)
		 {
				rootView = inflater.inflate(R.layout.nofavs, container,
						false);
				llfavs = (ListView) rootView.findViewById(R.id.listView);
				return rootView;
			
		}
		if(MyConstants.favoritenews.size() == 0)
		 {
			rootView = inflater.inflate(R.layout.nofavs, container,
					false);
			llfavs = (ListView) rootView.findViewById(R.id.listView);
			return rootView;
		 }
				headerView_ii = new View(getActivity());
		        res = getResources();
		
			headerView_ii = inflater.inflate(R.layout.homelistheader_ii, null);
			rootView = inflater.inflate(R.layout.favs_fragment, container,
					false);
			llfavs = new ListView(getActivity());
			llfavs = (ListView) rootView.findViewById(R.id.listView);
			spinnerfavs = (ProgressBar) rootView.findViewById(R.id.spinner);
			FavoritesFragment.Setup();
		    return rootView;
	}

}