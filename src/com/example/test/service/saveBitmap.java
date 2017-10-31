package com.example.test.service;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.graphics.Bitmap;

public class saveBitmap {
	
	public static void saveImage(Context context, Bitmap bm, String picName){
		File f = new File(context.getCacheDir(), picName);
		if (f.exists()) { 
			f.delete(); 
		}
		try { 
			FileOutputStream out = new FileOutputStream(f);
			if(bm==null){
//				Log.d("<<<<<<<<", "BMÃ»ÕÒµ½");
			}
			bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
			f.setReadable(true, false);
			f.setWritable(true, false);
			out.flush(); 
			out.close(); 
			} catch (Exception e) { 
			e.printStackTrace(); 
			}

	}

}
