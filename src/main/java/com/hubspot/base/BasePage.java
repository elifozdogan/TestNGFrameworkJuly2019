package com.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.description.ModifierReviewable.OfAbstraction;

/**
 * @author elifozdogan
 */
public class BasePage {

	public WebDriver driver;
	public Properties prop;
	public static String flash;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the driver on the basis of given browser
	 * @return this method returns webdriver instance
	 */
	
	public WebDriver initialize_driver(Properties prop){
		//String browser = "chrome";
		String browser = prop.getProperty("browser");
		String headless = prop.getProperty("headless");
		flash = prop.getProperty("elementflash");
		if(browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup();
			if(headless.equals("yes")){
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
			}else{
			driver = new ChromeDriver();
			}
		}
		else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			if(headless.equals("yes")){
				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--headless");
				driver = new FirefoxDriver(fo);
			}else{
				driver = new FirefoxDriver();
			}
		}
		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
		
	}
	/**
	 * This method is used to define properties
	 * @return  This method returns properties prop reference
	 */
public Properties initialize_properties(){
		
		prop = new Properties();
		
		try {
																											//user directory
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+   //--->my project's path or user.dir for mac 
					"/src/main/java/config/hubspot/config/config.properties");
			//if everybody uses mac in your team,when you sent your framework to them, they will not have to change it because user
			// directory is common for all macs
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		return prop;
	
	}
		public static synchronized WebDriver getDriver(){
			return tldriver.get();
}
	public void quitBrowser(){
		try {
			driver.quit();
		} catch (Exception e) {
		System.out.println("some exception occured while quitting the browser");
		}
	}
	public void closeBrowser(){
		try {
			driver.close();
		} catch (Exception e) {
			System.out.println("some exception occured while closing the browser");
		}
	}
}
