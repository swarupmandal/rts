package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;
import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.ResourceAllocationTrackingBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.ResourceAllocationSql;
import org.appsquad.sql.ResourceAllocationTrackingSql;
import org.appsquad.utility.Dateformatter;
import org.appsquad.utility.Pstm;

public class ResourceAllocationTrackingDao {
	final static Logger logger = Logger.getLogger(ResourceAllocationTrackingDao.class);
	
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
				logger.info(" fetchClientDetails- " + preparedStatement.unwrap(PreparedStatement.class));
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
			logger.fatal(e);
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static int countStatusTrackingTable(Integer rId, int resId, int statusId){
		int count = 0;
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection, ResourceAllocationTrackingSql.countStatusTrackingTable, Arrays.asList(rId,resId,statusId));
				
				//logger.info(" countStatusTrackingTable- " + preparedStatement.unwrap(PreparedStatement.class));
				
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					count = resultSet.getInt(1);
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
			//logger.fatal(e);
			e.printStackTrace();
		}
		return count;
	}
	
	
	public static String getTypeName(Integer rId){
		String typeName = "";
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection, ResourceAllocationTrackingSql.getTypeIdWrtReqId, Arrays.asList(rId));
				
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					typeName = resultSet.getString("type_name");
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
			//logger.fatal(e);
			e.printStackTrace();
		}
		return typeName;
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
				preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.fetchRequirementSql, Arrays.asList(clId));
				
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					RequirementGenerationBean bean = new RequirementGenerationBean();
					bean.setReq_id(resultSet.getInt("r_id"));
					bean.setReqSkill(resultSet.getString("master_skill_set_name"));
					bean.getResourceTypeBean().setResourceTypeName(resultSet.getString("type_name"));
					bean.getResourceTypeBean().setResourceTypeId(resultSet.getInt("type_id"));
					bean.setRaiseDateStr(resultSet.getString("req_raise_date"));
					
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
			//logger.fatal(e);
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
				
				logger.info("fetchClientDetailsSearch - " + preparedStatement.unwrap(PreparedStatement.class));
				
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
			//logger.fatal(e);
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
				
				//logger.info("fetchReqirmentDetailsSearch - " + preparedStatement.unwrap(PreparedStatement.class));
				
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
			logger.fatal(e);
			e.printStackTrace();
		}
		return list;
	}
	
	public static ArrayList<RequirementGenerationBean> fetchReqirmentDetailsSearch(int id){
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
				preparedStatement = Pstm.createQuery(connection, ResourceAllocationTrackingSql.loadReqIdSearchWithRid, Arrays.asList("%"+id+"%"));
				
				logger.info("fetchReqirmentDetailsSearch - " + preparedStatement.unwrap(PreparedStatement.class));
				
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					RequirementGenerationBean bean = new RequirementGenerationBean();
					bean.setReq_id(resultSet.getInt("r_id"));
					bean.setReqSkill(resultSet.getString("master_skill_set_name"));
					bean.setOcStatus(resultSet.getString("status"));
					
					bean.setCreatedDate(resultSet.getDate("created_date"));
					bean.setCreatedDateValue(resultSet.getString("created_date"));
					if(bean.getCreatedDateValue() != null){
						bean.setCreatedDateStr(Dateformatter.toStringDate(bean.getCreatedDateValue()));
					}
					
					bean.setrIdType(resultSet.getString("type_name"));
					
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
			logger.fatal(e);
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static ArrayList<ResourceAllocationTrackingBean> fetchResAllTrackingDetails(int clId,Integer r_id){
		ArrayList<ResourceAllocationTrackingBean> list = new ArrayList<ResourceAllocationTrackingBean>();
		if(list.size()>0){
			list.clear();
		}
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection, ResourceAllocationTrackingSql.loadTrackingBean, Arrays.asList(r_id,clId));
				
				//logger.info("fetchResAllTrackingDetails - " + preparedStatement.unwrap(PreparedStatement.class));
				
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					ResourceAllocationTrackingBean bean = new ResourceAllocationTrackingBean();
					
					bean.resourceMasterBean.setResourceId(resultSet.getInt("resource_id"));
					bean.resourceMasterBean.setFullName(resultSet.getString("full_name"));
					bean.resourceMasterBean.setYearOfExperience(resultSet.getInt("res_experience"));
					bean.resourceMasterBean.setAddress(resultSet.getString("res_address"));
					bean.resourceMasterBean.setContactNumber(resultSet.getString("rts_contact_no"));
					bean.resourceMasterBean.setEmailId(resultSet.getString("res_emailid"));
					bean.resourceMasterBean.setCvPath(resultSet.getString("res_upcv"));
					bean.setResourceType(resultSet.getString("type_name"));
					bean.setResourceTypeId(resultSet.getInt("res_type_id"));
					bean.setStatus(resultSet.getString("final_status"));
					bean.setStatusId(resultSet.getInt("final_status_id"));
					bean.resourceMasterBean.setIsAllocable(resultSet.getString("non_allocable_or_not"));
					
					bean.setInternalInterviewDate(resultSet.getDate("internal_interview_date"));
					bean.setInternalInterviewDateValue(resultSet.getString("internal_interview_date"));
					if(bean.getInternalInterviewDate() != null){
						bean.setInternalInterviewDateStr(Dateformatter.toStringDate(bean.getInternalInterviewDateValue()));
					}
					
					bean.setClientInterviewDate(resultSet.getDate("client_interview_date"));
					bean.setClientInterviewDateValue(resultSet.getString("client_interview_date"));
					if(bean.getClientInterviewDateValue() != null){
						bean.setClientInterviewDateStr(Dateformatter.toStringDate(bean.getClientInterviewDateValue()));
					}
					
					bean.setOnboardDate(resultSet.getDate("onboard_date"));
					bean.setOnboardDateValue(resultSet.getString("onboard_date"));
					if(bean.getOnboardDateValue() != null){
						bean.setOnboardDateStr(Dateformatter.toStringDate(bean.getOnboardDateValue()));
					}	
					list.add(bean);
				}
				list.add(new ResourceAllocationTrackingBean());
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
			logger.fatal(e);
			e.printStackTrace();
		}
		return list;
	}
	
	public static ArrayList<ResourceAllocationTrackingBean> fetchResAllTrackingSearch(int clId,Integer r_id, String fullname){
		ArrayList<ResourceAllocationTrackingBean> list = new ArrayList<ResourceAllocationTrackingBean>();
		if(list.size()>0){
			list.clear();
		}
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection, ResourceAllocationTrackingSql.loadTrackingBeanSearch, Arrays.asList(r_id,clId, "%"+fullname.trim().toUpperCase()+"%"));
				
				//logger.info(" fetchResAllTrackingSearch- " + preparedStatement.unwrap(PreparedStatement.class));
				
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					ResourceAllocationTrackingBean bean = new ResourceAllocationTrackingBean();
					
					bean.resourceMasterBean.setResourceId(resultSet.getInt("resource_id"));
					bean.resourceMasterBean.setFullName(resultSet.getString("full_name"));
					bean.resourceMasterBean.setYearOfExperience(resultSet.getInt("res_experience"));
					bean.resourceMasterBean.setAddress(resultSet.getString("res_address"));
					bean.resourceMasterBean.setContactNumber(resultSet.getString("rts_contact_no"));
					bean.resourceMasterBean.setEmailId(resultSet.getString("res_emailid"));
					bean.resourceMasterBean.setCvPath(resultSet.getString("res_upcv"));
					bean.setResourceType(resultSet.getString("type_name"));
					bean.setResourceTypeId(resultSet.getInt("res_type_id"));
					bean.setStatus(resultSet.getString("final_status"));
					bean.setStatusId(resultSet.getInt("final_status_id"));
					
					bean.resourceMasterBean.setIsAllocable(resultSet.getString("non_allocable_or_not"));
					bean.setInternalInterviewDate(resultSet.getDate("internal_interview_date"));
					
					bean.setInternalInterviewDateValue(resultSet.getString("internal_interview_date"));
					if(bean.getInternalInterviewDate() != null){
						bean.setInternalInterviewDateStr(Dateformatter.toStringDate(bean.getInternalInterviewDateValue()));
					}
					bean.setClientInterviewDate(resultSet.getDate("client_interview_date"));
					bean.setClientInterviewDateValue(resultSet.getString("client_interview_date"));
					if(bean.getClientInterviewDateValue() != null){
						bean.setClientInterviewDateStr(Dateformatter.toStringDate(bean.getClientInterviewDateValue()));
					}
					bean.setOnboardDate(resultSet.getDate("onboard_date"));
					bean.setOnboardDateValue(resultSet.getString("onboard_date"));
					if(bean.getOnboardDateValue() != null){
						bean.setOnboardDateStr(Dateformatter.toStringDate(bean.getOnboardDateValue()));
					}	
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
			logger.fatal(e);
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static int inSertFinalStatus(Integer rId, int resId, int statusId, String userId){
		int i = 0;
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection, ResourceAllocationTrackingSql.inSertLastStatus, Arrays.asList(rId, resId, statusId,userId, userId));
				
				//logger.info(" inSertFinalStatus- " + preparedStatement.unwrap(PreparedStatement.class));
				
				i = preparedStatement.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();		
				}
			}
		} catch (Exception e) {
			logger.fatal(e);
			e.printStackTrace();
		}
		return i;
	}

	
	public static int inSertRejectStatus(Integer rId, Integer resId, Integer clientId,String userId){
		int i = 0;
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection, ResourceAllocationTrackingSql.insertIntoRejectMapper, Arrays.asList(rId, resId, clientId, userId));
				
				logger.info(" INSERT INTO REJECT MAPPER TABLE - " + preparedStatement.unwrap(PreparedStatement.class));
				
				i = preparedStatement.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();		
				}
			}
		} catch (Exception e) {
			logger.fatal(e);
			e.printStackTrace();
		}
		return i;
	}
	
	public static int intInterviewDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComments){
		int i = 0;
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = connection.prepareStatement(ResourceAllocationTrackingSql.insertIntInterviewDate);
				preparedStatement.setInt(1, rId);
				preparedStatement.setInt(2, resId);
				preparedStatement.setInt(3, clientId);
				if(date != null){
					preparedStatement.setDate(4, Dateformatter.sqlDate(date));
				}else {
					preparedStatement.setNull(4, Types.NULL);
				}
				preparedStatement.setString(5, userId);
				preparedStatement.setString(6, userId);
				if(otherComments!=null){
					preparedStatement.setString(7, otherComments);	
				}else{
					preparedStatement.setNull(7, Types.VARCHAR);
				}
				
				
				logger.info(" intInterviewDate- " + preparedStatement.unwrap(PreparedStatement.class));
				
				i = preparedStatement.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();		
				}
			}
		} catch (Exception e) {
			logger.fatal(e);
			e.printStackTrace();
		}
		return i;
	}
	
	public static int updateInterviewDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComments){
		int j = 0;
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = connection.prepareStatement(ResourceAllocationTrackingSql.UpdateIntInterviewDate);
				if(date != null){
					preparedStatement.setDate(1, Dateformatter.sqlDate(date));
				}else {
					preparedStatement.setNull(1, Types.NULL);
				}
				preparedStatement.setString(2, userId);
				preparedStatement.setString(3, userId);
				if(otherComments!=null){
					preparedStatement.setString(4, otherComments);	
				}else{
					preparedStatement.setNull(4, Types.VARCHAR);
				}
				preparedStatement.setInt(5, rId);
				preparedStatement.setInt(6, resId);
				preparedStatement.setInt(7, clientId);
				
				logger.info(" updateInterviewDate- " + preparedStatement.unwrap(PreparedStatement.class));
				
				j = preparedStatement.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();		
				}
			}
		} catch (Exception e) {
			logger.fatal(e);
			e.printStackTrace();
		}
		return j;
	}
	
	
	public static int updateResourceTable(Integer resId){
		int j = 0;
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = connection.prepareStatement(ResourceAllocationTrackingSql.updateResourceTable);
				preparedStatement.setInt(1, resId);
				logger.info(" update resource table - " + preparedStatement.unwrap(PreparedStatement.class));
				
				j = preparedStatement.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();		
				}
			}
		} catch (Exception e) {
			logger.fatal(e);
			e.printStackTrace();
		}
		return j;
	}
	
	
	public static Integer fetchAllocatedNumber(Integer r_id, String typeName){
		int value = 0;
		if(typeName.equalsIgnoreCase("CONTRACT")){
			try {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					connection = DbConnection.createConnection();
					preparedStatement = Pstm.createQuery(connection, ResourceAllocationTrackingSql.fetchConData, Arrays.asList(r_id));
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						value = resultSet.getInt(1);
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
				logger.fatal(e);
				e.printStackTrace();
			}
		}else{
			try {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					connection = DbConnection.createConnection();
					preparedStatement = Pstm.createQuery(connection, ResourceAllocationTrackingSql.fetchPerData, Arrays.asList(r_id));					
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						value = resultSet.getInt(1);
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
				logger.fatal(e);
				e.printStackTrace();
			}
		}
		return value;
	}
	
	
	public static int updateRejectedStatus(Integer rId, int resId,int clientId, String typeName){
		int p = 0;
		if(typeName.equalsIgnoreCase("CONTRACT")){
			try {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				try {
					connection = DbConnection.createConnection();
					preparedStatement = connection.prepareStatement(ResourceAllocationTrackingSql.updateRejectedSqlCon);
					preparedStatement.setInt(1, (fetchAllocatedNumber(rId, typeName)-1));
					preparedStatement.setInt(2, rId);
					
					logger.info(" UPDATE REJECTED STATUS(CON)- " + preparedStatement.unwrap(PreparedStatement.class));
					
					p = preparedStatement.executeUpdate();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if(preparedStatement != null){
						preparedStatement.close();
					}if(connection != null){
						connection.close();		
					}
				}
			} catch (Exception e) {
				logger.fatal(e);
				e.printStackTrace();
			}
		}else{
			try {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				try {
					connection = DbConnection.createConnection();
					preparedStatement = connection.prepareStatement(ResourceAllocationTrackingSql.updateRejectedSqlPer);
					preparedStatement.setInt(1, (fetchAllocatedNumber(rId, typeName)-1));
					preparedStatement.setInt(2, rId);
					
					logger.info(" UPDATE REJECTED STATUS(PER)- " + preparedStatement.unwrap(PreparedStatement.class));
					
					p = preparedStatement.executeUpdate();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if(preparedStatement != null){
						preparedStatement.close();
					}if(connection != null){
						connection.close();		
					}
				}
			} catch (Exception e) {
				logger.fatal(e);
				e.printStackTrace();
			}
		}
		return p;
	}
	
	public static int clientInterviewDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComments){
		int i = 0;
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = connection.prepareStatement(ResourceAllocationTrackingSql.insertClientInterviewDate);
				preparedStatement.setInt(1, rId);
				preparedStatement.setInt(2, resId);
				preparedStatement.setInt(3, clientId);
				if(date != null){
					preparedStatement.setDate(4, Dateformatter.sqlDate(date));
				}else {
					preparedStatement.setNull(4, Types.NULL);
				}
				preparedStatement.setString(5, userId);
				preparedStatement.setString(6, userId);
				if(otherComments!=null){
					preparedStatement.setString(7, otherComments);	
				}else{
					preparedStatement.setNull(7, Types.VARCHAR);
				}
				
				logger.info(" clientInterviewDate- " + preparedStatement.unwrap(PreparedStatement.class));
				
				i = preparedStatement.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();		
				}
			}
		} catch (Exception e) {
			logger.fatal(e);
			e.printStackTrace();
		}
		return i;
	}
	
	public static int updateClientInterviewDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComments){
		int i = 0;
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = connection.prepareStatement(ResourceAllocationTrackingSql.updateClientInterviewDate);
				if(date != null){
					preparedStatement.setDate(1, Dateformatter.sqlDate(date));
				}else {
					preparedStatement.setNull(1, Types.NULL);
				}
				preparedStatement.setString(2, userId);
				preparedStatement.setString(3, userId);
				if(otherComments!=null){
					preparedStatement.setString(4, otherComments);	
				}else{
					preparedStatement.setNull(4, Types.VARCHAR);
				}
				preparedStatement.setInt(5, rId);
				preparedStatement.setInt(6, resId);
				preparedStatement.setInt(7, clientId);
				
				logger.info("updateClientInterviewDate- " + preparedStatement.unwrap(PreparedStatement.class));
				
				i = preparedStatement.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();		
				}
			}
		} catch (Exception e) {
			logger.fatal(e);
			e.printStackTrace();
		}
		return i;
	}
	
	public static int onboardDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComments){
		int i = 0;
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = connection.prepareStatement(ResourceAllocationTrackingSql.insertOnboardDate);
				preparedStatement.setInt(1, rId);
				preparedStatement.setInt(2, resId);
				preparedStatement.setInt(3, clientId);
				if(date != null){
					preparedStatement.setDate(4, Dateformatter.sqlDate(date));
				}else {
					preparedStatement.setNull(4, Types.NULL);
				}
				preparedStatement.setString(5, userId);
				preparedStatement.setString(6, userId);
				if(otherComments!=null){
					preparedStatement.setString(7, otherComments);	
				}else{
					preparedStatement.setNull(7, Types.VARCHAR);
				}
				logger.info("onboardDate- " + preparedStatement.unwrap(PreparedStatement.class));
				
				i = preparedStatement.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();		
				}
			}
		} catch (Exception e) {
			logger.fatal(e);
			e.printStackTrace();
		}
		return i;
	}
	
	public static int updateOnboardDate(Integer rId, int resId,int clientId, Date date, String userId, String otherComments){
		int i = 0;
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = connection.prepareStatement(ResourceAllocationTrackingSql.updateOnboardDate);
				if(date != null){
					preparedStatement.setDate(1, Dateformatter.sqlDate(date));
				}else {
					preparedStatement.setNull(1, Types.NULL);
				}
				preparedStatement.setString(2, userId);
				preparedStatement.setString(3, userId);
				if(otherComments!=null){
					preparedStatement.setString(4, otherComments);	
				}else{
					preparedStatement.setNull(4, Types.VARCHAR);
				}
				preparedStatement.setInt(5, rId);
				preparedStatement.setInt(6, resId);
				preparedStatement.setInt(7, clientId);
				
				logger.info("updateOnboardDate- " + preparedStatement.unwrap(PreparedStatement.class));
				
				i = preparedStatement.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();		
				}
			}
		} catch (Exception e) {
			logger.fatal(e);
			e.printStackTrace();
		}
		return i;
	}
}
