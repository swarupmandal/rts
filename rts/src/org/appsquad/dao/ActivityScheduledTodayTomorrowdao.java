package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

import org.appsquad.bean.TaskNameBean;
import org.appsquad.database.DbConnection;
import org.appsquad.utility.ScheduleDateComparator;

public class ActivityScheduledTodayTomorrowdao {
      
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
						    	String sql = "select * from vw_rts_task_details where schedl_date IN  ( "+ getSplitedDateCode(dateCode) +") order by rts_task_id ";
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
						    	    taskNameBean.setStatus(resultSet.getString("task_status_name"));
						    	    taskNameBean.setRemarksOrResults(resultSet.getString("remarks_or_results"));
						    	    taskNameBean.setScheduledDateStr(resultSet.getString("schedl_date"));
						    	    taskNameBean.setScheduledDateSql(resultSet.getDate("schedl_date"));
						    	    taskNameBean.setActualCompletionDateStr(resultSet.getString("completion_date"));
						    	    taskNameBean.setTaskDescription(resultSet.getString("task_details"));
						    	    
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
	
}
