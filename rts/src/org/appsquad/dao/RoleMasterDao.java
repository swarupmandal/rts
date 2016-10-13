package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.appsquad.bean.RoleMasterBean;
import org.appsquad.bean.RollDropDownBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.RoleMasterSql;
import org.appsquad.utility.Pstm;
import org.zkoss.zul.Messagebox;

public class RoleMasterDao {
	final static Logger logger = Logger.getLogger(RoleMasterDao.class);
	
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
									RoleMasterSql.insertRoleQuery, Arrays.asList(roleMasterBean.getRoll().toUpperCase(),roleMasterBean.getUserid()));
					    	/*logger.info("insertSkillData- " + preparedStatementInsert.unwrap(PreparedStatement.class));*/
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
			/*logger.fatal(e);*/
			e.printStackTrace();
		}
	}
	
	
	public static void insertAssignData(RoleMasterBean roleMasterBean){
		boolean isSavedAssign = false;
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
									RoleMasterSql.insertMappingQuery, Arrays.asList(roleMasterBean.getUserprofileBean().getId(),roleMasterBean.getDownBean().getRollId()));
					    	/*logger.info("insertAssignData- " + preparedStatementInsert.unwrap(PreparedStatement.class));*/
							int i = preparedStatementInsert.executeUpdate();
							if(i>0){
								isSavedAssign = true;	
							}
						} finally{
							if(preparedStatementInsert!=null){
								preparedStatementInsert.close();
							}
						}
				    }
				
					if( isSavedAssign){
						Messagebox.show(" Role User Mapping Saved successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Role User Mapping failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
			/*logger.fatal(e);*/
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
						   /*logger.info("onLoadRoleDeatils- " + preparedStatement.unwrap(PreparedStatement.class));*/
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
			/*logger.fatal(e);*/
			e.printStackTrace();
		}
		return roleList;
	}
	
	
	public static ArrayList<RollDropDownBean> onLoadRoleDropDownDeatils(){
		ArrayList<RollDropDownBean> roleDropList = new ArrayList<RollDropDownBean>();
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
						   /*logger.info("onLoadRoleDropDownDeatils- " + preparedStatement.unwrap(PreparedStatement.class));*/
						   ResultSet resultSet = preparedStatement.executeQuery();
						   while (resultSet.next()) {
								RollDropDownBean bean = new RollDropDownBean();
								bean.setRollId(resultSet.getInt("id"));
								bean.setRoll(resultSet.getString("master_role_name"));
								
								roleDropList.add(bean);
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
			logger.fatal(e);
			e.printStackTrace();
		}
		return roleDropList;
	}
	
	
	public static ArrayList<RollDropDownBean> onLoadRoleDropDownDeatilsForUpdateScreen(RoleMasterBean roleMasterBean){
		ArrayList<RollDropDownBean> roleDropList = new ArrayList<RollDropDownBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						   preparedStatement = Pstm.createQuery(connection, RoleMasterSql.fetchRoleQueryForDrop, Arrays.asList(roleMasterBean.getDownBean().getRoll()));
						   /*logger.info("onLoadRoleDropDownDeatils- " + preparedStatement.unwrap(PreparedStatement.class));*/
						   ResultSet resultSet = preparedStatement.executeQuery();
						   while (resultSet.next()) {
								RollDropDownBean bean = new RollDropDownBean();
								bean.setRollId(resultSet.getInt("id"));
								bean.setRoll(resultSet.getString("master_role_name"));
								
								roleDropList.add(bean);
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
			logger.fatal(e);
			e.printStackTrace();
		}
		return roleDropList;
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
					    	/*logger.info("updateRoleData- " + preparedStatementInsert.unwrap(PreparedStatement.class));*/
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
			/*logger.fatal(e);*/
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
					    	/*logger.info("deleteRoleData- " + preparedStatementInsert.unwrap(PreparedStatement.class));*/
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
			/*logger.fatal(e);*/
			e.printStackTrace();
		}
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
						   preparedStatement = Pstm.createQuery(connection, RoleMasterSql.fetchUserQuery, null);
						   /*logger.info("onLoadUserDeatils- " + preparedStatement.unwrap(PreparedStatement.class));*/
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
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			/*logger.fatal(e);*/
			e.printStackTrace();
		}
		return userList;
	}
	
	public static ArrayList<RoleMasterBean> onLoadMappingDeatils(){
		ArrayList<RoleMasterBean> mappingList = new ArrayList<RoleMasterBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						   preparedStatement = Pstm.createQuery(connection, RoleMasterSql.fetchMappingQuery, null);
						   logger.info("onLoadMappingDeatils- " + preparedStatement.unwrap(PreparedStatement.class));
						   ResultSet resultSet = preparedStatement.executeQuery();
						   while (resultSet.next()) {
								RoleMasterBean bean = new RoleMasterBean();
								bean.setRollId(resultSet.getInt("role_id"));
								bean.getDownBean().setRoll(resultSet.getString("master_role_name"));
								bean.getUserprofileBean().setId(resultSet.getInt("user_id"));
								bean.getUserprofileBean().setUsername(resultSet.getString("user_name"));
								bean.setMapperId(resultSet.getInt("user_role_mapper_id"));
								bean.setUserSerialId(resultSet.getInt("id"));
								
								
								mappingList.add(bean);
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
			/*logger.fatal(e);*/
			e.printStackTrace();
		}
		return mappingList;
	}
	
	public static boolean updateAssignData(RoleMasterBean roleMasterBean){
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
									RoleMasterSql.updateMappingQuery, Arrays.asList(roleMasterBean.getDownBean().getRollId(),roleMasterBean.getMapperId()));
					    	
					    	System.out.println(preparedStatementInsert);
					    	/*logger.info("updateAssignData- " + preparedStatementInsert.unwrap(PreparedStatement.class));*/
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
						Messagebox.show(" Mapper Details Updated successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Mapper Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
			/*logger.fatal(e);*/
			e.printStackTrace();
		}
		return isUpdated;
	}
	
	
	public static Integer onLoadCountDeatils(RoleMasterBean bean){
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
						   preparedStatement = Pstm.createQuery(connection, RoleMasterSql.countSqlQuery, Arrays.asList(bean.getUserprofileBean().getId()));
						
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
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			/*logger.fatal(e);*/
			e.printStackTrace();
		}
		return count;
	}
	
	public static Integer onLoadCountTabDeatils(RoleMasterBean bean){
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
						   preparedStatement = Pstm.createQuery(connection, RoleMasterSql.countSqlQuery, Arrays.asList(bean.getuId(),
								                                                                         bean.getDownBean().getRollId()));
						   /*logger.info("onLoadCountTabDeatils- " + preparedStatement.unwrap(PreparedStatement.class));*/
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
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			/*logger.fatal(e);*/
			e.printStackTrace();
		}
		return count;
	}
	

	public static Integer onLoadRoleNameCountDeatils(RoleMasterBean bean){
		int countRole = 0;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						   preparedStatement = Pstm.createQuery(connection, RoleMasterSql.countRoleSqlQuery, Arrays.asList(bean.getRoll()));
						   /*logger.info("onLoadRoleNameCountDeatils- " + preparedStatement.unwrap(PreparedStatement.class));*/
						   ResultSet resultSet = preparedStatement.executeQuery();
						   while (resultSet.next()) {
								countRole = resultSet.getInt(1);
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
			/*logger.fatal(e);*/
			e.printStackTrace();
		}
		return countRole;
	}
	
	public static Integer countUserPresentsWrtRole(RoleMasterBean bean){
		int countRole = 0;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						   preparedStatement = Pstm.createQuery(connection, RoleMasterSql.countUserPresentsWrtRole, Arrays.asList(bean.getRollId()));
						   logger.info("onLoadRoleNameCountDeatils- " + preparedStatement.unwrap(PreparedStatement.class));
						   ResultSet resultSet = preparedStatement.executeQuery();
						   while (resultSet.next()) {
								countRole = resultSet.getInt(1);
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
			/*logger.fatal(e);*/
			e.printStackTrace();
		}
		return countRole;
	}
	
}
