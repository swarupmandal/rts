package org.appsquad.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import org.apache.log4j.Logger;
import org.appsquad.bean.IndividualClientReportBean;
import org.appsquad.bean.IndividualClientReportSubBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.IndividualClientReportSql;
import org.appsquad.sql.IndividualClientRequirementSql;
import org.appsquad.utility.Dateformatter;
import org.appsquad.utility.Pstm;

public class IndividualClientReportDao {

	final static Logger logger=Logger.getLogger(IndividualClientReportDao.class);
	
	public static ArrayList<IndividualClientReportSubBean> loadRidDetails(Connection connection ,int id){
		ArrayList<IndividualClientReportSubBean> arrayList  = new ArrayList<IndividualClientReportSubBean>();
		if(arrayList.size()>0){
			arrayList.clear();
		}
		try {
			PreparedStatement preparedStatement = null;
			preparedStatement = Pstm.createQuery(connection, IndividualClientReportSql.loadRidDetailsList, Arrays.asList(id));
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				IndividualClientReportSubBean bean = new IndividualClientReportSubBean();
				
				bean.setTestId1(resultSet.getInt("resource_id"));
				bean.setTestId2(resultSet.getInt("rts_req_resource_mapper"));
			
				arrayList.add(bean);
			}
			
			
			
		} catch (Exception e) {
			
		}
		return arrayList;
	}
	
	
	
	public static ArrayList<RequirementGenerationBean> onLoadReqList(){
		ArrayList<RequirementGenerationBean> reqIdList = new ArrayList<RequirementGenerationBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						   preparedStatement = Pstm.createQuery(connection, IndividualClientRequirementSql.fetchReqIdList, null);
							
						   logger.info("onLoadReqList - " + preparedStatement.unwrap(PreparedStatement.class));
						   
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								RequirementGenerationBean bean = new RequirementGenerationBean();
								bean.setReq_id(resultSet.getInt("r_id"));
								
								reqIdList.add(bean);
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
			logger.fatal("onLoadReqList" + e);
			e.printStackTrace();
		}
		return reqIdList;	
	}
	
	public static ArrayList<IndividualClientReportBean> loadRidList(int clientId){
		
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if(list.size()>0){
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection, IndividualClientReportSql.loadRidList, Arrays.asList(clientId));
				
				logger.info("load Rid List - " + preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					IndividualClientReportBean bean = new IndividualClientReportBean();
					
					bean.getrIdLabel();
					bean.setReqId(resultSet.getInt("req_id"));
					
					bean.getrIdDateLabel();
					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if(bean.getCreatedDateStr() != null){
						bean.setCreatedDateValue(Dateformatter.toStringDate(bean.getCreatedDateStr()));
					}
					
					bean.getSkillSetLabel();
					bean.setSkillId(resultSet.getInt("req_skill_id"));
					bean.setSkillSet(resultSet.getString("master_skill_set_name"));
					
					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));
					
					bean.setRidLbFieldVis(true);
					bean.setRidFieldVis(true);
					bean.setRidDatelbFieldVis(true);
					bean.setRidDateFieldVis(true);
					bean.setSklStFieldVis(true);
					bean.setSklStFieldVis(true);
					bean.setClNameFieldVis(true);
					bean.setCompanyFieldVis(true);
					
					
					bean.setStatusFieldVis(false);
					bean.setResNameFieldVis(false);
					bean.setYoExpFieldVis(false);
					bean.setContNoFieldVis(false);
					bean.setEmailFieldVis(false);
					bean.setIntIntvDateFieldVis(false);
					bean.setClIntvDateFieldVis(false);
					
					list.add(bean);
					
					sub_sql:{
					
					try {
						if(subList.size()>0){
							subList.clear();
						}
						
						PreparedStatement preparedStatement2 = null;
						preparedStatement2 = Pstm.createQuery(connection, IndividualClientReportSql.loadRidDetailsList, Arrays.asList(bean.getReqId()));
						//System.out.println("preparedStatement2 -- " + preparedStatement2);
						
						logger.info("R_ID DETAILS - " + preparedStatement2.unwrap(PreparedStatement.class));
						
						ResultSet resultSet2 = preparedStatement2.executeQuery();
						
						while (resultSet2.next()) {
							
							IndividualClientReportBean subBean = new IndividualClientReportBean();
							
							subBean.setStatus(resultSet2.getString("final_status"));
							subBean.setResourceName(resultSet2.getString("res_name"));
							subBean.setYoExp(resultSet2.getInt("res_experience"));
							subBean.setContNo(resultSet2.getString("rts_contact_no"));
							subBean.setEmailId(resultSet2.getString("res_emailid"));
							
							subBean.setIntIntvStr(resultSet2.getString("internal_interview_date"));
							if(subBean.getIntIntvStr() != null){
								subBean.setIntIntvValue(Dateformatter.toStringDate(subBean.getIntIntvStr()));
							}
							
							subBean.setClntIntvStr(resultSet2.getString("client_interview_date"));
							if(subBean.getClntIntvStr() != null){
								subBean.setClntIntvValue(Dateformatter.toStringDate(subBean.getClntIntvStr()));
							}
							
							subBean.setStatusFieldVis(true);
							subBean.setResNameFieldVis(true);
							subBean.setYoExpFieldVis(true);
							subBean.setContNoFieldVis(true);
							subBean.setEmailFieldVis(true);
							subBean.setIntIntvDateFieldVis(true);
							subBean.setClIntvDateFieldVis(true);
							
							subBean.setRidLbFieldVis(false);
							subBean.setRidFieldVis(false);
							subBean.setRidDatelbFieldVis(false);
							subBean.setRidDateFieldVis(false);
							subBean.setSklStLbFieldVis(false);
							subBean.setSklStFieldVis(false);
							subBean.setClNameFieldVis(false);
							subBean.setCompanyFieldVis(false);
							
							
							list.add(subBean);
							
							
						}
						
						
						
					} finally{
						
					}
					
				}
					
				}
				
				
			} finally {
				if(preparedStatement != null){
					preparedStatement.close();
				}
				if(resultSet != null){
					resultSet.close();
				}
				if(connection != null){
					connection.close();
				}
			}
			
		} catch (Exception e) {
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}
		
		return list;
		
	}
	
