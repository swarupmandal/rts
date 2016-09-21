package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.RequirementGenerationSql;
import org.appsquad.utility.Pstm;

public class RequirementGenerationDao {
	
	public static ArrayList<ClientInformationBean> fetchClientNameList(){
		
		ArrayList<ClientInformationBean> nameBeanList = new ArrayList<ClientInformationBean>();
		if(nameBeanList.size()>0){
			nameBeanList.clear();
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DbConnection.createConnection();
			preparedStatement = Pstm.createQuery(connection, RequirementGenerationSql.loadClientName, null);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ClientInformationBean bean = new ClientInformationBean();
				bean.setClientId(resultSet.getInt("id"));
				bean.setName(resultSet.getString("name"));
				bean.setSurName(resultSet.getString("surname"));
				bean.setFullName(bean.getName()+" " +bean.getSurName());
				
				nameBeanList.add(bean);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			}
		}
		
		return nameBeanList;
	}



	public static ArrayList<SkillsetMasterbean> fetchSkillSetList(){
		
		ArrayList<SkillsetMasterbean> list = new ArrayList<SkillsetMasterbean>();
		if(list.size()>0){
			list.clear();
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DbConnection.createConnection();
			preparedStatement = Pstm.createQuery(connection, RequirementGenerationSql.loadSkillSetName, null);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				SkillsetMasterbean bean = new SkillsetMasterbean();
				bean.setId(resultSet.getInt("id"));
				bean.setSkillset(resultSet.getString("master_skill_set_name"));
				bean.setSkillsetdetails(resultSet.getString("skill_set_details"));
				
				list.add(bean);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public static ArrayList<StatusMasterBean> fetchStatusList(){
		
		ArrayList<StatusMasterBean> list = new ArrayList<StatusMasterBean>();
		if(list.size()>0){
			list.clear();
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DbConnection.createConnection();
			preparedStatement = Pstm.createQuery(connection, RequirementGenerationSql.loadocStatus, null);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				StatusMasterBean bean = new StatusMasterBean();
				bean.setOcstatusId(resultSet.getInt("id"));
				bean.setOcstatus(resultSet.getString("status"));
				
				list.add(bean);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	
	public static int onClikSubmit(RequirementGenerationBean bean){
		int i =0;
		Connection connection = null;
		PreparedStatement preparedStatement= null;
		
		try {
			connection = DbConnection.createConnection();
			preparedStatement = Pstm.createQuery(connection, RequirementGenerationSql.insertReqGen, Arrays.asList(bean.getClientId(), bean.getReqSkillId(),bean.getJobType(),bean.getDetailedJob(),
																												bean.getNofPerResource(), bean.getNofPerResource(), bean.getRaiseDate(), bean.getCloseDate(),
																												bean.getContactNo(), bean.getEmail(),bean.getReqStatusId(),bean.getClosureReason()));
			
			i = preparedStatement.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(preparedStatement != null){
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		}
		
		return i;
	}
	
	
	

}



