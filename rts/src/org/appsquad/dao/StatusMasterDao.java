package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.SkillSetMasterSql;
import org.appsquad.sql.StatusMasterSql;
import org.appsquad.utility.Pstm;
import org.zkoss.zul.Messagebox;

public class StatusMasterDao {
    
	public static void insertSkillData(StatusMasterBean statusMasterBean){
		boolean isSaved = false;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_insert:{
					    PreparedStatement preparedStatementInsert = null;
					    try {
					    	preparedStatementInsert = Pstm.createQuery(connection, 
									StatusMasterSql.insertStatusQuery, Arrays.asList(statusMasterBean.getUserId(),statusMasterBean.getStatus().toUpperCase()));
					   
							int i = preparedStatementInsert.executeUpdate();
							if(i>0){
								isSaved = true;	
							}
						} finally{
							if(preparedStatementInsert!=null){
								preparedStatementInsert.close();
							}
						}
				    }
				
					if( isSaved){
						Messagebox.show(" Status Details Saved successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Status Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
	
	public static ArrayList<StatusMasterBean> onLoadStatusDeatils(){
		ArrayList<StatusMasterBean> statusList = new ArrayList<StatusMasterBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						   preparedStatement = Pstm.createQuery(connection, StatusMasterSql.fetchStatusQuery, null);
							
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								StatusMasterBean bean = new StatusMasterBean();
								bean.setStatusId(resultSet.getInt("id"));
								bean.setStatus(resultSet.getString("master_status_name"));
								
								statusList.add(bean);
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
		return statusList;
	}
	
	public static boolean deleteStatus(StatusMasterBean statusMasterBean){
		boolean isSaved = false;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_insert:{
					    PreparedStatement preparedStatementInsert = null;
					    try {
					    	preparedStatementInsert = Pstm.createQuery(connection, 
									StatusMasterSql.deleteStatusQuery, Arrays.asList(statusMasterBean.getStatusId()));
					   
							int i = preparedStatementInsert.executeUpdate();
							if(i>0){
								isSaved = true;	
							}
						} finally{
							if(preparedStatementInsert!=null){
								preparedStatementInsert.close();
							}
						}
				    }
				
					if( isSaved){
						Messagebox.show(" Status Details Deleted Successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Status Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
	
}
