/*package org.appsquad.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.appsquad.database.DbConnection;

public class MonthListCreater {
    public static ArrayList<Integer> populateList(java.sql.Date date,java.sql.Date date1){
    	int counter = 0;
        try {
			Connection connection = DbConnection.createConnection();
			sql_connection:{
				  try {
					counter = countTotal(date, date1, connection);
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static int countTotal(java.sql.Date date1,java.sql.Date date2,Connection connection) throws Exception{
		   PreparedStatement preparedStatement = null;
		   int numbers = 0;
		   try{
			   String sql = "SELECT date_part ('year', f) * 12 as year, "
	                       +" date_part ('month', f) as month "
	                       +" FROM age ('"+date2+"'"+"::DATE, '"+date1+"'"+"::DATE) f ";
			   System.out.println(sql);
			   preparedStatement = connection.prepareStatement(sql);
			   ResultSet resultSet = preparedStatement.executeQuery();
			   while(resultSet.next()){
				  numbers = resultSet.getInt("month");
			   }
		   }finally{
			   if(preparedStatement!=null){
				   preparedStatement.close();
			   }
		   }
		 return numbers;
	   }
}
*/