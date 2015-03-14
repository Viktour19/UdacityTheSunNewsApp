package com.nectar.thesun;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nectar.thesun.library.MyButton;
import com.nectar.thesun.library.MyConstants;
import com.nectar.thesun.library.MyTextView;


public class MainActivity extends ActionBarActivity {
	private static final int QUOTE_DIALOG = 0;
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] DrawlerTitles;
	public String quote;

	@SuppressWarnings("deprecation")
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case QUOTE_DIALOG:

			LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View layout = inflater.inflate(R.layout.quote,
					(ViewGroup) findViewById(R.id.root));
			MyTextView mtq = (MyTextView) layout
					.findViewById(R.id.myTextViewQuote);
			mtq.setText(quote);
			MyTextView mtqt = (MyTextView) layout
					.findViewById(R.id.myTextViewQuoteTitle);
			mtqt.setText("~" + title);

			final MyButton dismiss = (MyButton) layout
					.findViewById(R.id.ButtonEnterApp);
			dismiss.setOnClickListener(dismissListener);

			AlertDialog.Builder builder = new AlertDialog.Builder(
					new ContextThemeWrapper(MainActivity.this,
							android.R.style.Theme_DeviceDefault_Dialog));
			builder.setView(layout);
			builder.setCancelable(false);

			AlertDialog mydialog = builder.create();

			mydialog.getWindow().setLayout(
					android.view.ViewGroup.LayoutParams.MATCH_PARENT,
					android.view.ViewGroup.LayoutParams.MATCH_PARENT);
			return mydialog;

		default:
			break;
		}

		return super.onCreateDialog(id);
	}

	OnClickListener dismissListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			dismissDialog(QUOTE_DIALOG);
		}
	};
	private String title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mTitle = mDrawerTitle = getTitle();
		//requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		Intent i = getIntent();
		if (i.getStringExtra("quote") != null) {
			quote = i.getStringExtra("quote");
			title = i.getStringExtra("quotetitle");
		showDialog(QUOTE_DIALOG);
		}
		DrawlerTitles = MyConstants.drawertitles;
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, DrawlerTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setTitle("");

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.navigationdrawericon, /*
										 * nav drawer image to replace 'Up'
										 * caret
										 */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				// getSupportActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
			//	getSupportActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater a = getMenuInflater();
		a.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(
			MenuItem item) {

		switch (item.getItemId()) {
		case  R.id.action_refresh:
			
			//PageSlidingTabStripFragment.ln.execute();
			break;
		case R.id.action_share:
			Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			sharingIntent.putExtra(Intent.EXTRA_TITLE, "Get The Latest news by downloading the Sun News App");
			sharingIntent.putExtra( Intent.EXTRA_TEXT, MyConstants.appurl);
			
			startActivity(sharingIntent);
			break;
		case android.R.id.home: {
			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
			break;
		}

		}

		return super.onOptionsItemSelected(item);
	}

	// The click listener for ListView in the navigation drawer
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private void selectItem(int position) {

		switch (position) {
		case 0:
			getSupportFragmentManager()
					.beginTransaction()
					.add(R.id.content,
							PageSlidingTabStripFragment.newInstance(),
							PageSlidingTabStripFragment.TAG).commit();
			break;
		case 10:
			mDrawerLayout.closeDrawer(mDrawerList);
			getSupportFragmentManager()
			.beginTransaction()
			.add(R.id.content,
					EyewitnessActivity.newInstance(),
					EyewitnessActivity.TAG).commit();
		
			break;
		case 11:
			mDrawerLayout.closeDrawer(mDrawerList);
			getSupportFragmentManager()
			.beginTransaction()
			.add(R.id.content,	MarketSummaryFragment.newInstance(),
					MarketSummaryFragment.TAG).commit();
			
					
		
			break;
		case 12:
			mDrawerLayout.closeDrawer(mDrawerList);
			getSupportFragmentManager()
			.beginTransaction()
		.add(R.id.content,
				SettingsFragment.newInstance(),
				SettingsFragment.TAG).commit();
				
			break;
		default:

			Fragment fragment = new OtherPagesFragment();
			Bundle args = new Bundle();
			args.putInt(OtherPagesFragment.ARG_PLANET_NUMBER, position);
			fragment.setArguments(args);

			getSupportFragmentManager().beginTransaction()
					.add(R.id.content, fragment).commit();
			break;
		}

		mDrawerLayout.closeDrawer(mDrawerList);
	}

	public void onItemClick(int mPosition, int j) {
		Intent i = new Intent(getApplicationContext(), ReadActivity.class);
		i.putExtra("listname", j);
		if(j == 0)
        {
           mPosition= MyConstants.homenews.get(mPosition).getPageid();
        }
        i.putExtra("position", mPosition);

		startActivity(i);
        finish();

	}

}