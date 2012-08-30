package com.ly.weatherreport;

import java.io.File;

public class WeatherReport {
	final static String njUrl = "http://www.weather.com.cn/weather/101190101.shtml";
	final static String ycUrl = "http://www.weather.com.cn/weather/101190701.shtml";
	final static String shUrl = "http://www.weather.com.cn/weather/101020100.shtml";
	final static String yzUrl = "http://www.weather.com.cn/weather/101190601.shtml";
	final static String bjUrl = "http://www.weather.com.cn/weather/101010100.shtml";
	final static String gzUrl = "http://www.weather.com.cn/weather/101280101.shtml";
	final static String szUrl = "http://www.weather.com.cn/weather/101190401.shtml";
	final static String tzUrl = "http://www.weather.com.cn/weather/101191201.shtml";
	public static void main(String[] args){
		MyDate myDate = new MyDate();
		String date = myDate.getDate();
		System.out.println(date);
		
		ScreenShot ss = new ScreenShot();
		
		ss.getScreenShot(njUrl, date+"NJ");
		ss.getScreenShot(ycUrl, date+"YC");
		ss.getScreenShot(shUrl, date+"SH");
		ss.getScreenShot(yzUrl, date+"YZ");
		ss.getScreenShot(bjUrl, date+"BJ");
		ss.getScreenShot(gzUrl, date+"GZ");
		ss.getScreenShot(szUrl, date+"SZ");
		ss.getScreenShot(tzUrl, date+"TZ");
		
		Post ps = new Post(ss.getDriver());
		try {
			ps.doPost(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//clean
		File screenShot = new File(date +"NJ.png");
		screenShot.delete();
		screenShot = new File(date +"YC.png");
		screenShot.delete();
		screenShot = new File(date +"SH.png");
		screenShot.delete();
		screenShot = new File(date +"YZ.png");
		screenShot.delete();
		screenShot = new File(date +"BJ.png");
		screenShot.delete();
		screenShot = new File(date +"GZ.png");
		screenShot.delete();
		screenShot = new File(date +"SZ.png");
		screenShot.delete();
		screenShot = new File(date +"TZ.png");
		screenShot.delete();
		
		ps.tearDown();
		ss.destory();
		
	}
	
}
