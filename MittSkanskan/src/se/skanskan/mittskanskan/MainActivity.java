package se.skanskan.mittskanskan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	/**
	 * 
	 * Variablerna som hämtas från RSSens XML-fil.
	 */
	String key_items = "item";
	String key_title = "title";
	String key_description = "description";
	String key_link = "link";
	String key_date = "pubDate";
	String linken; // URL from the selected item.
	String theDate;
	ListView lstPost = null;
	List<HashMap<String, Object>> post_lists = new ArrayList<HashMap<String, Object>>();
	List<String> lists = new ArrayList<String>();
	ArrayAdapter<String> adapter = null;
	RSSReader rssfeed = new RSSReader();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lstPost = (ListView) findViewById(R.id.lstPosts);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_2, android.R.id.text1, lists) {

			/**
			 * Datan som visas på listan.
			 */
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				View view = super.getView(position, convertView, parent);
				TextView txt1 = (TextView) view
						.findViewById(android.R.id.text1);
				TextView txt2 = (TextView) view
						.findViewById(android.R.id.text2);
				HashMap<String, Object> data = post_lists.get(position);
				theDate = data.get(key_date).toString();
				txt1.setText(data.get(key_title).toString());
				txt2.setText(data.get(key_description).toString() + "\n"
						+ theDate.substring(0, theDate.length() - 5).toString());

				return view;
			}

		};
		Document xmlFeed = rssfeed
				.getRSSFromServer("http://www.skanskan.se/section/skd&template=rss&mime=xml");
		NodeList nodes = xmlFeed.getElementsByTagName("item");
		for (int i = 0; i < nodes.getLength(); i++) {
			Element item = (Element) nodes.item(i);
			HashMap<String, Object> feed = new HashMap<String, Object>();
			feed.put(key_title, rssfeed.getValue(item, key_title));
			feed.put(key_description, rssfeed.getValue(item, key_description));
			feed.put(key_link, rssfeed.getValue(item, key_link));
			feed.put(key_date, rssfeed.getValue(item, key_date));
			post_lists.add(feed);
			lists.add(feed.get(key_title).toString());
		}
		lstPost.setAdapter(adapter);
		lstPost.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent i = new Intent(MainActivity.this, WebViewSkanskan.class);
				HashMap<String, Object> data = post_lists.get(position);
				linken = data.get(key_link).toString();

				i.putExtra("url", linken);
				startActivity(i);

			}

		});
	}
}
