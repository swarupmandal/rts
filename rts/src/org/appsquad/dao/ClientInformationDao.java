package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.CountryBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.bean.StateBean;
import org.appsquad.bean.UserprofileBean;
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
						    logger.info("onLoad State Query:- " + preparedStatement.unwrap(PreparedStatement.class));
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
									ClientInformationsql.insertClientInfo, Arrays.asList(clientInformationBean.getName().trim(),
											clientInformationBean.getSurName().trim(),
											clientInformationBean.getClientOriginalName().trim(),
											clientInformationBean.getAddress().trim(),
											clientInformationBean.getState().trim(),clientInformationBean.getCountry().trim(),
											clientInformationBean.getPinZipCode(),clientInformationBean.getContactNo(),
											clientInformationBean.getEmailId().trim(),clientInformationBean.getUserId()));
					    	
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
	
	
	/*public static boolean insertClientData(ClientInformationBean clientInformationBean, ArrayList<UserprofileBean> userProfileBeanList){
		boolean isSaved = false;
		int key =0;
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
											clientInformationBean.getEmailId(),clientInformationBean.getUserId()));
					    			
					    	preparedStatementInsert = connection.prepareStatement(ClientInformationsql.insertClientInfo, Statement.RETURN_GENERATED_KEYS);
					    	preparedStatementInsert.setString(1, clientInformationBean.getName());
					    	preparedStatementInsert.setString(2, clientInformationBean.getSurName());
					    	preparedStatementInsert.setString(3, clientInformationBean.getClientOriginalName());
					    	preparedStatementInsert.setString(4, clientInformationBean.getAddress());
					    	preparedStatementInsert.setString(5, clientInformationBean.getState());
					    	preparedStatementInsert.setString(6, clientInformationBean.getCountry());
					    	preparedStatementInsert.setString(7, clientInformationBean.getPinZipCode());
					    	preparedStatementInsert.setString(8, clientInformationBean.getContactNo());
					    	preparedStatementInsert.setString(9, clientInformationBean.getEmailId());
					    	preparedStatementInsert.setString(10, clientInformationBean.getUserId());
					    	
					    	logger.info("Inserting Client Data Into Table: "+preparedStatementInsert.unwrap(PreparedStatement.class));
							int i = preparedStatementInsert.executeUpdate();
							ResultSet rs = preparedStatementInsert.getGeneratedKeys();
					    	if(rs.next()){
					    		key = rs.getInt(1);
					    		System.out.println("KEY - " + key);
					    	}
							if(i>0){
								isSaved = true;
								PreparedStatement preparedStatement = null;
								try {
									
									for(UserprofileBean ub: userProfileBeanList){
										if(ub.isChecked()){
											preparedStatement = Pstm.createQuery(connection, ClientInformationsql.insertClientUserMapperSql, Arrays.asList(key, ub.getId(), ub.getUsername(), ub.getEmail(), clientInformationBean.getUserId(), clientInformationBean.getUserId()));
											int insert = preparedStatement.executeUpdate();
											System.out.println("INSERT " + insert);
											logger.info("Inserting Client User Mapper Table: "+preparedStatement.unwrap(PreparedStatement.class));
										}
									}
									
								} finally{
									if(preparedStatement != null){
										preparedStatement.close();
									}
								}
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
	}*/
	
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
									ClientInformationsql.clientDetailsUpdate, Arrays.asList(
											clientInformationBean.getClientOriginalName().trim(),
											clientInformationBean.getName().trim(),
											clientInformationBean.getSurName().trim(),
											clientInformationBean.getAddress().trim(),
											clientInformationBean.getState().trim(),
											clientInformationBean.getPinZipCode(),clientInformationBean.getContactNo(),
											clientInformationBean.getEmailId().trim(),clientInformationBean.getUserId(),
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
						    logger.info("onLoad Client Deatils - " + preparedStatement.unwrap(PreparedStatement.class));
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
	
	public static ArrayList<UserprofileBean> onLoadUserProfile(Connection connection){
		ArrayList<UserprofileBean> list = new ArrayList<UserprofileBean>();
		if(list.size()>0){
			list.clear();
		}
		try {
			connection= DbConnection.createConnection();
			PreparedStatement preparedStatement = null;
			
			try {
				preparedStatement = Pstm.createQuery(connection, ClientInformationsql.selectUserDetailsSql, null);
				logger.info("onLoad User Details Query:- " + preparedStatement.unwrap(PreparedStatement.class));
				ResultSet resultSet = preparedStatement.executeQuery(); 
				while (resultSet.next()) {
					UserprofileBean bean = new UserprofileBean();
					bean.setId(resultSet.getInt("id"));
					bean.setUserid(resultSet.getString("user_id"));
					bean.setUsername(resultSet.getString("user_name"));
					bean.setEmail(resultSet.getString("email"));
					bean.setChecked(false);
					
					list.add(bean);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				logger.fatal(e);
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}
				if(connection != null){
					connection.close();
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
}
