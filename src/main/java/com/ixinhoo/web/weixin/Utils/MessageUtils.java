package com.ixinhoo.web.weixin.Utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ixinhoo.web.weixin.TestMessage;
import com.thoughtworks.xstream.XStream;

public class MessageUtils {
	
	public static final String MSGTYPE_TEXT="text";
	public static final String MSGTYPE_IMAGE="image";
	public static final String MSGTYPE_VOICE="voice";
	public static final String MSGTYPE_VIDEO="video";
	public static final String MSGTYPE_LINK="link";
	public static final String MSGTYPE_EVENT="event";
	public static final String MSGTYPE_SUBSCRIBE="subscribe";
	public static final String MSGTYPE_UNSUBSCRIBE="unsubscribe";
	public static final String MSGTYPE_CLICK="CLICK";
	public static final String MSGTYPE_VIEW="VIEW";
	
	
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws Exception{
		Map<String,String> map= new HashMap<String,String>();
		SAXReader reader = new SAXReader();
	     InputStream ins = request.getInputStream();
		Document doc =reader.read(ins);
		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		for(Element e:list) {
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
		
	}
	public static String textToXml(TestMessage testMesssage) throws Exception{
		  XStream stream = new XStream();
		  stream.alias("xml", testMesssage.getClass());
		 return stream.toXML(testMesssage);
			
	}
	
	
	public static String menus() {
		StringBuffer sb  = new StringBuffer();
		sb.append("欢迎您的关注：\n");
		sb.append("1,我们主要是软件开发：\n");
		sb.append("2，您可以随时过来看看：\n");
		sb.append("3，不信您别了关注：\n");
		return sb.toString();
		
	}
	public static String firstMenu() {
		StringBuffer sb  = new StringBuffer();
		sb.append("这个公司主要从事软件开发");
		return sb.toString();
	}
	public static String secondMenu() {
		StringBuffer sb  = new StringBuffer();
		sb.append("我们公司很伟大");
		return sb.toString();
	}
	public static String allMenus() {
		StringBuffer sb  = new StringBuffer();
		sb.append("这里是全部技术列表，欢迎选用我们的人才");
		return sb.toString();
	}
}
