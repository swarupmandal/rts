package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.ClientInformationsql;
import org.appsquad.sql.SkillSetMasterSql;
import org.appsquad.sql.SortCriteriaSql;
import org.appsquad.utility.Pstm;

public class SortCriteriaDao {
    
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
		}
		return skillList;
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
			e.printStackTrace();
		}
		return clientList;
	}
	
}
