package com.nectar.thesun.library;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nectar.thesun.MainActivity;
import com.nectar.thesun.R;
import com.nectar.thesun.library.MyConstants.categories;
import com.squareup.picasso.Picasso;


public class NewsListAdapter extends BaseAdapter implements OnClickListener {
	
	private ArrayList<String> checker;
	private Activity activity;
	private ArrayList<News> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	News tempValues = null;
	int i = 0;
	categories ListName;

	public NewsListAdapter(Activity a, ArrayList<News> d, Resources resLocal, categories home) {
		// TODO Auto-generated constructor stub
		/********** Take passed values **********/
		activity = a;
		data = d;
		//Collections.sort(data);
		res = resLocal;
		checker = new ArrayList<String>();
		this.ListName= home;
		/*********** Layout inflator to call external xml layout () ***********/
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}


	/******** What is the size of Passed Arraylist Size ************/
	public int getCount() {

		if (data.size() <= 0)
			return 1;
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	/********* Create a holder Class to contain inflated xml file elements *********/
	private static class ViewHolder {

		public TextView newstitle;
		public TextView category;
		public TextView datetime, reporter;
		public ImageView media;
	    public TextView prenews;

	}

	/****** Depends upon data size called for each row , Create each ListView row *****/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		ViewHolder holder;
   
		if (convertView == null && data.size() > 0) {

	
			vi = inflater.inflate(R.layout.newslistitem, null);
		

			holder = new ViewHolder();
			holder.category = (TextView) vi.findViewById(R.id.newscategory);
			holder.datetime = (TextView) vi
					.findViewById(R.id.date);
			holder.newstitle = (TextView) vi
					.findViewById(R.id.newsheader);
	        holder.prenews = (TextView) vi.findViewById(R.id.prenews);
			holder.media = (ImageView) vi.findViewById(R.id.newsimage);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} 
		else
		{
			holder = (ViewHolder) vi.getTag();
		}
		int swz = data.size();
		if (swz <= 0) {

			holder.category.setText("No news");
			holder.datetime.setText("00:00 today");
			holder.newstitle.setText("");
		
		} 
		
		else {
			
			tempValues = null;
			tempValues = (News) data.get(position);
			checker.add(tempValues.category);
			for (int i = 1; i < checker.size(); i++) {
				
				if(checker.get(i).equalsIgnoreCase(checker.get(i-1)))
				{
					//holder.category.setText(tempValues.category);
				}
			}
			
			holder.newstitle.setText(tempValues.title);
			String dt = tempValues.datetime;
			dt = (dt.substring(0, 10) + ", " + dt.substring(
					dt.length() - 5, dt.length()));
			holder.datetime.setText(dt);
			String prn = tempValues.description.substring(tempValues.description.indexOf("/>")+2);
			
			holder.prenews.setText(Html.fromHtml(prn));
			
			Picasso.with(activity).load(tempValues.imageuri).placeholder(R.drawable.imgload).into(holder.media);
			vi.setOnClickListener(new OnItemClickListener(position));
		}
		return vi;
	}

	@Override
	public void onClick(View v) {
		Log.v("CustomAdapter", "=====Row button clicked=====");
	}

	/********* Called when Item click in ListView ************/
	private class OnItemClickListener implements OnClickListener {
		private int mPosition;

		OnItemClickListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View arg0) {

			MainActivity sct = (MainActivity) activity;
			sct.onItemClick(mPosition, ListName.ordinal());
		}
	}
}