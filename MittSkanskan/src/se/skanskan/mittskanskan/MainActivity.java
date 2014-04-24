package se.skanskan.mittskanskan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	RSSReader rssfeed = new RSSReader();

	String key_items = "item";
	String key_title = "title";
	String key_description = "description";
	String key_link = "link";
	String key_date = "pubDate";
	String feedTitle;
	String feedDesc;
	String descNoHTML;
	String feedLink;
	String feedDate;
	SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss",
			Locale.getDefault());

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final List<Rssitem> rsslist = new ArrayList<Rssitem>();

		for (int i = 0; i < nodesSport.getLength(); i++) {
			Element item = (Element) nodesSport.item(i);

			this.feedTitle = RSSReader.getValue(item, key_title).toString();
			this.feedDesc = RSSReader.getValue(item, key_description).toString();
			descNoHTML = feedDesc.replaceAll("\\<.*?>","");
			this.feedLink = RSSReader.getValue(item, key_link).toString();
			this.feedDate = RSSReader.getValue(item, key_date).toString();
			rsslist.add(new Rssitem(feedTitle, descNoHTML, feedLink, feedDate));

		}

		for (int i = 0; i < nodesOpinion.getLength(); i++) {
			Element item = (Element) nodesOpinion.item(i);
			
			this.feedTitle = RSSReader.getValue(item, key_title).toString();
			this.feedDesc = RSSReader.getValue(item, key_description).toString();
			descNoHTML = feedDesc.replaceAll("\\<.*?>","");
			this.feedLink = RSSReader.getValue(item, key_link).toString();
			this.feedDate = RSSReader.getValue(item, key_date).toString();
			rsslist.add(new Rssitem(feedTitle, descNoHTML, feedLink, feedDate));
			
			
		}
		

		Collections.sort(rsslist, new Comparator<Rssitem>() {
			public int compare(Rssitem o1, Rssitem o2) {

				if (o1.getDate() == null || o2.getDate() == null)
					return 0;
				return o2.getDate().substring(5)
						.compareTo(o1.getDate().substring(5));
			}
		});

		ListView list = (ListView) findViewById(R.id.lstPosts);
		list.setClickable(true);

		RSSAdapter adapter = new RSSAdapter(this, rsslist);

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long index) {

				Intent i = new Intent(MainActivity.this, WebViewSkanskan.class);

				i.putExtra("url", rsslist.get(position).getLink().toString());

				startActivity(i);

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

	Document xmlFeedOpinion = rssfeed
			.getRSSFromServer("http://www.skanskan.se/section/opinion&template=rss&mime=xml");

	NodeList nodesSport = xmlFeedSport.getElementsByTagName("item");
	NodeList nodesOpinion = xmlFeedOpinion.getElementsByTagName("item");
	

}
