package com.ly.weatherreport;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MyDate {
	public String getDate(){
		SimpleDateFormat lFormat;
		Date date = null;
		Calendar MyDate = Calendar.getInstance();
		MyDate.setTime(new Date());
		date = MyDate.getTime();
		lFormat = new SimpleDateFormat("yyyy-MM-dd");
		String gRtnStr = lFormat.format(date);
		return gRtnStr;
	}
	
	/*
	public static void main(String[] args){
		GetDate gd = new GetDate();
		String time = gd.getDate();
		System.out.println(time);
	}
	*/
}
