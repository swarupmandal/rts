package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.bean.StatusMasterBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.RequirementGenerationSql;
import org.appsquad.utility.Dateformatter;
import org.appsquad.utility.Pstm;

public class RequirementGenerationDao {
	
	final static Logger logger=Logger.getLogger(RequirementGenerationDao.class);
	
	
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
			
			 logger.info("fetchClientNameList - " + preparedStatement.unwrap(PreparedStatement.class));
			
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
			
			logger.fatal("--------------------->>>>>>>>>"+e);
			
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
	
	
	public static void fetchEmailIdAndContactNumber(RequirementGenerationBean generationBean){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DbConnection.createConnection();
			preparedStatement = Pstm.createQuery(connection, RequirementGenerationSql.FETCHCLIENTEMAILANDCONTACTNO, Arrays.asList(generationBean.getClientId()));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				generationBean.setEmail(resultSet.getString("emailid"));
				generationBean.setContactNo(resultSet.getString("contactno"));
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
	}

     
	public static int fetchOverallStatusId(){
		int statusId = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DbConnection.createConnection();
			preparedStatement = Pstm.createQuery(connection, RequirementGenerationSql.fetchStatusId, null);
			
			logger.info("fetchOverallStatusId - " + preparedStatement.unwrap(PreparedStatement.class));
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				statusId = resultSet.getInt("id");
			}
		} catch (Exception e) {
			
			logger.fatal("--------------------->>>>>>>>>"+e);
			
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
		return statusId;
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
			
			logger.info("fetchSkillSetList- " + preparedStatement.unwrap(PreparedStatement.class));
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				SkillsetMasterbean bean = new SkillsetMasterbean();
				bean.setId(resultSet.getInt("id"));
				bean.setSkillset(resultSet.getString("master_skill_set_name"));
				bean.setSkillsetdetails(resultSet.getString("skill_set_details"));
				
				list.add(bean);
				
			}
			
		} catch (Exception e) {
			
			logger.fatal("------------------>>>>"+e);
			
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
			
			logger.info("fetchStatusList- " + preparedStatement.unwrap(PreparedStatement.class));
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				StatusMasterBean bean = new StatusMasterBean();
				bean.setOcstatusId(resultSet.getInt("id"));
				bean.setOcstatus(resultSet.getString("status"));
				
				list.add(bean);
			}
		} catch (Exception e) {
			
			logger.fatal("-------------------->>>>>>>"+e);
			
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
																										bean.getContactNo(), bean.getEmail(),1,bean.getClosureReason(), bean.getUserName(), bean.getUserName(),
																												bean.getReqStatusId()));
			
			
			logger.info("onClikSubmit - " + preparedStatement.unwrap(PreparedStatement.class));
			
			i = preparedStatement.executeUpdate();
		} catch (Exception e) {
			
			logger.fatal("------------>>>>>>>>>>>>"+e);
			
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
			
			logger.info("fetchReqGenMasterData - " + preparedStatement.unwrap(PreparedStatement.class));
			
			
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
			
			logger.fatal("----------------->>>>>"+e);
			
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
				preparedStatement = connection.prepareStatement(RequirementGenerationSql.updateReqGen);
				preparedStatement.setInt(1, bean.getNofPerResource());	
				preparedStatement.setInt(2, bean.getNofConResource());
				  
				if(bean.getRaiseDate() != null){
					 preparedStatement.setDate(3, Dateformatter.sqlDate(bean.getRaiseDate()));
				}else {
					preparedStatement.setNull(3, Types.NULL);
				}
				  
				if(bean.getCloseDate() != null){
					 preparedStatement.setDate(4, Dateformatter.sqlDate(bean.getCloseDate()));
				}else {
					preparedStatement.setNull(4, Types.NULL);
				  }
				  
				  preparedStatement.setString(5,bean.getContactNo()); 
				  preparedStatement.setString(6,bean.getEmail());
				  preparedStatement.setInt(7,bean.getOcStatusId());
				  preparedStatement.setString(8, bean.getClosureReason());
				  preparedStatement.setString(9, bean.getUserName());
				  preparedStatement.setInt(10, bean.getReq_id());
				  
				  
				  logger.info("onClikUpdate - " + preparedStatement.unwrap(PreparedStatement.class));

				  
				  i = preparedStatement.executeUpdate();
				
				
			} catch (Exception e) {
				
				logger.fatal("------------>>>>>>"+e);
				
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



