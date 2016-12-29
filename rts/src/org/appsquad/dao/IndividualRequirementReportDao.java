package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.appsquad.bean.IndividualClientReportBean;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.IndividualClientReportSql;
import org.appsquad.sql.IndividualRequirementReportSql;
import org.appsquad.utility.Dateformatter;
import org.appsquad.utility.Pstm;

public class IndividualRequirementReportDao {

	public static final Logger logger = Logger.getLogger(IndividualRequirementReportDao.class);
	
	
	public static ArrayList<RequirementGenerationBean> fetchReqirmentDetails(){
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
				preparedStatement = Pstm.createQuery(connection, IndividualRequirementReportSql.loadReqIdDetails, null);
			    logger.info(" Individual client report fetchReqirmentDetails- " + preparedStatement.unwrap(PreparedStatement.class));
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
					bean.setClientOriginalName(resultSet.getString("clientname"));
					
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
	
  public static ArrayList<IndividualClientReportBean> loadRidList(int reqId){	
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
				preparedStatement = Pstm.createQuery(connection, IndividualClientReportSql.loadIndividualRid, Arrays.asList(reqId));
				
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
					bean.setEmailId("");
					
					bean.setSkillId(resultSet.getInt("req_skill_id"));
					
					bean.setSkillSetLabel("Skill : " + resultSet.getString("master_skill_set_name")); 
					
					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));  
					bean.setIntIntvValue("");
					bean.setClntIntvValue("");
					bean.setYoExp(0);
					
					bean.setStyle(bean.getBoldStyle());
					bean.setBackGroundStyle(bean.getBackGroundpaParent());
					bean.setRidLbFieldVis(true); 
					bean.setRidDatelbFieldVis(true); 
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
						preparedStatement2 = Pstm.createQuery(connection, IndividualClientReportSql.loadIndividualRidDetails, Arrays.asList(reqId));
						
						logger.info("R_ID DETAILS - " + preparedStatement2.unwrap(PreparedStatement.class));
						
						ResultSet resultSet2 = preparedStatement2.executeQuery();
						
						while (resultSet2.next()) {
							
							IndividualClientReportBean subBean = new IndividualClientReportBean();
							
							subBean.setrIdLabel(resultSet2.getString("final_status"));
							subBean.setrIdDateLabel(resultSet2.getString("res_name"));
							subBean.setYoExp(resultSet2.getDouble("res_experience"));
							subBean.setSkillSetLabel(resultSet2.getString("rts_contact_no"));
							
							subBean.setEmailId(resultSet2.getString("res_emailid"));
							
							subBean.setIntIntvStr(resultSet2.getString("internal_interview_date"));
							if(subBean.getIntIntvStr() != null){
								subBean.setIntIntvValue(Dateformatter.toStringDate(subBean.getIntIntvStr()));
							}else {
								subBean.setIntIntvValue("");
							}
							
							
							subBean.setClntIntvStr(resultSet2.getString("client_interview_date"));
							if(subBean.getClntIntvStr() != null){
								subBean.setClntIntvValue(Dateformatter.toStringDate(subBean.getClntIntvStr()));
							}else {
								subBean.setClntIntvValue("");
							}
							
							subBean.setCompanyName(resultSet2.getString("other_info"));
							if(subBean.getCompanyName() == null){
								subBean.setCompanyName("");
							}
							
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
  
  public static ArrayList<IndividualClientReportBean> loadRidListWithStatus(int reqId, int statusId){
		
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
				preparedStatement = Pstm.createQuery(connection, IndividualClientReportSql.loadIndividualRid, Arrays.asList(reqId));
				
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
					bean.setEmailId("");
					
					bean.setSkillId(resultSet.getInt("req_skill_id"));
					
					bean.setSkillSetLabel("Skill : " + resultSet.getString("master_skill_set_name")); 
					
					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));  
					bean.setIntIntvValue("");
					bean.setClntIntvValue("");
					bean.setYoExp(0);
					
					bean.setStyle(bean.getBoldStyle());
					bean.setBackGroundStyle(bean.getBackGroundpaParent());
					bean.setRidLbFieldVis(true); 
					bean.setRidDatelbFieldVis(true); 
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
						preparedStatement2 = Pstm.createQuery(connection, IndividualClientReportSql.loadRidDetailsListWithStatus, Arrays.asList(reqId,statusId));
						
						logger.info("R_ID DETAILS - " + preparedStatement2.unwrap(PreparedStatement.class));
						
						ResultSet resultSet2 = preparedStatement2.executeQuery();
						
						while (resultSet2.next()) {
							
							IndividualClientReportBean subBean = new IndividualClientReportBean();
							
							subBean.setrIdLabel(resultSet2.getString("final_status"));
							subBean.setrIdDateLabel(resultSet2.getString("res_name"));
							subBean.setYoExp(resultSet2.getDouble("res_experience"));
							subBean.setSkillSetLabel(resultSet2.getString("rts_contact_no"));
							
							subBean.setEmailId(resultSet2.getString("res_emailid"));
							
							subBean.setIntIntvStr(resultSet2.getString("internal_interview_date"));
							if(subBean.getIntIntvStr() != null){
								subBean.setIntIntvValue(Dateformatter.toStringDate(subBean.getIntIntvStr()));
							}else {
								subBean.setIntIntvValue("");
							}
							
							
							subBean.setClntIntvStr(resultSet2.getString("client_interview_date"));
							if(subBean.getClntIntvStr() != null){
								subBean.setClntIntvValue(Dateformatter.toStringDate(subBean.getClntIntvStr()));
							}else {
								subBean.setClntIntvValue("");
							}
							
							subBean.setCompanyName(resultSet2.getString("other_info"));
							if(subBean.getCompanyName() == null){
								subBean.setCompanyName("");
							}
							
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
