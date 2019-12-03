package com.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hubspot.base.BasePage;
import com.hubspot.util.Constants;
import com.hubspot.util.ElementUtil;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;
	
	By header = By.xpath("//h3//span[starts-with(@data-key,'getting')]//span[3]");
	By accountName= By.xpath("//h3//span[starts-with(@data-key,'getting')]//span[2]");
	
	By contactsMainTab= By.id("nav-primary-contacts-branch");
	By contactsChildTab =By.id("nav-secondary-contacts");
	//By contactMainTab = By.xpath("//a[contains(text(),'Contacts') and @data-tracking='data-tracking']");
	
	//Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementUtil =new ElementUtil(driver);
	}
	//Methods
	public String getHomePageTitle(){
		return elementUtil.waitForGetPageTitle(Constants.HOME_PAGE_TITLE);
	}
	public String getHomePageHeaderValue(){
		return elementUtil.doGetText(header);
	}
	public boolean verifyLoggedInUserInfoName(){
		
		return elementUtil.isElementDisplayed(accountName);
	}
	public String getLoggedInUserName(){
		return elementUtil.doGetText(accountName);
	}
	private void clickOnContacts(){
		elementUtil.doClick(contactsMainTab);
		elementUtil.doClick(contactsChildTab);
	}
	public ContactsPage gotoContactsPage(){
		 clickOnContacts();
		 return new ContactsPage(driver);
	}
}
