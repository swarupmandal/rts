package org.appsquad.dao;

import org.appsquad.bean.TaskNameBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.TaskModifySql;
import org.appsquad.utility.Pstm;
import org.zkoss.zhtml.Pre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.Arrays;

public class TaskModifyDao {

	public static int updateTask(TaskNameBean taskNameBean){
		int noOrRowsUpdated = 0;
		try {
			SQL:{
					Connection connection = DbConnection.createConnection();
					PreparedStatement preparedStatement = null;
					try {
						preparedStatement = connection.prepareStatement(TaskModifySql.updateTaskSql);
						if(taskNameBean.getRemarksOrResults()!=null){
							preparedStatement.setString(1, taskNameBean.getRemarksOrResults());
						}else{
							preparedStatement.setNull(1, Types.NULL);
						}
						if(taskNameBean.getActualCompletionDate()!=null){
							preparedStatement.setDate(2, new java.sql.Date(taskNameBean.getActualCompletionDate().getTime()));
						}else{
							preparedStatement.setNull(2, Types.NULL);	
						}
						preparedStatement.setInt(3, taskNameBean.getTaskStatusBean().getTaskStatusId());
						preparedStatement.setInt(4, taskNameBean.getRtsTaskId());
						preparedStatement.setString(5,taskNameBean.getAssignedTo());
						noOrRowsUpdated = preparedStatement.executeUpdate();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}finally{
						if(preparedStatement!=null){
							preparedStatement.close();
						}
						if(connection!=null){
							connection.close();
						}
					}
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return noOrRowsUpdated;
	}
}
