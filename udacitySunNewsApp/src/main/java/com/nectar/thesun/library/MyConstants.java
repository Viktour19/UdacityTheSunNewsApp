package com.nectar.thesun.library;

import java.util.ArrayList;
import java.util.HashMap;

public class MyConstants {
	
	public static enum categories
	{
		home, breaking, national,politics, business, sports, editorial, entertainment, features,column, opinion, favorite
		
	}
	
	public static ArrayList<News> homenews = new ArrayList<News>();
	public static ArrayList<News> breakingnews = new ArrayList<News>();
	public static ArrayList<News> favoritenews = new ArrayList<News>();
	public static ArrayList<News> opinionnews = new ArrayList<News>();
	public static ArrayList<News> editorialnews = new ArrayList<News>();
	public static ArrayList<News> nationalnews = new ArrayList<News>();
	public static ArrayList<News> politicsnews = new ArrayList<News>();
	public static ArrayList<News> businessnews = new ArrayList<News>();
	public static ArrayList<News> sportsnews = new ArrayList<News>();
	public static ArrayList<News> entertainmentnews = new ArrayList<News>();
	public static ArrayList<News> featuresnews = new ArrayList<News>();
	public static ArrayList<News> eyewitnessnews = new ArrayList<News>();
	public static ArrayList<News> colunmnnews = new ArrayList<News>();
	
	public final static String homenewsfeedul = "http://sunnewsonline.com/new/?feed=rss2";
	public final static String breakingnewsfeedul = "http://sunnewsonline.com/new/?cat=626&feed=rss2";
	public final static String nationalnewsfeedul = "http://sunnewsonline.com/new/?cat=3&feed=rss2";
	public final static String poiticsnewsfeedul = "http://sunnewsonline.com/new/?cat=4&feed=rss2";
	public final static String businessnewsfeedul = "http://sunnewsonline.com/new/?cat=5&feed=rss2";
	public final static String sportsnewsfeedul = "http://sunnewsonline.com/new/?cat=6&feed=rss2";
	public final static String editorialnewsfeedul = "http://sunnewsonline.com/new/?cat=24&feed=rss2";
	public final static String entertainmentnewsfeedul = "http://sunnewsonline.com/new/?cat=7&feed=rss2";
	public final static String featuresnewsfeedul = "http://sunnewsonline.com/new/?cat=8&feed=rss2";
	public final static String columsnewsfeedul = "http://sunnewsonline.com/new/?cat=16&feed=rss2";
	public final static String opinionnewsfeedul = "http://sunnewsonline.com/new/?cat=10&feed=rss2";
	
	public final static String quotesfeedurl = "http://feeds.feedburner.com/brainyquote/QUOTEBR";
	public final static String webcrawlerapiurl = "http://thesunnewswebapp.azurewebsites.net/api/WebCrawler/";
	public static final String[] drawertitles = {" Home", " National", " Politics", " Business",  " Sports", " Editorial", " Entertainment", " Features" ,
		" Opinion", " Columns", " EyeWitness", " Market Summary", " Settings"};
	public static final String nsestockurl = "http://nsefinance.com/api/stocks/";
	public static ArrayList<Symbols> symbols = null;
	public static String appurl = "https://play.google.com/store/apps/details?id=com.nectar.thesun";
//	public static String appurl = "http://appworld.blackberry.com/webstore/clientlaunch/59948662";

