package com.example.test.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;


public class ImgParser {
	static String path;
    static String str_showCount;
    
	@SuppressWarnings("deprecation")
	public static List<Map<String,String>> getInfo(Context context,String list){
		String a_href = "";
		String picName;
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		int count = 0;
		try {

				Document doc = Jsoup.parse(new URL("http://www.51tietu.net" + list), 1000000);
//				Log.d("--AAAAA--", element.attr("abs:href"));
				str_showCount = doc.getElementsByClass("showCount").text();
//				showCount = Integer.parseInt(str_showCount);
				Elements elements = doc.getElementsByClass("c");
				for (Element element1 : elements) {
					Map<String, String> map = new HashMap<String, String>();
					
					String str = element1.getElementsByTag("img").attr("abs:src");
					if(!element1.getElementsByTag("a").text().isEmpty()){
//							Log.d("--MYTEST--", element1.getElementsByTag("a").attr("abs:href"));
							a_href += "## "+ element1.getElementsByTag("a").attr("abs:href");

						
					}
					
					count++;
					Log.d("--MYTEST--", str);
					if(str.endsWith(".jpg")){
						picName = str.substring(38, str.indexOf(".jpg")+4);
					}else{
						picName = str.substring(38, str.indexOf(".gif")+4);
					}
					Bitmap bm = getBitmap.getImage(element1.getElementsByTag("img").attr("abs:src"));
//					if(bm==null){
//						Log.d("<<<<<", "BM为空！");
//					}
					saveBitmap.saveImage(context, bm, picName);
					map.put("图片", context.getCacheDir()+"/"+picName);
					map.put("描述", element1.getElementsByClass("ct2").text());
					map.put("赞", element1.getElementsByClass("szs").text());
					String str_title = element1.getElementsByTag("b").text();
					map.put("标题", str_title.substring(0, 8));
					String str_zs = element1.getElementsByClass("info").text();
					map.put("张数", str_zs.substring(str_zs.indexOf("("), str_zs.indexOf(")")+1));

					mapList.add(map);
				}
//			}
				ImgParser.path = a_href;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapList;
	}
	
	public static String gethref(Context context, int itemId){
		String url;
		String[] paths = path.split("##");
		url = paths[itemId+1];
		return url;
	}
	
	public static String getListPage(){
		return str_showCount;
	}
}
