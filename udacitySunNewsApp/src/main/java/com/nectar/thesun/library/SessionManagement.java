package com.nectar.thesun.library;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManagement {

	SharedPreferences pref;

	Editor editor;
	Context _context;

	int PRIVATE_MODE = 0;

	private static final String PREF_NAME = "TheSunPref";

	// Constructor
	public SessionManagement(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();

	}

	public boolean addFavorite(News news) {
		ArrayList<News> curr = getfavs();
		if (curr != null) {
			curr.add(news);
			Gson gson = new Gson();
			String json = gson.toJson(curr);
			editor.putString("FAVS", json);
			return editor.commit();			
		}
		else
		{
			curr = new ArrayList<News>();
			curr.add(news);
			Gson gson = new Gson();
			String json = gson.toJson(curr);
			editor.putString("FAVS", json);
			return editor.commit();		
		}
	}

	public ArrayList<News> getfavs() {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		String json = pref.getString("FAVS", "");
		Type type;
		type = new TypeToken<ArrayList<News>>() {
		}.getType();
		ArrayList<News> news;
		news = gson.fromJson(json, type);
		
		
		return news;
	}

	public void addNews(String name, ArrayList<News> news) {
		Gson gson = new Gson();
		String json = gson.toJson(news);
		editor.putString(name, json);
		editor.commit();
	}

	public ArrayList<News> getNews(String name) {
		Gson gson = new Gson();
		String json = pref.getString(name, "");
		Type type;
		type = new TypeToken<ArrayList<News>>() {
		}.getType();
		ArrayList<News> news = new ArrayList<News>();
		news = gson.fromJson(json, type);
		
		return news;
	}

	public void addSettingPref(boolean vibrate, boolean sound, boolean update) {

		editor.putBoolean("vibrate", vibrate);
		editor.putBoolean("sound", sound);
		editor.putBoolean("update", update);

		editor.commit();

	}

	public ArrayList<Boolean> getSettings() {
		ArrayList<Boolean> setting = new ArrayList<Boolean>(3);
		setting.add(pref.getBoolean("vibrate", false));
		setting.add(pref.getBoolean("sound", false));
		setting.add(pref.getBoolean("update", false));

		return setting;
	}

	public ArrayList<Symbols> getSymbols() {
		Gson gson = new Gson();
		String json = pref.getString("SYMBOLS", "");
		Type type;
		type = new TypeToken<ArrayList<Symbols>>() {
		}.getType();
		ArrayList<Symbols> symb = gson.fromJson(json, type);

		return symb;
	}

	public String getsummarylastchecked() {
		return pref.getString("summarylastchecked", "");

	}

	public void setSymbols(ArrayList<Symbols> symbols) {
		Gson gson = new Gson();
		String json = gson.toJson(symbols);
		editor.putString("SYMBOLS", json);
		editor.putString("summarylastchecked",
				String.valueOf(Calendar.getInstance().getTimeInMillis()));
		editor.commit();

	}

	public boolean getsoundSetting() {
		// TODO Auto-generated method stub
		return pref.getBoolean("SOUNDSETTING", false);
	}

	public boolean getupdateSetting() {
		// TODO Auto-generated method stub
		return pref.getBoolean("UPDATESETTING", false);
	}

	public boolean getvibrateSetting() {
		// TODO Auto-generated method stub
		return pref.getBoolean("VIBRATESETTING", false);
	}

	public void setSoundSetting(boolean b) {
		// TODO Auto-generated method stub
		editor.putBoolean("SOUNDSETTING", b);
		editor.commit();
	}

	public void setupdateSetting(boolean b) {
		// TODO Auto-generated method stub
		editor.putBoolean("UPDATESETTING", b);
		editor.commit();
	}

	public void setvibrateSetting(boolean b) {
		// TODO Auto-generated method stub
		editor.putBoolean("VIBRATESETTING", b);
		editor.commit();
	}

}