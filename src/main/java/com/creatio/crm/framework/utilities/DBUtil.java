package com.creatio.crm.framework.utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DBUtil {	
	
	public static Properties prop;
	static {
		try {
			prop = PropUtil.readData("config.properties");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

// Prepare DB Connection and get the raw data
	public static ResultSet getData(String query) throws SQLException {
		ResultSet dataSet = null;
		String url = prop.getProperty("DB_URL");
		String username = prop.getProperty("DB_USERNAME");
		String password = prop.getProperty("DB_PASSWORD");		
		Connection connection = DriverManager.getConnection(url, username, password);
		dataSet =connection.createStatement().executeQuery(query);		
		return dataSet;
	}
	
	// Common method to convert the raw data into proper collections-related data structure.
	public static List<Map<String,String>> readData(String query) throws SQLException{
		List<Map<String,String>> data = new ArrayList<Map<String,String>>();
		
		//Get the raw data from the database
		ResultSet dataSet =getData(query);
		
		//Convert the data into a list of maps.
		while (dataSet.next()) { //if next row available-go to next row . else-end the iterations
			
			//Create an empty map to store each raw data.
			Map<String,String> rowData = new HashMap<String,String>(); 
			
			//By using for loop, collect each and every cell data within the row.
			for(int c=1; c<=dataSet.getMetaData().getColumnCount(); c++) {				
				String columnName = dataSet.getMetaData().getColumnName(c);
				String columnValue = dataSet.getString(c);
				rowData.put(columnName, columnValue);				
			}
			
			//Add each row data into the main list (data).
			data.add(rowData);
			
		}
		
		return data;		
	}
	

}