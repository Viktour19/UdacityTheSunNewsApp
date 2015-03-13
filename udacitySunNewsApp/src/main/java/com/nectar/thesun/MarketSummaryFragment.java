package com.nectar.thesun;

import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.nectar.thesun.library.JSONParser;
import com.nectar.thesun.library.MyConstants;
import com.nectar.thesun.library.MyTextView;
import com.nectar.thesun.library.SessionManagement;
import com.nectar.thesun.R;

public class MarketSummaryFragment extends Fragment {
	Bundle savedInstanceState;
	LayoutInflater inflater;
	ViewGroup container;
	private static double hours =25;
	private static FragmentActivity act;
	static ProgressBar pb;
	static Boolean done = false;
	public static View v;
	public static final String TAG = MarketSummaryFragment.class
			.getSimpleName();

	public static MarketSummaryFragment newInstance() {
		return new MarketSummaryFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.savedInstanceState = savedInstanceState;
		act= getActivity();
		this.inflater = inflater;
		this.container = container;
		
		View rootview = inflater.inflate(R.layout.market_sumary, container,
				false);
		pb = (ProgressBar) rootview.findViewById(R.id.progressBar);
		
		v = rootview;
		return rootview;
	}

	


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		SessionManagement sm = new SessionManagement(act);
		String ss = sm.getsummarylastchecked();
		if(ss != null && !ss.isEmpty())
		{
			long hourstored = 24*((Long.valueOf(ss))/86400000);
			long hournow = 24*(Calendar.getInstance().getTimeInMillis()/86400000);
			hours = hournow - hourstored;
		}
		
