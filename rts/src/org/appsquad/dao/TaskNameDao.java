package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.appsquad.bean.TaskNameBean;
import org.appsquad.database.DbConnection;
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
				    	String sql = "INSERT INTO rts_task_details(task_name,assigned_by,assigned_to,created_date) VALUES(?,?,?,NOW()) ";
				    	preparedStatementInsert = connection.prepareStatement(sql);
				    	preparedStatementInsert.setString(1, taskNameBean.getTaskName().toUpperCase());
				    	preparedStatementInsert.setString(2, taskNameBean.getAssignedByUserId());
				    	preparedStatementInsert.setString(3, taskNameBean.userprofileBean.getUserid());
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
   
}
