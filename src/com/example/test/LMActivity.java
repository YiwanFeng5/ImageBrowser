package com.example.test;

import java.util.List;
import java.util.Map;

import com.example.test.service.ImgParser;
import com.example.test.utils.List_Path;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class LMActivity extends Activity implements OnClickListener{

	ListView lv_lm;
	List<Map<String,String>> data;
	SimpleAdapter adapter;
	ArrayAdapter<String> adapter1;
	Spinner spinner;
	TextView tv_spinner;
	static List_Path path;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lm);
		
		lv_lm = (ListView) findViewById(R.id.lv_lm);
		findViewById(R.id.btn_l).setOnClickListener(this);
		findViewById(R.id.btn_n).setOnClickListener(this);
		spinner = (Spinner) findViewById(R.id.spinner1);
		tv_spinner = (TextView)findViewById(R.id.tv_spinner);
//		findViewById(R.id.spinner1).
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
//				Intent intent = XQXActivity.this.getIntent();
//				path = (List_Path)intent.getSerializableExtra("list");
//				String list = path.gettList();
				String list = "/lomo/";
				data = ImgParser.getInfo(LMActivity.this,list);
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				
				String[] from = {"图片","描述","赞","标题","张数"};
				int[] to = {R.id.iv,R.id.tv_description,R.id.tv_zan,R.id.tv_title,R.id.tv_zs};
				adapter = new SimpleAdapter(LMActivity.this,data,R.layout.item,from,to){

					@Override
					public View getView(int position, View convertView,
							ViewGroup parent) {
						final int p = position;
						final View view = super.getView(position, convertView, parent);
						ImageView iv = (ImageView) view.findViewById(R.id.iv);
						iv.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
//								Log.d("MYTEST", "您点击了图片");
								MainActivity.id = (int) getItemId(p);
//								Log.d("--MYTEST--", getItemId(p)+"");
								Intent intent = new Intent(LMActivity.this, MainActivity.class);
								startActivity(intent);
							}
						});
						
						return view;
					}
					
				};
				if(adapter==null){
					Log.d("<<<<<<<<", "adapter为空！");
				}
				lv_lm.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				lv_lm.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
				lv_lm.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
//						ImageView item_iv = (ImageView) view.findViewById(R.id.iv);
					}
				});
			}
			
		}.execute();
		
		new AsyncTask<Void, Void, Void>(){

			private String string = "";

			@Override
			protected Void doInBackground(Void... params) {
//				Log.d("--MYTEST--", ImgParser.getListPage());
				String str = ImgParser.getListPage();
				int list = Integer.parseInt(str.substring(str.indexOf("共")+1, str.indexOf("页")));
//				Log.d("--MYTEST--", list+"");
				for(int i=0; i<list;i++){
					string  += "##" + (i+1);
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				 String[] str = string.split("##");
	                
	                adapter1 = new ArrayAdapter<String>(LMActivity.this,android.R.layout.simple_spinner_item, str){

						@Override
						public View getView(int position, View convertView,
								ViewGroup parent) {
							final int p = position;
							final View view = super.getView(position, convertView, parent);
							
//							if(tv_spinner == null){
//								Log.d("-----tv-spinner is null -----","<<<<<<<<");
//							}
							spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

								@Override
								public void onItemSelected(
										AdapterView<?> parent, View view,
										int position, long id) {
//									String list = "/xiaoqingxin/" + getItemId(p);
//									Intent intent = new Intent();
//									intent.setClass(getContext(), XQXActivity.class);
//									Bundle bundle = new Bundle();
//									bundle.putSerializable("list", list);
//									XQXActivity.this.startActivity(intent);
									
									
								}

								@Override
								public void onNothingSelected(
										AdapterView<?> parent) {
//									String list = "/xiaoqingxin/";
//									Intent intent = new Intent();
//									intent.setClass(getContext(), XQXActivity.class);
//									Bundle bundle = new Bundle();
//									bundle.putSerializable("list", list);
//									XQXActivity.this.startActivity(intent);
									
								}
							
								
								
							});
							return view;
						}
	                	
	                };
	                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	                spinner.setAdapter(adapter1);
//	                spinner.setAdapter(null);
	                adapter.notifyDataSetChanged();
			}
			
			
		}.execute();
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_l:
			
			break;
		case R.id.btn_n:
			
			break;

		default:
			break;
		}
	}
	
}
