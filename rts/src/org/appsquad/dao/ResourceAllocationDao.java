package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.Logger;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.bean.ResourceAllocationBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.bean.ResourceTypeBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.ResourceAllocationSql;
import org.appsquad.utility.Pstm;

public class ResourceAllocationDao {
	final static Logger logger = Logger.getLogger(ResourceAllocationDao.class);
	
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
						    logger.info("onLoadReqDeatilsWrtClientName - " + preparedStatement.unwrap(PreparedStatement.class));
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								RequirementGenerationBean bean = new RequirementGenerationBean();
								bean.setRequirementId(resultSet.getInt("r_id"));
								bean.setReqSkillId(resultSet.getInt("id"));
								bean.setReqSkill(resultSet.getString("master_skill_set_name"));
								bean.getResourceTypeBean().setResourceTypeName(resultSet.getString("type_name"));
								bean.getResourceTypeBean().setResourceTypeId(resultSet.getInt("type_id"));
								bean.setRaiseDateStr(resultSet.getString("req_raise_date"));
								bean.setClientOriginalName(resultSet.getString("clientname"));
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
						    
						    logger.info("fetchSkillDetails - " + preparedStatement.unwrap(PreparedStatement.class));
						    
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
	
	
	public static void getAllFieldData(ResourceAllocationBean resourceAllocationBean){
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.fetchAllFieldSql, Arrays.asList(resourceAllocationBean.getClientInformationBean().getClientId(), 
						    		                                                                                 resourceAllocationBean.getRequirementGenerationBean().getRequirementId()));
							System.out.println(preparedStatement);
						    ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								resourceAllocationBean.getResourceTypeBean().setResourceTypeId(resultSet.getInt("type_id"));
								resourceAllocationBean.getResourceTypeBean().setResourceTypeName(resultSet.getString("type_name"));
								resourceAllocationBean.getMasterbean().setSkillset(resultSet.getString("master_skill_set_name"));
								if(resourceAllocationBean.getResourceTypeBean().getResourceTypeName().equalsIgnoreCase("CONTRACT")){
                                   resourceAllocationBean.setRequiredResourcenumber(resultSet.getInt("req_no_of_con_res"));	
                                   resourceAllocationBean.setAllocatedResourceNumber(resultSet.getInt("num_of_con_res_allocated"));	
								}else{
								   resourceAllocationBean.setRequiredResourcenumber(resultSet.getInt("req_no_of_per_res"));
								   resourceAllocationBean.setAllocatedResourceNumber(resultSet.getInt("num_of_per_res_allocated"));	
								}						
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
							    
							    logger.info("fetchRequiredResourceNumber - " + preparedStatement.unwrap(PreparedStatement.class));
							    
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
							    logger.info("fetchRequiredResourceNumber1 - " + preparedStatement.unwrap(PreparedStatement.class));
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
							    
							    logger.info(" fetchRequiredResourceNumberAllocated- " + preparedStatement.unwrap(PreparedStatement.class));
							    
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
							    
							    logger.info(" fetchRequiredResourceNumberAllocated1- " + preparedStatement.unwrap(PreparedStatement.class));
							    
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
	
	public static ArrayList<ResourceMasterBean> onLoadResourceDetails(ResourceAllocationBean resourceAllocationBean){
		ArrayList<ResourceMasterBean> resourceList = new ArrayList<ResourceMasterBean>();
		if(resourceList.size()>0){
			resourceList.clear();
		}
		Connection connection = null;
		int count = 0;
		int countNumber = 0;
		int presentForPreBillResource = 0;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.fetchResourceDetails, Arrays.asList(resourceAllocationBean.getMasterbean().getSkillset()));
						    System.out.println("RESOURCE DETAILS QUERY :"+preparedStatement);
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								ResourceMasterBean bean = new ResourceMasterBean();
								bean.setResourceId(resultSet.getInt("id"));
								bean.setFullName(resultSet.getString("res_name")+" "+resultSet.getString("res_surname"));
								bean.setYearOfExperience(resultSet.getInt("res_experience"));
								bean.setAddress(resultSet.getString("res_address"));
								bean.setEmailId(resultSet.getString("res_emailid"));
								bean.getSkillsetMasterbean().setSkillset(resultSet.getString("rts_skill_name"));
								countNumber = presentInMapperTableForResAndClientID(bean.getResourceId(), resourceAllocationBean.getClientInformationBean().getClientId(),resourceAllocationBean.getRequirementGenerationBean().getRequirementId());
								System.out.println("COUNT NUMBER IS :"+countNumber);
								if((countNumber>0)){
										
								}else{
									presentForPreBillResource = presentInTrackingTableForPreBill(bean.getResourceId(), connection);
									if(!(presentForPreBillResource>0)){
										resourceList.add(bean);
									}
								}
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
	
