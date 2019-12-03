package com.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hubspot.base.BasePage;
import com.hubspot.util.Constants;
import com.hubspot.util.ElementUtil;

public class LoginPage extends BasePage{

	WebDriver driver;
	ElementUtil elementUtil;
	//define locators / or with By locator (NPF) Non-Page-Factory
	
		By emailId =By.id("username");
		By password=By.id("password");
		By loginButton  = By.id("loginBtn");
		
		
	//Constructor of page class
		public LoginPage(WebDriver driver) {
			this.driver = driver;
			elementUtil =new ElementUtil(driver);
		}

	//page actions
		public String getLoginPageTitle(){
			return elementUtil.waitForGetPageTitle(Constants.LOGIN_PAGE_TITLE);
		}
		
		public HomePage doLogin(String username, String pwd){
	//		elementUtil.waitForElementPresent(emailId);
			elementUtil.doSendKeys(emailId, username);
			elementUtil.doSendKeys(password, pwd);
			elementUtil.doClick(loginButton);
			return new HomePage(driver);
		}
}
