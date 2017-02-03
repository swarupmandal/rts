package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.appsquad.bean.TaskNameBean;
import org.appsquad.bean.TaskStatusBean;
import org.appsquad.database.DbConnection;
import org.appsquad.utility.MonthPdfComparator;
import org.appsquad.utility.ScheduleDateComparator;
import org.appsquad.utility.WeekPdfComparator;
import org.zkoss.zul.Messagebox;

public class TaskNameDao {
	   public static boolean saveTaskDetailsDao(TaskNameBean taskNameBean){
		   boolean isSaved = false;
		   try {
			 Connection connection = DbConnection.createConnection();
			 sql_connection:{
				 try {
					//insert query
					 sql:{
					    PreparedStatement preparedStatementInsert = null;
					    try{
					    	String sql = "INSERT INTO rts_task_details(task_name,assigned_by,assigned_to,created_date,venue,scheduled_date,actual_completion_date,status,remarks_or_results,task_details) VALUES(?,?,?,NOW(),?,?,?,?,?,?) ";
					    	preparedStatementInsert = connection.prepareStatement(sql);
					    	preparedStatementInsert.setString(1, taskNameBean.getTaskName());
					    	preparedStatementInsert.setString(2, taskNameBean.getAssignedByUserId());
					    	preparedStatementInsert.setString(3, taskNameBean.userprofileBean.getUserid());
					    	preparedStatementInsert.setString(4, taskNameBean.getVenue());
					    	preparedStatementInsert.setDate(5, taskNameBean.getScheduledDateSql());
					    	preparedStatementInsert.setDate(6, taskNameBean.getActualCompletionDateSql());
					    	preparedStatementInsert.setString(7, taskNameBean.getStatus());
					    	preparedStatementInsert.setString(8, taskNameBean.getRemarksOrResults());
					    	preparedStatementInsert.setString(9, taskNameBean.getTaskDescription());
					    	int i = preparedStatementInsert.executeUpdate();
					    	if(i>0){
					    		isSaved = true;
					    	}else{
					    		isSaved = false;
					    	}
					    }finally{
					    	if(preparedStatementInsert!=null){
					    		preparedStatementInsert.close();
					    	}
					    }
				    }
				 
				    if(isSaved){
						Messagebox.show(" Task Deatils Created Successfully ","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Task Deatils failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
		 return isSaved;
	   }
	   
	   public static ArrayList<TaskNameBean> fetchTaskDeatils(){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details ";
						    	preparedStatementFetch = connection.prepareStatement(sql);
						    	ResultSet resultSet = preparedStatementFetch.executeQuery();
						    	while(resultSet.next()){
						    	    TaskNameBean taskNameBean = new TaskNameBean();
						    	    taskNameBean.setRtsTaskId(resultSet.getInt("rts_task_id"));
						    	    taskNameBean.setTaskName(resultSet.getString("task_name"));
						    	    taskNameBean.setAssignedByUserId(resultSet.getString("assigned_by"));
						    	    taskNameBean.userprofileBean.setUserid(resultSet.getString("assigned_to"));
						    	    taskNameBean.setCreatedDateStr(resultSet.getString("creation_date"));
						    	    taskNameBean.setVenue(resultSet.getString("venue"));
						    	    taskNameBean.setStatus(resultSet.getString("status"));
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
	   
	   public static ArrayList<TaskNameBean> fetchAssignedTaskDeatils(String userId){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details where assigned_to = ?"
						    			+ " or assigned_by = ? order by rts_task_id";
						    	preparedStatementFetch = connection.prepareStatement(sql);
						    	preparedStatementFetch.setString(1, userId);
						    	preparedStatementFetch.setString(2, userId);
						    	ResultSet resultSet = preparedStatementFetch.executeQuery();
						    	while(resultSet.next()){
						    	    TaskNameBean taskNameBean = new TaskNameBean();
						    	    taskNameBean.setRtsTaskId(resultSet.getInt("rts_task_id"));
						    	    taskNameBean.setTaskName(resultSet.getString("task_name"));
						    	    taskNameBean.setAssignedByUserId(resultSet.getString("assigned_by"));
						    	    taskNameBean.userprofileBean.setUserid(resultSet.getString("assigned_to"));
						    	    taskNameBean.setAssignedTo(resultSet.getString("assigned_to"));
						    	    taskNameBean.setCreatedDateStr(resultSet.getString("creation_date"));
						    	    taskNameBean.setCreatedDate(resultSet.getDate("created_date"));
						    	    taskNameBean.setScheduledDate(resultSet.getDate("schedl_date"));
						    	    taskNameBean.setVenue(resultSet.getString("venue"));
						    	    TaskStatusBean statusBean = new TaskStatusBean();
						    	    statusBean.setTaskStatusId(resultSet.getInt("task_status_id"));
						    	    statusBean.setTaskStatusName(resultSet.getString("task_status_name"));
						    	    taskNameBean.setTaskStatusBean(statusBean);
						    	    taskNameBean.setStatus(resultSet.getString("status"));
						    	    taskNameBean.setRemarksOrResults(resultSet.getString("remarks_or_results"));
						    	    taskNameBean.setScheduledDateStr(resultSet.getString("schedl_date"));
						    	    taskNameBean.setActualCompletionDateStr(resultSet.getString("completion_date"));
						    	    taskNameBean.setActualCompletionDate(resultSet.getDate("completion_date"));
						    	    taskNameBean.setTaskDescription(resultSet.getString("task_details"));
						    	    if(!(statusBean.getTaskStatusId()==4)){
							    	    detailsList.add(taskNameBean);	
						    	    }
						    	}
						    }finally{
						    	if(preparedStatementFetch!=null){
						    		preparedStatementFetch.close();
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
		  return detailsList;
	   }
	   
	   
	   public static ArrayList<TaskNameBean> fetchNewlyCreatedTaskDeatils(String userId){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details where assigned_by = ? "
						    			+ "order by rts_task_id ";
						    	preparedStatementFetch = connection.prepareStatement(sql);
						    	preparedStatementFetch.setString(1, userId);
						    	
						    	ResultSet resultSet = preparedStatementFetch.executeQuery();
						    	while(resultSet.next()){
						    	    TaskNameBean taskNameBean = new TaskNameBean();
						    	    taskNameBean.setRtsTaskId(resultSet.getInt("rts_task_id"));
						    	    taskNameBean.setTaskName(resultSet.getString("task_name"));
						    	    taskNameBean.setAssignedByUserId(resultSet.getString("assigned_by"));
						    	    taskNameBean.userprofileBean.setUserid(resultSet.getString("assigned_to"));
						    	    taskNameBean.setAssignedTo(resultSet.getString("assigned_to"));
						    	    taskNameBean.setCreatedDateStr(resultSet.getString("creation_date"));
						    	    taskNameBean.setCreatedDate(resultSet.getDate("created_date"));
						    	    taskNameBean.setScheduledDate(resultSet.getDate("schedl_date"));
						    	    taskNameBean.setVenue(resultSet.getString("venue"));
						    	    TaskStatusBean statusBean = new TaskStatusBean();
						    	    statusBean.setTaskStatusId(resultSet.getInt("task_status_id"));
						    	    statusBean.setTaskStatusName(resultSet.getString("task_status_name"));
						    	    taskNameBean.setTaskStatusBean(statusBean);
						    	    taskNameBean.setStatus(resultSet.getString("status"));
						    	    taskNameBean.setRemarksOrResults(resultSet.getString("remarks_or_results"));
						    	    taskNameBean.setScheduledDateStr(resultSet.getString("schedl_date"));
						    	    taskNameBean.setActualCompletionDateStr(resultSet.getString("completion_date"));
						    	    taskNameBean.setActualCompletionDate(resultSet.getDate("completion_date"));
						    	    taskNameBean.setTaskDescription(resultSet.getString("task_details"));
						    	    detailsList.add(taskNameBean);
						    	}
						    }finally{
						    	if(preparedStatementFetch!=null){
						    		preparedStatementFetch.close();
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
		  return detailsList;
	   }
	   
	   public static String getSplitedDateCode(String date){
			String[] split = date.split(",");
			String code = "";
			for(int i =0; i<split.length; i++){
				code += "'"+split[i]+"',";
			}
			code = code.substring(0, code.length()-1);
			return code;
	   }
	   
	   public static ArrayList<TaskNameBean> fetchTaskDeatilsForTodayAndTomorrow(String dateCode){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details where schedl_date IN  ( "+ getSplitedDateCode(dateCode) +") ";
						    	
						    	preparedStatementFetch = connection.prepareStatement(sql);
						    	ResultSet resultSet = preparedStatementFetch.executeQuery();
						    	while(resultSet.next()){
						    	    TaskNameBean taskNameBean = new TaskNameBean();
						    	    taskNameBean.setRtsTaskId(resultSet.getInt("rts_task_id"));
						    	    taskNameBean.setTaskName(resultSet.getString("task_name"));
						    	    taskNameBean.setAssignedByUserId(resultSet.getString("assigned_by"));
						    	    taskNameBean.userprofileBean.setUserid(resultSet.getString("assigned_to"));
						    	    taskNameBean.setCreatedDateStr(resultSet.getString("creation_date"));
						    	    taskNameBean.setVenue(resultSet.getString("venue"));
						    	    taskNameBean.setStatus(resultSet.getString("status"));
						    	    taskNameBean.setRemarksOrResults(resultSet.getString("remarks_or_results"));
						    	    taskNameBean.setScheduledDateStr(resultSet.getString("schedl_date"));
						    	    taskNameBean.setScheduledDateSql(resultSet.getDate("schedl_date"));
						    	    taskNameBean.setActualCompletionDateStr(resultSet.getString("completion_date"));
						    	    
						    	    detailsList.add(taskNameBean);
						    	    Collections.sort(detailsList, new ScheduleDateComparator());
						    	}
						    }finally{
						    	if(preparedStatementFetch!=null){
						    		preparedStatementFetch.close();
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
		  return detailsList;
	   }
	   
	   public static ArrayList<TaskNameBean> fetchTaskDeatilsForScheduleDateWiseReport(){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details order by rts_task_id ";
						    	
						    	preparedStatementFetch = connection.prepareStatement(sql);
						    	ResultSet resultSet = preparedStatementFetch.executeQuery();
						    	while(resultSet.next()){
						    	    TaskNameBean taskNameBean = new TaskNameBean();
						    	    taskNameBean.setRtsTaskId(resultSet.getInt("rts_task_id"));
						    	    taskNameBean.setTaskName(resultSet.getString("task_name"));
						    	    taskNameBean.setAssignedByUserId(resultSet.getString("assigned_by"));
						    	    taskNameBean.userprofileBean.setUserid(resultSet.getString("assigned_to"));
						    	    taskNameBean.setCreatedDateStr(resultSet.getString("creation_date"));
						    	    taskNameBean.setCreatedDateSql(resultSet.getDate("creation_date"));
						    	    taskNameBean.setVenue(resultSet.getString("venue"));
						    	    taskNameBean.setStatus(resultSet.getString("task_status_name"));
						    	    taskNameBean.setRemarksOrResults(resultSet.getString("remarks_or_results"));
						    	    taskNameBean.setScheduledDateStr(resultSet.getString("schedl_date"));
						    	    taskNameBean.setScheduledDateSql(resultSet.getDate("schedl_date"));
						    	    taskNameBean.setActualCompletionDateStr(resultSet.getString("completion_date"));
						    	    taskNameBean.setActualCompletionDate(resultSet.getDate("completion_date"));
						    	    taskNameBean.setTaskDescription(resultSet.getString("task_details"));
						    	    
						    	    detailsList.add(taskNameBean);
						    	}
						    }finally{
						    	if(preparedStatementFetch!=null){
						    		preparedStatementFetch.close();
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
		  return detailsList;
	   }
	   
	   
	   public static ArrayList<TaskNameBean> fetchTaskDeatilsForScheduleDateWiseReportForAssignerBy(String name,java.sql.Date fromDate, java.sql.Date toDate){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details where assigned_by like ? and scheduled_date >=? and  scheduled_date <=? order by rts_task_id ";
						    	
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
	   
	   public static ArrayList<TaskNameBean> fetchTaskDeatilsForScheduleDateWiseReportForAssignerTo(String name,java.sql.Date fromDate, java.sql.Date toDate){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details where assigned_to like ? and scheduled_date >=? and  scheduled_date <=? order by rts_task_id ";
						    	
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
	   
	   
	   public static ArrayList<TaskNameBean> fetchTaskDeatilsForScheduleDateWiseReportFordateRange(java.sql.Date fromDate, java.sql.Date toDate){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details where scheduled_date >=? and  scheduled_date <=? order by rts_task_id ";
						    	
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
						    	    taskNameBean.setCreatedDateSql(resultSet.getDate("creation_date"));
						    	    taskNameBean.setVenue(resultSet.getString("venue"));
						    	    taskNameBean.setStatus(resultSet.getString("task_status_name"));
						    	    taskNameBean.setRemarksOrResults(resultSet.getString("remarks_or_results"));
						    	    taskNameBean.setScheduledDateStr(resultSet.getString("schedl_date"));
						    	    taskNameBean.setScheduledDateSql(resultSet.getDate("schedl_date"));
						    	    taskNameBean.setActualCompletionDateStr(resultSet.getString("completion_date"));
						    	    taskNameBean.setActualCompletionDateSql(resultSet.getDate("completion_date"));
						    	    taskNameBean.setTaskDescription(resultSet.getString("task_details"));
						    	    
						    	    detailsList.add(taskNameBean);
						    	}
						    }finally{
						    	if(preparedStatementFetch!=null){
						    		preparedStatementFetch.close();
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
		  return detailsList;
	   }
	   
	   
	   public static ArrayList<TaskNameBean> fetchTaskDeatilsForScheduleDateWiseReportFordateRangeWithByTo(java.sql.Date fromDate, java.sql.Date toDate,String name,String another){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details where scheduled_date >=? and  scheduled_date <=? and assigned_by like ? and assigned_to like ? order by rts_task_id ";
						    	
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

	   
	   public static ArrayList<TaskNameBean> fetchDetailsForByTo(String name,String another){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details where assigned_to like ? and assigned_by like ? ";
						    	
						    	preparedStatementFetch = connection.prepareStatement(sql);
						    	preparedStatementFetch.setString(1, "%"+name+"%");
						    	preparedStatementFetch.setString(2, "%"+another+"%");
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
	   
	   
	   public static ArrayList<TaskNameBean> fetchTaskDeatilsForWeeklyReport(){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details order by rts_task_id ";
						    	
						    	preparedStatementFetch = connection.prepareStatement(sql);
						    	ResultSet resultSet = preparedStatementFetch.executeQuery();
						    	while(resultSet.next()){
						    	    TaskNameBean taskNameBean = new TaskNameBean();
						    	    taskNameBean.setRtsTaskId(resultSet.getInt("rts_task_id"));
						    	    taskNameBean.setTaskName(resultSet.getString("task_name"));
						    	    taskNameBean.setAssignedByUserId(resultSet.getString("assigned_by"));
						    	    taskNameBean.userprofileBean.setUserid(resultSet.getString("assigned_to"));
						    	    taskNameBean.setCreatedDateStr(resultSet.getString("creation_date"));
						    	    taskNameBean.setCreatedDateSql(resultSet.getDate("creation_date"));
						    	    taskNameBean.setVenue(resultSet.getString("venue"));
						    	    taskNameBean.setStatus(resultSet.getString("task_status_name"));
						    	    taskNameBean.setRemarksOrResults(resultSet.getString("remarks_or_results"));
						    	    taskNameBean.setScheduledDateStr(resultSet.getString("schedl_date"));
						    	    taskNameBean.setScheduledDateSql(resultSet.getDate("schedl_date"));
						    	    taskNameBean.setActualCompletionDateStr(resultSet.getString("completion_date"));
						    	    taskNameBean.setActualCompletionDate(resultSet.getDate("completion_date"));
						    	    taskNameBean.setTaskDescription(resultSet.getString("task_details"));
						    	    System.out.println(taskNameBean.getScheduledDateStr());
						    	    int week = TaskNameDao.calculateWeekStatusOfTask(connection, taskNameBean.getScheduledDateStr());
						    	    System.out.println("CALCULATING WEEK IS :"+week);
						    	    if(week<0 || week==0){
						    	    	taskNameBean.setWeek(0);
						    	    }else{
						    	    	taskNameBean.setWeek(week);
						    	    	detailsList.add(taskNameBean);
						    	    }	
						    	    
						    	    //This method is used to sort the detailsList using WeekPdfComparator().
						    	    Collections.sort(detailsList, new WeekPdfComparator());
						    	}
						    }finally{
						    	if(preparedStatementFetch!=null){
						    		preparedStatementFetch.close();
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
		  return detailsList;
	   }
	   
	   public static Integer calculateWeekStatusOfTask(Connection connection,String date) throws Exception{
		   int week = 0;
		   PreparedStatement preparedStatement = null;
		   try{
			   String sql = "SELECT (EXTRACT(days FROM (now() - '"+date+"')) / 7)::int ";
			   
			   preparedStatement = connection.prepareStatement(sql);
			   ResultSet resultSet = preparedStatement.executeQuery();
			   while(resultSet.next()){
				   week = resultSet.getInt(1);
			   }
		   }finally{
			   if(preparedStatement!=null){
				   preparedStatement.close();
			   }
		   }
		return week;
	   }
	   
	   public static ArrayList<TaskNameBean> fetchTaskDeatilsForMonthlyReport(){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details order by rts_task_id ";
						    	
						    	preparedStatementFetch = connection.prepareStatement(sql);
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
						    	    int month = TaskNameDao.calculateMonthStatusOfTask(connection, taskNameBean.getScheduledDateStr());
						    	    System.out.println("CALCULATING MONTH IS :"+month);
						    	    if(month<0 || month==0){
						    	    	taskNameBean.setMonth(0);
						    	    }else{
						    	    	taskNameBean.setMonth(month);
						    	    	detailsList.add(taskNameBean);
						    	    }
						    	    //This method is used to sort the detailsList using MonthPdfComparator().
						    	    Collections.sort(detailsList, new MonthPdfComparator());
						    	}
						    }finally{
						    	if(preparedStatementFetch!=null){
						    		preparedStatementFetch.close();
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
		  return detailsList;
	   }
	   
	   public static Integer calculateMonthStatusOfTask(Connection connection,String date) throws Exception{
		   int week = 0;
		   PreparedStatement preparedStatement = null;
		   try{
			   String sql = "SELECT (EXTRACT(days FROM (now() - '"+date+"')) / 30)::int ";
			  
			   preparedStatement = connection.prepareStatement(sql);
			   ResultSet resultSet = preparedStatement.executeQuery();
			   while(resultSet.next()){
				   week = resultSet.getInt(1);
			   }
		   }finally{
			   if(preparedStatement!=null){
				   preparedStatement.close();
			   }
		   }
		return week;
	   }
	   
	   public static String createdDateString(){
		   String dateString = "";
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select date(TIMESTAMP 'today') "
						    			    +"UNION ALL "
						    			    +"select date(TIMESTAMP 'tomorrow') ";
						    	
						    	preparedStatementFetch = connection.prepareStatement(sql);
						    	ResultSet resultSet = preparedStatementFetch.executeQuery();
						    	while(resultSet.next()){
						    	   dateString = dateString+resultSet.getString("date")+",";
						    	}
						    }finally{
						    	if(preparedStatementFetch!=null){
						    		preparedStatementFetch.close();
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
		  return dateString;
	   }
	   
	}
