package com.demo.automation.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.demo.automation.bean.UserDataBean;
import com.demo.automation.pages.ContactUSPage;
import com.demo.automation.util.CommonProperties;

/**
 * @author
 */
public class ExcelUtility {
	// static String[] contentValues;
	ContactUSPage contactUSPage = new ContactUSPage();
	// Creating variable for file input stream
	private FileInputStream fileInputStream = null;

	// Creating variable for workbook
	private Workbook workbook = null;

	// Creating variable for file output stream
	private FileOutputStream fileOutputStream = null;

	// Creating variable for sheet
	private Sheet sheet = null;

	// Creating variable for row count
	private int rowCount = 0;

	// Creating variable for row
	private Row row = null;

	// Creating variable for new row
	private Row newRow = null;

	// Creating variable for file path
	private String filePath = null;

	// Creating variable for file
	private File file = null;

	public ExcelUtility(String filePath) {
		this.filePath = filePath;
		file = new File(filePath);
	}

	// Writing User details data into excel
	public void writeUserDetails(UserDataBean userDataBean, boolean nextLine) {

		// Creating objects
		createObjects();

		// Generating new sheet name
		String sheetName = (new SimpleDateFormat("ddMMyyyy").format(new Date()));

		// Setting checkSheet variable to true
		boolean checkSheet = true;

		// Checking sheet
		for (int sheet = 0; sheet < workbook.getNumberOfSheets(); sheet++) {
			if (workbook.getSheetName(sheet).equalsIgnoreCase(sheetName)) {
				checkSheet = false;
			}
		}

		if (checkSheet) {

			// Creating new sheet
			workbook.createSheet(sheetName);

			// Writing data into excel file
			writeData();

			// Closing objects
			closeObjects();

			// Creating objects
			createObjects();

			// Reading excel sheet by sheet name
			sheet = workbook.getSheet(sheetName);

			// Getting current count of rows
			rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

			// Creating a new row
			newRow = sheet.createRow(rowCount);

			// Header values
			String[] headerValues =
					{"dataType", "firstName", "lastName", "emailAddress", "result"};

			// Creating cells into excel
			for (int content = 0; content < headerValues.length; content++) {

				// Filling data in row
				Cell cell = newRow.createCell(content);
				cell.setCellValue(headerValues[content]);
			}

			// Writing data into excel file
			writeData();

			// Closing objects
			closeObjects();

			// Creating objects
			createObjects();
		}

		// Reading excel sheet by sheet name
		sheet = workbook.getSheet(sheetName);

		// Getting current count of rows
		rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		// Creating a new row
		if (nextLine) {
			newRow = sheet.createRow(rowCount + 2);
		} else {
			newRow = sheet.createRow(rowCount + 1);
		}

		// Content values

		// String[] contentValues = {userDataBean.getDataType(),
		// userDataBean.getFirstName(),
		// userDataBean.getLastName(), userDataBean.getEmailAddress(),
		// userDataBean.getResult()};

		String[] contentValues = null;

		if (userDataBean.getDataType().equals("Excel")) {
			System.out.println(userDataBean.getUserName() + "eXCEL block data*******");
			contentValues = new String[]{userDataBean.getDataType(),
					userDataBean.getFirstName(), userDataBean.getLastName(),
					userDataBean.getEmailAddress(), userDataBean.getResult()};
		} else if (userDataBean.getDataType().equals("UI_1")) {
			System.out.println(userDataBean.getUserName() + "UI_1 block data*******");
			contentValues = new String[]{userDataBean.getDataType(),
					userDataBean.getFirstName(), userDataBean.getLastName(),
					userDataBean.getEmailAddress(), userDataBean.getResult()};
		} else if (userDataBean.getDataType().equals(CommonProperties.UI_DATA_2)) {
			System.out.println(userDataBean.getUserName() + "UI_2 block data*******");
			contentValues = new String[]{userDataBean.getDataType(),
					userDataBean.getUserName(), userDataBean.getPassword(),
					userDataBean.getSaveUserName(), userDataBean.getResult()};

		} else if (userDataBean.getDataType().equals("Compared")) {
			contentValues = new String[]{userDataBean.getDataType(),
					userDataBean.getUserName(), userDataBean.getPassword(),
					userDataBean.getSaveUserName(), userDataBean.getResult()};
		} else {
		}

		// Creating cells into excel
		for (int content = 0; content < contentValues.length; content++) {

			// Filling data in row
			Cell cell = newRow.createCell(content);
			cell.setCellValue(contentValues[content]);
		}

		// Writing data into excel file
		writeData();

		// Closing objects
		closeObjects();
	}

	// Reading data from excel
	public void readExcel(String sheetName) {

		try {
			// Creating object of file input stream
			fileInputStream = new FileInputStream(file);

			// Identifying file type
			String fileExtensionName = filePath.substring(filePath.indexOf("."));

			// Creating object of workbook
			if (fileExtensionName.equals(".xlsx")) {
				workbook = new XSSFWorkbook(fileInputStream);
			} else if (fileExtensionName.equals(".xls")) {
				workbook = new HSSFWorkbook(fileInputStream);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Reading excel sheet by sheet name
		sheet = workbook.getSheet(sheetName);

		// Getting current count of rows
		rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		// Reading data
		for (int rows = 0; rows < rowCount + 1; rows++) {
			row = sheet.getRow(rows);
			for (int columns = 0; columns < row.getLastCellNum(); columns++) {
				System.out.print(row.getCell(columns) + "\t");
			}
			System.out.println();
		}

		try {
			// Closing file input stream
			fileInputStream.close();

			// Closing workbook
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Creating objects
	private void createObjects() {

		try {
			// Creating object of file input stream
			fileInputStream = new FileInputStream(file);
			// Identifying file type
			String fileExtensionName = filePath.substring(filePath.indexOf("."));

			// Creating object of workbook
			if (fileExtensionName.equals(".xlsx")) {
				workbook = new XSSFWorkbook(fileInputStream);
			} else if (fileExtensionName.equals(".xls")) {
				workbook = new HSSFWorkbook(fileInputStream);
			}

			// Creating object of file output stream
			fileOutputStream = new FileOutputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Writing data into excel file
	private void writeData() {

		try {
			// Writing data into excel file
			workbook.write(fileOutputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Closing objects
	private void closeObjects() {

		try {
			// Closing file input stream
			fileInputStream.close();

			// Closing workbook
			workbook.close();

			// Closing file output stream
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		UserDataBean userDataBean = new UserDataBean();
		userDataBean.setFirstName("");
		userDataBean.setLastName("");
		userDataBean.setEmailAddress("");

		String sheetName = new SimpleDateFormat("ddMMyyyy").format(new Date());

		ExcelUtility excelUtility = new ExcelUtility("resources//data//ActualData.xls");
		excelUtility.writeUserDetails(userDataBean, false);
		excelUtility.readExcel(sheetName);
	}
}
