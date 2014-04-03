package se.skanskan.mittskanskan;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewSkanskan extends Activity {
	
	private WebView webView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
 	        setContentView(R.layout.webviewlayout);
		
		String url =getIntent().getStringExtra("url");//get url that pass from the other screen
		
		webView = (WebView)findViewById(R.id.webview);
		WebSettings webSetting= webView.getSettings(); //create new settings for webView
		webSetting.setJavaScriptEnabled(true); // enabled javascript
		webView.setWebViewClient(new WebViewClient()); //set up webviewclient, this set not to open the default browser when link click
		
		webView.loadUrl(url);//load the web page
							
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	   
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
	        webView.goBack();
	        return true;
	    }
	   
	    return super.onKeyDown(keyCode, event);
	}

}
