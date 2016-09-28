package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.ResourceAllocationTrackingBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.ResourceAllocationTrackingSql;
import org.appsquad.utility.Dateformatter;
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
	
	
	public static int countStatusTrackingTable(Integer rId, int resId, int statusId){
		int count = 0;
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection, ResourceAllocationTrackingSql.countStatusTrackingTable, Arrays.asList(rId,resId,statusId));
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
			e.printStackTrace();
		}
		return count;
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
			e.printStackTrace();
		}
		return i;
	}
	
	public static int intInterviewDate(Integer rId, int resId,int clientId, Date date, String userId){
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
			e.printStackTrace();
		}
		return i;
	}
	
	public static int updateInterviewDate(Integer rId, int resId,int clientId, Date date, String userId){
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
				preparedStatement.setInt(4, rId);
				preparedStatement.setInt(5, resId);
				preparedStatement.setInt(6, clientId);
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
			e.printStackTrace();
		}
		return j;
	}
	
	public static int clientInterviewDate(Integer rId, int resId,int clientId, Date date, String userId){
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
			e.printStackTrace();
		}
		return i;
	}
	
	public static int updateClientInterviewDate(Integer rId, int resId,int clientId, Date date, String userId){
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
				preparedStatement.setInt(4, rId);
				preparedStatement.setInt(5, resId);
				preparedStatement.setInt(6, clientId);
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
			e.printStackTrace();
		}
		return i;
	}
	
	public static int onboardDate(Integer rId, int resId,int clientId, Date date, String userId){
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
			e.printStackTrace();
		}
		return i;
	}
	
	public static int updateOnboardDate(Integer rId, int resId,int clientId, Date date, String userId){
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
				preparedStatement.setInt(4, rId);
				preparedStatement.setInt(5, resId);
				preparedStatement.setInt(6, clientId);
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
			e.printStackTrace();
		}
		return i;
	}
}
