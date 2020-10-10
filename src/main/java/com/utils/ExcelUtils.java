package com.utils;

import java.io.File;
import java.io.FileInputStream;	
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ExcelUtils {
	
	private static XSSFWorkbook wb;
	private static XSSFSheet sheet;
	private static DataFormatter formatter = new DataFormatter();
	
	/**
	 * Use this method to return the data as String from Excel
	 * @param filePath  excel file path
	 * @param sheetName sheet name in xlsx file
	 * @return excel data
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public static Object[][] readExcel(String filePath, String sheetName) throws InvalidFormatException, IOException {
		FileInputStream file = new FileInputStream(filePath);
		wb = new XSSFWorkbook(file);
		sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		int column = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rowCount][column];
		for (int i = 1; i <= rowCount; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < column; j++) {
				XSSFCell cell = row.getCell(j);
				String val = formatter.formatCellValue(cell);
				data[i - 1][j] = val;
			}
		}
		return data;
	}
	
	/**
	 * Convert List of Map into Two Dimensional Array
	 * @param interimResults
	 * @return Object[][]
	 */
	 private static Object[][] asTwoDimensionalArray(List<Map<String, String>> interimResults) {
		    Object[][] results = new Object[interimResults.size()][1];
		    int index = 0;
		    for (Map<String, String> interimResult : interimResults) {
		      results[index++] = new Object[] {interimResult};
		    }
		    return results;
		  }

		  private static Map<String, String> transform(List<String> names, List<String> values) {
		    Map<String, String> results = new HashMap<>();
		    for (int i = 0; i < names.size(); i++) {
		      String key = names.get(i);
		      String value = values.get(i);
		      results.put(key, value);
		    }
		    return results;
		  }

		  private static List<String> getValuesInEachRow(Row row) {
		    List<String> data = new ArrayList<>();
		    Iterable<Cell> columns = row::cellIterator;
		    for (Cell column : columns) {
		      data.add(formatter.formatCellValue(column));
		    }
		    return data;
		  }
		  
		  /**
		   * Use this method to return the data as Map from Excel
		   * @param filePath
		   * @param sheetName
		   * @return Object[][]
		   * @throws IOException
		   * @throws InvalidFormatException
		   */
		  public static Object[][] getData(String filePath, String sheetName) throws IOException, InvalidFormatException {
		    Workbook workbook = WorkbookFactory.create(new File(filePath));
		    Sheet sheet = workbook.getSheet(sheetName);
		    Iterable<Row> rows = sheet::rowIterator;
		    List<Map<String, String>> results = new ArrayList<>();
		    boolean header = true;
		    List<String> keys = null;
		    for (Row row : rows) {
		      List<String> values = getValuesInEachRow(row);
		      if (header) {
		        header = false;
		        keys = values;
		        continue;
		      }
		      results.add(transform(keys, values));
		    }
		    return asTwoDimensionalArray(results);
		  }
}
