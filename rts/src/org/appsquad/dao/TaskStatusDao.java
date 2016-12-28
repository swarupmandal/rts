package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.appsquad.bean.TaskStatusBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.TaskStatusSql;
import org.appsquad.utility.Pstm;

public class TaskStatusDao {

	/**
	 * Load all task status list
	 * @author somnath dutta
	 * @return task status bean list
	 */
	public static ArrayList<TaskStatusBean> loadAllTaskStatus(){
		ArrayList<TaskStatusBean> taskStatusList = new ArrayList<TaskStatusBean>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			SQL:{
				connection = DbConnection.createConnection();
				try {
					preparedStatement = Pstm.createQuery(connection, TaskStatusSql.loadAllTaskStatus, null);
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next() ) {
						TaskStatusBean taskStatusBean = new TaskStatusBean();
						taskStatusBean.setTaskStatusId(resultSet.getInt("task_status_id"));
						taskStatusBean.setTaskStatusName(resultSet.getString("task_status_name"));
						taskStatusList.add(taskStatusBean);
					}
				} catch (Exception e) {
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
			e.printStackTrace();
		}
		return taskStatusList;
	}
}
