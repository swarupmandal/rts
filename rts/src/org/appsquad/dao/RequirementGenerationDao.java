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
import org.appsquad.utility.Dateformatter;
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
				if(bean.getSurName() !=null){
				bean.setFullName(bean.getName()+" " +bean.getSurName());
				}else {
					bean.setFullName(bean.getName());
				}
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
																												bean.getNofPerResource(), bean.getNofConResource(), bean.getRaiseDatesql(), bean.getCloseDatesql(),
																												bean.getContactNo(), bean.getEmail(),bean.getOcStatusId(),bean.getClosureReason(), bean.getUserName(), bean.getUserName()));
			
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
	
public static ArrayList<RequirementGenerationBean> fetchReqGenMasterData(){
		
		ArrayList<RequirementGenerationBean> list = new ArrayList<RequirementGenerationBean>();
		if(list.size()>0){
			list.clear();
		}
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DbConnection.createConnection();
			preparedStatement = Pstm.createQuery(connection, RequirementGenerationSql.loadReqGenMasterData, null);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				RequirementGenerationBean bean = new RequirementGenerationBean();
				bean.setReq_id(resultSet.getInt("r_id"));
				bean.setClientName(resultSet.getString("full_name"));
				bean.setClientId(resultSet.getInt("req_client_id"));
				bean.setReqSkillId(resultSet.getInt("req_skill_id"));
				bean.setReqSkill(resultSet.getString("master_skill_set_name"));
				bean.setClientfName(resultSet.getString("name"));
				bean.setClientsName(resultSet.getString("surname"));
				bean.setOcStatusId(resultSet.getInt("req_status_id"));
				bean.setOcStatus(resultSet.getString("status"));
				bean.setJobType(resultSet.getString("req_jobtype"));
				bean.setJobType(resultSet.getString("req_jobtype"));
				bean.setDetailedJob(resultSet.getString("req_job_details"));
				bean.setNofPerResource(resultSet.getInt("req_no_of_per_res"));
				bean.setNofConResource(resultSet.getInt("req_no_of_con_res"));
				
				bean.setRaiseDate(resultSet.getDate("req_raise_date"));
				bean.setRaiseDateValue(resultSet.getString("req_raise_date"));
				if(bean.getRaiseDateValue() !=null){
				bean.setRaiseDateStr(Dateformatter.toStringDate(bean.getRaiseDateValue()));
				}
				
				bean.setCloseDate(resultSet.getDate("req_close_date"));
				bean.setCloseDateValue(resultSet.getString("req_close_date"));
				if(bean.getCloseDateValue() != null){
				bean.setCloseDateStr(Dateformatter.toStringDate(bean.getCloseDateValue()));
				}
				
				bean.setContactNo(resultSet.getString("cont_no"));
				bean.setEmail(resultSet.getString("email_id"));
				
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
	
	
		public static int onClikUpdate(RequirementGenerationBean bean){
			int i =0;
			Connection connection = null;
			PreparedStatement preparedStatement= null;
			
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection, RequirementGenerationSql.updateReqGen, Arrays.asList(bean.getNofPerResource(), bean.getNofConResource(), Dateformatter.sqlDate(bean.getRaiseDate()), Dateformatter.sqlDate(bean.getCloseDate()),
																													bean.getContactNo(), bean.getEmail(),bean.getOcStatusId(),bean.getClosureReason(), bean.getUserName(), bean.getReq_id()));
				
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



