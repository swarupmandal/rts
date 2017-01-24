package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.appsquad.bean.TaskNameBean;
import org.appsquad.database.DbConnection;

public class CreationDateWiseReportDao {
	public static ArrayList<TaskNameBean> fetchTaskDeatilsForCreationDateWiseReportFordateRange(java.sql.Date fromDate, java.sql.Date toDate){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details where creation_date >=? and  creation_date <=? order by rts_task_id ";
						    	System.out.println("SQL FOR Schedule DateWise Report :"+sql);
						    	preparedStatementFetch = connection.prepareStatement(sql);
						    	preparedStatementFetch.setDate(1, fromDate);
						    	preparedStatementFetch.setDate(2, toDate);
						    	ResultSet resultSet = preparedStatementFetch.executeQuery();
						    	while(resultSet.next()){
						    	    TaskNameBean taskNameBean = new TaskNameBean();
						    	    taskNameBean.setRtsTaskId(resultSet.getInt("rts_task_id"));
						    	    taskNameBean.setTaskName(resultSet.getString("task_name"));
						    	    taskNameBean.setAssignedByUserId(resultSet.getString("assigned_by"));
						    	    taskNameBean.userprofileBean.setUserid(resultSet.getString("assigned_to"));
						    	    taskNameBean.setCreatedDateStr(resultSet.getString("creation_date"));
						    	    taskNameBean.setVenue(resultSet.getString("venue"));
						    	    taskNameBean.setStatus(resultSet.getString("task_status_name"));
						    	    taskNameBean.setRemarksOrResults(resultSet.getString("remarks_or_results"));
						    	    taskNameBean.setScheduledDateStr(resultSet.getString("schedl_date"));
						    	    taskNameBean.setActualCompletionDateStr(resultSet.getString("completion_date"));
						    	    taskNameBean.setTaskDescription(resultSet.getString("task_details"));
						    	    
						    	    detailsList.add(taskNameBean);
						    	}
						    }finally{
						    	if(preparedStatementFetch!=null){
						    		preparedStatementFetch.close();
						    	}
						    }
					    }
					 System.out.println(detailsList.size());
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
		  return detailsList;
	   }
	
	
	public static ArrayList<TaskNameBean> fetchTaskDeatilsForCreationDateWiseReportFordateRangeWithByTo(java.sql.Date fromDate, java.sql.Date toDate,String name,String another){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details where creation_date >=? and  creation_date <=? and assigned_by like ? and assigned_to like ? order by rts_task_id ";
						    	System.out.println("SQL FOR Schedule DateWise Report :"+sql);
						    	preparedStatementFetch = connection.prepareStatement(sql);
						    	preparedStatementFetch.setDate(1, fromDate);
						    	preparedStatementFetch.setDate(2, toDate);
						    	preparedStatementFetch.setString(3, "%"+name+"%");
						    	preparedStatementFetch.setString(4, "%"+another+"%");
						    	System.out.println(preparedStatementFetch);
						    	ResultSet resultSet = preparedStatementFetch.executeQuery();
						    	while(resultSet.next()){
						    	    TaskNameBean taskNameBean = new TaskNameBean();
						    	    taskNameBean.setRtsTaskId(resultSet.getInt("rts_task_id"));
						    	    taskNameBean.setTaskName(resultSet.getString("task_name"));
						    	    taskNameBean.setAssignedByUserId(resultSet.getString("assigned_by"));
						    	    taskNameBean.userprofileBean.setUserid(resultSet.getString("assigned_to"));
						    	    taskNameBean.setCreatedDateStr(resultSet.getString("creation_date"));
						    	    taskNameBean.setVenue(resultSet.getString("venue"));
						    	    taskNameBean.setStatus(resultSet.getString("task_status_name"));
						    	    taskNameBean.setRemarksOrResults(resultSet.getString("remarks_or_results"));
						    	    taskNameBean.setScheduledDateStr(resultSet.getString("schedl_date"));
						    	    taskNameBean.setActualCompletionDateStr(resultSet.getString("completion_date"));
						    	    taskNameBean.setTaskDescription(resultSet.getString("task_details"));
						    	    
						    	    detailsList.add(taskNameBean);
						    	}
						    }finally{
						    	if(preparedStatementFetch!=null){
						    		preparedStatementFetch.close();
						    	}
						    }
					    }
					 System.out.println(detailsList.size());
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
		  return detailsList;
	   }
	
	   
	 public static ArrayList<TaskNameBean> fetchTaskDeatilsForCreationDateWiseReportForAssignerBy(String name,java.sql.Date fromDate, java.sql.Date toDate){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details where assigned_by like ? and creation_date >=? and  creation_date <=? order by rts_task_id ";
						    	System.out.println("SQL FOR Schedule DateWise Report :"+sql);
						    	preparedStatementFetch = connection.prepareStatement(sql);
						    	preparedStatementFetch.setString(1, "%"+name+"%");
						    	preparedStatementFetch.setDate(2, fromDate);
						    	preparedStatementFetch.setDate(3, toDate);
						    	ResultSet resultSet = preparedStatementFetch.executeQuery();
						    	while(resultSet.next()){
						    	    TaskNameBean taskNameBean = new TaskNameBean();
						    	    taskNameBean.setRtsTaskId(resultSet.getInt("rts_task_id"));
						    	    taskNameBean.setTaskName(resultSet.getString("task_name"));
						    	    taskNameBean.setAssignedByUserId(resultSet.getString("assigned_by"));
						    	    taskNameBean.userprofileBean.setUserid(resultSet.getString("assigned_to"));
						    	    taskNameBean.setCreatedDateStr(resultSet.getString("creation_date"));
						    	    taskNameBean.setVenue(resultSet.getString("venue"));
						    	    taskNameBean.setStatus(resultSet.getString("task_status_name"));
						    	    taskNameBean.setRemarksOrResults(resultSet.getString("remarks_or_results"));
						    	    taskNameBean.setScheduledDateStr(resultSet.getString("schedl_date"));
						    	    taskNameBean.setActualCompletionDateStr(resultSet.getString("completion_date"));
						    	    taskNameBean.setTaskDescription(resultSet.getString("task_details"));
						    	    
						    	    detailsList.add(taskNameBean);
						    	}
						    }finally{
						    	if(preparedStatementFetch!=null){
						    		preparedStatementFetch.close();
						    	}
						    }
					    }
					 System.out.println(detailsList.size());
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
		  return detailsList;
	   }
	 
	 
	 public static ArrayList<TaskNameBean> fetchTaskDeatilsForCreationDateWiseReportForAssignerTo(String name,java.sql.Date fromDate, java.sql.Date toDate){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details where assigned_to like ? and creation_date >=? and  creation_date <=? order by rts_task_id ";
						    	System.out.println("SQL FOR Schedule DateWise Report :"+sql);
						    	preparedStatementFetch = connection.prepareStatement(sql);
						    	preparedStatementFetch.setString(1, "%"+name+"%");
						    	preparedStatementFetch.setDate(2, fromDate);
						    	preparedStatementFetch.setDate(3, toDate);
						    	ResultSet resultSet = preparedStatementFetch.executeQuery();
						    	while(resultSet.next()){
						    	    TaskNameBean taskNameBean = new TaskNameBean();
						    	    taskNameBean.setRtsTaskId(resultSet.getInt("rts_task_id"));
						    	    taskNameBean.setTaskName(resultSet.getString("task_name"));
						    	    taskNameBean.setAssignedByUserId(resultSet.getString("assigned_by"));
						    	    taskNameBean.userprofileBean.setUserid(resultSet.getString("assigned_to"));
						    	    taskNameBean.setCreatedDateStr(resultSet.getString("creation_date"));
						    	    taskNameBean.setVenue(resultSet.getString("venue"));
						    	    taskNameBean.setStatus(resultSet.getString("task_status_name"));
						    	    taskNameBean.setRemarksOrResults(resultSet.getString("remarks_or_results"));
						    	    taskNameBean.setScheduledDateStr(resultSet.getString("schedl_date"));
						    	    taskNameBean.setActualCompletionDateStr(resultSet.getString("completion_date"));
						    	    taskNameBean.setTaskDescription(resultSet.getString("task_details"));
						    	    
						    	    detailsList.add(taskNameBean);
						    	}
						    }finally{
						    	if(preparedStatementFetch!=null){
						    		preparedStatementFetch.close();
						    	}
						    }
					    }
					 System.out.println(detailsList.size());
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
		  return detailsList;
	   }
}
