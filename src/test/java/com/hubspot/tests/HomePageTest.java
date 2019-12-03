package com.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hubspot.base.BasePage;
import com.hubspot.pages.HomePage;
import com.hubspot.pages.LoginPage;
import com.hubspot.util.Constants;

public class HomePageTest {

		WebDriver driver;
		Properties prop;
		BasePage basePage;
		LoginPage loginPage;
		HomePage homePage;
	
	
		@BeforeMethod
		public void setUp() throws InterruptedException{
			basePage = new BasePage();
			prop = basePage.initialize_properties();
			driver =basePage.initialize_driver(prop);
			loginPage = new LoginPage(driver);
			homePage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
			Thread.sleep(5000);
			
			}
		@Test (priority=1, description="this method verifies home page title")
		public void verifyHomePageTitleTest(){
			String title=homePage.getHomePageTitle();
			System.out.println("Page title is "+title);
			Assert.assertEquals(title, Constants.HOME_PAGE_TITLE, "title is incorrect");
	}
		@Test (priority=2, description="this method verifies home page header")
		public void verifyHomePageHeader(){
			String headerValue=homePage.getHomePageHeaderValue();
			System.out.println("HomePage header is "+headerValue);
			Assert.assertEquals(headerValue, Constants.HOME_PAGE_HEADER, "incorrect header");
	}
		@Test (priority=3, description="this method verifies the user who logged in")
		public void verifyLoggedInUserInfo(){
			String accountInfo=homePage.getLoggedInUserName();
			System.out.println("Account name is "+accountInfo);
			Assert.assertEquals(accountInfo, prop.getProperty("userinfo"));
	}
	
		@AfterMethod
		public void tearDown(){
			basePage.quitBrowser();
	}
}