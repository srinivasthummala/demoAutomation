package com.demo.automation.pages;

import java.util.Map;

import com.demo.automation.bean.FillContactUSForm;
import com.demo.automation.bean.UserDataBean;
import com.demo.automation.util.CommonProperties;
import com.demo.automation.util.CommonUtility;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Reporter;

public class ContactUSPage extends WebDriverBaseTestPage<WebDriverTestPage> {

	public FillContactUSForm contactForm;

	@FindBy(locator = "text.box.page.contact.us.first.name.loc")
	private QAFWebElement firstNameTextBox;
	@FindBy(locator = "text.box.page.contact.us.last.name.loc")
	private QAFWebElement lastNameTextBox;
	@FindBy(locator = "text.box.page.contact.us.business.email.loc")
	private QAFWebElement businessEmailTextBox;

	@Override
	protected void openPage(PageLocator locator, Object... args) {

	}

	@Override
	public void waitForPageToLoad() {

	}

	public ContactUSPage() {
		contactForm = new FillContactUSForm();
	}

	public FillContactUSForm getContactForm() {
		return contactForm;
	}
	public QAFWebElement getFirstNameTextBox() {
		return firstNameTextBox;
	}
	public QAFWebElement getLastNameTextBox() {
		return lastNameTextBox;
	}
	public QAFWebElement getBusinessEmailTextBox() {
		return businessEmailTextBox;
	}

	@SuppressWarnings("all")
	public void fillRegistrationForm(Object data) {

		// Switching on Frame
		driver.switchTo().frame("frm_contact");

		if (data instanceof Map) {
			Map<String, String> myData = (Map<String, String>) data;

			// Filling form with data
			getContactForm().fillData(myData);

			// Creating bean for storing data
			UserDataBean userDataBean = new UserDataBean();
			userDataBean.fillData(myData);
			userDataBean.setDataType(CommonProperties.EXCEL_DATA);
			CommonUtility.doExcelActivity(userDataBean, true);

			// Storing data into system
			pageProps.setProperty(CommonProperties.KEY_EXCEL_USER_DETAILS, userDataBean);

			getFirstNameTextBox().verifyVisible();
			getLastNameTextBox().verifyVisible();
			getBusinessEmailTextBox().verifyVisible();

			// Filling data in UI
			getContactForm().fillUiElements();
			Reporter.logWithScreenShot("Data entered successfully");

			// Getting UI_1 data
			userDataBean.setFirstName(
					getFirstNameTextBox().getAttribute(CommonProperties.VALUE).trim());
			userDataBean.setLastName(
					getLastNameTextBox().getAttribute(CommonProperties.VALUE).trim());
			userDataBean.setEmailAddress(getBusinessEmailTextBox()
					.getAttribute(CommonProperties.VALUE).trim());
			userDataBean.setDataType(CommonProperties.UI_DATA);
			CommonUtility.doExcelActivity(userDataBean, false);
			// Storing data into system
			pageProps.setProperty(CommonProperties.KEY_UI_USER_DETAILS, userDataBean);

			// Getting UI_2 data

			driver.get("https://www.ally.com/");

			AllyhomeTestPage allyhomeTestPage = new AllyhomeTestPage();
			allyhomeTestPage.getUserNameText().verifyVisible();
			allyhomeTestPage.getPasswordText().verifyVisible();
			allyhomeTestPage.getSaveUserNameCheckBox().verifyVisible();

			/*
			 * userDataBean
			 * .setFirstName(allyhomeTestPage.getUserNameText().getText().trim()
			 * );
			 * userDataBean.setLastName(allyhomeTestPage.getPasswordText().
			 * getText().trim());
			 * userDataBean.setEmailAddress(
			 * allyhomeTestPage.getSaveUserNameCheckBox().getText().trim());
			 * userDataBean.setDataType(CommonProperties.UI_DATA_2);
			 * CommonUtility.doExcelActivity(userDataBean, false);
			 * pageProps.setProperty(CommonProperties.KEY_UI_2_USER_DETAILS,
			 * userDataBean);
			 */

			userDataBean.setUserName(allyhomeTestPage.getUserNameText().getText().trim());
			userDataBean.setPassword(allyhomeTestPage.getPasswordText().getText().trim());
			userDataBean.setSaveUserName(
					allyhomeTestPage.getSaveUserNameCheckBox().getText().trim());
			userDataBean.setDataType(CommonProperties.UI_DATA_2);

			System.out.println("Bean storing" + userDataBean.getUserName());
			System.out.println("Bean storing" + userDataBean.getDataType());

			pageProps.setProperty(CommonProperties.KEY_UI_2_USER_DETAILS, userDataBean);
			CommonUtility.doExcelActivity(userDataBean, false);

		} else {

		}
	}
}
