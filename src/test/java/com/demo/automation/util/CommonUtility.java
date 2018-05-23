package com.demo.automation.util;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.demo.automation.bean.UserDataBean;
import com.demo.automation.excel.ExcelUtility;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.util.Reporter;

public class CommonUtility {

	// Method to write data in excel
	public static void doExcelActivity(UserDataBean userData, boolean nextLine) {

		ExcelUtility excelUtility = new ExcelUtility("resources//data//ActualData.xls");
		excelUtility.writeUserDetails(userData, nextLine);
	}

	// Method to compare data
	public static void compareData() {

		UserDataBean excelUserDataBean = (UserDataBean) ConfigurationManager.getBundle()
				.getObject(CommonProperties.KEY_EXCEL_USER_DETAILS);
		UserDataBean uiUserDataBean = (UserDataBean) ConfigurationManager.getBundle()
				.getObject(CommonProperties.KEY_UI_USER_DETAILS);
		UserDataBean ui_2_UserDataBean = (UserDataBean) ConfigurationManager.getBundle()
				.getObject(CommonProperties.KEY_UI_2_USER_DETAILS);

		UserDataBean comparedUserDataBean = new UserDataBean();
		comparedUserDataBean.setDataType(CommonProperties.COMPARED_DATA);
		boolean success = true;

		if (excelUserDataBean.getFirstName().equals(uiUserDataBean.getFirstName())) {
			comparedUserDataBean.setFirstName(excelUserDataBean.getFirstName());
			Reporter.log("First name successfully compared", MessageTypes.Pass);
		} else {
			comparedUserDataBean.setFirstName("Excel:" + excelUserDataBean.getFirstName()
					+ " and UI:" + uiUserDataBean.getFirstName());
			success = false;
			Reporter.log("Failed to compare first name", MessageTypes.Fail);

		}
		if (excelUserDataBean.getLastName().equals(uiUserDataBean.getLastName())) {
			comparedUserDataBean.setLastName(excelUserDataBean.getLastName());
			Reporter.log("Last name successfully compared", MessageTypes.Pass);
		} else {
			comparedUserDataBean.setLastName("Excel:" + excelUserDataBean.getLastName()
					+ " and UI:" + uiUserDataBean.getLastName());
			success = false;
			Reporter.log("Failed to compare last name", MessageTypes.Fail);
		}
		if (excelUserDataBean.getEmailAddress()
				.equals(uiUserDataBean.getEmailAddress())) {
			comparedUserDataBean.setEmailAddress(excelUserDataBean.getEmailAddress());
			Reporter.log("Email address successfully compared", MessageTypes.Pass);
		} else {
			comparedUserDataBean
					.setEmailAddress("Excel:" + excelUserDataBean.getEmailAddress()
							+ " and UI:" + uiUserDataBean.getEmailAddress());
			success = false;
			Reporter.log("Failed to compare email address", MessageTypes.Fail);
		}

		// UI_2 verification
		System.out.println(excelUserDataBean.getFirstName());
		System.out.println(ui_2_UserDataBean.getFirstName());

		if (excelUserDataBean.getFirstName().equals(ui_2_UserDataBean.getUserName())) {
			comparedUserDataBean.setFirstName(excelUserDataBean.getFirstName());
			Reporter.log("First name successfully compared", MessageTypes.Pass);
		} else {
			comparedUserDataBean.setFirstName("Excel:" + excelUserDataBean.getFirstName()
					+ " and UI_2:" + ui_2_UserDataBean.getUserName());
			success = false;
			Reporter.log("Failed to compare first name", MessageTypes.Fail);

			// clring
			XSSFWorkbook wb = new XSSFWorkbook();

			CellStyle backgroundStyle = wb.createCellStyle();
			backgroundStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
			// backgroundStyle.setFillPattern(FillPatternType.LESS_DOTS);
		}

		if (excelUserDataBean.getLastName().equals(ui_2_UserDataBean.getPassword())) {
			comparedUserDataBean.setLastName(excelUserDataBean.getLastName());
			Reporter.log("Last name successfully compared", MessageTypes.Pass);
		} else {
			comparedUserDataBean.setLastName("Excel:" + excelUserDataBean.getLastName()
					+ " and UI_2:" + ui_2_UserDataBean.getPassword());
			success = false;
			Reporter.log("Failed to compare last name", MessageTypes.Fail);
		}

		if (excelUserDataBean.getEmailAddress()
				.equals(ui_2_UserDataBean.getSaveUserName())) {
			comparedUserDataBean.setEmailAddress(excelUserDataBean.getEmailAddress());
			Reporter.log("Email address successfully compared", MessageTypes.Pass);
		} else {
			comparedUserDataBean
					.setEmailAddress("Excel:" + excelUserDataBean.getEmailAddress()
							+ " and UI_2:" + ui_2_UserDataBean.getSaveUserName());
			success = false;
			Reporter.log("Failed to compare email address", MessageTypes.Fail);
		}

		if (success) {
			comparedUserDataBean.setResult(CommonProperties.PASS);
		} else {
			comparedUserDataBean.setResult(CommonProperties.FAIL);
		}
		CommonUtility.doExcelActivity(comparedUserDataBean, false);
	}
}
