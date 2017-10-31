package com.example.test.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.content.Context;

public class ImgService {
	public static boolean saveImage(Context context, String imagePath){
		try {
			File file = new File(context.getCacheDir(),"imgpath.txt");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write((imagePath).getBytes());
			fos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String[] getImage(Context context){
		String[] imgs = null;
		File file = new File(context.getCacheDir(),"imgpath.txt");
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String str = br.readLine();
			imgs = str.split("##");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgs;
	}
	
}

