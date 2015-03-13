package com.nectar.thesun.library;

public class News implements Comparable<News>{

	public String news;
	public String title;
	public String link;
	public String imageuri;
	public int pageid;
	public String reporter;
	public String datetime;
	public String category;
	public String getNews() {
		return news;
	}



	public void setNews(String news) {
		this.news = news;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getLink() {
		return link;
	}



	public void setLink(String link) {
		this.link = link;
	}



	public String getImageuri() {
		return imageuri;
	}



	public void setImageuri(String imageuri) {
		this.imageuri = imageuri;
	}



	public int getPageid() {
		return pageid;
	}



	public void setPageid(int pageid) {
		this.pageid = pageid;
	}



	public String getReporter() {
		return reporter;
	}



	public void setReporter(String reporter) {
		this.reporter = reporter;
	}



	public String getDatetime() {
		return datetime;
	}



	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String description;
	
	
	
	public News(String news, String title, String link, String imageuri, String reporter, String datetime, String category, int pageid, String description)
	{
		super();
		this.news = news;
		this.title = title;
		this.link = link;
		this.imageuri = imageuri;
		this.reporter = reporter;
		this.datetime = datetime;
		this.category = category;
		this.pageid = pageid;
		this.description = description;
	}



	@Override
	public int compareTo(News news) {
		// TODO Auto-generated method stub
		return this.category.compareTo(news.category);
	}

	
	
}
