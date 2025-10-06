package com.creatio.crm.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	public static String [][] readData(String workbookName, String sheetName){
		
		String [][] data = null;
		//Method 1(used for TEstNG): Read Excel data in the form of 2D array		
		try {
			
			//Read the Excel file using Java FileInputStream class.
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\"+workbookName);
			
			//Load the file into Excel related class.
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			//Reading the data for a specific sheet within the Excel workbook.
			XSSFSheet sh = wb.getSheet(sheetName);
			
			// Identify the total number of rows and columns having test data.
			int totalRows = sh.getPhysicalNumberOfRows();
			int totalColumns = sh.getRow(0).getPhysicalNumberOfCells();
			
			//Initialize the 2D array to store the data
			data = new String [totalRows-1][totalColumns];
			
			//So by using a for loop, we are going to iterate over each and every row starting from the 0th row to till the last row. 
			for(int r=1; r<totalRows ;r++) {
				
				//Similarly, inside the row, starting from the first column to the last column.
				for(int c=0; c<totalColumns;c++) {
					
					//Read the data from each and every cell, starting from the first row first column to the last row last column
					data[r-1][c] = sh.getRow(r).getCell(c).getStringCellValue();
				}
				
			}	
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return data;
		
	}
	
	
	//Method 2:(used for cucumber BDD) Read Excel data in the form of List<Map<>>
public static List<Map<String,String>> readExcelData(String workbookName, String sheetName){
		
	List<Map<String,String>> data = new ArrayList<Map<String,String>>();
				
		try {
			
			//Read the Excel file using Java FileInputStream class.
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\"+workbookName);
			
			//Load the file into Excel related class.
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			
			//Reading the data for a specific sheet within the Excel workbook.
			XSSFSheet sh = wb.getSheet(sheetName);
			
			// Identify the total number of rows and columns having test data.
			int totalRows = sh.getPhysicalNumberOfRows();
			int totalColumns = sh.getRow(0).getPhysicalNumberOfCells();
			
			//So by using a for loop, we are going to iterate over each and every row starting from the 0th row to till the last row. 
			for(int r=1; r<totalRows ;r++) {
				
				//Create empty HashMap to store each row data
				Map<String,String> rowData = new HashMap<String,String>();
				
				//Similarly, inside the row, starting from the first column to the last column.
				for(int c=0; c<totalColumns;c++) {
					
					//Read Column name and column value from specific row
					String columnName = sh.getRow(0).getCell(c).getStringCellValue();
					String columnValue = sh.getRow(r).getCell(c).getStringCellValue();
					rowData.put(columnName, columnValue);
				}
				data.add(rowData);
			}		
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return data;
		
	}
	

}