public static ArrayList<IndividualClientReportBean> loadRidListWithDateRange(Date fromDate, Date toDate, int clientId){
		
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if(list.size()>0){
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection, IndividualClientReportSql.loadRidListWithDateRange, Arrays.asList(fromDate, toDate, clientId));
				
				logger.info("load Rid List with date range- " + preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					IndividualClientReportBean bean = new IndividualClientReportBean();
					
					bean.getrIdLabel();
					bean.setReqId(resultSet.getInt("req_id"));
					
					bean.getrIdDateLabel();
					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if(bean.getCreatedDateStr() != null){
						bean.setCreatedDateValue(Dateformatter.toStringDate(bean.getCreatedDateStr()));
					}
					
					bean.getSkillSetLabel();
					bean.setSkillId(resultSet.getInt("req_skill_id"));
					bean.setSkillSet(resultSet.getString("master_skill_set_name"));
					
					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));
					
					bean.setRidLbFieldVis(true);
					bean.setRidFieldVis(true);
					bean.setRidDatelbFieldVis(true);
					bean.setRidDateFieldVis(true);
					bean.setSklStFieldVis(true);
					bean.setSklStFieldVis(true);
					bean.setClNameFieldVis(true);
					bean.setCompanyFieldVis(true);
					
					
					bean.setStatusFieldVis(false);
					bean.setResNameFieldVis(false);
					bean.setYoExpFieldVis(false);
					bean.setContNoFieldVis(false);
					bean.setEmailFieldVis(false);
					bean.setIntIntvDateFieldVis(false);
					bean.setClIntvDateFieldVis(false);
					
					list.add(bean);
					
					sub_sql:{
					
					try {
						if(subList.size()>0){
							subList.clear();
						}
						
						PreparedStatement preparedStatement2 = null;
						preparedStatement2 = Pstm.createQuery(connection, IndividualClientReportSql.loadRidDetailsList, Arrays.asList(bean.getReqId()));
						//System.out.println("preparedStatement2 -- " + preparedStatement2);
						
						logger.info("R_ID DETAILS WITH DATE RANGE - " + preparedStatement2.unwrap(PreparedStatement.class));
						
						ResultSet resultSet2 = preparedStatement2.executeQuery();
						
						while (resultSet2.next()) {
							
							IndividualClientReportBean subBean = new IndividualClientReportBean();
							
							subBean.setStatus(resultSet2.getString("final_status"));
							subBean.setResourceName(resultSet2.getString("res_name"));
							subBean.setYoExp(resultSet2.getInt("res_experience"));
							subBean.setContNo(resultSet2.getString("rts_contact_no"));
							subBean.setEmailId(resultSet2.getString("res_emailid"));
							
							subBean.setIntIntvStr(resultSet2.getString("internal_interview_date"));
							if(subBean.getIntIntvStr() != null){
								subBean.setIntIntvValue(Dateformatter.toStringDate(subBean.getIntIntvStr()));
							}
							
							subBean.setClntIntvStr(resultSet2.getString("client_interview_date"));
							if(subBean.getClntIntvStr() != null){
								subBean.setClntIntvValue(Dateformatter.toStringDate(subBean.getClntIntvStr()));
							}
							
							subBean.setStatusFieldVis(true);
							subBean.setResNameFieldVis(true);
							subBean.setYoExpFieldVis(true);
							subBean.setContNoFieldVis(true);
							subBean.setEmailFieldVis(true);
							subBean.setIntIntvDateFieldVis(true);
							subBean.setClIntvDateFieldVis(true);
							
							subBean.setRidLbFieldVis(false);
							subBean.setRidFieldVis(false);
							subBean.setRidDatelbFieldVis(false);
							subBean.setRidDateFieldVis(false);
							subBean.setSklStLbFieldVis(false);
							subBean.setSklStFieldVis(false);
							subBean.setClNameFieldVis(false);
							subBean.setCompanyFieldVis(false);
							
							
							list.add(subBean);
							
							
						}
						
						
						
					} finally{
						
					}
					
				}
					
				}
				
				
			} finally {
				if(preparedStatement != null){
					preparedStatement.close();
				}
				if(resultSet != null){
					resultSet.close();
				}
				if(connection != null){
					connection.close();
				}
			}
			
		} catch (Exception e) {
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}
		
		return list;
		
	}
	

	public static ArrayList<IndividualClientReportBean> loadRidListWithDateRangeAndSkill(Date fromDate, Date toDate, int skillId, int clientId){
			
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
			if(list.size()>0){
				list.clear();
			}
			ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();
			
			try {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				
				try {
					connection = DbConnection.createConnection();
					preparedStatement = Pstm.createQuery(connection, IndividualClientReportSql.loadRidListWithDateRangeAndSkill, Arrays.asList(fromDate, toDate, skillId, clientId));
					
					logger.info("load Rid List with date range and skill - " + preparedStatement.unwrap(PreparedStatement.class));
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						IndividualClientReportBean bean = new IndividualClientReportBean();
						
						bean.getrIdLabel();
						bean.setReqId(resultSet.getInt("req_id"));
						
						bean.getrIdDateLabel();
						bean.setCreatedDateStr(resultSet.getString("created_date"));
						if(bean.getCreatedDateStr() != null){
							bean.setCreatedDateValue(Dateformatter.toStringDate(bean.getCreatedDateStr()));
						}
						
						bean.getSkillSetLabel();
						bean.setSkillId(resultSet.getInt("req_skill_id"));
						bean.setSkillSet(resultSet.getString("master_skill_set_name"));
						
						bean.setClientFullName(resultSet.getString("client_name"));
						bean.setCompanyName(resultSet.getString("companyname"));
						
						bean.setRidLbFieldVis(true);
						bean.setRidFieldVis(true);
						bean.setRidDatelbFieldVis(true);
						bean.setRidDateFieldVis(true);
						bean.setSklStFieldVis(true);
						bean.setSklStFieldVis(true);
						bean.setClNameFieldVis(true);
						bean.setCompanyFieldVis(true);
						
						
						bean.setStatusFieldVis(false);
						bean.setResNameFieldVis(false);
						bean.setYoExpFieldVis(false);
						bean.setContNoFieldVis(false);
						bean.setEmailFieldVis(false);
						bean.setIntIntvDateFieldVis(false);
						bean.setClIntvDateFieldVis(false);
						
						list.add(bean);
						
						sub_sql:{
						
						try {
							if(subList.size()>0){
								subList.clear();
							}
							
							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm.createQuery(connection, IndividualClientReportSql.loadRidDetailsList, Arrays.asList(bean.getReqId()));
							//System.out.println("preparedStatement2 -- " + preparedStatement2);
							
							logger.info("R_ID DETAILS WITH DATE RANGE AND SKILL - " + preparedStatement2.unwrap(PreparedStatement.class));
							
							ResultSet resultSet2 = preparedStatement2.executeQuery();
							
							while (resultSet2.next()) {
								
								IndividualClientReportBean subBean = new IndividualClientReportBean();
								
								subBean.setStatus(resultSet2.getString("final_status"));
								subBean.setResourceName(resultSet2.getString("res_name"));
								subBean.setYoExp(resultSet2.getInt("res_experience"));
								subBean.setContNo(resultSet2.getString("rts_contact_no"));
								subBean.setEmailId(resultSet2.getString("res_emailid"));
								
								subBean.setIntIntvStr(resultSet2.getString("internal_interview_date"));
								if(subBean.getIntIntvStr() != null){
									subBean.setIntIntvValue(Dateformatter.toStringDate(subBean.getIntIntvStr()));
								}
								
								subBean.setClntIntvStr(resultSet2.getString("client_interview_date"));
								if(subBean.getClntIntvStr() != null){
									subBean.setClntIntvValue(Dateformatter.toStringDate(subBean.getClntIntvStr()));
								}
								
								subBean.setStatusFieldVis(true);
								subBean.setResNameFieldVis(true);
								subBean.setYoExpFieldVis(true);
								subBean.setContNoFieldVis(true);
								subBean.setEmailFieldVis(true);
								subBean.setIntIntvDateFieldVis(true);
								subBean.setClIntvDateFieldVis(true);
								
								subBean.setRidLbFieldVis(false);
								subBean.setRidFieldVis(false);
								subBean.setRidDatelbFieldVis(false);
								subBean.setRidDateFieldVis(false);
								subBean.setSklStLbFieldVis(false);
								subBean.setSklStFieldVis(false);
								subBean.setClNameFieldVis(false);
								subBean.setCompanyFieldVis(false);
								
								
								list.add(subBean);
								
							}
							
						} finally{
							
						}
						
					}
						
					}
					
					
				} finally {
					if(preparedStatement != null){
						preparedStatement.close();
					}
					if(resultSet != null){
						resultSet.close();
					}
					if(connection != null){
						connection.close();
					}
				}
				
			} catch (Exception e) {
				logger.fatal("Load R_ID List - " + e);
				e.printStackTrace();
			}
			
			return list;
	  }
	
	
	public static ArrayList<IndividualClientReportBean> loadRidListWithSkill(int skillId, int clientId){
		
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
			if(list.size()>0){
				list.clear();
			}
			ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();
			
			try {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				
				try {
					connection = DbConnection.createConnection();
					preparedStatement = Pstm.createQuery(connection, IndividualClientReportSql.loadRidListWithSkill, Arrays.asList(skillId, clientId));
					
					logger.info("load Rid List with skill - " + preparedStatement.unwrap(PreparedStatement.class));
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						IndividualClientReportBean bean = new IndividualClientReportBean();
						
						bean.getrIdLabel();
						bean.setReqId(resultSet.getInt("req_id"));
						
						bean.getrIdDateLabel();
						bean.setCreatedDateStr(resultSet.getString("created_date"));
						if(bean.getCreatedDateStr() != null){
							bean.setCreatedDateValue(Dateformatter.toStringDate(bean.getCreatedDateStr()));
						}
						
						bean.getSkillSetLabel();
						bean.setSkillId(resultSet.getInt("req_skill_id"));
						bean.setSkillSet(resultSet.getString("master_skill_set_name"));
						
						bean.setClientFullName(resultSet.getString("client_name"));
						bean.setCompanyName(resultSet.getString("companyname"));
						
						bean.setRidLbFieldVis(true);
						bean.setRidFieldVis(true);
						bean.setRidDatelbFieldVis(true);
						bean.setRidDateFieldVis(true);
						bean.setSklStFieldVis(true);
						bean.setSklStFieldVis(true);
						bean.setClNameFieldVis(true);
						bean.setCompanyFieldVis(true);
						
						
						bean.setStatusFieldVis(false);
						bean.setResNameFieldVis(false);
						bean.setYoExpFieldVis(false);
						bean.setContNoFieldVis(false);
						bean.setEmailFieldVis(false);
						bean.setIntIntvDateFieldVis(false);
						bean.setClIntvDateFieldVis(false);
						
						list.add(bean);
						
						sub_sql:{
						
						try {
							if(subList.size()>0){
								subList.clear();
							}
							
							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm.createQuery(connection, IndividualClientReportSql.loadRidDetailsList, Arrays.asList(bean.getReqId()));
							//System.out.println("preparedStatement2 -- " + preparedStatement2);
							
							logger.info("R_ID DETAILS WITH SKILL - " + preparedStatement2.unwrap(PreparedStatement.class));
							
							ResultSet resultSet2 = preparedStatement2.executeQuery();
							
							while (resultSet2.next()) {
								
								IndividualClientReportBean subBean = new IndividualClientReportBean();
								
								subBean.setStatus(resultSet2.getString("final_status"));
								subBean.setResourceName(resultSet2.getString("res_name"));
								subBean.setYoExp(resultSet2.getInt("res_experience"));
								subBean.setContNo(resultSet2.getString("rts_contact_no"));
								subBean.setEmailId(resultSet2.getString("res_emailid"));
								
								subBean.setIntIntvStr(resultSet2.getString("internal_interview_date"));
								if(subBean.getIntIntvStr() != null){
									subBean.setIntIntvValue(Dateformatter.toStringDate(subBean.getIntIntvStr()));
								}
								
								subBean.setClntIntvStr(resultSet2.getString("client_interview_date"));
								if(subBean.getClntIntvStr() != null){
									subBean.setClntIntvValue(Dateformatter.toStringDate(subBean.getClntIntvStr()));
								}
								
								subBean.setStatusFieldVis(true);
								subBean.setResNameFieldVis(true);
								subBean.setYoExpFieldVis(true);
								subBean.setContNoFieldVis(true);
								subBean.setEmailFieldVis(true);
								subBean.setIntIntvDateFieldVis(true);
								subBean.setClIntvDateFieldVis(true);
								
								subBean.setRidLbFieldVis(false);
								subBean.setRidFieldVis(false);
								subBean.setRidDatelbFieldVis(false);
								subBean.setRidDateFieldVis(false);
								subBean.setSklStLbFieldVis(false);
								subBean.setSklStFieldVis(false);
								subBean.setClNameFieldVis(false);
								subBean.setCompanyFieldVis(false);
								
								
								list.add(subBean);
								
							}
							
						} finally{
							
						}
						
					}
						
					}
					
					
				} finally {
					if(preparedStatement != null){
						preparedStatement.close();
					}
					if(resultSet != null){
						resultSet.close();
					}
					if(connection != null){
						connection.close();
					}
				}
				
			} catch (Exception e) {
				logger.fatal("Load R_ID List - " + e);
				e.printStackTrace();
			}
			
			return list;
		}
	
	
	public static ArrayList<IndividualClientReportBean> loadRidListWithStatus(int statusId, int clientId){
		
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
			if(list.size()>0){
				list.clear();
			}
			ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();
			
			try {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				
				try {
					connection = DbConnection.createConnection();
					preparedStatement = Pstm.createQuery(connection, IndividualClientReportSql.loadRidListWithStatus, Arrays.asList(clientId));
					
					logger.info("load Rid List with skill - " + preparedStatement.unwrap(PreparedStatement.class));
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						IndividualClientReportBean bean = new IndividualClientReportBean();
						
						bean.getrIdLabel();
						bean.setReqId(resultSet.getInt("req_id"));
						
						bean.getrIdDateLabel();
						bean.setCreatedDateStr(resultSet.getString("created_date"));
						if(bean.getCreatedDateStr() != null){
							bean.setCreatedDateValue(Dateformatter.toStringDate(bean.getCreatedDateStr()));
						}
						
						bean.getSkillSetLabel();
						bean.setSkillId(resultSet.getInt("req_skill_id"));
						bean.setSkillSet(resultSet.getString("master_skill_set_name"));
						
						bean.setClientFullName(resultSet.getString("client_name"));
						bean.setCompanyName(resultSet.getString("companyname"));
						
						bean.setRidLbFieldVis(true);
						bean.setRidFieldVis(true);
						bean.setRidDatelbFieldVis(true);
						bean.setRidDateFieldVis(true);
						bean.setSklStFieldVis(true);
						bean.setSklStFieldVis(true);
						bean.setClNameFieldVis(true);
						bean.setCompanyFieldVis(true);
						
						
						bean.setStatusFieldVis(false);
						bean.setResNameFieldVis(false);
						bean.setYoExpFieldVis(false);
						bean.setContNoFieldVis(false);
						bean.setEmailFieldVis(false);
						bean.setIntIntvDateFieldVis(false);
						bean.setClIntvDateFieldVis(false);
						
						list.add(bean);
						
						sub_sql:{
						
						try {
							if(subList.size()>0){
								subList.clear();
							}
							
							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm.createQuery(connection, IndividualClientReportSql.loadRidDetailsListWithStatus, Arrays.asList(bean.getReqId(),statusId));
							
							
							logger.info("R_ID DETAILS WITH SKILL - " + preparedStatement2.unwrap(PreparedStatement.class));
							
							ResultSet resultSet2 = preparedStatement2.executeQuery();
							
							while (resultSet2.next()) {
								
								IndividualClientReportBean subBean = new IndividualClientReportBean();
								
								subBean.setStatus(resultSet2.getString("final_status"));
								subBean.setResourceName(resultSet2.getString("res_name"));
								subBean.setYoExp(resultSet2.getInt("res_experience"));
								subBean.setContNo(resultSet2.getString("rts_contact_no"));
								subBean.setEmailId(resultSet2.getString("res_emailid"));
								
								subBean.setIntIntvStr(resultSet2.getString("internal_interview_date"));
								if(subBean.getIntIntvStr() != null){
									subBean.setIntIntvValue(Dateformatter.toStringDate(subBean.getIntIntvStr()));
								}
								
								subBean.setClntIntvStr(resultSet2.getString("client_interview_date"));
								if(subBean.getClntIntvStr() != null){
									subBean.setClntIntvValue(Dateformatter.toStringDate(subBean.getClntIntvStr()));
								}
								
								subBean.setStatusFieldVis(true);
								subBean.setResNameFieldVis(true);
								subBean.setYoExpFieldVis(true);
								subBean.setContNoFieldVis(true);
								subBean.setEmailFieldVis(true);
								subBean.setIntIntvDateFieldVis(true);
								subBean.setClIntvDateFieldVis(true);
								
								subBean.setRidLbFieldVis(false);
								subBean.setRidFieldVis(false);
								subBean.setRidDatelbFieldVis(false);
								subBean.setRidDateFieldVis(false);
								subBean.setSklStLbFieldVis(false);
								subBean.setSklStFieldVis(false);
								subBean.setClNameFieldVis(false);
								subBean.setCompanyFieldVis(false);
								
								
								list.add(subBean);
								
							}
							
						} finally{
							
						}
						
					}
						
					}
					
					
				} finally {
					if(preparedStatement != null){
						preparedStatement.close();
					}
					if(resultSet != null){
						resultSet.close();
					}
					if(connection != null){
						connection.close();
					}
				}
				
			} catch (Exception e) {
				logger.fatal("Load R_ID List - " + e);
				e.printStackTrace();
			}
			
			return list;
		}
	    
	
	    public static ArrayList<IndividualClientReportBean> loadRidListWithStatusSkillDate(Date fromDate, Date toDate, int skillId,int statusId, int clientId){
		
		    ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
			if(list.size()>0){
				list.clear();
			}
			ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();
			
			try {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				
				try {
					connection = DbConnection.createConnection();
					preparedStatement = Pstm.createQuery(connection, IndividualClientReportSql.loadRidListWithDateAndSkillAndStatus, Arrays.asList(fromDate, toDate, skillId, clientId));
					
					logger.info("load Rid List with skill - " + preparedStatement.unwrap(PreparedStatement.class));
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						IndividualClientReportBean bean = new IndividualClientReportBean();
						
						bean.getrIdLabel();
						bean.setReqId(resultSet.getInt("req_id"));
						
						bean.getrIdDateLabel();
						bean.setCreatedDateStr(resultSet.getString("created_date"));
						if(bean.getCreatedDateStr() != null){
							bean.setCreatedDateValue(Dateformatter.toStringDate(bean.getCreatedDateStr()));
						}
						
						bean.getSkillSetLabel();
						bean.setSkillId(resultSet.getInt("req_skill_id"));
						bean.setSkillSet(resultSet.getString("master_skill_set_name"));
						
						bean.setClientFullName(resultSet.getString("client_name"));
						bean.setCompanyName(resultSet.getString("companyname"));
						
						bean.setRidLbFieldVis(true);
						bean.setRidFieldVis(true);
						bean.setRidDatelbFieldVis(true);
						bean.setRidDateFieldVis(true);
						bean.setSklStFieldVis(true);
						bean.setSklStFieldVis(true);
						bean.setClNameFieldVis(true);
						bean.setCompanyFieldVis(true);
						
						
						bean.setStatusFieldVis(false);
						bean.setResNameFieldVis(false);
						bean.setYoExpFieldVis(false);
						bean.setContNoFieldVis(false);
						bean.setEmailFieldVis(false);
						bean.setIntIntvDateFieldVis(false);
						bean.setClIntvDateFieldVis(false);
						
						list.add(bean);
						
						sub_sql:{
						
						try {
							if(subList.size()>0){
								subList.clear();
							}
							
							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm.createQuery(connection, IndividualClientReportSql.loadRidDetailsListWithStatus, Arrays.asList(bean.getReqId(),statusId));
							
							
							logger.info("R_ID DETAILS WITH SKILL - " + preparedStatement2.unwrap(PreparedStatement.class));
							
							ResultSet resultSet2 = preparedStatement2.executeQuery();
							
							while (resultSet2.next()) {
								
								IndividualClientReportBean subBean = new IndividualClientReportBean();
								
								subBean.setStatus(resultSet2.getString("final_status"));
								subBean.setResourceName(resultSet2.getString("res_name"));
								subBean.setYoExp(resultSet2.getInt("res_experience"));
								subBean.setContNo(resultSet2.getString("rts_contact_no"));
								subBean.setEmailId(resultSet2.getString("res_emailid"));
								
								subBean.setIntIntvStr(resultSet2.getString("internal_interview_date"));
								if(subBean.getIntIntvStr() != null){
									subBean.setIntIntvValue(Dateformatter.toStringDate(subBean.getIntIntvStr()));
								}
								
								subBean.setClntIntvStr(resultSet2.getString("client_interview_date"));
								if(subBean.getClntIntvStr() != null){
									subBean.setClntIntvValue(Dateformatter.toStringDate(subBean.getClntIntvStr()));
								}
								
								subBean.setStatusFieldVis(true);
								subBean.setResNameFieldVis(true);
								subBean.setYoExpFieldVis(true);
								subBean.setContNoFieldVis(true);
								subBean.setEmailFieldVis(true);
								subBean.setIntIntvDateFieldVis(true);
								subBean.setClIntvDateFieldVis(true);
								
								subBean.setRidLbFieldVis(false);
								subBean.setRidFieldVis(false);
								subBean.setRidDatelbFieldVis(false);
								subBean.setRidDateFieldVis(false);
								subBean.setSklStLbFieldVis(false);
								subBean.setSklStFieldVis(false);
								subBean.setClNameFieldVis(false);
								subBean.setCompanyFieldVis(false);
								
								
								list.add(subBean);
								
							}
							
						} finally{
							
						}
						
					}
						
					}
					
					
				} finally {
					if(preparedStatement != null){
						preparedStatement.close();
					}
					if(resultSet != null){
						resultSet.close();
					}
					if(connection != null){
						connection.close();
					}
				}
				
			} catch (Exception e) {
				logger.fatal("Load R_ID List - " + e);
				e.printStackTrace();
			}
			
			return list;
		}
	
	    public static ArrayList<IndividualClientReportBean> loadRidListWithStatusSkill(int skillId,int statusId, int clientId){
			
		    ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
			if(list.size()>0){
				list.clear();
			}
			ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();
			
			try {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				
				try {
					connection = DbConnection.createConnection();
					preparedStatement = Pstm.createQuery(connection, IndividualClientReportSql.loadRidListWithSkillAndStatus, Arrays.asList(skillId, clientId));
					
					logger.info("load Rid List with skill - " + preparedStatement.unwrap(PreparedStatement.class));
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						IndividualClientReportBean bean = new IndividualClientReportBean();
						
						bean.getrIdLabel();
						bean.setReqId(resultSet.getInt("req_id"));
						
						bean.getrIdDateLabel();
						bean.setCreatedDateStr(resultSet.getString("created_date"));
						if(bean.getCreatedDateStr() != null){
							bean.setCreatedDateValue(Dateformatter.toStringDate(bean.getCreatedDateStr()));
						}
						
						bean.getSkillSetLabel();
						bean.setSkillId(resultSet.getInt("req_skill_id"));
						bean.setSkillSet(resultSet.getString("master_skill_set_name"));
						
						bean.setClientFullName(resultSet.getString("client_name"));
						bean.setCompanyName(resultSet.getString("companyname"));
						
						bean.setRidLbFieldVis(true);
						bean.setRidFieldVis(true);
						bean.setRidDatelbFieldVis(true);
						bean.setRidDateFieldVis(true);
						bean.setSklStFieldVis(true);
						bean.setSklStFieldVis(true);
						bean.setClNameFieldVis(true);
						bean.setCompanyFieldVis(true);
						
						
						bean.setStatusFieldVis(false);
						bean.setResNameFieldVis(false);
						bean.setYoExpFieldVis(false);
						bean.setContNoFieldVis(false);
						bean.setEmailFieldVis(false);
						bean.setIntIntvDateFieldVis(false);
						bean.setClIntvDateFieldVis(false);
						
						list.add(bean);
						
						sub_sql:{
						
						try {
							if(subList.size()>0){
								subList.clear();
							}
							
							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm.createQuery(connection, IndividualClientReportSql.loadRidDetailsListWithStatus, Arrays.asList(bean.getReqId(),statusId));
							
							
							logger.info("R_ID DETAILS WITH SKILL - " + preparedStatement2.unwrap(PreparedStatement.class));
							
							ResultSet resultSet2 = preparedStatement2.executeQuery();
							
							while (resultSet2.next()) {
								
								IndividualClientReportBean subBean = new IndividualClientReportBean();
								
								subBean.setStatus(resultSet2.getString("final_status"));
								subBean.setResourceName(resultSet2.getString("res_name"));
								subBean.setYoExp(resultSet2.getInt("res_experience"));
								subBean.setContNo(resultSet2.getString("rts_contact_no"));
								subBean.setEmailId(resultSet2.getString("res_emailid"));
								
								subBean.setIntIntvStr(resultSet2.getString("internal_interview_date"));
								if(subBean.getIntIntvStr() != null){
									subBean.setIntIntvValue(Dateformatter.toStringDate(subBean.getIntIntvStr()));
								}
								
								subBean.setClntIntvStr(resultSet2.getString("client_interview_date"));
								if(subBean.getClntIntvStr() != null){
									subBean.setClntIntvValue(Dateformatter.toStringDate(subBean.getClntIntvStr()));
								}
								
								subBean.setStatusFieldVis(true);
								subBean.setResNameFieldVis(true);
								subBean.setYoExpFieldVis(true);
								subBean.setContNoFieldVis(true);
								subBean.setEmailFieldVis(true);
								subBean.setIntIntvDateFieldVis(true);
								subBean.setClIntvDateFieldVis(true);
								
								subBean.setRidLbFieldVis(false);
								subBean.setRidFieldVis(false);
								subBean.setRidDatelbFieldVis(false);
								subBean.setRidDateFieldVis(false);
								subBean.setSklStLbFieldVis(false);
								subBean.setSklStFieldVis(false);
								subBean.setClNameFieldVis(false);
								subBean.setCompanyFieldVis(false);
								
								
								list.add(subBean);
								
							}
							
						} finally{
							
						}
						
					}
						
					}
					
					
				} finally {
					if(preparedStatement != null){
						preparedStatement.close();
					}
					if(resultSet != null){
						resultSet.close();
					}
					if(connection != null){
						connection.close();
					}
				}
				
			} catch (Exception e) {
				logger.fatal("Load R_ID List - " + e);
				e.printStackTrace();
			}
			
			return list;
		}
	    
	   
	    public static ArrayList<IndividualClientReportBean> loadRidListWithStatusDate(Date fromDate, Date toDate ,int statusId, int clientId){
			
		    ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
			if(list.size()>0){
				list.clear();
			}
			ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();
			
			try {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				
				try {
					connection = DbConnection.createConnection();
					preparedStatement = Pstm.createQuery(connection, IndividualClientReportSql.loadRidListWithDateStatus, Arrays.asList(fromDate, toDate,  clientId));
					
					logger.info("load Rid List with skill - " + preparedStatement.unwrap(PreparedStatement.class));
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						IndividualClientReportBean bean = new IndividualClientReportBean();
						
						bean.getrIdLabel();
						bean.setReqId(resultSet.getInt("req_id"));
						
						bean.getrIdDateLabel();
						bean.setCreatedDateStr(resultSet.getString("created_date"));
						if(bean.getCreatedDateStr() != null){
							bean.setCreatedDateValue(Dateformatter.toStringDate(bean.getCreatedDateStr()));
						}
						
						bean.getSkillSetLabel();
						bean.setSkillId(resultSet.getInt("req_skill_id"));
						bean.setSkillSet(resultSet.getString("master_skill_set_name"));
						
						bean.setClientFullName(resultSet.getString("client_name"));
						bean.setCompanyName(resultSet.getString("companyname"));
						
						bean.setRidLbFieldVis(true);
						bean.setRidFieldVis(true);
						bean.setRidDatelbFieldVis(true);
						bean.setRidDateFieldVis(true);
						bean.setSklStFieldVis(true);
						bean.setSklStFieldVis(true);
						bean.setClNameFieldVis(true);
						bean.setCompanyFieldVis(true);
						
						
						bean.setStatusFieldVis(false);
						bean.setResNameFieldVis(false);
						bean.setYoExpFieldVis(false);
						bean.setContNoFieldVis(false);
						bean.setEmailFieldVis(false);
						bean.setIntIntvDateFieldVis(false);
						bean.setClIntvDateFieldVis(false);
						
						list.add(bean);
						
						sub_sql:{
						
						try {
							if(subList.size()>0){
								subList.clear();
							}
							
							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm.createQuery(connection, IndividualClientReportSql.loadRidDetailsListWithStatus, Arrays.asList(bean.getReqId(),statusId));
							
							
							logger.info("R_ID DETAILS WITH SKILL - " + preparedStatement2.unwrap(PreparedStatement.class));
							
							ResultSet resultSet2 = preparedStatement2.executeQuery();
							
							while (resultSet2.next()) {
								
								IndividualClientReportBean subBean = new IndividualClientReportBean();
								
								subBean.setStatus(resultSet2.getString("final_status"));
								subBean.setResourceName(resultSet2.getString("res_name"));
								subBean.setYoExp(resultSet2.getInt("res_experience"));
								subBean.setContNo(resultSet2.getString("rts_contact_no"));
								subBean.setEmailId(resultSet2.getString("res_emailid"));
								
								subBean.setIntIntvStr(resultSet2.getString("internal_interview_date"));
								if(subBean.getIntIntvStr() != null){
									subBean.setIntIntvValue(Dateformatter.toStringDate(subBean.getIntIntvStr()));
								}
								
								subBean.setClntIntvStr(resultSet2.getString("client_interview_date"));
								if(subBean.getClntIntvStr() != null){
									subBean.setClntIntvValue(Dateformatter.toStringDate(subBean.getClntIntvStr()));
								}
								
								subBean.setStatusFieldVis(true);
								subBean.setResNameFieldVis(true);
								subBean.setYoExpFieldVis(true);
								subBean.setContNoFieldVis(true);
								subBean.setEmailFieldVis(true);
								subBean.setIntIntvDateFieldVis(true);
								subBean.setClIntvDateFieldVis(true);
								
								subBean.setRidLbFieldVis(false);
								subBean.setRidFieldVis(false);
								subBean.setRidDatelbFieldVis(false);
								subBean.setRidDateFieldVis(false);
								subBean.setSklStLbFieldVis(false);
								subBean.setSklStFieldVis(false);
								subBean.setClNameFieldVis(false);
								subBean.setCompanyFieldVis(false);
								
								
								list.add(subBean);
								
							}
							
						} finally{
							
						}
						
					}
						
					}
					
					
				} finally {
					if(preparedStatement != null){
						preparedStatement.close();
					}
					if(resultSet != null){
						resultSet.close();
					}
					if(connection != null){
						connection.close();
					}
				}
				
			} catch (Exception e) {
				logger.fatal("Load R_ID List - " + e);
				e.printStackTrace();
			}
			
			return list;
		}
	    
	    public static ArrayList<IndividualClientReportBean> loadRidSummary(ArrayList<IndividualClientReportBean> parentList){
	    	
	    	ArrayList<IndividualClientReportBean> summarList = new ArrayList<IndividualClientReportBean>();
	    	
	    	if(summarList.size()>0){
	    		summarList.clear();
	    	}
	    	
	    	ArrayList<Integer> list = new ArrayList<Integer>();
	    	if(list.size()>0){
	    		list.clear();
	    	}
	    	for(IndividualClientReportBean pb : parentList){
	    		if(pb.getReqId() != null){
	    			list.add(pb.getReqId());
	    		}
	    	}
	    	
	    	try {
	    		Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
				    connection = DbConnection.createConnection();
				       	
					   for(Integer id: list){
						   
							   preparedStatement  = Pstm.createQuery(connection, IndividualClientReportSql.loadRidListSummay,Arrays.asList(id));
							   logger.info("Summury List Rid - " + preparedStatement.unwrap(PreparedStatement.class));
							   resultSet = preparedStatement.executeQuery();
							   while (resultSet.next()) {
							   IndividualClientReportBean parentBean = new IndividualClientReportBean();
							   
							   parentBean.setrIdLabel("R id : " + resultSet.getInt("req_id"));
							   parentBean.setrIdDateLabel("Date : " + resultSet.getString("created_date"));
							   parentBean.setClientFullName("Client : " + resultSet.getString("client_name") );
							   parentBean.setSkillSetLabel("Skill : " + resultSet.getString("master_skill_set_name"));
							   parentBean.setNoOfReqLebel("Total Req : " + resultSet.getInt("total_req_number")); 	   
							   
							   parentBean.setStyle(parentBean.getBoldStyle());
							   parentBean.setBackGroundStyle(parentBean.getBackGroundpaParent());
							   
							   parentBean.setRidLbFieldVis(true);
							   parentBean.setRidDatelbFieldVis(true);
							   parentBean.setClNameLbFieldVis(true);
							   parentBean.setSklStLbFieldVis(true);
							   parentBean.setNoOfReqVis(true);
							   
							   summarList.add(parentBean);
							   
							subSql:{
								   
							   try {
								   PreparedStatement preparedStatement2 = null;
								   preparedStatement2 = Pstm.createQuery(connection, IndividualClientReportSql.loadStatusSummmary, Arrays.asList(id));
								   logger.info("Summury List Rid Details - " + preparedStatement2.unwrap(PreparedStatement.class));
								   ResultSet resultSet2 = preparedStatement2.executeQuery();
								   
								   while (resultSet2.next()) {
								    
								   IndividualClientReportBean childBean = new IndividualClientReportBean();	   
								   
								   childBean.setrIdLabel(resultSet2.getString("final_status"));
								   childBean.setNoOfReqLebel(resultSet2.getInt("total_count") + "");
								   
								   childBean.setStyle(childBean.getLighterStyle());
								   
								   childBean.setRidLbFieldVis(true);
								   parentBean.setRidDatelbFieldVis(true);
								   //childBean.setRidDateFieldVis(false);
								   childBean.setClNameLbFieldVis(false);
								   childBean.setSklStLbFieldVis(false);
								   childBean.setNoOfReqVis(true);
								   
								   summarList.add(childBean);
									   
								}  
								
							} finally {
								
							}  
						 }
							   
					    }
						   
						   
					   }
				    		
				} finally {
					
					if(preparedStatement != null){
						preparedStatement.close();
					}
					if(resultSet != null){
						resultSet.close();
					}
					if(connection != null){
						connection.close();
					}
					
				}
	    		
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return summarList;
	    	
	    }
	    
	    /*************************************************************** T E S T ********************************************************************/
	    
	    public static ArrayList<IndividualClientReportBean> loadRidListTest(int clientId){
			
			ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
			if(list.size()>0){
				list.clear();
			}
			ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();
			
			try {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				
				try {
					connection = DbConnection.createConnection();
					preparedStatement = Pstm.createQuery(connection, IndividualClientReportSql.loadRidList, Arrays.asList(clientId));
					
					logger.info("load Rid List - " + preparedStatement.unwrap(PreparedStatement.class));
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						
						IndividualClientReportBean bean = new IndividualClientReportBean();
						
						
						bean.setReqId(resultSet.getInt("req_id"));
						
						bean.setrIdLabel("R ID :" +resultSet.getInt("req_id")); 
						
						bean.setCreatedDateStr(resultSet.getString("created_date"));
						if(bean.getCreatedDateStr() != null){
							bean.setCreatedDateValue(Dateformatter.toStringDate(bean.getCreatedDateStr()));
							bean.setrIdDateLabel("Date : " + bean.getCreatedDateValue()); 
							
						}
						
						
						bean.setSkillId(resultSet.getInt("req_skill_id"));
						
						bean.setSkillSetLabel("Skill : " + resultSet.getString("master_skill_set_name")); 
						
						bean.setClientFullName(resultSet.getString("client_name"));
						bean.setCompanyName(resultSet.getString("companyname"));  
						
						bean.setStyle(bean.getBoldStyle());
						bean.setBackGroundStyle(bean.getBackGroundpaParent());
						bean.setRidLbFieldVis(true); 
						bean.setRidDatelbFieldVis(true); 
						//bean.setSklStFieldVis(true);
						bean.setSklStLbFieldVis(true);
						bean.setCompanyFieldVis(true); 
						
						bean.setYoExpFieldVis(false);
						bean.setEmailFieldVis(false);
						bean.setIntIntvDateFieldVis(false);
						bean.setClIntvDateFieldVis(false);
						
						list.add(bean);
						
						sub_sql:{
						
						try {
							if(subList.size()>0){
								subList.clear();
							}
							
							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm.createQuery(connection, IndividualClientReportSql.loadRidDetailsList, Arrays.asList(bean.getReqId()));
							
							logger.info("R_ID DETAILS - " + preparedStatement2.unwrap(PreparedStatement.class));
							
							ResultSet resultSet2 = preparedStatement2.executeQuery();
							
							while (resultSet2.next()) {
								
								IndividualClientReportBean subBean = new IndividualClientReportBean();
								
								subBean.setrIdLabel(resultSet2.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2.getString("res_name"));
								subBean.setYoExp(resultSet2.getInt("res_experience"));
								subBean.setSkillSetLabel(resultSet2.getString("rts_contact_no"));
								System.out.println("Cont. " + subBean.getSkillSetLabel());
								subBean.setEmailId(resultSet2.getString("res_emailid"));
								
								subBean.setIntIntvStr(resultSet2.getString("internal_interview_date"));
								if(subBean.getIntIntvStr() != null){
									subBean.setIntIntvValue(Dateformatter.toStringDate(subBean.getIntIntvStr()));
								}
								System.out.println("INT IN " + subBean.getIntIntvValue());
								subBean.setClntIntvStr(resultSet2.getString("client_interview_date"));
								if(subBean.getClntIntvStr() != null){
									subBean.setClntIntvValue(Dateformatter.toStringDate(subBean.getClntIntvStr()));
								}
								System.out.println("CLINT IN " + subBean.getClntIntvValue());
								
								subBean.setStyle(subBean.getLighterStyle());
								
								subBean.setRidLbFieldVis(true); 
								subBean.setRidDatelbFieldVis(true);
								subBean.setSklStLbFieldVis(true);
								subBean.setCompanyFieldVis(true);
								subBean.setYoExpFieldVis(true);
								subBean.setEmailFieldVis(true);
								subBean.setIntIntvDateFieldVis(true);
								subBean.setClIntvDateFieldVis(true);
								
								list.add(subBean);
								
								
							}
							
							
							
						} finally{
							
						}
						
					}
						
					}
					
					
				} finally {
					if(preparedStatement != null){
						preparedStatement.close();
					}
					if(resultSet != null){
						resultSet.close();
					}
					if(connection != null){
						connection.close();
					}
				}
				
			} catch (Exception e) {
				logger.fatal("Load R_ID List - " + e);
				e.printStackTrace();
			}
			
			return list;
			
		}
	    
	    
	    
	    
	    
	
}
