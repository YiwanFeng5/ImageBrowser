package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class IndexActivity extends Activity implements OnClickListener{

	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		
		findViewById(R.id.btn_xqx).setOnClickListener(this);
		findViewById(R.id.btn_wm).setOnClickListener(this);
		findViewById(R.id.btn_fzl).setOnClickListener(this);
		findViewById(R.id.btn_ns).setOnClickListener(this);
		findViewById(R.id.btn_wz).setOnClickListener(this);
		findViewById(R.id.btn_qqpf).setOnClickListener(this);
		findViewById(R.id.btn_sg).setOnClickListener(this);
		findViewById(R.id.btn_lomo).setOnClickListener(this);
		findViewById(R.id.btn_aq).setOnClickListener(this);
		findViewById(R.id.btn_gx).setOnClickListener(this);
//		findViewById(R.id.btn_xg).setOnClickListener(this);
//		findViewById(R.id.btn_mn).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_xqx:
			intent = new Intent(this, XQXActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_wm:
			intent = new Intent(this, WMActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_fzl:
			intent = new Intent(this, FZLActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_ns:
			intent = new Intent(this, NSActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_wz:
			intent = new Intent(this, WMActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_qqpf:
			intent = new Intent(this, QQActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_sg:
			intent = new Intent(this, SGActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_lomo:
			intent = new Intent(this, LMActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_aq:
			intent = new Intent(this, AQActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_gx:
			intent = new Intent(this, GXActivity.class);
			startActivity(intent);
//		case R.id.btn_xg:
//			intent = new Intent(this, XGActivity.class);
//			startActivity(intent);
//		case R.id.btn_mn:
//			intent = new Intent(this, MNActivity.class);
//			startActivity(intent);
			break;
		default:
			break;
		}
	}
	
	

}
