package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.Logger;
import org.appsquad.bean.CountryBean;
import org.appsquad.bean.ResourceAllocationTrackingBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StateBean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.ClientInformationsql;
import org.appsquad.sql.RecourceAllocationUpdateSql;
import org.appsquad.sql.ResourceMasterSql;
import org.appsquad.utility.Pstm;
import org.zkoss.zul.Messagebox;

public class ResourceMasterDao {
	final static Logger logger=Logger.getLogger(ResourceMasterDao.class);
	
	public static ArrayList<StateBean> onLoadState(){
		ArrayList<StateBean> stateList = new ArrayList<StateBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ClientInformationsql.stateQuery, null);
						    logger.info("onLoadState- " + preparedStatement.unwrap(PreparedStatement.class));
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								StateBean bean = new StateBean();
								bean.setStateId(resultSet.getInt("state_id"));
								bean.setStateName(resultSet.getString("state_name").toUpperCase());
								stateList.add(bean);
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
			logger.fatal(e);
		}
		return stateList;	
	}
	
	
	public static int countLastNumber(){
		int num = 0;
		int returnNum = 0;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ResourceMasterSql.countLastNumberSql, null);
						    logger.info("Count Last Number- " + preparedStatement.unwrap(PreparedStatement.class));
						    ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								num = resultSet.getInt(1);
								returnNum = num+1;
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
			logger.fatal(e);
		}
		return returnNum;	
	}
	
	public static int countLastNumberOfResource(ResourceMasterBean resourceMasterBean){
		int num = 0;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ResourceMasterSql.countResourceNumberInMapperTableSql, Arrays.asList(resourceMasterBean.getResourceId()));
						    logger.info("Count Last Number- " + preparedStatement.unwrap(PreparedStatement.class));
						    ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								num = resultSet.getInt(1);
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
			logger.fatal(e);
		}
		return num;	
	}
	
	public static ArrayList<CountryBean> onLoadCountry(){
		ArrayList<CountryBean> countryList = new ArrayList<CountryBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ClientInformationsql.countryQuery, null);
						    logger.info("onLoadCountry- " + preparedStatement.unwrap(PreparedStatement.class));
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								CountryBean bean = new CountryBean();
								bean.setCountryId(resultSet.getInt("country_id"));
								bean.setCountryName(resultSet.getString("country_name").toUpperCase());
								countryList.add(bean);
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
			logger.fatal(e);
		}
		return countryList;
	}
	
	public static ArrayList<SkillsetMasterbean> onLoadSkill(){
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
						    preparedStatement = Pstm.createQuery(connection, ClientInformationsql.skillSetQuery, null);
						    logger.info("onLoadSkill- " + preparedStatement.unwrap(PreparedStatement.class));
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
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal(e);
		}
		return skillList;	
	}
	
	public static ArrayList<StatusMasterBean> onLoadStatus(){
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
						    preparedStatement = Pstm.createQuery(connection, ClientInformationsql.statusSetQuery, null);
						    logger.info("onLoadStatus- " + preparedStatement.unwrap(PreparedStatement.class));
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
			logger.fatal(e);
		}
		return statusList;	
	}
	
	public static ArrayList<StatusMasterBean> onLoadStatusForTrackingUpdateScreen(ResourceAllocationTrackingBean allocationTrackingBean){
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
						    preparedStatement = Pstm.createQuery(connection, RecourceAllocationUpdateSql.statusSetQuery, Arrays.asList(allocationTrackingBean.getStatus()));
						    logger.info("onLoadStatus- " + preparedStatement.unwrap(PreparedStatement.class));
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
			logger.fatal(e);
		}
		return statusList;	
	}
	
	public static boolean insertClientData(ResourceMasterBean resourceMasterBean){
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
									ResourceMasterSql.insertResourceQuery, Arrays.asList(resourceMasterBean.getName().toUpperCase(),resourceMasterBean.getSurName().toUpperCase(),
											                                              resourceMasterBean.getYearOfExperience(),resourceMasterBean.getAddress().toUpperCase(),resourceMasterBean.getEmailId(),
											                                              resourceMasterBean.getUserId(),resourceMasterBean.getSkillsetMasterbean().getId(),
											                                              resourceMasterBean.getPicCode().toUpperCase(),resourceMasterBean.getContactNumber().toUpperCase(),resourceMasterBean.getStatusMasterBean().getStatusId(),
											                                              resourceMasterBean.getCtc(),resourceMasterBean.getSkillsetMasterbean().getSkillset().toUpperCase(),
											                                              resourceMasterBean.getCountry().toUpperCase(),
											                                              resourceMasterBean.getState().toUpperCase(),resourceMasterBean.getProfit(),resourceMasterBean.getFilePath(),
											                                              resourceMasterBean.getOtherInfo()));
					    	
					    	logger.info("Inserting Resource Data Into Table: "+preparedStatementInsert.unwrap(PreparedStatement.class));
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
						Messagebox.show(" Resource Details Saved successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Resource Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
			logger.fatal(e);
		}
		return isSaved;
	}
	
	public static ArrayList<ResourceMasterBean> onLoadResourceDeatils(){
		ArrayList<ResourceMasterBean> resourceList = new ArrayList<ResourceMasterBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						   preparedStatement = Pstm.createQuery(connection, ResourceMasterSql.fetchResourceQyery, null);
						   logger.info(" onLoadResourceDeatils- " + preparedStatement.unwrap(PreparedStatement.class));	   
						   ResultSet resultSet = preparedStatement.executeQuery();
						   while (resultSet.next()) {
								ResourceMasterBean bean = new ResourceMasterBean();
								
								bean.setResourceId(resultSet.getInt("id"));
								bean.setName(resultSet.getString("res_name"));
								bean.setSurName(resultSet.getString("res_surname"));
								bean.setYearOfExperience(resultSet.getInt("res_experience"));
								bean.getSkillsetMasterbean().setSkillset(resultSet.getString("rts_skill_name"));
								bean.setAddress(resultSet.getString("res_address"));
								bean.setState(resultSet.getString("rts_state_name"));
								bean.setCountry(resultSet.getString("rts_country_name"));
								bean.setPicCode(resultSet.getString("rts_pincode"));
								bean.setContactNumber(resultSet.getString("rts_contact_no"));
								bean.setEmailId(resultSet.getString("res_emailid"));
								bean.getStatusMasterBean().setStatus(resultSet.getString("rts_status_name"));
								bean.setCtc(resultSet.getDouble("rts_ctc"));
								bean.getSkillsetMasterbean().setId(resultSet.getInt("rts_skill_id"));
								bean.getStatusMasterBean().setStatusId(resultSet.getInt("rts_status_id"));
								bean.setFilePath(resultSet.getString("res_upcv"));
								bean.setProfit(resultSet.getDouble("profit"));
								bean.setOtherInfo(resultSet.getString("other_info"));
								
								resourceList.add(bean);
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
			logger.fatal(e);
		}
		return resourceList;
	}
	
	public static boolean updateResourceData(ResourceMasterBean resourceMasterBean){
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
									ResourceMasterSql.updateResourceQuery, Arrays.asList( resourceMasterBean.getName().toUpperCase(),resourceMasterBean.getSurName().toUpperCase(),
											                                              resourceMasterBean.getYearOfExperience(),resourceMasterBean.getAddress().toUpperCase(),resourceMasterBean.getEmailId(),
											                                              resourceMasterBean.getSkillsetMasterbean().getId(),
											                                              resourceMasterBean.getPicCode().toUpperCase(),resourceMasterBean.getContactNumber().toUpperCase(),resourceMasterBean.getStatusMasterBean().getStatusId(),
											                                              resourceMasterBean.getCtc(),resourceMasterBean.getSkillsetMasterbean().getSkillset().toUpperCase(),
											                                              resourceMasterBean.getCountry().toUpperCase(),
											                                              resourceMasterBean.getState().toUpperCase(),resourceMasterBean.getFilePath(),resourceMasterBean.getProfit(),
											                                              resourceMasterBean.getOtherInfo(),resourceMasterBean.getResourceId()));
					    	
					    	logger.info("Updating Resource Data Into Table: "+preparedStatementInsert.unwrap(PreparedStatement.class));
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
						Messagebox.show(" Resource Details Updated successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Resource Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
			logger.fatal(e);
		}
		return isUpdated;
	}
	
	public static boolean deleteResourceData(ResourceMasterBean resourceMasterBean){
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
									ResourceMasterSql.deleteResourceFromTableSql, Arrays.asList(resourceMasterBean.getResourceId()));
					    	
					    	logger.info("Deleting Resource Data From Table: "+preparedStatementInsert.unwrap(PreparedStatement.class));
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
				
					if( isDeleted){
						Messagebox.show(" Resource Details Deleted successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Resource Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
			logger.fatal(e);
		}
		return isDeleted;
	}
	
}
