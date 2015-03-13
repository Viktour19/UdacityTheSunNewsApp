package com.nectar.thesun;

import com.nectar.thesun.library.JSONParser;
import com.nectar.thesun.library.MyConstants;
import com.nectar.thesun.library.Urls;



public class UserFunctions {
	private String url = MyConstants.webcrawlerapiurl;
	private JSONParser jsonParser;
	
	public UserFunctions()
	{
		super();
		jsonParser = new JSONParser();
	}
	
	public void getImageUrl(Urls urls)
	{

		for (int i = 0; i < urls.pageid.size(); i++) {
			String json  = jsonParser.getJSONFromUrl(url + urls.pageid.get(i).toString());
			if(json != null)
			{
				urls.imageurls.add(i, json);
			}
		}
		
	
	}
}
