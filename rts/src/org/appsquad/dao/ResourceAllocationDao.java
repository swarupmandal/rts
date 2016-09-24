package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.bean.ResourceTypeBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.ResourceAllocationSql;
import org.appsquad.utility.Pstm;

public class ResourceAllocationDao {
	
	public static ArrayList<RequirementGenerationBean> onLoadRequirementSkillDetails(Integer clientId){
		ArrayList<RequirementGenerationBean> requirementList = new ArrayList<RequirementGenerationBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.fetchRequirementSql, Arrays.asList(clientId));
						    System.out.println(preparedStatement);
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								RequirementGenerationBean bean = new RequirementGenerationBean();
								bean.setRequirementId(resultSet.getInt("r_id"));
								bean.setReqSkillId(resultSet.getInt("id"));
								bean.setReqSkill(resultSet.getString("master_skill_set_name"));
								
								requirementList.add(bean);
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
		return requirementList;	
	}
	
	public static String fetchSkillDetails(Integer clientId,Integer reqId){
		String skillName = "";
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.fetchSkillName, Arrays.asList(clientId,reqId));
						    System.out.println(preparedStatement);
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								skillName = resultSet.getString("master_skill_set_name");
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
		return skillName;	
	}
	
	public static Integer fetchRequiredResourceNumber(Integer clientId,Integer reqId,String type){
		int countNumber = 0;
		Connection connection = null;
		if(type.startsWith("CONTRACT")){
			try {
				connection = DbConnection.createConnection();
				sql_connection:{
					try {
						
						//1st SQL block
						sql_fetch:{
						   PreparedStatement preparedStatement = null;
						   try {
							    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.fetchRequiredResourceNumberCon, Arrays.asList(clientId,reqId));
							    System.out.println(preparedStatement);
								ResultSet resultSet = preparedStatement.executeQuery();
								while (resultSet.next()) {
									countNumber = resultSet.getInt("req_no_of_con_res");
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
		}else if (type.startsWith("PERMANANT")){
			try {
				connection = DbConnection.createConnection();
				sql_connection:{
					try {
						
						//1st SQL block
						sql_fetch:{
						   PreparedStatement preparedStatement = null;
						   try {
							    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.fetchRequiredResourceNumberPer, Arrays.asList(clientId,reqId));
							    System.out.println(preparedStatement);
								ResultSet resultSet = preparedStatement.executeQuery();
								while (resultSet.next()) {
									countNumber = resultSet.getInt("req_no_of_per_res");
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
		}
		return countNumber;	
	}
	
	public static Integer fetchRequiredResourceNumberAllocated(Integer clientId,Integer reqId,String type){
		int countNumberAllocated = 0;
		Connection connection = null;
		if(type.startsWith("CONTRACT")){
			try {
				connection = DbConnection.createConnection();
				sql_connection:{
					try {
						
						//1st SQL block
						sql_fetch:{
						   PreparedStatement preparedStatement = null;
						   try {
							    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.fetchRequiredResourceNumberConAllocated, Arrays.asList(clientId,reqId));
							    System.out.println(preparedStatement);
								ResultSet resultSet = preparedStatement.executeQuery();
								while (resultSet.next()) {
									countNumberAllocated = resultSet.getInt("num_of_con_res_allocated");
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
		}else if (type.startsWith("PERMANANT")){
			try {
				connection = DbConnection.createConnection();
				sql_connection:{
					try {
						
						//1st SQL block
						sql_fetch:{
						   PreparedStatement preparedStatement = null;
						   try {
							    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.fetchRequiredResourceNumberPerAllocated, Arrays.asList(clientId,reqId));
							    System.out.println(preparedStatement);
								ResultSet resultSet = preparedStatement.executeQuery();
								while (resultSet.next()) {
									countNumberAllocated = resultSet.getInt("num_of_per_res_allocated");
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
		}
		return countNumberAllocated;	
	}
	
	public static ArrayList<ResourceTypeBean> onLoadResourceTypeDetails(){
		ArrayList<ResourceTypeBean> typeList = new ArrayList<ResourceTypeBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.fetchTypeSql, null);
						    System.out.println(preparedStatement);
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								ResourceTypeBean resourceTypeBean = new ResourceTypeBean();
								resourceTypeBean.setResourceTypeId(resultSet.getInt("type_id"));
								resourceTypeBean.setResourceTypeName(resultSet.getString("type_name"));
								
								typeList.add(resourceTypeBean);
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
		return typeList;	
	}
	
	public static ArrayList<ResourceMasterBean> onLoadResourceDetails(){
		ArrayList<ResourceMasterBean> resourceList = new ArrayList<ResourceMasterBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.fetchResourceDetails, null);
						    System.out.println(preparedStatement);
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								ResourceMasterBean bean = new ResourceMasterBean();
								bean.setResourceId(resultSet.getInt("id"));
								bean.setFullName(resultSet.getString("res_name")+" "+resultSet.getString("res_surname"));
								bean.setYearOfExperience(resultSet.getInt("res_experience"));
								bean.setAddress(resultSet.getString("res_address"));
								bean.setEmailId(resultSet.getString("res_emailid"));
								
								resourceList.add(bean);
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
		return resourceList;	
	}
	
}
