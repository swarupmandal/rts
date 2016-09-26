package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.appsquad.bean.CountryBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StateBean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.ClientInformationsql;
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
					logger.error(e);
					logger.error(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.error(e);
		}
		return stateList;	
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
					logger.error(e);
					logger.error(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.error(e);
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
					logger.error(e);
					logger.error(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.error(e);
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
					logger.error(e);
					logger.error(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.error(e);
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
											                                              resourceMasterBean.getYearOfExperience(),resourceMasterBean.getAddress().toUpperCase(),resourceMasterBean.getEmailId().toUpperCase(),
											                                              resourceMasterBean.getUserId(),resourceMasterBean.getSkillsetMasterbean().getId(),resourceMasterBean.getCountryBean().getCountryId(),
											                                              resourceMasterBean.getPicCode().toUpperCase(),resourceMasterBean.getContactNumber().toUpperCase(),resourceMasterBean.getStatusMasterBean().getStatusId(),
											                                              resourceMasterBean.getCtc(),resourceMasterBean.getSkillsetMasterbean().getSkillset().toUpperCase(),
											                                              resourceMasterBean.getCountryBean().getCountryName().toUpperCase(),
											                                              resourceMasterBean.getStateBean().getStateName().toUpperCase(), resourceMasterBean.getFilePath()));
					    	
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
					logger.error(e);
					logger.error(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.error(e);
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
							
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								ResourceMasterBean bean = new ResourceMasterBean();
								
								bean.setResourceId(resultSet.getInt("id"));
								bean.setName(resultSet.getString("res_name"));
								bean.setSurName(resultSet.getString("res_surname"));
								bean.setYearOfExperience(resultSet.getInt("res_experience"));
								bean.getSkillsetMasterbean().setSkillset(resultSet.getString("rts_skill_name"));
								bean.setAddress(resultSet.getString("res_address"));
								bean.getStateBean().setStateName(resultSet.getString("rts_state_name"));
								bean.getCountryBean().setCountryName(resultSet.getString("rts_country_name"));
								bean.setPicCode(resultSet.getString("rts_pincode"));
								bean.setContactNumber(resultSet.getString("rts_contact_no"));
								bean.setEmailId(resultSet.getString("res_emailid"));
								bean.getStatusMasterBean().setStatus(resultSet.getString("rts_status_name"));
								bean.setCtc(resultSet.getDouble("rts_ctc"));
								bean.getSkillsetMasterbean().setId(resultSet.getInt("rts_skill_id"));
								bean.getStateBean().setStateId(resultSet.getInt("rts_state_id"));
								bean.getCountryBean().setCountryId(resultSet.getInt("rts_country_id"));
								bean.getStatusMasterBean().setStatusId(resultSet.getInt("rts_status_id"));
								bean.setFilePath(resultSet.getString("res_upcv"));
								
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
					logger.error(e);
					logger.error(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.error(e);
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
									ResourceMasterSql.updateResourceQuery, Arrays.asList(resourceMasterBean.getName().toUpperCase(),resourceMasterBean.getSurName().toUpperCase(),
											                                              resourceMasterBean.getYearOfExperience(),resourceMasterBean.getAddress().toUpperCase(),resourceMasterBean.getEmailId().toUpperCase(),
											                                              resourceMasterBean.getSkillsetMasterbean().getId(),resourceMasterBean.getCountryBean().getCountryId(),
											                                              resourceMasterBean.getPicCode().toUpperCase(),resourceMasterBean.getContactNumber().toUpperCase(),resourceMasterBean.getStatusMasterBean().getStatusId(),
											                                              resourceMasterBean.getCtc(),resourceMasterBean.getSkillsetMasterbean().getSkillset().toUpperCase(),
											                                              resourceMasterBean.getCountryBean().getCountryName().toUpperCase(),
											                                              resourceMasterBean.getStateBean().getStateName().toUpperCase(),resourceMasterBean.getFilePath(),resourceMasterBean.getResourceId()));
					    	
					    	
					    	//System.out.println(preparedStatementInsert);
					    	logger.info("Inserting Client Data Into Table: "+preparedStatementInsert.unwrap(PreparedStatement.class));
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
					logger.error(e);
					logger.error(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.error(e);
		}
		return isUpdated;
	}
	
}
