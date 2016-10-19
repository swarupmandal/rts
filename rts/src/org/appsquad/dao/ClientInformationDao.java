package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.CountryBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.bean.StateBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.ClientInformationsql;
import org.appsquad.utility.Pstm;
import org.zkoss.zul.Messagebox;

public class ClientInformationDao {
	final static Logger logger=Logger.getLogger(ClientInformationDao.class);
	
	public static ArrayList<StateBean> onLoadState(ClientInformationBean clientInformationBean){
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
						    preparedStatement = Pstm.createQuery(connection, ClientInformationsql.stateQuery, Arrays.asList(clientInformationBean.getCountryBean().getCountryId()));
						    logger.info("onLoadState - " + preparedStatement.unwrap(PreparedStatement.class));
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
					logger.fatal("--------------->>>"+ e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("---------------->>>>" + e);
		}
		return stateList;	
	}
	
	public static ArrayList<StateBean> onLoadStateForResource(ResourceMasterBean resourceMasterBean){
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
						    preparedStatement = Pstm.createQuery(connection, ClientInformationsql.stateQuery, Arrays.asList(resourceMasterBean.getCountryBean().getCountryId()));
						    logger.info("onLoadStateForResource - " + preparedStatement.unwrap(PreparedStatement.class));
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
					logger.fatal("-------------->>>>"+ e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		    logger.fatal("------------------>>>>"+e);
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
						    logger.info("onLoadCountry - " + preparedStatement.unwrap(PreparedStatement.class));
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
					logger.fatal("-------------------->>>>"+e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("----------------------->>>>>>>>>"+e);
		}
		return countryList;
	}
	
	public static boolean insertClientData(ClientInformationBean clientInformationBean){
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
									ClientInformationsql.insertClientInfo, Arrays.asList(clientInformationBean.getName().toUpperCase(),clientInformationBean.getSurName().toUpperCase(),
											clientInformationBean.getClientOriginalName().toUpperCase(),clientInformationBean.getAddress().toUpperCase(),
											clientInformationBean.getState(),clientInformationBean.getCountry(),
											clientInformationBean.getPinZipCode(),clientInformationBean.getContactNo().toUpperCase(),
											clientInformationBean.getEmailId(),clientInformationBean.getUserId(),
											clientInformationBean.getStateBean().getStateId(),clientInformationBean.getCountryBean().getCountryId()));
					    	
					    	logger.info("Inserting Client Data Into Table: "+preparedStatementInsert.unwrap(PreparedStatement.class));
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
						Messagebox.show(" Client Details Saved Successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Client Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
	
	public static boolean updateClientData(ClientInformationBean clientInformationBean){
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
									ClientInformationsql.clientDetailsUpdate, Arrays.asList(clientInformationBean.getAddress().toUpperCase(),
											clientInformationBean.getState().toUpperCase(),
											clientInformationBean.getPinZipCode(),clientInformationBean.getContactNo().toUpperCase(),
											clientInformationBean.getEmailId(),clientInformationBean.getUserId(),
											clientInformationBean.getClientId()));
					    	logger.info("Updating Client Data Into Table: "+preparedStatementInsert.unwrap(PreparedStatement.class));
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
						Messagebox.show(" Client Details Updated Successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Client Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.fatal("---------------------->>>>>>>>>>>"+e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("------------------>>>>>>>>>"+e);
		}
		return isUpdated;
	}
	
	
	public static boolean deleteClientData(ClientInformationBean clientInformationBean){
		boolean isDeleted = false;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_insert:{
					    PreparedStatement preparedStatementDelete = null;
					    try {
					    	preparedStatementDelete = Pstm.createQuery(connection, 
													ClientInformationsql.deleteClientDetailsSql, Arrays.asList(clientInformationBean.getClientId()));
					    	System.out.println(preparedStatementDelete);
					    	logger.info("Deleting Client Data From Table: "+preparedStatementDelete.unwrap(PreparedStatement.class));
							int i = preparedStatementDelete.executeUpdate();
							if(i>0){
								isDeleted = true;	
							}
						} finally{
							if(preparedStatementDelete!=null){
								preparedStatementDelete.close();
							}
						}
				    }
				
					if( isDeleted){
						Messagebox.show(" Client Details Deleted Successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Client Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.fatal("------------------>>>>>>>>>"+e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("------------------>>>>>>>>>>"+e);
		}
		return isDeleted;
	}
   
	public static ArrayList<ClientInformationBean> onLoadClientDeatils(){
		ArrayList<ClientInformationBean> clientList = new ArrayList<ClientInformationBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ClientInformationsql.fetchClientDeatils, null);
						    logger.info("onLoadClientDeatils - " + preparedStatement.unwrap(PreparedStatement.class));
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								ClientInformationBean bean = new ClientInformationBean();
								bean.setFullName(resultSet.getString("name")+" "+resultSet.getString("surname"));
								bean.setName(resultSet.getString("name"));
								bean.setSurName(resultSet.getString("surname"));
								bean.setClientOriginalName(resultSet.getString("clientname"));
								bean.setAddress(resultSet.getString("officeaddress"));
								bean.setState(resultSet.getString("state"));
								bean.setCountry(resultSet.getString("country"));
								bean.setPinZipCode(resultSet.getString("zipcode"));
								bean.setContactNo(resultSet.getString("contactno"));
								bean.setEmailId(resultSet.getString("emailid"));
								bean.setClientId(resultSet.getInt("id"));
								
								clientList.add(bean);
							}  
						} finally{
							if(preparedStatement!=null){
								preparedStatement.close();
							}
						}
				    }
				} catch (Exception e) {
					e.printStackTrace();
					logger.fatal("-------------------->>>>>>>"+e);
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
		return clientList;
	}
	
	public static int countClientPresentWrtRequirementDao(ClientInformationBean clientInformationBean){
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
						    preparedStatement = Pstm.createQuery(connection, ClientInformationsql.countClientPresentWrtRequirementSql, Arrays.asList(clientInformationBean.getClientId()));
						    logger.info("count Client Number Present In Req Table - " + preparedStatement.unwrap(PreparedStatement.class));
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
					logger.fatal("--------------->>>"+ e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("---------------->>>>" + e);
		}
		return count;	
	}
}
