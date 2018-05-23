package com.demo.automation.pages;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;

public class AllyhomeTestPage extends WebDriverBaseTestPage<WebDriverTestPage> {

	@FindBy(locator = "text.box.ally.homepage.username.loc")
	private QAFWebElement userNameText;
	@FindBy(locator = "text.box.ally.homepage.password.loc")
	private QAFWebElement passwordText;
	@FindBy(locator = "text.box.ally.homepage.saveusername.loc")
	private QAFWebElement saveUserNameCheckBox;

	@Override
	protected void openPage(PageLocator pageLocator, Object... args) {
	}

	public QAFWebElement getUserNameText() {
		return userNameText;
	}

	public QAFWebElement getPasswordText() {
		return passwordText;
	}

	public QAFWebElement getSaveUserNameCheckBox() {
		return saveUserNameCheckBox;
	}

}
