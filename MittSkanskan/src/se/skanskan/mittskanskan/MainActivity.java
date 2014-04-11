package se.skanskan.mittskanskan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.text.format.DateFormat;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	/**
	 * 
	 * Variablerna som h�mtas fr�n RSSens XML-fil.
	 */
	
	//Klass med listans attribut->ArrayList med objectet "klass"->sortera ArrayListen.
	
	
//	String key_items = "item";
//	String key_title = "title";
//	String key_description = "description";
//	String key_link = "link";
//	String key_date = "pubDate";
//	String linken; // URL from the selected item.
//	String theDate;
//	String adUrl = "Visit my <a href='http://www.newblog.com'>blog</a>";
	
	
//	ListView lstPost = null;
//    List<HashMap<String, Object>> post_lists = new ArrayList<HashMap<String, Object>>();
//	List<String> lists = new ArrayList<String>();
//	ArrayAdapter<String> adapter = null;
//	RSSReader rssfeed = new RSSReader();
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//		lstPost = (ListView) findViewById(R.id.lstPosts);
//		
//		TextView mTextSample = new TextView(this);
//		mTextSample.setMovementMethod(LinkMovementMethod.getInstance());
//
//		mTextSample.setText(Html.fromHtml(adUrl));
//		
//		adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_2, android.R.id.text1, lists) {

			/**
			 * Datan som visas p� listan.
			 */

