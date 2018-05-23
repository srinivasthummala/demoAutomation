package com.demo.automation.excel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class XLSXtoXML {
	static DecimalFormat df = new DecimalFormat("#####0");
	public static void main(String[] args) throws Exception {
		FileWriter fostream;
		PrintWriter out = null;
		String strOutputPath = "src//test//resources//";
		String strFilePrefix = "Master";
		DataFormatter dataFormatter = new DataFormatter();
		try {
			InputStream inputStream = new FileInputStream(
					new File("src//test//resources//TestImportXml.xlsx"));
			Workbook wb = WorkbookFactory.create(inputStream);
			Sheet sheet = wb.getSheet("Sheet1");

			fostream = new FileWriter(strOutputPath + "\\" + strFilePrefix + ".xml");
			out = new PrintWriter(new BufferedWriter(fostream));
			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			out.println("<Bin-code>");

			List<String> answers = new ArrayList<String>();
			int i = 0;

			for (Row row0 : sheet) {
				int numCol = row0.getLastCellNum();
				for (int j = 0; j <= numCol; j++) {
					String temp = formatCell(row0.getCell(i));
					// do whatever initialization you need here
					answers.add(temp);
					i++;
				}
				break;
			}
			// System.out.println(answers);
			int count = 0;
			boolean firstRow = true;
			for (Row row : sheet) {
				if (firstRow == true) {
					firstRow = false;
					continue;
				}
				out.println("\t<DCT>");
				int numCol1 = row.getLastCellNum();
				for (int k = 0; k < numCol1; k++) {
					if (answers.get(k).equalsIgnoreCase("dt_app_id")) {

						int m = Integer.parseInt(
								dataFormatter.formatCellValue(row.getCell(k)).toString())
								+ count;

						// int s =
						// (Integer.parseInt(dataFormatter.formatCellValue(row.getCell(k)).toString()))+m;
						String a = Integer.toString(m);
						out.println(formatElement("\t\t", answers.get(k), a));
						count++;
						continue;
					} else if (answers.get(k).equalsIgnoreCase("housing_status")) {
						out.println(formatElements("\t\t", answers.get(k) + " " + "type"
								+ "=" + dataFormatter.formatCellValue(row.getCell(k))));
						continue;
					} else if (answers.get(k).equalsIgnoreCase("salary")) {
						out.println(formatElementss("\t\t",
								answers.get(k) + " " + "type" + "=" + "Monthly",
								dataFormatter.formatCellValue(row.getCell(k))));
						break;
					} else if (answers.get(k).equalsIgnoreCase("ttl_estimate")) {
						String value = dataFormatter.formatCellValue(row.getCell(k));
						StringBuilder cellValue = new StringBuilder(value);
						cellValue.setCharAt(1, 's');
						out.println(
								formatElements("\t\t", answers.get(k) + " " + cellValue));

					}

					out.println(formatElement("\t\t", answers.get(k),
							dataFormatter.formatCellValue(row.getCell(k))));
				}
				out.println("\t</DCT>");
			}
			out.write("</Bin-code>");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		XLSXtoXML xLSXtoXML = new XLSXtoXML();
		String Body = xLSXtoXML.EncodedUrl("src//test//resources//Master.xml");
		BufferedWriter bw = null;
		FileWriter fw = null;

		File f = new File("src//test//resources//products.txt");
		fw = new FileWriter(f);
		bw = new BufferedWriter(fw);
		bw.write("XML=" + Body);
		// System.out.println("Done");
		bw.flush();
		fw.flush();
	}
	private static String formatCell(Cell cell) {
		if (cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_BLANK :
				return "";
			case Cell.CELL_TYPE_BOOLEAN :
				return Boolean.toString(cell.getBooleanCellValue());
			case Cell.CELL_TYPE_ERROR :
				return "*error*";
			case Cell.CELL_TYPE_NUMERIC :
				return XLSXtoXML.df.format(cell.getNumericCellValue());
			case Cell.CELL_TYPE_STRING :
				return cell.getStringCellValue();
			default :
				return "<unknown value>";
		}
	}
	private static String formatElement(String prefix, String tag, String value) {
		StringBuilder sb = new StringBuilder(prefix);
		sb.append("<");
		sb.append(tag);
		if (value != null && value.length() > 0) {
			sb.append(">");
			sb.append(value);
			sb.append("</");
			sb.append(tag);
			sb.append(">");
		} else {
			sb.append("/>");
		}
		return sb.toString();
	}
	private static String formatElements(String prefix, String tagValue) {
		StringBuilder sb = new StringBuilder(prefix);
		sb.append("<");
		sb.append(tagValue);
		sb.append("/>");

		return sb.toString();
	}
	private static String formatElementss(String prefix, String tag, String value) {
		StringBuilder sb = new StringBuilder(prefix);
		sb.append("<");
		sb.append(tag);
		if (value != null && value.length() > 0) {
			sb.append(">");
			sb.append(value);
			sb.append("</");

			// int salary = tag.indexOf("salary");
			tag = tag.substring(0, 6);
			sb.append(tag);
			sb.append(">");
		} else {
			sb.append("/>");
		}
		return sb.toString();
	}
	public String EncodedUrl(String File) throws Exception {
		File xmlFile = new File(File);
		// Let's get XML file as String using BufferedReader
		// FileReader uses platform's default character encoding
		// if you need to specify a different encoding, use InputStreamReader
		Reader fileReader = new FileReader(xmlFile);
		BufferedReader bufReader = new BufferedReader(fileReader);
		StringBuilder sb = new StringBuilder();
		String line = bufReader.readLine();
		while (line != null) {
			sb.append(line).append("\n");

			line = bufReader.readLine();
		}
		String xml2String = sb.toString();

		String encodedUrl = URLEncoder.encode(xml2String, "UTF-8");
		return encodedUrl;
	}
}