	void Constants()
	{
		
	}
	public static void initSymbols()
	{
		symbols = new ArrayList<Symbols>();
		symbols.add(new Symbols("LIVESTOCK", "AGRICULTURE"));
		symbols.add(new Symbols("OKOMUOIL", "AGRICULTURE"));
		symbols.add(new Symbols("PRESCO", "AGRICULTURE"));
		symbols.add(new Symbols("AIRSERVICE", "AIRLINES"));
		symbols.add(new Symbols("NACHO", "AIRLINES"));
		symbols.add(new Symbols("DUNLOP", "AUTOMOBILE & TYRE"));
		symbols.add(new Symbols("RTBRISCOE", "AUTOMOBILE & TYRE"));
		symbols.add(new Symbols("ABBEYBDS", "BANKING"));
		symbols.add(new Symbols("ACCESS", "BANKING"));
		symbols.add(new Symbols("ASOSAVINGS", "BANKING"));
		symbols.add(new Symbols("DIAMONDBANK", "BANKING"));
		symbols.add(new Symbols("FCMB", "BANKING"));
		symbols.add(new Symbols("FIDELITYBK", "BANKING"));
		symbols.add(new Symbols("GUARANTY", "BANKING"));
		symbols.add(new Symbols("SKYEBANK", "BANKING"));
		symbols.add(new Symbols("STERLNBANK", "BANKING"));
		symbols.add(new Symbols("TRANSCORP", "BANKING"));
		symbols.add(new Symbols("UBA", "BANKING"));
		symbols.add(new Symbols("UBN", "BANKING"));
		symbols.add(new Symbols("UNITYBNK", "BANKING"));
		symbols.add(new Symbols("WEMABANK", "BANKING"));
		symbols.add(new Symbols("ZENITHBANK", "BANKING"));
		symbols.add(new Symbols("CHAMPION", "BREWERIES"));
		symbols.add(new Symbols("GUINNESS", "BREWERIES"));
		symbols.add(new Symbols("INTBREW", "BREWERIES"));
		symbols.add(new Symbols("NB", "BREWERIES"));
		symbols.add(new Symbols("PREMBREW", "BREWERIES"));
		symbols.add(new Symbols("ASHAKACEM", "BUILDING MATERIALS"));
		symbols.add(new Symbols("CCNN", "BUILDING MATERIALS"));
		symbols.add(new Symbols("DANGCEM", "BUILDING MATERIALS"));
		symbols.add(new Symbols("MULTIVERSE", "BUILDING MATERIALS"));
		symbols.add(new Symbols("WAPCO", "BUILDING MATERIALS"));
		symbols.add(new Symbols("BERGER", "CHEMICALS AND PAINTS"));
		symbols.add(new Symbols("CAP", "CHEMICALS AND PAINTS"));
		symbols.add(new Symbols("DNMEYER", "CHEMICALS AND PAINTS"));
		symbols.add(new Symbols("PORTPAINT", "CHEMICALS AND PAINTS"));
		symbols.add(new Symbols("ABCTRANS", "COMMERCIAL SERVICES"));
		symbols.add(new Symbols("COURTVILLE", "COMMERCIAL SERVICES"));
		symbols.add(new Symbols("IKEJAHOTEL", "COMMERCIAL SERVICES"));
		symbols.add(new Symbols("REDSTAREX", "COMMERCIAL SERVICES"));
		symbols.add(new Symbols("TRANSEXPR", "COMMERCIAL SERVICES"));
		symbols.add(new Symbols("CHAMS", "COMPUTER OFFICE EQUIPMENT"));
		symbols.add(new Symbols("AGLEVENT", "CONGLOMORATES"));
		symbols.add(new Symbols("PZ", "CONGLOMORATES"));
		symbols.add(new Symbols("UAC-PROP", "CONGLOMORATES"));
		symbols.add(new Symbols("UACN", "CONGLOMORATES"));
		symbols.add(new Symbols("UNILEVER", "CONGLOMORATES"));
		symbols.add(new Symbols("COSTAIN", "CONSTRUCTION"));
		symbols.add(new Symbols("JBERGER", "CONSTRUCTION"));
		symbols.add(new Symbols("7UP", "FOOD BEVERAGES AND TOBACCO"));
		symbols.add(new Symbols("CADBURY", "FOOD BEVERAGES AND TOBACCO"));
		symbols.add(new Symbols("DANGFLOUR", "FOOD BEVERAGES AND TOBACCO"));
		symbols.add(new Symbols("DANGSUGAR", "FOOD BEVERAGES AND TOBACCO"));
		symbols.add(new Symbols("FLOURMILL", "FOOD BEVERAGES AND TOBACCO"));
		symbols.add(new Symbols("FTNCOCOA", "FOOD BEVERAGES AND TOBACCO"));
		symbols.add(new Symbols("HONYFLOUR", "FOOD BEVERAGES AND TOBACCO"));
		symbols.add(new Symbols("NASCON", "FOOD BEVERAGES AND TOBACCO"));
		symbols.add(new Symbols("NESTLE", "FOOD BEVERAGES AND TOBACCO"));
		symbols.add(new Symbols("EKOCORP", "HEALTHCARE"));
		symbols.add(new Symbols("EVANSMED", "HEALTHCARE"));
		symbols.add(new Symbols("FIDSON", "HEALTHCARE"));
		symbols.add(new Symbols("GLAXOSMITH", "HEALTHCARE"));
		symbols.add(new Symbols("MAYBAKER", "HEALTHCARE"));
		symbols.add(new Symbols("NEIMETH", "HEALTHCARE"));
		symbols.add(new Symbols("PHARMDEKO", "HEALTHCARE"));
		symbols.add(new Symbols("VITAFOAM", "INDUSTRIAL DOMESTIC PRODUCTS"));
		symbols.add(new Symbols("VONO", "INDUSTRIAL DOMESTIC PRODUCTS"));
		symbols.add(new Symbols("AIICO", "INSURANCE"));
		symbols.add(new Symbols("CILEASING", "INSURANCE"));
		symbols.add(new Symbols("CONTINSURE", "INSURANCE"));
		symbols.add(new Symbols("CORNERST", "INSURANCE"));
		symbols.add(new Symbols("CUSTODYINS", "INSURANCE"));
		symbols.add(new Symbols("EQUITYASUR", "INSURANCE"));
		symbols.add(new Symbols("HMARKINS", "INSURANCE"));
		symbols.add(new Symbols("INTENEGINS", "INSURANCE"));
		symbols.add(new Symbols("LASACO", "INSURANCE"));
		symbols.add(new Symbols("LAWUNION", "INSURANCE"));
		symbols.add(new Symbols("LINKASSURE", "INSURANCE"));
		symbols.add(new Symbols("NEM", "INSURANCE"));
		symbols.add(new Symbols("NIGERINS", "INSURANCE"));
		symbols.add(new Symbols("PRESTIGE", "INSURANCE"));
		symbols.add(new Symbols("REGALINS", "INSURANCE"));
		symbols.add(new Symbols("ROYALEX", "INSURANCE"));
		symbols.add(new Symbols("SOUVRENINS", "INSURANCE"));
		symbols.add(new Symbols("STACO", "INSURANCE"));
		symbols.add(new Symbols("STDINSURE", "INSURANCE"));
		symbols.add(new Symbols("UNIC", "INSURANCE"));
		symbols.add(new Symbols("UNIVINSURE", "INSURANCE"));
		symbols.add(new Symbols("WAPIC", "INSURANCE"));
		symbols.add(new Symbols("NESF", "INVESTMENT COMPANIES"));
		symbols.add(new Symbols("BETAGLAS", "PACKAGING"));
		symbols.add(new Symbols("CONOIL", "PETROLEUM MARKETING"));
		symbols.add(new Symbols("JAPAULOIL", "PETROLEUM MARKETING"));
		symbols.add(new Symbols("MOBIL", "PETROLEUM MARKETING"));
		symbols.add(new Symbols("OANDO", "PETROLEUM MARKETING"));
		symbols.add(new Symbols("TOTAL", "PETROLEUM MARKETING"));
		symbols.add(new Symbols("CUTIX", "SECOND TIER SECURITIES"));
		symbols.add(new Symbols("AFRIPRUD", "OTHERS"));
		symbols.add(new Symbols("ETERNA", "OTHERS"));
		symbols.add(new Symbols("DAARCOMM", "OTHERS"));
		symbols.add(new Symbols("FO", "OTHERS"));
		symbols.add(new Symbols("LEARNAFRICA", "OTHERS"));
		symbols.add(new Symbols("SEPLAT", "OTHERS"));
		symbols.add(new Symbols("CAVERTON", "OTHERS"));
		
	}
}
