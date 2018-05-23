package com.demo.automation.steps;

import com.demo.automation.pages.ContactUSPage;
import com.demo.automation.pages.InfostretchHomePage;
import com.demo.automation.util.CommonUtility;
import com.qmetry.qaf.automation.step.QAFTestStep;

public class InfostretchHomeStepLibrary {

	InfostretchHomePage infostretchHomePage;
	ContactUSPage contactUSPage;

	public InfostretchHomeStepLibrary() {
		infostretchHomePage = new InfostretchHomePage();
		contactUSPage = new ContactUSPage();
	}

	@QAFTestStep(description = "user launches Infostretch site")
	public void userLaunchesInfostretchSite() {
		infostretchHomePage.launchPage(null);
	}
	@QAFTestStep(description = "user navigates to ContactUs page")
	public void userNavigatesToContactUsPage() {
		infostretchHomePage.navigatesToContactUSPage();
	}
	@QAFTestStep(description = "user fills registration form using {0} details")
	public void fillRegistrationForm(Object data) {
		contactUSPage.fillRegistrationForm(data);
	}
	@QAFTestStep(description = "user compared the user details")
	public void compareData() {
		CommonUtility.compareData();
	}
}
