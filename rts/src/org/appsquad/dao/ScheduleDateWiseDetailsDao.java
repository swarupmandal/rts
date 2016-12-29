package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.RoleMasterSql;
import org.appsquad.utility.Pstm;

public class ScheduleDateWiseDetailsDao {
	final static Logger logger = Logger.getLogger(ScheduleDateWiseDetailsDao.class);
	
	public static ArrayList<UserprofileBean> onLoadUserDeatils(){
		ArrayList<UserprofileBean> userList = new ArrayList<UserprofileBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, RoleMasterSql.fetchUserQuery, null);
						    logger.info("onLoadUserDeatils- " + preparedStatement.unwrap(PreparedStatement.class));
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								UserprofileBean bean = new UserprofileBean();
								bean.setId(resultSet.getInt("id"));
								bean.setUserid(resultSet.getString("user_id"));
								bean.setUsername(resultSet.getString("user_name"));
								
								userList.add(bean);
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
		return userList;
	}
	
	
	public static ArrayList<UserprofileBean> onLoadUserDeatilsBy(String name){
		ArrayList<UserprofileBean> byList = new ArrayList<UserprofileBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, RoleMasterSql.fetchUserQueryForreportSql, Arrays.asList("%"+name+"%"));
						    logger.info("onLoadUserDeatils- " + preparedStatement.unwrap(PreparedStatement.class));
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								UserprofileBean bean = new UserprofileBean();
								bean.setId(resultSet.getInt("id"));
								bean.setUserid(resultSet.getString("user_id"));
								bean.setUsername(resultSet.getString("user_name"));
								
								byList.add(bean);
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
		return byList;
	}
	
	public static ArrayList<UserprofileBean> onLoadUserDeatilsForAssignTo(){
		ArrayList<UserprofileBean> assignToList = new ArrayList<UserprofileBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, RoleMasterSql.fetchUserQuery, null);
						    logger.info("onLoadUserDeatils- " + preparedStatement.unwrap(PreparedStatement.class));
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								UserprofileBean bean = new UserprofileBean();
								bean.setId(resultSet.getInt("id"));
								bean.setAnotherUserId(resultSet.getString("user_id"));
								bean.setUsername(resultSet.getString("user_name"));
								
								assignToList.add(bean);
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
		return assignToList;
	}
	
	
	public static ArrayList<UserprofileBean> onLoadUserDeatilsForAssignToSearch(String name){
		ArrayList<UserprofileBean> toList = new ArrayList<UserprofileBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						   preparedStatement = Pstm.createQuery(connection, RoleMasterSql.fetchUserQueryForreportSql, Arrays.asList("%"+name+"%"));
						    logger.info("onLoadUserDeatils- " + preparedStatement.unwrap(PreparedStatement.class));
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								UserprofileBean bean = new UserprofileBean();
								bean.setId(resultSet.getInt("id"));
								bean.setAnotherUserId(resultSet.getString("user_id"));
								bean.setUsername(resultSet.getString("user_name"));
								
								toList.add(bean);
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
		return toList;
	}
	
}
