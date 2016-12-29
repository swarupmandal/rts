package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.Logger;
import org.appsquad.bean.ClientUserAssignBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.ClientUserAssignSql;
import org.appsquad.utility.Pstm;
import org.zkoss.zul.Messagebox;

public class ClientUserAssignDao {
	final static Logger logger = Logger.getLogger(ClientUserAssignDao.class);
	
	public static boolean insertClientUserAssignData(ClientUserAssignBean userAssignBean){
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
							ClientUserAssignSql.insertUserClientAssignSql, Arrays.asList(userAssignBean.clientInformationBean.getClientId(),userAssignBean.userprofileBean.getUserid()));
					    	logger.info("insertClientUserAssignData- " + preparedStatementInsert.unwrap(PreparedStatement.class));
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
					if(isSaved){
						Messagebox.show(" User Saved Successfully For Client ","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" User Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
					logger.fatal(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.fatal(e);
		}
		return isSaved;
	}
	
	public static boolean deleteClientUserAssignDataDao(ClientUserAssignBean userAssignBean){
		boolean isDelete = false;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_insert:{
					    PreparedStatement preparedStatementInsert = null;
					    try {
					    	preparedStatementInsert = Pstm.createQuery(connection,ClientUserAssignSql.deleteAssignDataSql, Arrays.asList(userAssignBean.getAssignId()));
					    	logger.info("deleteClientUserAssignData- " + preparedStatementInsert.unwrap(PreparedStatement.class));
							int i = preparedStatementInsert.executeUpdate();
							if(i>0){
								isDelete = true;	
							}
						} finally{
							if(preparedStatementInsert!=null){
								preparedStatementInsert.close();
							}
						}
				    }
				
					if(isDelete){
						Messagebox.show(" User Deleted Successfully For Client ","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" User Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
					logger.fatal(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.fatal(e);
		}
		return isDelete;
	}
	
	public static Integer CountDeatils(ClientUserAssignBean bean){
		int count = 0;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ClientUserAssignSql.countUserClientMappingSql, Arrays.asList(bean.clientInformationBean.getClientId(),bean.userprofileBean.getUserid()));
						    logger.info("countClientUserAssignData- " + preparedStatement.unwrap(PreparedStatement.class));
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								count = resultSet.getInt(1);
							}  
						} finally{
							if(preparedStatement!=null){
								preparedStatement.close();
							}
						}
				    }
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
					logger.fatal(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.fatal(e);
		}
		return count;
	}
	
	
	public static ArrayList<ClientUserAssignBean> onLoadAssignDeatils(){
		ArrayList<ClientUserAssignBean> assignList = new ArrayList<ClientUserAssignBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						   preparedStatement = Pstm.createQuery(connection, ClientUserAssignSql.fetchUserClientAssignDetailsSql, null);
						   logger.info("onLoadRoleDeatils- " + preparedStatement.unwrap(PreparedStatement.class));
						   ResultSet resultSet = preparedStatement.executeQuery();
						   while (resultSet.next()) {
								ClientUserAssignBean assignBean = new ClientUserAssignBean();
								assignBean.clientInformationBean.setClientId(resultSet.getInt("client_id"));
								assignBean.clientInformationBean.setFullName(resultSet.getString("clientname"));
								assignBean.userprofileBean.setUserSerialId(resultSet.getInt("user_serial_id"));
								assignBean.userprofileBean.setUserid(resultSet.getString("user_id"));
								assignBean.setAssignId(resultSet.getInt("assign_id"));
								
								assignList.add(assignBean);
							}  
						} finally{
							if(preparedStatement!=null){
								preparedStatement.close();
							}
						}
				    }
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
					logger.fatal(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.fatal(e);
		}
		return assignList;
	}

	public static Logger getLogger() {
		return logger;
	}
}
