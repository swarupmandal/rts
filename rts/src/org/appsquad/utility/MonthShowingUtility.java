package org.appsquad.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import org.appsquad.bean.CurrentOpportunitiesReportGenerationBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.CurrentOpportunitiesReportGenerationSql;

public class MonthShowingUtility {
	   public static int countTotal(java.util.Date date1,java.util.Date date2,Connection connection) throws Exception{
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
	   
	   public static String monthName(java.util.Date date1,Connection connection,Integer rtsId) throws Exception{
		   PreparedStatement preparedStatement = null;
		   String name = "";
		   try{
			   String sql = "select to_char(tenure_from, 'month') from rts_opportunitywise_visibility where rts_tracking_details_id = "+ rtsId+"";
			   preparedStatement = connection.prepareStatement(sql);
			   ResultSet resultSet = preparedStatement.executeQuery();
			   while(resultSet.next()){
				  name = resultSet.getString(1);
			   }
		   }finally{
			   if(preparedStatement!=null){
				   preparedStatement.close();
			   }
		   }
		 return name;
	   }
	   
	   public static String fetchresourceName(CurrentOpportunitiesReportGenerationBean bean){
			String resourceName = "";
			Connection connection = null;
			try {
				connection = DbConnection.createConnection();
				sql_connection:{
					try {
						
						//1st SQL block
						sql_fetch:{
						   PreparedStatement preparedStatement = null;
						   try {
							    preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesReportGenerationSql.fetchResourceNameInCurrentOppurtunityScreenSql, Arrays.asList(bean.getRtsTrackingDetailsId()));
							    ResultSet resultSet = preparedStatement.executeQuery();
								while (resultSet.next()) {
									resourceName = resultSet.getString("resource_name");
								}  
							} finally{
								if(preparedStatement!=null){
									preparedStatement.close();
								}
							}
					    }
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
			return resourceName;
		}
	   
	   public static void fetchdateWrtRtrackingID(CurrentOpportunitiesReportGenerationBean bean){
			Connection connection = null;
			try {
				connection = DbConnection.createConnection();
				sql_connection:{
					try {
						//1st SQL block
						sql_fetch:{
						   PreparedStatement preparedStatement = null;
						   try {
							    preparedStatement = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.fetchDateInCurrentOppurtunityScreenSql,Arrays.asList(bean.getRtsTrackingDetailsId()));
								ResultSet resultSet = preparedStatement.executeQuery();
								while (resultSet.next()) {
									bean.getCurrentOpportunitiesBean().setTentureFromUtil(resultSet.getDate("tenure_from"));
									bean.getCurrentOpportunitiesBean().setTentureToUtil(resultSet.getDate("tenure_to"));
								}  
							} finally{
								if(preparedStatement!=null){
									preparedStatement.close();
								}
							}
					    }
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
}
