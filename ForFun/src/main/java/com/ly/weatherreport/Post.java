package com.ly.weatherreport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;

import com.thoughtworks.selenium.Selenium;


public class Post {
	private Selenium selenium;
	public Post(WebDriver driver){
		String baseUrl = "http://bbs.nju.edu.cn/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}
	
	public void doPost(String date) throws Exception{
//		doPostYC(date);
//		doPostSZ(date);
		doPostTZ(date);
	}
	public void doPostYC(String date) throws Exception {
		selenium.open("/");
		
		selenium.selectFrame("f2");
		selenium.type("id=FOCUS", "BHYBautoWF");
		selenium.type("name=pw", "bhyb");
		selenium.click("css=input.button");
		selenium.waitForPageToLoad("30000");
		
		selenium.selectFrame("f3");
		selenium.click("xpath=(//a[contains(text(),'更多版面...')])[3]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=○ 登瀛渔火");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("link=主题模式");
		selenium.waitForPageToLoad("30000");
		
		
		selenium.click("link=发表文章");
		selenium.waitForPageToLoad("30000");
		selenium.type("name=title", date+" weather report");
		
		selenium.selectFrame("fontpanel");
		selenium.type("name=up", "C:\\workspace\\ForFun\\"+date+"NJ.png");
		selenium.click("//input[@value='上传到版面']");
		selenium.waitForPageToLoad("100000");

		selenium.type("name=up", "C:\\workspace\\ForFun\\"+date+"YC.png");
		selenium.click("//input[@value='上传到版面']");
		selenium.waitForPageToLoad("100000");

		selenium.type("name=up", "C:\\workspace\\ForFun\\"+date+"SH.png");
		selenium.click("//input[@value='上传到版面']");
		selenium.waitForPageToLoad("100000");

		selenium.type("name=up", "C:\\workspace\\ForFun\\"+date+"YZ.png");
		selenium.click("//input[@value='上传到版面']");
		selenium.waitForPageToLoad("100000");

		selenium.type("name=up", "C:\\workspace\\ForFun\\"+date+"BJ.png");
		selenium.click("//input[@value='上传到版面']");
		selenium.waitForPageToLoad("100000");
		
		selenium.type("name=up", "C:\\workspace\\ForFun\\"+date+"GZ.png");
		selenium.click("//input[@value='上传到版面']");
		selenium.waitForPageToLoad("100000");
		

		selenium.selectFrame("relative=top");
		selenium.selectFrame("f3");
		selenium.click("css=input.button");
		selenium.waitForPageToLoad("50000");
		
		selenium.selectFrame("relative=top");
		selenium.selectFrame("f2");
		selenium.click("name=Submit");
		selenium.waitForPageToLoad("30000");
	
	}
	
	public void doPostSZ(String date) throws Exception {
		selenium.open("/");
		
		selenium.selectFrame("f2");
		selenium.type("id=FOCUS", "gusu");
		selenium.type("name=pw", "suzhou");
		selenium.click("css=input.button");
		selenium.waitForPageToLoad("30000");
		
		selenium.selectFrame("f3");
		selenium.click("xpath=(//a[contains(text(),'更多版面...')])[3]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=○ 雨渍东吴");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("link=主题模式");
		selenium.waitForPageToLoad("30000");
		
		
		selenium.click("link=发表文章");
		selenium.waitForPageToLoad("30000");
		selenium.type("name=title", date+" weather report");
		
		selenium.selectFrame("fontpanel");
		selenium.type("name=up", "C:\\workspace\\ForFun\\"+date+"NJ.png");
		selenium.click("//input[@value='上传到版面']");
		selenium.waitForPageToLoad("100000");
		
		selenium.type("name=up", "C:\\workspace\\ForFun\\"+date+"SZ.png");
		selenium.click("//input[@value='上传到版面']");
		selenium.waitForPageToLoad("100000");
		

		selenium.selectFrame("relative=top");
		selenium.selectFrame("f3");
		selenium.click("css=input.button");
		selenium.waitForPageToLoad("50000");
		
		selenium.selectFrame("relative=top");
		selenium.selectFrame("f2");
		selenium.click("name=Submit");
		selenium.waitForPageToLoad("30000");
	
	}
	
	public void doPostTZ(String date) throws Exception {
		selenium.open("/");
		
		selenium.selectFrame("f2");
		selenium.type("id=FOCUS", "supergod");
		selenium.type("name=pw", "tangyaming");
		selenium.click("css=input.button");
		selenium.waitForPageToLoad("30000");
		
		selenium.selectFrame("f3");
		selenium.click("xpath=(//a[contains(text(),'更多版面...')])[3]");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=○ 古韵泰州");
		selenium.waitForPageToLoad("30000");
		
		selenium.click("link=主题模式");
		selenium.waitForPageToLoad("30000");
		
		
		selenium.click("link=发表文章");
		selenium.waitForPageToLoad("30000");
		selenium.type("name=title", date+" weather report");
		
		selenium.selectFrame("fontpanel");
		selenium.type("name=up", "C:\\workspace\\ForFun\\"+date+"NJ.png");
		selenium.click("//input[@value='上传到版面']");
		selenium.waitForPageToLoad("100000");
		
		selenium.type("name=up", "C:\\workspace\\ForFun\\"+date+"TZ.png");
		selenium.click("//input[@value='上传到版面']");
		selenium.waitForPageToLoad("100000");
		

		selenium.selectFrame("relative=top");
		selenium.selectFrame("f3");
		selenium.click("css=input.button");
		selenium.waitForPageToLoad("50000");
		
		selenium.selectFrame("relative=top");
		selenium.selectFrame("f2");
		selenium.click("name=Submit");
		selenium.waitForPageToLoad("30000");
	
	}
	
	public void tearDown(){
		selenium.stop();
	}
}	
