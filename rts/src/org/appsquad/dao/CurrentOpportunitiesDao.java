package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.appsquad.bean.CurrentOpportunitiesBean;
import org.appsquad.bean.UserClientMappingBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.CurrentOpportunitiesSql;
import org.appsquad.utility.Pstm;
import org.zkoss.zul.Messagebox;

public class CurrentOpportunitiesDao {
	final static Logger logger = Logger.getLogger(CurrentOpportunitiesDao.class);
	
	public static String getDataEntryOperatorName(Integer trackingId) throws Exception{
		String name = "";
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try{
			connection = DbConnection.createConnection();
			String sql = "select user_id from rts_req_res_status_tracking_details where rts_req_res_status_tracking_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, trackingId);
			System.out.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				name = resultSet.getString("user_id");
			}
		}finally{
			if(connection!=null){
				connection.close();
			}
			if(preparedStatement!=null){
				preparedStatement.close();
			}
		}
		return name;
	}
	
	public static String getDataEntryOperatorEmailId(Integer trackingId,Connection connection,String userID) throws Exception{
		String emailID = "";
		PreparedStatement preparedStatement = null;
		try{
			String sql = "select b.email from rts_req_res_status_tracking_details a,rts_user_master b where a.user_id = b.user_id and rts_tracking_details_id = ? and b.user_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, trackingId);
			preparedStatement.setString(2, userID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				emailID = resultSet.getString("email");
			}
		}finally{
			if(preparedStatement!=null){
				preparedStatement.close();
			}
		}
		return emailID;
	}
	
	
	public static ArrayList<CurrentOpportunitiesBean> loadCurrenOpportunity(){
		ArrayList<CurrentOpportunitiesBean> list = new ArrayList<CurrentOpportunitiesBean>();
		if(list.size()>0){
			list.clear();
		}
		try {
			Connection connection = DbConnection.createConnection();
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesSql.loadOpportumityDetailsQuery, null);
				logger.info("IN PRE BILLING SCREEN LOAD QUERY :::: "+ preparedStatement.unwrap(PreparedStatement.class));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesBean bean = new CurrentOpportunitiesBean();
					
					bean.setReqResStatusTrackingId(resultSet.getInt("rts_req_res_status_tracking_id"));
					bean.setClientId(resultSet.getInt("client_id"));
					bean.setClientName(resultSet.getString("clientname"));
					bean.setRid(resultSet.getInt("r_id"));
					bean.setResourceId(resultSet.getInt("resource_id"));
					bean.setResourceName(resultSet.getString("resource_name"));
					bean.setResourceType(resultSet.getString("type_name"));
					bean.setSkillset(resultSet.getString("rts_skill_name"));
					fetchTrackingDetailsWrtTrackingId(connection, bean.getReqResStatusTrackingId(), bean);
					bean.setUserClBeanList(userBeanList(connection, bean.getClientId()));
					
					list.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
				String msg = e.getMessage();
				Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
				logger.fatal("Load Current Opportunities - " + e);
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void fetchTrackingDetailsWrtTrackingId(Connection connection, Integer trackingId,CurrentOpportunitiesBean currentOpportunitiesBean) throws SQLException{
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesSql.fetchTrackingDetailsWrtTrackingIdSql, Arrays.asList(trackingId));
			logger.info("FETCH TRACKING DETAILS WITH RESPECT TO TRACK-ID >>> >> > "+ preparedStatement.unwrap(PreparedStatement.class));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
		       currentOpportunitiesBean.setTenureFromsql(resultSet.getDate("tenure_from"));
		       currentOpportunitiesBean.setTenureTosql(resultSet.getDate("tenure_to"));
		       currentOpportunitiesBean.setChargeoutRate(resultSet.getDouble("charge_out_rate"));
		       currentOpportunitiesBean.setResourceSalary(resultSet.getDouble("resource_salary"));
		       currentOpportunitiesBean.setMargin(resultSet.getDouble("margin"));
		       currentOpportunitiesBean.setApproval(resultSet.getString("approval_status"));
		       currentOpportunitiesBean.getBean().setUserID(resultSet.getString("approvar_person"));
		       currentOpportunitiesBean.setPercentage(resultSet.getDouble("percentage"));
		       
			}
		} finally {
			if(preparedStatement !=null){
				preparedStatement.close();
			}
		}
	}
	
	public static String getSplitedId(ArrayList<Integer> list){
		String code = "";
		for(Integer data : list){
			code += "'"+data+"',";
		}
		code = code.substring(0, code.length()-1);
		return code;
	}
	
	public static ArrayList<CurrentOpportunitiesBean> loadCurrenOpportunityForApprover(ArrayList<Integer> idList){
		ArrayList<CurrentOpportunitiesBean> list = new ArrayList<CurrentOpportunitiesBean>();
		if(list.size()>0){
			list.clear();
		}
		try {
			Connection connection = DbConnection.createConnection();
			PreparedStatement preparedStatement = null;
			try {
				String sql = "select * from vw_opportunity_details where status_id = (select id from rts_status_master where is_pre_bill = 'Y') and client_id IN ( "+ getSplitedId(idList) +") ";
				preparedStatement = connection.prepareStatement(sql);
				logger.info("Current Opportunities Load For Approver>>> >> > "+ preparedStatement.unwrap(PreparedStatement.class));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesBean bean = new CurrentOpportunitiesBean();
					
					bean.setReqResStatusTrackingId(resultSet.getInt("rts_req_res_status_tracking_id"));
					bean.setClientId(resultSet.getInt("client_id"));
					bean.setClientName(resultSet.getString("clientname"));
					bean.setRid(resultSet.getInt("r_id"));
					bean.setResourceId(resultSet.getInt("resource_id"));
					bean.setResourceName(resultSet.getString("resource_name"));
					bean.setResourceType(resultSet.getString("type_name"));
					bean.setSkillset(resultSet.getString("rts_skill_name"));
					fetchTrackingDetailsWrtTrackingId(connection, bean.getReqResStatusTrackingId(), bean);
					bean.setUserClBeanList(userBeanList(connection, bean.getClientId()));
					
					list.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
				String msg = e.getMessage();
				Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
				logger.fatal("Load Current Opportunities - " + e);
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static ArrayList<Integer> fetchClientIdListWrtUserId(String userId){
		ArrayList<Integer> list = new ArrayList<Integer>();
		if(list.size()>0){
			list.clear();
		}
		try {
			Connection connection = DbConnection.createConnection();
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesSql.fetchClientIdSql, Arrays.asList(userId));
				logger.info("Fetch Client Id List: "+ preparedStatement.unwrap(PreparedStatement.class));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					list.add(resultSet.getInt("client_id"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				String msg = e.getMessage();
				Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
				logger.fatal("Load Current Opportunities - " + e);
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static int checjCountWhetherTraetDataEntryOrApprover(String userId,String clientName){
		int count = 0;
		try {
			Connection connection = DbConnection.createConnection();
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesSql.countTreatSql, Arrays.asList(clientName,userId));
				logger.info("Fetch Client Id List: "+ preparedStatement.unwrap(PreparedStatement.class));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					count = resultSet.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
				String msg = e.getMessage();
				Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
				logger.fatal("Load Current Opportunities - " + e);
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public static ArrayList<UserClientMappingBean> userBeanList(Connection connection, Integer clientId) throws SQLException{
		ArrayList<UserClientMappingBean> list = new ArrayList<UserClientMappingBean>();
		if(list.size()>0){
			list.clear();
		}
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesSql.loadUserDetailsList, Arrays.asList(clientId));
			logger.info("User Client Load >>> >> > "+ preparedStatement.unwrap(PreparedStatement.class));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserClientMappingBean bean = new UserClientMappingBean();
				bean.setUserID(resultSet.getString("user_id"));
				
				list.add(bean);
			}
		} finally {
			if(preparedStatement !=null){
				preparedStatement.close();
			}
		}
		return list;
	}

	
	public static ArrayList<UserClientMappingBean> userBeanListForFirstTime(String userId) throws SQLException{
		ArrayList<UserClientMappingBean> clList = new ArrayList<UserClientMappingBean>();
		if(clList.size()>0){
			clList.clear();
		}
		PreparedStatement preparedStatement = null;
		try {
			UserClientMappingBean bean = new UserClientMappingBean();
			bean.setUserID(userId);
			
			clList.add(bean);
		} finally {
			if(preparedStatement !=null){
				preparedStatement.close();
			}
		}
		return clList;
	}
	
	public static String fetchRoleNameService(String UserId){
		String role = "";
		try {
			Connection connection = DbConnection.createConnection();
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesSql.fetchRoleName, Arrays.asList(UserId));
				logger.info("Fetch Role Name: "+ preparedStatement.unwrap(PreparedStatement.class));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					role = resultSet.getString("master_role_name");
				}
			} catch (Exception e) {
				e.printStackTrace();
				String msg = e.getMessage();
				Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
				logger.fatal("Load Current Opportunities - " + e);
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.fatal(e);
		}
		return role;
	}
	
	public static String fetchEmailId(String UserId){
		String email = "";
		try {
			Connection connection = DbConnection.createConnection();
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesSql.fetchEmailWrtUserIdSql, Arrays.asList(UserId));
				logger.info("Fetch Email: "+ preparedStatement.unwrap(PreparedStatement.class));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					email = resultSet.getString("email");
				}
			} catch (Exception e) {
				e.printStackTrace();
				String msg = e.getMessage();
				Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
				logger.fatal("Load Current Opportunities - " + e);
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.fatal(e);
		}
		return email;
	}
	
	public static Integer fetchCountTrackingIdService(Integer trackingId){
		int count = 0;
		try {
			Connection connection = DbConnection.createConnection();
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesSql.fetchCountTrackingIdSql, Arrays.asList(trackingId));
				logger.info("Fetch Count Number: "+ preparedStatement.unwrap(PreparedStatement.class));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					count = resultSet.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
				String msg = e.getMessage();
				Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
				logger.fatal("Load Current Opportunities - " + e);
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.fatal(e);
		}
		return count;
	}

	public static boolean insertTrackingData(CurrentOpportunitiesBean opportunitiesBean){
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
									CurrentOpportunitiesSql.insertTrackingSql, Arrays.asList(opportunitiesBean.getReqResStatusTrackingId(),opportunitiesBean.getTenureFromsql(),
											                                     opportunitiesBean.getTenureTosql(),opportunitiesBean.getChargeoutRate(),
											                                     opportunitiesBean.getResourceSalary(),opportunitiesBean.getMargin(),
											                                     opportunitiesBean.getApproval(),opportunitiesBean.getBean().getUserID(),
											                                     opportunitiesBean.getLoginID(),opportunitiesBean.getPercentage()));
					    	logger.info("insertTrackingData- " + preparedStatementInsert.unwrap(PreparedStatement.class));
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
				
					if(isSaved && opportunitiesBean.getOnClickButtonValue().equalsIgnoreCase("onSend")){
						Messagebox.show(" Approval Request Sent Successfully ","Information",Messagebox.OK,Messagebox.INFORMATION);
					}
					
					if(isSaved && opportunitiesBean.getOnClickButtonValue().equalsIgnoreCase("onCreate")){
						System.out.println(" Approved Successfully ");
					}
					
					if(isSaved && opportunitiesBean.getOnClickButtonValue().equalsIgnoreCase("onSave")){
						System.out.println(" Approved Successfully ");
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
		return isSaved;
	}
	
	
	public static boolean deleteRoleData(CurrentOpportunitiesBean opportunitiesBean){
		boolean isDeleted= false;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_insert:{
					    PreparedStatement preparedStatementInsert = null;
					    try {
					    	preparedStatementInsert = Pstm.createQuery(connection,CurrentOpportunitiesSql.deleteTrackingIdSql, Arrays.asList(opportunitiesBean.getReqResStatusTrackingId()));
					    	logger.info("deleteTrackingIdData- " + preparedStatementInsert.unwrap(PreparedStatement.class));
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
	
	public static boolean updateDetailsTableDao(CurrentOpportunitiesBean opportunitiesBean){
		boolean isDetailsUpdate= false;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_insert:{
					    PreparedStatement preparedStatementInsert = null;
					    try {
					    	preparedStatementInsert = Pstm.createQuery(connection,CurrentOpportunitiesSql.updateDetailsTableSql, Arrays.asList(opportunitiesBean.getApproval(),opportunitiesBean.getReqResStatusTrackingId()));
					    	logger.info("update Tracking Details Data- " + preparedStatementInsert.unwrap(PreparedStatement.class));
							int i = preparedStatementInsert.executeUpdate();
							if(i>0){
								isDetailsUpdate = true;	
							}
						} finally{
							if(preparedStatementInsert!=null){
								preparedStatementInsert.close();
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
		return isDetailsUpdate;
	}
	
	public static boolean updateTrackingTableDao(CurrentOpportunitiesBean opportunitiesBean){
		boolean isTrackingUpdate= false;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_insert:{
					    PreparedStatement preparedStatementInsert = null;
					    try {
					    	preparedStatementInsert = Pstm.createQuery(connection,CurrentOpportunitiesSql.updateTrackingTableSql, Arrays.asList(opportunitiesBean.getApproval(),opportunitiesBean.getReqResStatusTrackingId()));
					    	logger.info("update Tracking Data- " + preparedStatementInsert.unwrap(PreparedStatement.class));
							int i = preparedStatementInsert.executeUpdate();
							if(i>0){
								isTrackingUpdate = true;	
							}
						} finally{
							if(preparedStatementInsert!=null){
								preparedStatementInsert.close();
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
		return isTrackingUpdate;
	}
	
	public static String fetchApproverNameDao(Integer trackingId){
		String approverName = "";
		try {
			Connection connection = DbConnection.createConnection();
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesSql.fetchApproverNameSql, Arrays.asList(trackingId));
				logger.info("Fetch Approver Name: "+ preparedStatement.unwrap(PreparedStatement.class));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					approverName = resultSet.getString("approvar_person");
				}
			} catch (Exception e) {
				e.printStackTrace();
				String msg = e.getMessage();
				Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
				logger.fatal("Load Current Opportunities - " + e);
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.fatal(e);
		}
		return approverName;
	}
	
	
	public static int coutrowForDataEntryAndApproverBoth(CurrentOpportunitiesBean opportunitiesBean){
		int count = 0;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_insert:{
					    PreparedStatement preparedStatementInsert = null;
					    try {
					    	preparedStatementInsert = Pstm.createQuery(connection,CurrentOpportunitiesSql.countTrackingIdSql, Arrays.asList(opportunitiesBean.getReqResStatusTrackingId()));
					    	logger.info("deleteTrackingIdData- " + preparedStatementInsert.unwrap(PreparedStatement.class));
							ResultSet resultSet = preparedStatementInsert.executeQuery();
							while(resultSet.next()){
								count = resultSet.getInt(1);
							}
						} finally{
							if(preparedStatementInsert!=null){
								preparedStatementInsert.close();
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
	
}
