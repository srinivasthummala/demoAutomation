package com.demo.automation.pages;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Reporter;

public class InfostretchHomePage extends WebDriverBaseTestPage<WebDriverTestPage> {

	@FindBy(locator = "header.page.infostretch.home.page.contact.us.loc")
	private QAFWebElement contactUsLink;

	@Override
	protected void openPage(PageLocator pageLocator, Object... args) {
		driver.manage().window().maximize();
		driver.get("/");
		Reporter.logWithScreenShot("Launched URL is " + driver.getCurrentUrl());
	}

	public QAFWebElement getContactUsLink() {
		return contactUsLink;
	}

	public void navigatesToContactUSPage() {
		getContactUsLink().waitForVisible();
		getContactUsLink().verifyVisible();
		getContactUsLink().click();
		Reporter.log("Contact us link clicked successfully");
	}
}
