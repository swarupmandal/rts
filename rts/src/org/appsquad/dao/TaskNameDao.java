package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

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
					    	String sql = "INSERT INTO rts_task_details(task_name,assigned_by,assigned_to,created_date,venue,scheduled_date,actual_completion_date,status,remarks_or_results) VALUES(?,?,?,NOW(),?,?,?,?,?) ";
					    	preparedStatementInsert = connection.prepareStatement(sql);
					    	preparedStatementInsert.setString(1, taskNameBean.getTaskName().toUpperCase());
					    	preparedStatementInsert.setString(2, taskNameBean.getAssignedByUserId());
					    	preparedStatementInsert.setString(3, taskNameBean.userprofileBean.getUserid());
					    	preparedStatementInsert.setString(4, taskNameBean.getVenue());
					    	preparedStatementInsert.setDate(5, taskNameBean.getScheduledDateSql());
					    	preparedStatementInsert.setDate(6, taskNameBean.getActualCompletionDateSql());
					    	preparedStatementInsert.setString(7, taskNameBean.getStatus());
					    	preparedStatementInsert.setString(8, taskNameBean.getRemarksOrResults());
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
						    	String sql = "select * from vw_rts_task_details where assigned_to = ?";
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
						    	System.out.println("SQL FOR TODAY & TOMORROW DATA :"+sql);
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
						    	String sql = "select * from vw_rts_task_details ";
						    	System.out.println("SQL FOR Schedule DateWise Report :"+sql);
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
	   
	   
	   public static ArrayList<TaskNameBean> fetchTaskDeatilsForScheduleDateWiseReportForAssignerBy(String name){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details where assigned_by like ? ";
						    	System.out.println("SQL FOR Schedule DateWise Report :"+sql);
						    	preparedStatementFetch = connection.prepareStatement(sql);
						    	preparedStatementFetch.setString(1, "%"+name+"%");
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
	   
	   public static ArrayList<TaskNameBean> fetchTaskDeatilsForScheduleDateWiseReportForAssignerTo(String name){
		   ArrayList<TaskNameBean> detailsList = new ArrayList<TaskNameBean>();
		   try {
				 Connection connection = DbConnection.createConnection();
				 sql_connection:{
					 try {
						//insert query
						 sql:{
						    PreparedStatement preparedStatementFetch = null;
						    try{
						    	String sql = "select * from vw_rts_task_details where assigned_to like ? ";
						    	System.out.println("SQL FOR Schedule DateWise Report :"+sql);
						    	preparedStatementFetch = connection.prepareStatement(sql);
						    	preparedStatementFetch.setString(1, "%"+name+"%");
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
						    	System.out.println("SQL FOR Schedule DateWise Report :"+sql);
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
						    	String sql = "select * from vw_rts_task_details ";
						    	System.out.println("SQL FOR Schedule DateWise Report :"+sql);
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
	   
	   public static Integer calculateWeekStatusOfTask(Connection connection,String date) throws Exception{
		   int week = 0;
		   PreparedStatement preparedStatement = null;
		   try{
			   String sql = "SELECT (EXTRACT(days FROM (now() - '"+date+"')) / 7)::int ";
			   System.out.println("WEEK REPORT SQL :"+sql);
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
						    	String sql = "select * from vw_rts_task_details ";
						    	System.out.println("SQL FOR Schedule DateWise Report :"+sql);
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
	   
	   public static Integer calculateMonthStatusOfTask(Connection connection,String date) throws Exception{
		   int week = 0;
		   PreparedStatement preparedStatement = null;
		   try{
			   String sql = "SELECT (EXTRACT(days FROM (now() - '"+date+"')) / 30)::int ";
			   System.out.println("WEEK REPORT SQL :"+sql);
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
						    	System.out.println("TODAY AND TOMORROW DATE SQL :"+sql);
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
