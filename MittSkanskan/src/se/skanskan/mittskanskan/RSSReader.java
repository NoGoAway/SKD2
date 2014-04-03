package se.skanskan.mittskanskan;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.os.StrictMode;

public class RSSReader {

	DefaultHttpClient httpClient = new DefaultHttpClient();

	public Document getRSSFromServer(String url) {
		Document response = null;
		response = getDomFromXMLString(getFeedFromServer(url));
		return response;
	}

	public String getFeedFromServer(String url) {

		String xml = null;
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		try {
			HttpGet httpget = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpget);
			HttpEntity httpEntity = httpResponse.getEntity();
			xml = EntityUtils.toString(httpEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;

	}

	private Document getDomFromXMLString(String xml) {
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xml));
			doc = db.parse(is);

		} catch (Exception e) {
		}
		return doc;
	}

	static public Document getDomElement(String xmlString)
			throws ParserConfigurationException, SAXException, IOException {
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xmlString));
		doc = db.parse(is);

		return doc;
	}

	static public String getValue(Element item, String str) {
		NodeList n = item.getElementsByTagName(str);
		return getElementValue(n.item(0));
	}

	static public final String getElementValue(Node elem) {
		try {
			Node child;
			if (elem != null) {
				if (elem.hasChildNodes()) {
					for (child = elem.getFirstChild(); child != null; child = child
							.getNextSibling()) {
						if (child.getNodeType() == Node.CDATA_SECTION_NODE
								|| child.getNodeType() == Node.TEXT_NODE) {
							return child.getNodeValue().trim();
						}
					}
				}
			}
			return "";
		} catch (DOMException e) {
			return "";
		}
	}
}
