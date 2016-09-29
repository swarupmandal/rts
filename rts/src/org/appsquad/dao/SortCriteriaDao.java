package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.SortCriteriaRidorStatusBean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.ClientInformationsql;
import org.appsquad.sql.SkillSetMasterSql;
import org.appsquad.sql.SortCriteriaSql;
import org.appsquad.utility.Pstm;

public class SortCriteriaDao {
	
	final static Logger logger = Logger.getLogger(SortCriteriaDao.class);
    
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
							
						   
						   logger.info(" onLoadSetDeatils- " + preparedStatement.unwrap(PreparedStatement.class));
						   
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
			logger.fatal(e);
			e.printStackTrace();
		}
		return skillList;
	}
	
	public static ArrayList<SortCriteriaRidorStatusBean> onLoadDeatilsList(String frmDate, String toDate, String skillName, String statusName, String clientName){
		ArrayList<SortCriteriaRidorStatusBean> detailsList = new ArrayList<SortCriteriaRidorStatusBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, SortCriteriaSql.detailsSqlQuery, null);
						    logger.info(" onLoadSDeatlsReport- " + preparedStatement.unwrap(PreparedStatement.class));
						    if(statusName!=null && statusName.trim().length()>0){
						    	preparedStatement.setString(1, ("%"+statusName+"%").trim().toUpperCase());
						    }else{
						    	preparedStatement.setString(1, "%%");
						    }
						    
						    if(clientName!=null && clientName.trim().length()>0){
						    	preparedStatement.setString(2, ("%"+clientName+"%").trim().toUpperCase());
						    }else{
						    	preparedStatement.setString(2, "%%");
						    }
						    
						    if(skillName!=null && skillName.trim().length()>0){
						    	preparedStatement.setString(3, ("%"+skillName+"%").trim().toUpperCase());
						    }else{
						    	preparedStatement.setString(3, "%%");
						    }
						    
						    if(frmDate!=null && frmDate.trim().length()>0){
						    	preparedStatement.setString(4, frmDate);
						    }else{
						    }
						    
						    if(toDate!=null && toDate.trim().length()>0){
						    	preparedStatement.setString(5, toDate);
						    }else{
						    }
						    logger.info(" OnCheckRadioButton<Detail>:- " + preparedStatement.unwrap(PreparedStatement.class));
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								SortCriteriaRidorStatusBean bean = new SortCriteriaRidorStatusBean();
								bean.getMasterbean().setSkillset(resultSet.getString("skill_name"));
								
								detailsList.add(bean);
							}
							System.out.println(detailsList.size());
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
		return detailsList;
	}
	
	public static ArrayList<StatusMasterBean> onLoadStatus(){
		ArrayList<StatusMasterBean> statusList = new ArrayList<StatusMasterBean>();
		if(statusList.size()>0){
			statusList.clear();
		}
		
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, SortCriteriaSql.statusQuery, null);
							
						    logger.info(" onLoadStatus- " + preparedStatement.unwrap(PreparedStatement.class));
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
			
			logger.fatal(e);
			
			e.printStackTrace();
		}
		return statusList;	
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
							
						   logger.info(" onLoadClientDeatils- " + preparedStatement.unwrap(PreparedStatement.class));
						   
						   
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								ClientInformationBean bean = new ClientInformationBean();
								bean.setFullName(resultSet.getString("name")+" "+resultSet.getString("surname"));
								bean.setSurName(resultSet.getString("surname"));
								bean.setCompanyName(resultSet.getString("companyname"));
								bean.setAddress(resultSet.getString("officeaddress"));
								bean.getStateBean().setStateName(resultSet.getString("state"));
								bean.getCountryBean().setCountryName(resultSet.getString("country"));
								bean.setPinZipCode(resultSet.getString("zipcode"));
								bean.setContactNo(resultSet.getString("contactno"));
								bean.setEmailId(resultSet.getString("emailid"));
								bean.getStateBean().setStateId(resultSet.getInt("state_id"));
								bean.getCountryBean().setCountryId(resultSet.getInt("country_id"));
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
		return clientList;
	}
	
}
