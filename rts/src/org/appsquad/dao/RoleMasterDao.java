package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.appsquad.bean.RoleMasterBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.RoleMasterSql;
import org.appsquad.utility.Pstm;
import org.zkoss.zul.Messagebox;

public class RoleMasterDao {
	
	public static void insertSkillData(RoleMasterBean roleMasterBean){
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
									RoleMasterSql.insertRoleQuery, Arrays.asList(roleMasterBean.getRoll().toUpperCase(),roleMasterBean.getUserid().toUpperCase()));
					   
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
						Messagebox.show(" Role Details Saved successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Role Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
	
	public static ArrayList<RoleMasterBean> onLoadRoleDeatils(){
		ArrayList<RoleMasterBean> roleList = new ArrayList<RoleMasterBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						   preparedStatement = Pstm.createQuery(connection, RoleMasterSql.fetchRoleQuery, null);
							
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								RoleMasterBean bean = new RoleMasterBean();
								bean.setRollId(resultSet.getInt("id"));
								bean.setRoll(resultSet.getString("master_role_name"));
								
								roleList.add(bean);
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
		return roleList;
	}
	
	public static void updateRoleData(RoleMasterBean roleMasterBean){
		boolean isUpdate= false;
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
									RoleMasterSql.updateRoleQuery, Arrays.asList(roleMasterBean.getRoll().toUpperCase(),roleMasterBean.getRollId()));
					   
							int i = preparedStatementInsert.executeUpdate();
							if(i>0){
								isUpdate = true;	
							}
						} finally{
							if(preparedStatementInsert!=null){
								preparedStatementInsert.close();
							}
						}
				    }
					if( isUpdate){
						Messagebox.show(" Role Details Updated successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Role Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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

	public static void deleteRoleData(RoleMasterBean roleMasterBean){
		boolean isDeleted= false;
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
									RoleMasterSql.deleteSql, Arrays.asList(roleMasterBean.getRollId()));
					   
							int i = preparedStatementInsert.executeUpdate();
							if(i>0){
								isDeleted = true;	
							}
						} finally{
							if(preparedStatementInsert!=null){
								preparedStatementInsert.close();
							}
						}
				    }
					if(isDeleted){
						Messagebox.show(" Role Details Deleted successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Role Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