		if(sm.getSymbols() != null && hours <= 24)
		{
			MyConstants.initSymbols();
			MyConstants.symbols = sm.getSymbols();
			setup();
			pb.setVisibility(View.GONE);
			((LinearLayout)v.findViewById(R.id.layoutsummarycontainer)).setVisibility(View.VISIBLE);
			((LinearLayout)v.findViewById(R.id.summaryheader)).setVisibility(View.VISIBLE);
			return;
		}
		else if(!MarketSummaryFragment.done) {

			
			loadsummary ls = new loadsummary();
			ls.execute();

		}
	}


	public static void setup()
	{
		((MyTextView)v.findViewById(R.id.summarydate)).setText(MyConstants.symbols.get(0).date);
		((MyTextView)v.findViewById(R.id.openinglivestock)).setText(MyConstants.symbols.get(0).Opening);
		((MyTextView)v.findViewById(R.id.closinglivestock)).setText(MyConstants.symbols.get(0).Closing);
		((MyTextView)v.findViewById(R.id.openingokomuoil)).setText(MyConstants.symbols.get(1).Opening);
		((MyTextView)v.findViewById(R.id.closingokomuoil)).setText(MyConstants.symbols.get(1).Closing);
		((MyTextView)v.findViewById(R.id.openingpresco)).setText(MyConstants.symbols.get(2).Opening);
		((MyTextView)v.findViewById(R.id.closingpresco)).setText(MyConstants.symbols.get(2).Closing);
		((MyTextView)v.findViewById(R.id.openingAirService)).setText(MyConstants.symbols.get(3).Opening);
		((MyTextView)v.findViewById(R.id.closingAirService)).setText(MyConstants.symbols.get(3).Closing);
		((MyTextView)v.findViewById(R.id.openingnacho)).setText(MyConstants.symbols.get(4).Opening);
		((MyTextView)v.findViewById(R.id.closingnacho)).setText(MyConstants.symbols.get(4).Closing);
		((MyTextView)v.findViewById(R.id.openingdunlop)).setText(MyConstants.symbols.get(5).Opening);
		((MyTextView)v.findViewById(R.id.closingdunlop)).setText(MyConstants.symbols.get(5).Closing);
		((MyTextView)v.findViewById(R.id.openingbriscoe)).setText(MyConstants.symbols.get(6).Opening);
		((MyTextView)v.findViewById(R.id.closingbriscoe)).setText(MyConstants.symbols.get(6).Closing);
		((MyTextView)v.findViewById(R.id.openingabbeybds)).setText(MyConstants.symbols.get(7).Opening);
		((MyTextView)v.findViewById(R.id.closingabbeybds)).setText(MyConstants.symbols.get(7).Closing);
		((MyTextView)v.findViewById(R.id.openingaccess)).setText(MyConstants.symbols.get(8).Opening);
		((MyTextView)v.findViewById(R.id.closingaccess)).setText(MyConstants.symbols.get(8).Closing);
		((MyTextView)v.findViewById(R.id.openingasosavings)).setText(MyConstants.symbols.get(9).Opening);
		((MyTextView)v.findViewById(R.id.closingasosavings)).setText(MyConstants.symbols.get(9).Closing);
		((MyTextView)v.findViewById(R.id.openingdiamond)).setText(MyConstants.symbols.get(10).Opening);
		((MyTextView)v.findViewById(R.id.closingdiamond)).setText(MyConstants.symbols.get(10).Closing);
		((MyTextView)v.findViewById(R.id.openingfcmb)).setText(MyConstants.symbols.get(11).Opening);
		((MyTextView)v.findViewById(R.id.closingfcmb)).setText(MyConstants.symbols.get(11).Closing);
		((MyTextView)v.findViewById(R.id.openingfidelity)).setText(MyConstants.symbols.get(12).Opening);
		((MyTextView)v.findViewById(R.id.closingfidelity)).setText(MyConstants.symbols.get(12).Closing);
		((MyTextView)v.findViewById(R.id.openingguaranty)).setText(MyConstants.symbols.get(13).Opening);
		((MyTextView)v.findViewById(R.id.closingguaranty)).setText(MyConstants.symbols.get(13).Closing);
		((MyTextView)v.findViewById(R.id.openingskye)).setText(MyConstants.symbols.get(14).Opening);
		((MyTextView)v.findViewById(R.id.closingskye)).setText(MyConstants.symbols.get(14).Closing);
		((MyTextView)v.findViewById(R.id.openingsterling)).setText(MyConstants.symbols.get(15).Opening);
		((MyTextView)v.findViewById(R.id.closingsterlin)).setText(MyConstants.symbols.get(15).Closing);
		((MyTextView)v.findViewById(R.id.openingtranscorp)).setText(MyConstants.symbols.get(16).Opening);
		((MyTextView)v.findViewById(R.id.closingtranscorp)).setText(MyConstants.symbols.get(16).Closing);
		((MyTextView)v.findViewById(R.id.openinguba)).setText(MyConstants.symbols.get(17).Opening);
		((MyTextView)v.findViewById(R.id.closinguba)).setText(MyConstants.symbols.get(17).Closing);
		((MyTextView)v.findViewById(R.id.openingubn)).setText(MyConstants.symbols.get(18).Opening);
		((MyTextView)v.findViewById(R.id.closingubn)).setText(MyConstants.symbols.get(18).Closing);
		((MyTextView)v.findViewById(R.id.openingunitybk)).setText(MyConstants.symbols.get(19).Opening);
		((MyTextView)v.findViewById(R.id.closingunitybk)).setText(MyConstants.symbols.get(19).Closing);
		((MyTextView)v.findViewById(R.id.openingwemabk)).setText(MyConstants.symbols.get(20).Opening);
		((MyTextView)v.findViewById(R.id.closingwema)).setText(MyConstants.symbols.get(20).Closing);
		((MyTextView)v.findViewById(R.id.openingzenith)).setText(MyConstants.symbols.get(21).Opening);
		((MyTextView)v.findViewById(R.id.closingzenith)).setText(MyConstants.symbols.get(21).Closing);
		((MyTextView)v.findViewById(R.id.openingchampion)).setText(MyConstants.symbols.get(22).Opening);
		((MyTextView)v.findViewById(R.id.closingchampion)).setText(MyConstants.symbols.get(22).Closing);
		((MyTextView)v.findViewById(R.id.openingguinness)).setText(MyConstants.symbols.get(23).Opening);
		((MyTextView)v.findViewById(R.id.closingguinness)).setText(MyConstants.symbols.get(23).Closing);
		((MyTextView)v.findViewById(R.id.openingintbrew)).setText(MyConstants.symbols.get(24).Opening);
		((MyTextView)v.findViewById(R.id.closingintbrew)).setText(MyConstants.symbols.get(24).Closing);
		((MyTextView)v.findViewById(R.id.openingnb)).setText(MyConstants.symbols.get(25).Opening);
		((MyTextView)v.findViewById(R.id.closingnb)).setText(MyConstants.symbols.get(25).Closing);
		((MyTextView)v.findViewById(R.id.openingprembrew)).setText(MyConstants.symbols.get(26).Opening);
		((MyTextView)v.findViewById(R.id.closingprembrew)).setText(MyConstants.symbols.get(26).Closing);
		((MyTextView)v.findViewById(R.id.openingashakacem)).setText(MyConstants.symbols.get(27).Opening);
		((MyTextView)v.findViewById(R.id.closingashakacem)).setText(MyConstants.symbols.get(27).Closing);
		((MyTextView)v.findViewById(R.id.openingccnn)).setText(MyConstants.symbols.get(28).Opening);
		((MyTextView)v.findViewById(R.id.closingccnn)).setText(MyConstants.symbols.get(28).Closing);
		((MyTextView)v.findViewById(R.id.openingdangcem)).setText(MyConstants.symbols.get(29).Opening);
		((MyTextView)v.findViewById(R.id.closingdangcem)).setText(MyConstants.symbols.get(29).Closing);
		((MyTextView)v.findViewById(R.id.openingmultiverse)).setText(MyConstants.symbols.get(30).Opening);
		((MyTextView)v.findViewById(R.id.closingmultiverse)).setText(MyConstants.symbols.get(30).Closing);
		((MyTextView)v.findViewById(R.id.openingwapco)).setText(MyConstants.symbols.get(31).Opening);
		((MyTextView)v.findViewById(R.id.closingwapco)).setText(MyConstants.symbols.get(31).Closing);
		((MyTextView)v.findViewById(R.id.openingberger)).setText(MyConstants.symbols.get(32).Opening);
		((MyTextView)v.findViewById(R.id.closingberger)).setText(MyConstants.symbols.get(32).Closing);
		((MyTextView)v.findViewById(R.id.openingcap)).setText(MyConstants.symbols.get(33).Opening);
		((MyTextView)v.findViewById(R.id.closingcap)).setText(MyConstants.symbols.get(33).Closing);
		((MyTextView)v.findViewById(R.id.openingdnmeyer)).setText(MyConstants.symbols.get(34).Opening);
		((MyTextView)v.findViewById(R.id.closingdnmeyer)).setText(MyConstants.symbols.get(34).Closing);
		((MyTextView)v.findViewById(R.id.openingdnmeyer)).setText(MyConstants.symbols.get(35).Opening);
		((MyTextView)v.findViewById(R.id.closingportpaint)).setText(MyConstants.symbols.get(35).Closing);
		((MyTextView)v.findViewById(R.id.openingabctrans)).setText(MyConstants.symbols.get(36).Opening);
		((MyTextView)v.findViewById(R.id.closingabctrans)).setText(MyConstants.symbols.get(36).Closing);
		((MyTextView)v.findViewById(R.id.openingcourtville)).setText(MyConstants.symbols.get(37).Opening);
		((MyTextView)v.findViewById(R.id.closingcourtville)).setText(MyConstants.symbols.get(37).Closing);
		((MyTextView)v.findViewById(R.id.openingikejahotel)).setText(MyConstants.symbols.get(38).Opening);
		((MyTextView)v.findViewById(R.id.closingikejahotel)).setText(MyConstants.symbols.get(38).Closing);
		((MyTextView)v.findViewById(R.id.openingredstarex)).setText(MyConstants.symbols.get(39).Opening);
		((MyTextView)v.findViewById(R.id.closingredstarex)).setText(MyConstants.symbols.get(39).Closing);
		((MyTextView)v.findViewById(R.id.openingtransexpr)).setText(MyConstants.symbols.get(40).Opening);
		((MyTextView)v.findViewById(R.id.closingtransexpr)).setText(MyConstants.symbols.get(40).Closing);
		((MyTextView)v.findViewById(R.id.openingchams)).setText(MyConstants.symbols.get(41).Opening);
		((MyTextView)v.findViewById(R.id.closingchams)).setText(MyConstants.symbols.get(41).Closing);
		((MyTextView)v.findViewById(R.id.openingaglevent)).setText(MyConstants.symbols.get(42).Opening);
		((MyTextView)v.findViewById(R.id.closingaglevent)).setText(MyConstants.symbols.get(42).Closing);
		((MyTextView)v.findViewById(R.id.openingpz)).setText(MyConstants.symbols.get(43).Opening);
		((MyTextView)v.findViewById(R.id.closingpz)).setText(MyConstants.symbols.get(43).Closing);
		((MyTextView)v.findViewById(R.id.openinguacprop)).setText(MyConstants.symbols.get(44).Opening);
		((MyTextView)v.findViewById(R.id.closinguacprop)).setText(MyConstants.symbols.get(44).Closing);
		((MyTextView)v.findViewById(R.id.openinguacn)).setText(MyConstants.symbols.get(45).Opening);
		((MyTextView)v.findViewById(R.id.closinguacn)).setText(MyConstants.symbols.get(45).Closing);
		((MyTextView)v.findViewById(R.id.openingunilever)).setText(MyConstants.symbols.get(46).Opening);
		((MyTextView)v.findViewById(R.id.closingunilever)).setText(MyConstants.symbols.get(46).Closing);
		((MyTextView)v.findViewById(R.id.openingcostain)).setText(MyConstants.symbols.get(47).Opening);
		((MyTextView)v.findViewById(R.id.closingcostain)).setText(MyConstants.symbols.get(47).Closing);
		((MyTextView)v.findViewById(R.id.openingjberger)).setText(MyConstants.symbols.get(48).Opening);
		((MyTextView)v.findViewById(R.id.closingjberger)).setText(MyConstants.symbols.get(48).Closing);
		((MyTextView)v.findViewById(R.id.opening7up)).setText(MyConstants.symbols.get(49).Opening);
		((MyTextView)v.findViewById(R.id.closing7up)).setText(MyConstants.symbols.get(49).Closing);
		((MyTextView)v.findViewById(R.id.openingcadbury)).setText(MyConstants.symbols.get(50).Opening);
		((MyTextView)v.findViewById(R.id.closingcadbury)).setText(MyConstants.symbols.get(50).Closing);
		((MyTextView)v.findViewById(R.id.openingdangflour)).setText(MyConstants.symbols.get(51).Opening);
		((MyTextView)v.findViewById(R.id.closingdangflour)).setText(MyConstants.symbols.get(51).Closing);
		((MyTextView)v.findViewById(R.id.openingdangsugar)).setText(MyConstants.symbols.get(52).Opening);
		((MyTextView)v.findViewById(R.id.closingdangsugar)).setText(MyConstants.symbols.get(52).Closing);
		((MyTextView)v.findViewById(R.id.openingflourmill)).setText(MyConstants.symbols.get(53).Opening);
		((MyTextView)v.findViewById(R.id.closingflourmill)).setText(MyConstants.symbols.get(53).Closing);
		((MyTextView)v.findViewById(R.id.openingftncocoa)).setText(MyConstants.symbols.get(54).Opening);
		((MyTextView)v.findViewById(R.id.closingftncocoa)).setText(MyConstants.symbols.get(54).Closing);
		((MyTextView)v.findViewById(R.id.openinghonyflour)).setText(MyConstants.symbols.get(55).Opening);
		((MyTextView)v.findViewById(R.id.closinghonyflour)).setText(MyConstants.symbols.get(55).Closing);
		((MyTextView)v.findViewById(R.id.openingnascon)).setText(MyConstants.symbols.get(56).Opening);
		((MyTextView)v.findViewById(R.id.closingnascon)).setText(MyConstants.symbols.get(56).Closing);
		((MyTextView)v.findViewById(R.id.openingnestle)).setText(MyConstants.symbols.get(57).Opening);
		((MyTextView)v.findViewById(R.id.closingnestle)).setText(MyConstants.symbols.get(57).Closing);
		((MyTextView)v.findViewById(R.id.openingekocorp)).setText(MyConstants.symbols.get(58).Opening);
		((MyTextView)v.findViewById(R.id.closingekocorp)).setText(MyConstants.symbols.get(58).Closing);
		((MyTextView)v.findViewById(R.id.openingevensmed)).setText(MyConstants.symbols.get(59).Opening);
		((MyTextView)v.findViewById(R.id.closingevensmed)).setText(MyConstants.symbols.get(59).Closing);
		((MyTextView)v.findViewById(R.id.openingfidson)).setText(MyConstants.symbols.get(60).Opening);
		((MyTextView)v.findViewById(R.id.closingfidson)).setText(MyConstants.symbols.get(60).Closing);
		((MyTextView)v.findViewById(R.id.openingglaxosmith)).setText(MyConstants.symbols.get(61).Opening);
		((MyTextView)v.findViewById(R.id.closingglaxosmith)).setText(MyConstants.symbols.get(61).Closing);
		((MyTextView)v.findViewById(R.id.openingmaybaker)).setText(MyConstants.symbols.get(62).Opening);
		((MyTextView)v.findViewById(R.id.closingmaybaker)).setText(MyConstants.symbols.get(62).Closing);
		((MyTextView)v.findViewById(R.id.openingneimeth)).setText(MyConstants.symbols.get(63).Opening);
		((MyTextView)v.findViewById(R.id.closingneimeth)).setText(MyConstants.symbols.get(63).Closing);
		((MyTextView)v.findViewById(R.id.openingpharmdeko)).setText(MyConstants.symbols.get(64).Opening);
		((MyTextView)v.findViewById(R.id.closingpharmdeko)).setText(MyConstants.symbols.get(64).Closing);
		((MyTextView)v.findViewById(R.id.openingvitafoam)).setText(MyConstants.symbols.get(65).Opening);
		((MyTextView)v.findViewById(R.id.closingvitafoam)).setText(MyConstants.symbols.get(65).Closing);
		((MyTextView)v.findViewById(R.id.openingvono)).setText(MyConstants.symbols.get(66).Opening);
		((MyTextView)v.findViewById(R.id.closingvono)).setText(MyConstants.symbols.get(66).Closing);
		((MyTextView)v.findViewById(R.id.openingaiico)).setText(MyConstants.symbols.get(67).Opening);
		((MyTextView)v.findViewById(R.id.closingaiico)).setText(MyConstants.symbols.get(67).Closing);
		((MyTextView)v.findViewById(R.id.openingcileasing)).setText(MyConstants.symbols.get(68).Opening);
		((MyTextView)v.findViewById(R.id.closingcileasing)).setText(MyConstants.symbols.get(68).Closing);
		((MyTextView)v.findViewById(R.id.openingcontinsure)).setText(MyConstants.symbols.get(69).Opening);
		((MyTextView)v.findViewById(R.id.closingcontinsure)).setText(MyConstants.symbols.get(69).Closing);
		((MyTextView)v.findViewById(R.id.openingcornerst)).setText(MyConstants.symbols.get(70).Opening);
		((MyTextView)v.findViewById(R.id.closingcornerst)).setText(MyConstants.symbols.get(70).Closing);
		((MyTextView)v.findViewById(R.id.openingcustodyins)).setText(MyConstants.symbols.get(71).Opening);
		((MyTextView)v.findViewById(R.id.closingcustodyins)).setText(MyConstants.symbols.get(71).Closing);
		((MyTextView)v.findViewById(R.id.openingequityasur)).setText(MyConstants.symbols.get(72).Opening);
		((MyTextView)v.findViewById(R.id.closingequityasur)).setText(MyConstants.symbols.get(72).Closing);
		((MyTextView)v.findViewById(R.id.openinghmarkins)).setText(MyConstants.symbols.get(77).Opening);
		((MyTextView)v.findViewById(R.id.closinghmarkins)).setText(MyConstants.symbols.get(77).Closing);
		((MyTextView)v.findViewById(R.id.openingintenegins)).setText(MyConstants.symbols.get(79).Opening);
		((MyTextView)v.findViewById(R.id.closingintenegins)).setText(MyConstants.symbols.get(79).Closing);
		((MyTextView)v.findViewById(R.id.openinglasaco)).setText(MyConstants.symbols.get(75).Opening);
		((MyTextView)v.findViewById(R.id.closinglasaco)).setText(MyConstants.symbols.get(75).Closing);
		((MyTextView)v.findViewById(R.id.openinglawunion)).setText(MyConstants.symbols.get(76).Opening);
		((MyTextView)v.findViewById(R.id.closinglawunion)).setText(MyConstants.symbols.get(76).Closing);
		((MyTextView)v.findViewById(R.id.openinglinkassure)).setText(MyConstants.symbols.get(77).Opening);
		((MyTextView)v.findViewById(R.id.closinglinkassure)).setText(MyConstants.symbols.get(77).Closing);
		((MyTextView)v.findViewById(R.id.openingnem)).setText(MyConstants.symbols.get(77).Opening);
		((MyTextView)v.findViewById(R.id.closingnem)).setText(MyConstants.symbols.get(77).Closing);
		((MyTextView)v.findViewById(R.id.openingnigerins)).setText(MyConstants.symbols.get(79).Opening);
		((MyTextView)v.findViewById(R.id.closingnigerins)).setText(MyConstants.symbols.get(79).Closing);
		((MyTextView)v.findViewById(R.id.openingprestige)).setText(MyConstants.symbols.get(80).Opening);
		((MyTextView)v.findViewById(R.id.closingprestige)).setText(MyConstants.symbols.get(80).Closing);
		((MyTextView)v.findViewById(R.id.openingregalins)).setText(MyConstants.symbols.get(81).Opening);
		((MyTextView)v.findViewById(R.id.closingregalins)).setText(MyConstants.symbols.get(81).Closing);
		((MyTextView)v.findViewById(R.id.openingroyalex)).setText(MyConstants.symbols.get(82).Opening);
		((MyTextView)v.findViewById(R.id.closingroyalex)).setText(MyConstants.symbols.get(82).Closing);
		((MyTextView)v.findViewById(R.id.openingsouvrenins)).setText(MyConstants.symbols.get(83).Opening);
		((MyTextView)v.findViewById(R.id.closingsouvrenins)).setText(MyConstants.symbols.get(83).Closing);
		((MyTextView)v.findViewById(R.id.openingstaco)).setText(MyConstants.symbols.get(84).Opening);
		((MyTextView)v.findViewById(R.id.closingstaco)).setText(MyConstants.symbols.get(84).Closing);
		((MyTextView)v.findViewById(R.id.openingstdinsure)).setText(MyConstants.symbols.get(85).Opening);
		((MyTextView)v.findViewById(R.id.closingstdinsure)).setText(MyConstants.symbols.get(85).Closing);
		((MyTextView)v.findViewById(R.id.openingunic)).setText(MyConstants.symbols.get(86).Opening);
		((MyTextView)v.findViewById(R.id.closingunic)).setText(MyConstants.symbols.get(86).Closing);
		((MyTextView)v.findViewById(R.id.openingunivinsure)).setText(MyConstants.symbols.get(87).Opening);
		((MyTextView)v.findViewById(R.id.closingunivinsure)).setText(MyConstants.symbols.get(87).Closing);
		((MyTextView)v.findViewById(R.id.openingwapic)).setText(MyConstants.symbols.get(88).Opening);
		((MyTextView)v.findViewById(R.id.closingwapic)).setText(MyConstants.symbols.get(88).Closing);
		((MyTextView)v.findViewById(R.id.openingnesf)).setText(MyConstants.symbols.get(89).Opening);
		((MyTextView)v.findViewById(R.id.closingnesf)).setText(MyConstants.symbols.get(89).Closing);
		((MyTextView)v.findViewById(R.id.openingbetaglass)).setText(MyConstants.symbols.get(90).Opening);
		((MyTextView)v.findViewById(R.id.closingbetaglas)).setText(MyConstants.symbols.get(90).Closing);
		((MyTextView)v.findViewById(R.id.openingconoil)).setText(MyConstants.symbols.get(91).Opening);
		((MyTextView)v.findViewById(R.id.closingconoil)).setText(MyConstants.symbols.get(91).Closing);
		((MyTextView)v.findViewById(R.id.openingjapauloil)).setText(MyConstants.symbols.get(92).Opening);
		((MyTextView)v.findViewById(R.id.closingjapauloil)).setText(MyConstants.symbols.get(92).Closing);
		((MyTextView)v.findViewById(R.id.openingmobil)).setText(MyConstants.symbols.get(93).Opening);
		((MyTextView)v.findViewById(R.id.closingmobil)).setText(MyConstants.symbols.get(93).Closing);
		((MyTextView)v.findViewById(R.id.openingoando)).setText(MyConstants.symbols.get(94).Opening);
		((MyTextView)v.findViewById(R.id.closingoando)).setText(MyConstants.symbols.get(94).Closing);
		((MyTextView)v.findViewById(R.id.openingtotal)).setText(MyConstants.symbols.get(95).Opening);
		((MyTextView)v.findViewById(R.id.closingtotal)).setText(MyConstants.symbols.get(95).Closing);
		((MyTextView)v.findViewById(R.id.openingcutix)).setText(MyConstants.symbols.get(96).Opening);
		((MyTextView)v.findViewById(R.id.closingcutix)).setText(MyConstants.symbols.get(96).Closing);
		((MyTextView)v.findViewById(R.id.openingafriprud)).setText(MyConstants.symbols.get(97).Opening);
		((MyTextView)v.findViewById(R.id.closingafriprud)).setText(MyConstants.symbols.get(97).Closing);
		((MyTextView)v.findViewById(R.id.openingeterna)).setText(MyConstants.symbols.get(98).Opening);
		((MyTextView)v.findViewById(R.id.closingeterna)).setText(MyConstants.symbols.get(98).Closing);
		((MyTextView)v.findViewById(R.id.openingdaarcomm)).setText(MyConstants.symbols.get(99).Opening);
		((MyTextView)v.findViewById(R.id.closingdaarcomm)).setText(MyConstants.symbols.get(99).Closing);
		((MyTextView)v.findViewById(R.id.openingfo)).setText(MyConstants.symbols.get(100).Opening);
		((MyTextView)v.findViewById(R.id.closingfo)).setText(MyConstants.symbols.get(10).Closing);
		((MyTextView)v.findViewById(R.id.openinglearnafrica)).setText(MyConstants.symbols.get(101).Opening);
		((MyTextView)v.findViewById(R.id.closinglearnafrica)).setText(MyConstants.symbols.get(101).Closing);
		((MyTextView)v.findViewById(R.id.openingseplat)).setText(MyConstants.symbols.get(102).Opening);
		((MyTextView)v.findViewById(R.id.closingseplat)).setText(MyConstants.symbols.get(102).Closing);
		((MyTextView)v.findViewById(R.id.openingcaverton)).setText(MyConstants.symbols.get(103).Opening);
		((MyTextView)v.findViewById(R.id.closingcaverton)).setText(MyConstants.symbols.get(103).Closing);
	}

	static class loadsummary extends AsyncTask<Object, Object, Object> {

		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			
			done = (Boolean)result;
			super.onPostExecute(result);
			pb.setVisibility(View.GONE);
			if(done)
			{
				setup();
				pb.setVisibility(View.GONE);
				((LinearLayout)v.findViewById(R.id.layoutsummarycontainer)).setVisibility(View.VISIBLE);
				((LinearLayout)v.findViewById(R.id.summaryheader)).setVisibility(View.VISIBLE);
			}
			else
			{
				((MyTextView)v.findViewById(R.id.summarydate)).setText("Poor Internet Connection, Unable to retrieve Summary");
			}
		
			
		}

		@Override
		protected Boolean doInBackground(Object... arg0) {
			// TODO Auto-generated method stub
			SessionManagement sm = new SessionManagement(act);
			if(sm.getSymbols() != null && hours <= 24)
			{
				MyConstants.initSymbols();
				MyConstants.symbols = sm.getSymbols();
				return true;
			}
			else
			{
				MyConstants.initSymbols();
			}

			String symbol;
			JSONParser jp = new JSONParser();
			JSONArray jary = jp.getJSONFromUrl2(MyConstants.nsestockurl);

			if (jary != null) {
				for (int i = 0; i < jary.length(); i++) {
					try {
						JSONObject jsonoj = (JSONObject) jary.get(i);
						symbol = jsonoj.getString("symbol");
						for (int j = 0; j < MyConstants.symbols.size(); j++) {
							if (MyConstants.symbols.get(j).symbol
									.equalsIgnoreCase(symbol)) {
								MyConstants.symbols.get(j).Opening = jsonoj
										.getString("open");
								MyConstants.symbols.get(j).Closing = jsonoj
										.getString("close");
								MyConstants.symbols.get(j).Change = jsonoj
										.getString("change");
								MyConstants.symbols.get(j).date = jsonoj
										.getString("date");
							}
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block

						e.printStackTrace();
						return false;
					}
				}
				sm.setSymbols(MyConstants.symbols);
				return true;
			}
			return false;
		}

	}

}
