package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.StatusMasterSql;
import org.appsquad.utility.Pstm;
import org.zkoss.zul.Messagebox;

import sun.print.PeekGraphics;

public class StatusMasterDao {
	final static Logger logger = Logger.getLogger(StatusMasterDao.class);
	
	public static boolean insertStatusData(StatusMasterBean statusMasterBean){
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
					    	String initialStage = "N",preBilledStatus = "N",finalStage="N";
					    	if(statusMasterBean.isPreBilled()){
					    		preBilledStatus = "Y";
					    	}
					    	if(statusMasterBean.isInitial()){
					    		initialStage = "Y";
					    	}
					    	if(statusMasterBean.isFinalStage()){
					    		finalStage = "Y";
					    	}
					    	preparedStatementInsert = Pstm.createQuery(connection, 
									StatusMasterSql.insertStatusQuery, Arrays.asList(statusMasterBean.getUserId(),
											statusMasterBean.getStatus().toUpperCase().trim(),preBilledStatus,initialStage,finalStage));
					    	
					    	logger.info(" insertStatusData- " + preparedStatementInsert.unwrap(PreparedStatement.class));
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
	
	
	public static boolean updateStatusData(StatusMasterBean statusMasterBean){
		boolean isUpdate = false;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_insert:{
					    PreparedStatement preparedStatementInsert = null;
					    try {
					    	/*preparedStatementInsert = Pstm.createQuery(connection, 
									StatusMasterSql.updateStatusSql, Arrays.asList(statusMasterBean.getStatus().toUpperCase(),statusMasterBean.getStatusId()));*/
					    
					    	preparedStatementInsert = connection.prepareStatement(StatusMasterSql.updateStatusSql);
					    	preparedStatementInsert.setString(1, statusMasterBean.getStatus().trim());
					    	if(statusMasterBean.isPreBilled()){
					    		preparedStatementInsert.setString(2, "Y");
					    	}else{
					    		preparedStatementInsert.setString(2, "N");
					    	}
					    	if(statusMasterBean.isInitial()){
					    		preparedStatementInsert.setString(3, "Y");
					    	}else{
					    		preparedStatementInsert.setString(3, "N");
					    	}
					    	if(statusMasterBean.isFinalStage()){
					    		preparedStatementInsert.setString(4, "Y");
					    	}else{
					    		preparedStatementInsert.setString(4, "N");
					    	}
					    	preparedStatementInsert.setInt(5, statusMasterBean.getStatusId());
					    	logger.info(" updateStatusData- " + preparedStatementInsert.unwrap(PreparedStatement.class));
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
						Messagebox.show(" Status Details Updated successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Status Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
		return isUpdate;
	}
	
	/**
	 * @author somnathd
	 * Update others prebill
	 * @return
	 */
	public static void updateOthersStatus(StatusMasterBean statusMasterBean){
		boolean isUpdate = false;
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
									StatusMasterSql.updateOtherStatusSql, Arrays.asList(statusMasterBean.getStatusId()));
					    	int i = preparedStatementInsert.executeUpdate();
							if(i>0){
								isUpdate = true;	
								System.out.println("Others status updated succesfully!");
							}
						} finally{
							if(preparedStatementInsert!=null){
								preparedStatementInsert.close();
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
	}
	
	/**
	 * @author somnathd
	 * Update others prebill
	 * @return
	 */
	public static void updateOthersFinalStatus(StatusMasterBean statusMasterBean){
		boolean isUpdate = false;
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
									StatusMasterSql.updateOtherFinalStatusSql, Arrays.asList(statusMasterBean.getStatusId()));
					    	int i = preparedStatementInsert.executeUpdate();
							if(i>0){
								isUpdate = true;	
								System.out.println("Others final status updated succesfully!");
							}
						} finally{
							if(preparedStatementInsert!=null){
								preparedStatementInsert.close();
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
	}
	
	/**
	 * @author somnathd
	 * Update others prebill
	 * @return
	 */
	public static void updateOthersInitalStatus(StatusMasterBean statusMasterBean){
		boolean isUpdate = false;
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
									StatusMasterSql.updateOtherInitialStatusSql, Arrays.asList(statusMasterBean.getStatusId()));
					    	int i = preparedStatementInsert.executeUpdate();
							if(i>0){
								isUpdate = true;	
								System.out.println("Others initial status updated succesfully!");
							}
						} finally{
							if(preparedStatementInsert!=null){
								preparedStatementInsert.close();
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
						    logger.info("onLoad Status Deatils- " + preparedStatement.unwrap(PreparedStatement.class));
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								StatusMasterBean bean = new StatusMasterBean();
								bean.setStatusId(resultSet.getInt("id"));
								bean.setStatus(resultSet.getString("master_status_name"));
								bean.setStatusDisabled(true);
								if(resultSet.getString("is_initial").equals("Y")){
							    	bean.setInitial(true);
							    }else{
							    	bean.setInitial(false);
							    }
							    if(resultSet.getString("is_pre_bill").equals("Y")){
							    	bean.setPreBilled(true);
							    }else{
							    	bean.setPreBilled(false);
							    }
							    if(resultSet.getString("is_final").equals("Y")){
							    	bean.setFinalStage(true);
							    }else{
							    	bean.setFinalStage(false);
							    }
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
		return statusList;
	}

	public static int countStatusNumber(StatusMasterBean statusMasterBean){
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
						    preparedStatement = Pstm.createQuery(connection, StatusMasterSql.countStatusSql, Arrays.asList(statusMasterBean.getStatus().toUpperCase()));
							
							logger.info(" count Status Number- " + preparedStatement.unwrap(PreparedStatement.class));
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
					    	logger.info(" delete Status- " + preparedStatementInsert.unwrap(PreparedStatement.class));
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
}
