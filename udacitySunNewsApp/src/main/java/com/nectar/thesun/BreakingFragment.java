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

public class BreakingFragment extends Fragment {

	Bundle savedInstance = new Bundle();
	static ListView llhome;
	static ListView llbreaking;
	static ListView llfavs = null;
	ProgressBar spinnerhome, spinnerbreaking, spinnerfavs;
	static View headerView, headerView_i, headerView_ii;
	Boolean done = false;
	private static Resources res;
	private static FragmentActivity act;
	private static NewsListAdapter homenewsadapter;
	private static NewsListAdapter breakingnewsadapter;
	private static boolean called = false;
	private static int no = 0;
	private static SessionManagement sm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		res = getResources();
		act = getActivity();

		sm = new SessionManagement(act);
	}

	public static void Setup() {

		if (llbreaking != null) {
			if (MyConstants.breakingnews != null)
				no = MyConstants.breakingnews.size();
            else
            {
                if(!sm.getNews("BREAKING").isEmpty())
                {
                    no = sm.getNews("BREAKING").size();
                    MyConstants.breakingnews = sm.getNews("BREAKING");
                }
            }
			if (no != 0) {
				Random rd = new Random(Calendar.getInstance().getTimeInMillis());
				int pos = rd.nextInt() % no;
				if (pos < 0)
					pos = pos * -1;
				final int posit2 = pos;
				headerView_i = BreakingFragment.act.getLayoutInflater()
						.inflate(R.layout.homelistheader_i, null);
				((MyTextView) headerView_i.findViewById(R.id.newsheader))
						.setText(MyConstants.breakingnews.get(pos).title);
				((LinearLayout) headerView_i.findViewById(R.id.homeheadertop))
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								MainActivity sct = (MainActivity) act;
								sct.onItemClick(posit2, 1);
							}
						});
				String dt = MyConstants.breakingnews.get(pos).datetime;
				dt = (dt.substring(0, 10) + ", " + dt.substring(
						dt.length() - 5, dt.length()));
				((MyTextView) headerView_i.findViewById(R.id.date)).setText(dt);
				ImageView imgbreaking = (ImageView) headerView_i
						.findViewById(R.id.imageViewslider);
				Picasso.with(act)
						.load(MyConstants.breakingnews.get(pos).imageuri)
						.placeholder(R.drawable.imgload).into(imgbreaking);

				llbreaking.addHeaderView(headerView_i);
				breakingnewsadapter = new NewsListAdapter(act,
						MyConstants.breakingnews, res,
						MyConstants.categories.breaking);
				llbreaking.setAdapter(breakingnewsadapter);
				View v = (View) llbreaking.getParent();
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
        if (MyConstants.breakingnews != null) {
            if (breakingnewsadapter != null) {
                Setup();
            }
        }

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = null;

		headerView_i = new View(getActivity());

		res = getResources();

		headerView_i = inflater.inflate(R.layout.homelistheader_i, null);
		rootView = inflater.inflate(R.layout.breaking_fragment, container,
				false);
		llbreaking = new ListView(getActivity());
		llbreaking = (ListView) rootView.findViewById(R.id.listView);
		spinnerbreaking = (ProgressBar) rootView.findViewById(R.id.spinner);
		return rootView;
	}

}