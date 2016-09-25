package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.ResourceAllocationTrackingSql;
import org.appsquad.utility.Pstm;


public class ResourceAllocationTrackingDao {

	
	public static ArrayList<ClientInformationBean> fetchClientDetails(){
		
		ArrayList<ClientInformationBean> list = new ArrayList<ClientInformationBean>();
		if(list.size()>0){
			list.clear();
			
		}
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection, ResourceAllocationTrackingSql.loadClientList, null);
				
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					ClientInformationBean bean = new ClientInformationBean();
					bean.setClientId(resultSet.getInt("id"));
					bean.setFullName(resultSet.getString("full_name"));
					
					list.add(bean);
				}
				
			} finally {
				if(preparedStatement != null){
					preparedStatement.close();
				}if(resultSet != null){
					resultSet.close();
				}if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static ArrayList<RequirementGenerationBean> fetchReqirmentDetails(int clId){
		
		ArrayList<RequirementGenerationBean> list = new ArrayList<RequirementGenerationBean>();
		if(list.size()>0){
			list.clear();
			
		}
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection, ResourceAllocationTrackingSql.loadReqIdList, Arrays.asList(clId));
				
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					RequirementGenerationBean bean = new RequirementGenerationBean();
					bean.setReq_id(resultSet.getInt("r_id"));
					bean.setReqSkill(resultSet.getString("master_skill_set_name"));
					bean.setOcStatus(resultSet.getString("status"));
					
					list.add(bean);
				}
				
			} finally {
				if(preparedStatement != null){
					preparedStatement.close();
				}if(resultSet != null){
					resultSet.close();
				}if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
public static ArrayList<ClientInformationBean> fetchClientDetailsSearch(String name){
		
		ArrayList<ClientInformationBean> list = new ArrayList<ClientInformationBean>();
		if(list.size()>0){
			list.clear();
			
		}
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection, ResourceAllocationTrackingSql.loadClNameSearch, Arrays.asList("%"+name.trim().toUpperCase()+"%"));
				
				
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					ClientInformationBean bean = new ClientInformationBean();
					bean.setClientId(resultSet.getInt("id"));
					bean.setFullName(resultSet.getString("full_name"));
					
					list.add(bean);
				}
				
			} finally {
				if(preparedStatement != null){
					preparedStatement.close();
				}if(resultSet != null){
					resultSet.close();
				}if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static ArrayList<RequirementGenerationBean> fetchReqirmentDetailsSearch(int clId,int id){
		
		ArrayList<RequirementGenerationBean> list = new ArrayList<RequirementGenerationBean>();
		if(list.size()>0){
			list.clear();
			
		}
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection, ResourceAllocationTrackingSql.loadReqIdSearch, Arrays.asList(clId,"%"+id+"%"));
				
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					RequirementGenerationBean bean = new RequirementGenerationBean();
					bean.setReq_id(resultSet.getInt("r_id"));
					bean.setReqSkill(resultSet.getString("master_skill_set_name"));
					bean.setOcStatus(resultSet.getString("status"));
					
					list.add(bean);
				}
				
			} finally {
				if(preparedStatement != null){
					preparedStatement.close();
				}if(resultSet != null){
					resultSet.close();
				}if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

}
