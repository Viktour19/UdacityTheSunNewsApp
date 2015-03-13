package com.nectar.thesun;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nectar.thesun.R;

public class EyewitnessActivity extends Fragment {
	Bundle savedInstanceState;
	LayoutInflater inflater;
	ViewGroup container;

	private static FragmentActivity act;

	public static View v;
	public static final String TAG = EyewitnessActivity.class
			.getSimpleName();

	public static EyewitnessActivity newInstance() {
		return new EyewitnessActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.savedInstanceState = savedInstanceState;
		act= getActivity();
		this.inflater = inflater;
		this.container = container;
		
		View rootview = inflater.inflate(R.layout.eyewitness, container,
				false);
		
		return rootview;
	}

	


	


}
