package com.nectar.thesun;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.Toast;

import com.nectar.thesun.library.SessionManagement;

public class  SettingsFragment extends Fragment {
	Bundle savedInstanceState;
	LayoutInflater inflater;
	ViewGroup container;
	private SeekBar seekbar;
	private Switch switchvibrate;
	private Switch switchupdate;
	private Switch switchsound;

	private static FragmentActivity act;

	public static View v;
	public static final String TAG = SettingsFragment.class
			.getSimpleName();

	public static SettingsFragment newInstance() {
		return new SettingsFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.savedInstanceState = savedInstanceState;
		act= getActivity();
		this.inflater = inflater;
		this.container = container;
		
		View rootview = inflater.inflate(R.layout.settings, container,
				false);
		OnClickListener settingtoggle = null;
		
		
		 settingtoggle = new View.OnClickListener() {
		
		SessionManagement sm = new SessionManagement(getActivity());
		@Override
		public void onClick(View item) {
			switch (item.getId()) {
			case R.id.switchsound:
				sm.setSoundSetting(!sm.getsoundSetting());
				break;
			case R.id.switchupdate:
				sm.setupdateSetting(!sm.getupdateSetting());
				break;
			case R.id.switchvibrate:
				sm.setvibrateSetting(!sm.getvibrateSetting());
				break;
			default:
				break;
			}
			Toast.makeText(getActivity(), "Settings Updated", Toast.LENGTH_SHORT).show();
		}
		
	};
		switchsound = (Switch)rootview.findViewById(R.id.switchsound);
		switchsound.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        // do something, the isChecked will be
		        // true if the switch is in the On position
		    }
		});
		switchupdate = (Switch)rootview.findViewById(R.id.switchupdate);
		switchupdate.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        // do something, the isChecked will be
		        // true if the switch is in the On position
		    }
		});
		
		switchvibrate =(Switch) rootview.findViewById(R.id.switchvibrate);
	    switchvibrate.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        // do something, the isChecked will be
		        // true if the switch is in the On position
		    }
		});
		seekbar = (SeekBar)rootview.findViewById(R.id.seekbarfont);
	    seekbar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub
				
			}
		});
	return rootview;
	}

	

	
}
