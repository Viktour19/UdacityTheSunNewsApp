package com.nectar.thesun;

import java.util.Calendar;
import java.util.Random;

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

public class HomeFragment extends Fragment {

	private static final String ARG_POSITION = "position";
	private static int position;
	Bundle savedInstance = new Bundle();
	static ListView llhome;
	static ListView llbreaking;
	static ListView llfavs = null;
	static ProgressBar spinnerhome;
	ProgressBar spinnerbreaking;
	ProgressBar spinnerfavs;
	static View headerView, headerView_i, headerView_ii;
	Boolean done = false;
	private static Resources res;
	private static FragmentActivity act;
	private static NewsListAdapter homenewsadapter;
	private static NewsListAdapter breakingnewsadapter;
	private static boolean called = false;
	private static int no;
	private SessionManagement sm;
	private int counter = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		res = getResources();
		act = getActivity();

		sm = new SessionManagement(act);
	}

	public static void Setup() {

		if (llhome != null) {
			if (MyConstants.homenews != null)
				no = MyConstants.homenews.size();
			if (no != 0) {
				headerView = HomeFragment.act.getLayoutInflater().inflate(
						R.layout.homelistheader, null);

				Random rd = new Random(Calendar.getInstance().getTimeInMillis());
				int pos = rd.nextInt() % no;
				if (pos < 0)
					pos = pos * -1;
				final int posit = pos;
				((MyTextView) headerView.findViewById(R.id.newsheader))
						.setText(MyConstants.homenews.get(pos).title);
				String dt = MyConstants.homenews.get(pos).datetime;
				dt = (dt.substring(0, 10) + ", " + dt.substring(
						dt.length() - 5, dt.length()));
				((MyTextView) headerView.findViewById(R.id.date)).setText(dt);

				((LinearLayout) headerView.findViewById(R.id.homeheadertop))
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								MainActivity sct = (MainActivity) act;
								sct.onItemClick(posit, 0);
							}
						});
				ImageView imgvhome = (ImageView) headerView
						.findViewById(R.id.imageViewslider);
				Picasso.with(act)
						.load(MyConstants.homenews.get(pos).category
								.contentEquals("Back Page / Columns") ? MyConstants.homenews
								.get(pos % MyConstants.homenews.size()).imageuri
								: MyConstants.homenews.get(pos).imageuri)
						.placeholder(R.drawable.imgload).into(imgvhome);
				llhome.addHeaderView(headerView);
				homenewsadapter = new NewsListAdapter(act,
						MyConstants.homenews, res, MyConstants.categories.home);
				llhome.setAdapter(homenewsadapter);

				View v = (View) llhome.getParent();
				spinnerhome.setVisibility(View.INVISIBLE);
			}
		}

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		sm.addNews("HOME", MyConstants.homenews);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		if (MyConstants.homenews != null) {
			if (homenewsadapter != null) {
				Setup();
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = null;
		headerView = new View(getActivity());
		res = getResources();

		headerView = inflater.inflate(R.layout.homelistheader, null);
		rootView = inflater.inflate(R.layout.home_fragment, container, false);
		llhome = new ListView(getActivity());
		llhome = (ListView) rootView.findViewById(R.id.listView);
		spinnerhome = (ProgressBar) rootView.findViewById(R.id.spinner);
	
		return rootView;
	}

}