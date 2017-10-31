package com.example.test;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test.service.ImgParser;
import com.example.test.service.ImgService;

public class MainActivity extends Activity {

	protected static final int ERROR = 0;
	protected static final int SHOW_TEXT = 1;
	protected static final int MUYOULE = 2;
	protected static final int SHOW_IMAGE = 3;
	Document doc;
	ImageView iv;
	Message msg;
	FrameLayout layout;
	String imagePath = "##";
	static int id;

	int count = 0;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case ERROR:
				Toast.makeText(MainActivity.this, "获取数据失败", 0);
				break;
			case MUYOULE:
				Toast.makeText(MainActivity.this, "最后一张图片", 0);
				
				break;
			case SHOW_IMAGE:
				Bitmap bm = (Bitmap) msg.obj;
				if(layout.getChildCount()!=0){
					layout.removeAllViews();
					layout.addView(iv);
					iv.setImageBitmap(bm);
				}else{
					
					layout.addView(iv);
					iv.setImageBitmap(bm);
				}
				break;
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		layout = (FrameLayout) findViewById(R.id.fl_imgContent);
		new AsyncTask<String, String, String>() {

			@Override
			protected String doInBackground(String... params) {
				try {
					String href = ImgParser.gethref(MainActivity.this, id);
					Document doc = Jsoup.parse(new URL(href),1000000);
					Element link1 = doc.getElementById("scontent");
					Elements links = link1.getElementsByTag("img");
//					http://b337.photo.store.qq.com/psb?/V11q16BU33UbgQ/Df7B8mjooYlxLA1O.dIAX3JjN1n6yr0A.bMly232V0s!/m/dL1j6si0AQAAnull&bo=bALRAQAAAAABB54!&rf=photolist&t=5
//					http://b327.photo.store.qq.com/psb?/V11q16BU33UbgQ/wZigo8YGjdiD4FmPuvn3ngcMfwiBxRucCiuvuJVrGc4!/m/dNSH98LQMAAAnull&bo=bALRAQAAAAABB54!&rf=photolist&t=5	
					for (Element link : links) {
						imagePath += link.attr("abs:src")+"##";
						count++;
					}
					ImgService.saveImage(MainActivity.this, imagePath);
					String[] imgs = ImgService.getImage(MainActivity.this);
//					for(int i=0; i<imgs.length; i++){
						Log.d("--TEST--",imgs[count]);
						URL url = new URL(imgs[count]);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();

						conn.setRequestMethod("GET");
						conn.setReadTimeout(1000000);
						conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 UBrowser/3.1.1644.34 Safari/537.36");

						int code = conn.getResponseCode();
						if (code == 200) {
							InputStream is = conn.getInputStream();
							Bitmap bitmap = BitmapFactory.decodeStream(is);
							iv = new ImageView(MainActivity.this);
							Message msg = new Message();
							msg.what = SHOW_IMAGE;
							msg.obj = bitmap;
							handler.sendMessage(msg);

						} else {
							Message msg = new Message();
							msg.what = ERROR;
							handler.sendMessage(msg);
						}
//						}
//					}
//					Log.d("--Count--", ""+count);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

		}.execute();
	};
	
	
	
	@Override
	protected void onPause() {
		super.onPause();
		
	}



	public void lastImg(View view){
		final int i =count;
		new Thread(){

			@Override
			public void run() {
				if (count>i) {
					Message msg = new Message();
					msg.what = MUYOULE;
					handler.sendMessage(msg);
					count=0;
				} else {
//					while(count!=0){
						try {
							String[] imgs = ImgService.getImage(MainActivity.this);
//							for(int i=0; i<imgs.length; i++){
								Log.d("--TEST--",imgs[count]);
								URL url = new URL(imgs[count]);
								HttpURLConnection conn = (HttpURLConnection) url.openConnection();

								conn.setRequestMethod("GET");
								conn.setReadTimeout(1000000);
								conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 UBrowser/3.1.1644.34 Safari/537.36");

								int code = conn.getResponseCode();
								if (code == 200) {
									InputStream is = conn.getInputStream();
									Bitmap bitmap = BitmapFactory.decodeStream(is);
									iv = new ImageView(MainActivity.this);
									Message msg = new Message();
									msg.what = SHOW_IMAGE;
									msg.obj = bitmap;
									handler.sendMessage(msg);

								} else {
									Message msg = new Message();
									msg.what = ERROR;
									handler.sendMessage(msg);
								}
//							}
						} catch (Exception e) {
							e.printStackTrace();
						} 
						Log.d("--Count--", count+"");
						count++;
					}
//				}
				super.run();
			}

		}.start();
		
	
	}
	
	public void nextImg(View view){

		
		new Thread(){

			@Override
			public void run() {
				if (count<0) {
					Message msg = new Message();
					msg.what = MUYOULE;
					handler.sendMessage(msg);
					count=0;
				} else {
//					while(count!=0){
						try {
							String[] imgs = ImgService.getImage(MainActivity.this);
//							for(int i=0; i<imgs.length; i++){
								Log.d("--TEST--",imgs[count]);
								URL url = new URL(imgs[count]);
								HttpURLConnection conn = (HttpURLConnection) url.openConnection();

								conn.setRequestMethod("GET");
								conn.setReadTimeout(1000000);
								conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 UBrowser/3.1.1644.34 Safari/537.36");

								int code = conn.getResponseCode();
								if (code == 200) {
									InputStream is = conn.getInputStream();
									Bitmap bitmap = BitmapFactory.decodeStream(is);
									iv = new ImageView(MainActivity.this);
									Message msg = new Message();
									msg.what = SHOW_IMAGE;
									msg.obj = bitmap;
									handler.sendMessage(msg);

								} else {
									Message msg = new Message();
									msg.what = ERROR;
									handler.sendMessage(msg);
								}
//							}
						} catch (Exception e) {
							e.printStackTrace();
						} 
						Log.d("--Count--", count+"");
						count--;
					}
//				}
				super.run();
			}

		}.start();
		
	}
}