//			@Override
//			public View getView(int position, View convertView, ViewGroup parent) {
//
//				View view = super.getView(position, convertView, parent);
//				TextView txt1 = (TextView) view
//						.findViewById(android.R.id.text1);
//				TextView txt2 = (TextView) view
//						.findViewById(android.R.id.text2);
//			
//
//				HashMap<String, Object> data = post_lists.get(position);
//				theDate = data.get(key_date).toString();
//				txt1.setText(data.get(key_title).toString());
//				txt2.setText(data.get(key_description).toString() + "\n"
//						+ theDate.substring(0, theDate.length() - 5).toString());
//				
//
//				return view;
//			}
//
//		};
//		
//		Document xmlFeed = rssfeed
//				.getRSSFromServer("http://www.skanskan.se/section/sport&template=rss&mime=xml");
//		NodeList nodes = xmlFeed.getElementsByTagName("item");
//		for (int i = 0; i < nodes.getLength(); i++) {
//			Element item = (Element) nodes.item(i);
//			HashMap<String, Object> feed = new HashMap<String, Object>();
//			feed.put(key_title, RSSReader.getValue(item, key_title));
//			feed.put(key_description, RSSReader.getValue(item, key_description));
//			feed.put(key_link, RSSReader.getValue(item, key_link));
//			feed.put(key_date, RSSReader.getValue(item, key_date));
//			feed.put(adUrl, mTextSample.getText());
//			post_lists.add(feed);
//			lists.add(feed.get(key_title).toString());
//			
//		}
		
		
//		Document xmlFeed1 = rssfeed
//				.getRSSFromServer("http://www.skanskan.se/section/malmo&template=rss&mime=xml");
//		NodeList nodes1 = xmlFeed1.getElementsByTagName("item");
//		for (int i = 0; i < nodes1.getLength(); i++) {
//			Element item = (Element) nodes1.item(i);
//			HashMap<String, Object> feed = new HashMap<String, Object>();
//			feed.put(key_title, RSSReader.getValue(item, key_title));
//			feed.put(key_description, RSSReader.getValue(item, key_description));
//			feed.put(key_link, RSSReader.getValue(item, key_link));
//			feed.put(key_date, RSSReader.getValue(item, key_date));
//			post_lists.add(feed);
//		//	lists.add(feed.get(key_title).toString());
//		}  
//		
//		lstPost.setAdapter(adapter);
//		lstPost.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//
//				Intent i = new Intent(MainActivity.this, WebViewSkanskan.class);
//				HashMap<String, Object> data = post_lists.get(position);
//				linken = data.get(key_link).toString();
//
//				i.putExtra("url", linken);
//				startActivity(i);
	
	RSSReader rssfeed = new RSSReader();
	
	String key_items = "item";
	String key_title = "title";
	String key_description = "description";
	String key_link = "link";
	String key_date = "pubDate";
	String feedTitle;
	String feedDesc;
	String feedLink;
	String feedDate;
	String linken; // URL from the selected item.
	String theDate;
	SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.getDefault());
	


	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final List<Rssitem> rsslist = new ArrayList<Rssitem>();
        
        
        
        for (int i = 0; i < nodesSport.getLength(); i++) 
    	{
    		Element item = (Element) nodesSport.item(i);
    	//	HashMap<String, Object> feed = new HashMap<String, Object>();
    		this.feedTitle = RSSReader.getValue(item, key_title).toString();
    		this.feedDesc = RSSReader.getValue(item, key_description).toString();
    		this.feedLink = RSSReader.getValue(item, key_link).toString();
    		this.feedDate = RSSReader.getValue(item, key_date).toString();
    		rsslist.add(new Rssitem(feedTitle, feedDesc, feedLink, feedDate));
    		
    	//	feed.put(key_description, RSSReader.getValue(item, key_description));
    	//	feed.put(key_link, RSSReader.getValue(item, key_link));
    	//	feed.put(key_date, RSSReader.getValue(item, key_date));
    		
    	}
        
        for (int i = 0; i < nodesOpinion.getLength(); i++) 
    	{
    		Element item = (Element) nodesOpinion.item(i);
    	//	HashMap<String, Object> feed = new HashMap<String, Object>();
    		this.feedTitle = RSSReader.getValue(item, key_title).toString();
    		this.feedDesc = RSSReader.getValue(item, key_description).toString();
    		this.feedLink = RSSReader.getValue(item, key_link).toString();
    		this.feedDate = RSSReader.getValue(item, key_date).toString();
    	 	rsslist.add(new Rssitem(feedTitle, feedDesc, feedLink, feedDate));
    		
    	//	feed.put(key_description, RSSReader.getValue(item, key_description));
    	//	feed.put(key_link, RSSReader.getValue(item, key_link));
    	//	feed.put(key_date, RSSReader.getValue(item, key_date));
    		
    	}
        
      
        	for (int i = 0; i < rsslist.size() -1; i++)
        	{
        		Rssitem item1 = rsslist.get(i);
        		Rssitem item2 = rsslist.get(i+1);
        		Date itemDate1 = null;
				try
				{
					itemDate1 = df.parse(item1.getDate());
				} 
				
				catch (ParseException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		Date itemDate2 = null;
				try
				{
					itemDate2 = df.parse(item2.getDate());
				} 
				
				catch (ParseException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        		
        		if (itemDate2.after(itemDate1))
        		{
        			Rssitem temp = item1;
        			rsslist.set(i, null);
        			rsslist.set(i, item2);
        			rsslist.set(i+1, null);
        			rsslist.set(i+1, temp);
        			temp = null;
        		}
        		
        		
        	}
        
        
        
        

        ListView list = (ListView) findViewById(R.id.lstPosts);
        list.setClickable(true);

        
      
        RSSAdapter adapter = new RSSAdapter(this, rsslist);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) 
            {
            	
            	Intent i = new Intent(MainActivity.this, WebViewSkanskan.class);
//				HashMap<String, Object> data = post_lists.get(position);
//				linken = data.get(key_link).toString();
            	i.putExtra("url", rsslist.get(position).getLink().toString());
//
//				i.putExtra("url", linken);
				startActivity(i);
            	
            	
                System.out.println("sadsfsf");
                showToast(rsslist.get(position).getLink());
            }
        });

        list.setAdapter(adapter);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    
    Document xmlFeedSport = rssfeed
			.getRSSFromServer("http://www.skanskan.se/section/sport&template=rss&mime=xml");
    
    Document xmlFeedOpinion = rssfeed.getRSSFromServer("http://www.skanskan.se/section/opinion&template=rss&mime=xml");
    
	NodeList nodesSport = xmlFeedSport.getElementsByTagName("item");
	NodeList nodesOpinion = xmlFeedOpinion.getElementsByTagName("item");
}

	
	







