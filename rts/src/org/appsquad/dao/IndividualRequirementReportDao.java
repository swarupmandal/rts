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
	
	public static ArrayList<IndividualClientReportBean> loadRidList(int rid){
		
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
				preparedStatement = Pstm.createQuery(connection, IndividualRequirementReportSql.loadClientDetails, Arrays.asList(rid));
				
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
						preparedStatement2 = Pstm.createQuery(connection, IndividualRequirementReportSql.loadRidDetailsList, Arrays.asList(rid));
						System.out.println("preparedStatement2 -- " + preparedStatement2);
						
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
	
}
