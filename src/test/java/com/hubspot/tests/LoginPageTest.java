package com.hubspot.tests;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hubspot.base.BasePage;
import com.hubspot.pages.LoginPage;
import com.hubspot.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners(com.hubspot.listeners.TestAllureListener.class)

public class LoginPageTest {

Logger log = Logger.getLogger("LoginPageTest");
	
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@Test(priority=1, description="login test with correct username and correct password...") //2.
//	@Description("login test with correct username and correct password..")
	//@Severity(SeverityLevel.BLOCKER)
	public void loginTest(){
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=2, description="login page title with correct title") //2.
	@Description("login page title with correct title")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest(){
		String title = loginPage.getLoginPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "login page title is in-correct"); //2.
	}
	
	@AfterMethod
	public void tearDown(){
		basePage.quitBrowser();
	}

}