package com.ly.weatherreport;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScreenShot {
	private WebDriver driver;	
	public ScreenShot(){
		driver = new FirefoxDriver();
	}
	public WebDriver getDriver(){
		return driver;
	}
	public void getScreenShot(String url, String fileName){
		String handle = driver.getWindowHandle();
        String js_open = 
        	"window.open('"+url+"','"+ fileName +"','left=0,top=0,width=600,height=265'); ";
        ((JavascriptExecutor)driver).executeScript(js_open);
        driver.switchTo().window(fileName);
        
        System.out.println(driver.getTitle());
        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().contains("ÌìÆøÔ¤±¨");
            }
        });
        String js_handle = 
        	"var htmlElement = document.documentElement; " +
			"var headingElement = htmlElement.firstChild; " +
			"var bodyElement = headingElement.nextSibling; " +
			"var contentElement = bodyElement.firstChild; " +
			"while(contentElement != null) " +
			"{ " +
			"	if(contentElement.className != null && contentElement.className != \"weatherMain\") " + 
			"	{ " +
			"		contentElement.style.display = \"none\"; " +
			"	} " +
			"	else if(contentElement.className != null) " +
			"	{ " +
			"		contentElement.style.minHeight = \"100px\"; " +
			"		contentElement.style.width = \"605px\"; " +
			"		var mainContentLeft = contentElement.firstChild.nextSibling; " +
			"		var mainContentRight = mainContentLeft.nextSibling.nextSibling; " +
			"		var mainContentClear = mainContentRight.nextSibling.nextSibling; " +
			"		mainContentRight.style.display = \"none\"; " +
			"		mainContentRight.style.width = \"5px\"; " +
			"		mainContentClear.style.display = \"none\"; " +
			"		mainContentLeft.style.height = \"265px\"; " +
			"		mainContentLeft.style.width = \"600px\"; " +
			"		mainContentLeft.style.minHeight = \"100px\"; " +
			"		var divs = mainContentLeft.getElementsByTagName(\"div\"); " + 
			"		for(var i = 1; i < divs.length; i++) " +
			"		{" +
			"			if((divs.item(i).className == null)) " +
			"			{ " +
			"				divs.item(i).style.display = \"none\"; " +
			"			} " +
			"			else if((divs.item(i).className != \"weatherYubaoBox\") && (divs.item(i).className != \"weatherYubao\")) " +
			"			{ " +
			"				divs.item(i).style.display = \"none\"; " +
			"			} " +
			"		}" +
			"	} " +
			"	contentElement = contentElement.nextSibling; " + 
			"} " +
			"document.getElementById(\"live\").style.display = \"none\"; ";
        ((JavascriptExecutor)driver).executeScript(js_handle);    
         
    	File screenShot = new File(fileName +".png");
    	File tempScreenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	try {
			FileUtils.copyFile(tempScreenShot, screenShot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
		driver.switchTo().window(handle);
		
	}
	
	public void destory(){
		driver.quit();
	}

}