	public static Integer presentInTrackingTableForPreBill(Integer resId,Connection connection) throws Exception{
		int count = 0;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			String sql = "select count(*) from rts_req_res_status_tracking where resource_id = ? and status_id = (select id from rts_status_master where is_pre_bill = 'Y ')";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, resId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				count = resultSet.getInt(1);
			}
		}finally{
			if(preparedStatement!=null){
				preparedStatement.close();
			}
		}
		return count;
	}
	
	public static Integer presentInMapperTable(Integer reqId, Integer resId){
		int count = 0;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.countFromMapperSql, Arrays.asList(reqId,resId));
						    
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								count = resultSet.getInt(1);
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
		return count;	
	}
	
	
	public static Integer presentInMapperTableForResAndClientID(Integer resId, Integer clientId, Integer reqId){
		int count = 0;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.countFromMapperSqlForResAndClient, Arrays.asList(resId,clientId,reqId));
						    logger.info(" QUERY FOR RES AND CLIENT AND REQ COMBINATION- " + preparedStatement.unwrap(PreparedStatement.class));
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								count = resultSet.getInt(1);
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
		return count;	
	}
	
	public static boolean updateResourceTable(ArrayList<ResourceMasterBean> list){
		boolean isUpdated = false;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					for(ResourceMasterBean bean: list){
						if(bean.isChkSelect()){
							//1st SQL block
							sql_insert:{
							    PreparedStatement preparedStatementInsert = null;
							    try {
							    	preparedStatementInsert = Pstm.createQuery(connection, 
											ResourceAllocationSql.updateResourceTableSql, Arrays.asList(bean.getResourceId()));
							    	
									logger.info(" updateResourceTable- " + preparedStatementInsert.unwrap(PreparedStatement.class));
							    	
									int i = preparedStatementInsert.executeUpdate();
									if(i>0){
										isUpdated = true;	
									}
								} finally{
									if(preparedStatementInsert!=null){
										preparedStatementInsert.close();
									}
								}
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
		return isUpdated;
	}
	
	
	public static boolean insertIntoMapper(ArrayList<ResourceMasterBean> list,ResourceAllocationBean allocationBean){
		boolean isInsertedMapper = false;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					for(ResourceMasterBean bean: list){
						if(bean.isChkSelect()){
							//1st SQL block
							sql_insert:{
							    PreparedStatement preparedStatementInsert = null;
							    try {
							    	preparedStatementInsert = Pstm.createQuery(connection, 
											ResourceAllocationSql.insertIntoMapperSql, Arrays.asList(allocationBean.getRequirementGenerationBean().getRequirementId(),
													                                 bean.getResourceId(),allocationBean.getClientInformationBean().getClientId(),
													                                 allocationBean.getUserId(),allocationBean.getResourceTypeBean().getResourceTypeId()));
							    	
							    	logger.info(" insertIntoMapper- " + preparedStatementInsert.unwrap(PreparedStatement.class));
									int i = preparedStatementInsert.executeUpdate();
									if(i>0){
										isInsertedMapper = true;	
									}
								} finally{
									if(preparedStatementInsert!=null){
										preparedStatementInsert.close();
									}
								}
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
		return isInsertedMapper;
	}
	
	public static boolean insertIntoStatus(ArrayList<ResourceMasterBean> list,ResourceAllocationBean allocationBean){
		boolean isInsertedStatus = false;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					for(ResourceMasterBean bean: list){
						if(bean.isChkSelect()){
							//1st SQL block
							sql_insert:{
							    PreparedStatement preparedStatementInsert = null;
							    try {
							    	preparedStatementInsert = Pstm.createQuery(connection, 
									ResourceAllocationSql.insertIntoTrackingHistoryTableSql, Arrays.asList(allocationBean.getRequirementGenerationBean().getRequirementId(),
													                                 					   bean.getResourceId(),allocationBean.getStatusId(),
													                                 					   allocationBean.getUserId()));
							    	
							    	logger.info(" INSERT INTO STATUS TRACKING TABLE FROM RESOURCE ALLOCATION SCREEN- " + preparedStatementInsert.unwrap(PreparedStatement.class));
									int i = preparedStatementInsert.executeUpdate();
									if(i>0){
										isInsertedStatus = true;	
									}
								} finally{
									if(preparedStatementInsert!=null){
										preparedStatementInsert.close();
									}
								}
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
		return isInsertedStatus;
	}
	
	public static Integer fetchStatusId(){
		int statusId = 0;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.fetchStatusIdSql, null);
						    logger.info(" fetchStatusId- " + preparedStatement.unwrap(PreparedStatement.class));
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								statusId = resultSet.getInt("id");
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
		return statusId;	
	}
	
	
	public static boolean updateResourceTableNumber(Integer clientId,Integer reqId,String type,Integer number){
		boolean isUpdatedResource = false;
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
							    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.updateResourceTableConSql, Arrays.asList(number,clientId,reqId));
							    logger.info(" updateResourceTableNumber- " + preparedStatement.unwrap(PreparedStatement.class));
								int i = preparedStatement.executeUpdate();
								if(i>0){
									isUpdatedResource = true;	
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
		}else if (type.startsWith("PERMANANT")){
			try {
				connection = DbConnection.createConnection();
				sql_connection:{
					try {
						
						//1st SQL block
						sql_fetch:{
						   PreparedStatement preparedStatement = null;
						   try {
							    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.updateResourceTablePerSql, Arrays.asList(number,clientId,reqId));
							    logger.info(" updateResourceTableNumber1- " + preparedStatement.unwrap(PreparedStatement.class));
								int i = preparedStatement.executeUpdate();
								if(i>0){
									isUpdatedResource = true;	
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
		}
		return isUpdatedResource;	
	}
}
