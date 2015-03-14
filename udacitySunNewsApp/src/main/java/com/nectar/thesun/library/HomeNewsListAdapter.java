package com.nectar.thesun.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nectar.thesun.MainActivity;
import com.nectar.thesun.R;
import com.nectar.thesun.ReadActivity;
import com.squareup.picasso.Picasso;


public class HomeNewsListAdapter extends SimpleCursorAdapter implements OnClickListener {
	
    private Context mContext;
    private Context appContext;
    private int layout;
    Activity activity;
    private Cursor cr;
    private final LayoutInflater inflater;

	
	News tempValues = null;

	
    public HomeNewsListAdapter(Context context,int layout, Cursor c,String[] from,int[] to, int flag, Activity act) {
        super(context, layout, c, from, to, flag);
        this.layout=layout;
        this.mContext = context;
        activity = act;
        this.inflater=LayoutInflater.from(context);
        this.cr=c;
    }

    @Override
    public View newView (Context context, Cursor cursor, ViewGroup parent) {
            return inflater.inflate(layout, null);
    }

    @Override
    public void bindView(View vi, Context context, Cursor cursor) {
        super.bindView(vi, context, cursor);

        ViewHolder holder;
        holder = new ViewHolder();
        if (cursor.getCount() > 0) {


            holder.category = (TextView) vi.findViewById(R.id.newscategory);
            holder.datetime = (TextView) vi
                    .findViewById(R.id.date);
            holder.newstitle = (TextView) vi
                    .findViewById(R.id.newsheader);
            holder.prenews = (TextView) vi.findViewById(R.id.prenews);
            holder.media = (ImageView) vi.findViewById(R.id.newsimage);

            vi.setTag(holder);
        }
        else
        {
            holder.category.setText("No news");
            holder.datetime.setText("00:00 today");
            holder.newstitle.setText("");

            holder = (ViewHolder) vi.getTag();
        }
            cursor.getColumnIndex(NewsListDatabase.COL_TITLE);
            holder.newstitle.setText(tempValues.title);
            String dt = tempValues.datetime;
            dt = (dt.substring(0, 10) + ", " + dt.substring(
                    dt.length() - 5, dt.length()));
            holder.datetime.setText(dt);
            String prn = tempValues.description.substring(tempValues.description.indexOf("/>")+2);

            holder.prenews.setText(Html.fromHtml(prn));

            Picasso.with(activity).load(tempValues.imageuri).placeholder(R.drawable.imgload).into(holder.media);

        }

    @Override
    public void onClick(View v) {
      ViewHolder holder = (ViewHolder) v.getTag();

        MainActivity sct = (MainActivity) activity;
        sct.onItemClick( holder.pageid, 0);
    }

    private static class ViewHolder {

        public TextView newstitle;
        public TextView category;
        public TextView datetime, reporter;
        public ImageView media;
        public TextView prenews;
        public int pageid;

    }
 }
