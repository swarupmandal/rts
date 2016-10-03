package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.UserProfileSql;
import org.appsquad.utility.Pstm;
import org.zkoss.zul.Messagebox;

public class UserProfileDao {
	final static Logger logger=Logger.getLogger(UserProfileDao.class);
	
	public static boolean insertUserData(UserprofileBean userprofileBean){
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
									UserProfileSql.insertUserData, Arrays.asList(userprofileBean.getUserid(),userprofileBean.getUsername(),
																userprofileBean.getPassword(),userprofileBean.getAddress().toUpperCase(),
																userprofileBean.getContactno().toUpperCase(),userprofileBean.getEmail()));
					    
					    	/*logger.info(" insertUserData- " + preparedStatementInsert.unwrap(PreparedStatement.class));*/
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
						Messagebox.show(" User Profile Details Saved successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" User Profile Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
			//logger.fatal(e);
		}
		return isSaved;
	}
	
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
						   preparedStatement = Pstm.createQuery(connection, UserProfileSql.fetchUserDeatils, null);
						   //logger.info(" onLoadUserDeatils- " + preparedStatement.unwrap(PreparedStatement.class));
						   
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								UserprofileBean bean = new UserprofileBean();
								bean.setAddress(resultSet.getString("address"));
								bean.setContactno(resultSet.getString("contactno"));
								bean.setEmail(resultSet.getString("email"));
								bean.setPassword(resultSet.getString("password"));
								bean.setUserid(resultSet.getString("user_id"));
								bean.setUsername(resultSet.getString("user_name"));
								bean.setId(resultSet.getInt("id"));
								
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
					
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//logger.fatal(e);
		}
		return userList;
	}
	
	public static int countPresentUserDetails(UserprofileBean userprofileBean){
		int count = 0;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatementCount = null;
					   try {
						   preparedStatementCount = Pstm.createQuery(connection, UserProfileSql.countNumberSql, Arrays.asList(userprofileBean.getUserid(),
								                                                                             userprofileBean.getPassword()));
							
							//logger.info(" countPresentUserDetails- " + preparedStatementCount.unwrap(PreparedStatement.class));
							
							ResultSet resultSet = preparedStatementCount.executeQuery();
							while (resultSet.next()) {
								count = resultSet.getInt(1);
							}  
						} finally{
							if(preparedStatementCount!=null){
								preparedStatementCount.close();
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
			//logger.fatal(e);
		}
		return count;
	}
	
	public static boolean updateUserData(UserprofileBean userprofileBean){
		boolean isUpdated = false;
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
									UserProfileSql.updateUserDetails, Arrays.asList(userprofileBean.getUserid(),userprofileBean.getUsername(),
											  userprofileBean.getPassword(),userprofileBean.getAddress(),userprofileBean.getContactno(),
											  userprofileBean.getEmail(),userprofileBean.getId()));
					    	
					    	//logger.info(" Update User Data- " + preparedStatementInsert.unwrap(PreparedStatement.class));
					    	
							int i = preparedStatementInsert.executeUpdate();
							if(i>0){
								isUpdated = true;	
							}
						} finally{
							if(preparedStatementInsert!=null){
								preparedStatementInsert.close();
							}
						}
				    }
				
					if( isUpdated){
						Messagebox.show(" User Profile Details Updated successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" User Profile failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
			//logger.fatal(e);
		}
		return isUpdated;
	}
	
	public static boolean deleteUserData(UserprofileBean userprofileBean){
		boolean isDeleted = false;
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
									UserProfileSql.deleteUserSql, Arrays.asList(userprofileBean.getId()));
					    	logger.info(" deleteUserData- " + preparedStatementInsert.unwrap(PreparedStatement.class));
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
						Messagebox.show(" User Profile Details Deleted Successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" User Profile failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
			//logger.fatal(e);;
		}
		return isDeleted;
	}

	/*********************************************************************************************************************************/
	
	public static Logger getLogger() {
		return logger;
	}
}
