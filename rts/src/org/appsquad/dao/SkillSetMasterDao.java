package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.SkillSetMasterSql;
import org.appsquad.utility.Pstm;
import org.zkoss.zul.Messagebox;

public class SkillSetMasterDao {
	final static Logger logger = Logger.getLogger(SkillSetMasterDao.class);
	
	public static boolean insertSkillData(SkillsetMasterbean skillsetMasterbean){
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
									SkillSetMasterSql.insertSkillSetQuery, Arrays.asList(skillsetMasterbean.getSkillset().toUpperCase(),
																								skillsetMasterbean.getUserId(),
																										skillsetMasterbean.getSkillsetdetails().toUpperCase()));
					   
					    	logger.info("INSERT SKILL DATA INTO TABLE:- " + preparedStatementInsert.unwrap(PreparedStatement.class));
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
						Messagebox.show(" Skill Details Saved successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Skill Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.fatal(e);
					logger.error(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal(e);
			logger.error(e);
		}
		return isSaved;
	}
	
	
	public static ArrayList<SkillsetMasterbean> onLoadSetDeatils(){
		ArrayList<SkillsetMasterbean> skillList = new ArrayList<SkillsetMasterbean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, SkillSetMasterSql.fetchSkillSetDetails, null);
							
						    logger.info("onLoad Set Deatils:- " + preparedStatement.unwrap(PreparedStatement.class));
						    ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								SkillsetMasterbean masterbean = new SkillsetMasterbean();
								masterbean.setId(resultSet.getInt("id"));
								masterbean.setSkillset(resultSet.getString("master_skill_set_name"));
								masterbean.setSkillsetdetails(resultSet.getString("skill_set_details"));
								
								skillList.add(masterbean);
							}  
						} finally{
							if(preparedStatement!=null){
								preparedStatement.close();
							}
						}
				    }
				} catch (Exception e) {
					e.printStackTrace();
					logger.fatal(e);
					logger.error(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal(e);
			logger.error(e);
		}
		return skillList;
	}
	
	public static boolean updateSkillData(SkillsetMasterbean masterbean){
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
									SkillSetMasterSql.updateSkillSetDetails, Arrays.asList(masterbean.getSkillset().toUpperCase(),masterbean.getSkillsetdetails().toUpperCase(),masterbean.getId()));
					    	logger.info("update Skill Data - " + preparedStatementInsert.unwrap(PreparedStatement.class));
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
						Messagebox.show(" Client Details Updated successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Client Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.fatal(e);
					logger.error(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal(e);
			logger.error(e);
		}
		return isUpdated;
	}
	
	public static boolean deleteSkillDetails(SkillsetMasterbean masterbean){
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
					    	preparedStatementInsert = Pstm.createQuery(connection, SkillSetMasterSql.deleteSkillSql, Arrays.asList(masterbean.getId()));
					    	logger.info("delete skill data:- " + preparedStatementInsert.unwrap(PreparedStatement.class));
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
						Messagebox.show(" Skill Details Deleted Successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Client Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
		return isDeleted;
	}
	
	public static int countSkillName(SkillsetMasterbean masterbean){
		int count = 0;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_insert:{
					    PreparedStatement preparedStatementCount = null;
					    try {
					    	preparedStatementCount = Pstm.createQuery(connection,SkillSetMasterSql.countSkillNameSql, 
					    			                                                                       Arrays.asList(masterbean.getSkillset().toUpperCase()));
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
}
