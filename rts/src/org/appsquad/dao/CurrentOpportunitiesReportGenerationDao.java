package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import org.apache.log4j.Logger;
import org.appsquad.bean.CurrentOpportunitiesReportGenerationBean;
import org.appsquad.bean.MonthReportBean;
import org.appsquad.bean.ResourceMasterBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.CurrentOpportunitiesReportGenerationSql;
import org.appsquad.sql.ResourceAllocationSql;
import org.appsquad.utility.MonthShowingForReport;
import org.appsquad.utility.Pstm;

public class CurrentOpportunitiesReportGenerationDao {
	final static Logger logger = Logger.getLogger(CurrentOpportunitiesReportGenerationDao.class);
	
	public static int countForParentRow(int trackingID,Connection connection) throws Exception{
		int counter = 0;
		PreparedStatement preparedStatementFetch = null;
		try{
			String sql = "select count(*) from rts_unpaid_details_for_report where rts_tracking_details_id = ? ";
			preparedStatementFetch = connection.prepareStatement(sql);
			preparedStatementFetch.setInt(1, trackingID);
			
			ResultSet resultSet = preparedStatementFetch.executeQuery();
			while(resultSet.next()){
				counter = resultSet.getInt(1);
			}
		}finally{
			if(preparedStatementFetch!=null){
				preparedStatementFetch.close();
			}
		}
		return counter;
	}
	
	public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadReportData() {
		ArrayList<CurrentOpportunitiesReportGenerationBean> list = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
		if (list.size() > 0) {
			list.clear();
		}
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.reportSql,null);
				logger.info("load unpaid parent row- "+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportGenerationBean bean = new CurrentOpportunitiesReportGenerationBean();
					
					bean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
					bean.getCurrentOpportunitiesReportBean().setYear("Client Name: "+resultSet.getString("clientname"));
					bean.getCurrentOpportunitiesReportBean().setMonth("RequirementID: "+resultSet.getInt("r_id"));
					bean.getCurrentOpportunitiesReportBean().setBillNoString("Skill Set: "+resultSet.getString("master_skill_set_name"));
					bean.getCurrentOpportunitiesReportBean().setBillDateSql(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmountString("Resource Name: "+resultSet.getString("resource_name"));
					bean.getCurrentOpportunitiesReportBean().setPaid(null);
					bean.getCurrentOpportunitiesReportBean().setChqDetails(null);
					bean.setBackGround(bean.getBackGroundpaParent());
					bean.setStyle(bean.getBoldStyle());
					
					int count = countForParentRow(bean.getTrackingDetailsId(), connection);
					if(count>0){
						list.add(bean);
					}
					
					sub_sql: {
						PreparedStatement preparedStatement2 = null;
						ResultSet resultSet2 = null;
						try {
							preparedStatement2 = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.subReportSql,Arrays.asList(bean.getTrackingDetailsId()));
							logger.info("load unpaid child row- "+ preparedStatement2.unwrap(PreparedStatement.class));
							resultSet2 = preparedStatement2.executeQuery();
							while (resultSet2.next()) {
								CurrentOpportunitiesReportGenerationBean subBean = new CurrentOpportunitiesReportGenerationBean();
								
								subBean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
								subBean.getClientInformationBean().setFullName(null);
								subBean.getCurrentOpportunitiesReportBean().setYear(resultSet2.getString("year"));
								subBean.getCurrentOpportunitiesReportBean().setMonth(resultSet2.getString("month"));
								subBean.getRequirementGenerationBean().setRequirementId(null);
								subBean.getCurrentOpportunitiesReportBean().setBillNoString(resultSet2.getString("bill_no"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmountString(resultSet2.getString("bill_amount"));
								subBean.getCurrentOpportunitiesReportBean().setChqDetails(resultSet2.getString("chq_details"));
								/*if(Integer.valueOf(resultSet2.getString("bill_no"))==0){
									subBean.getCurrentOpportunitiesReportBean().setBillNoString(null);
								}else{
									subBean.getCurrentOpportunitiesReportBean().setBillNoString(resultSet2.getString("bill_no"));
								}*/
								subBean.getSkillsetMasterbean().setSkillset(null);
								subBean.getCurrentOpportunitiesReportBean().setBillDateSql(resultSet2.getDate("bill_date"));
								subBean.getResourceMasterBean().setFullName(null);
								/*if(Integer.valueOf(resultSet2.getString("bill_amount"))==0){
									subBean.getCurrentOpportunitiesReportBean().setBillAmountString(null);
								}else{
									subBean.getCurrentOpportunitiesReportBean().setBillAmountString(resultSet2.getString("bill_amount"));
								}*/
								subBean.getCurrentOpportunitiesReportBean().setPaid(resultSet2.getString("paid"));
								/*if(Integer.valueOf(resultSet2.getString("chq_details"))==0){
									subBean.getCurrentOpportunitiesReportBean().setChqDetails(null);
								}else{
									subBean.getCurrentOpportunitiesReportBean().setChqDetails(resultSet2.getString("chq_details"));
								}*/
								subBean.setStyle(subBean.getLighterStyle());
								
                                list.add(subBean);
							}
						} finally {
							if (preparedStatement2 != null) {
								preparedStatement2.close();
							}
							if (resultSet2 != null) {
								resultSet2.close();
							}
						}
					}
				}
			} finally {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			logger.fatal(e);
			logger.error(e);
			e.printStackTrace();
		}
		return list;
	}
	
	
	/*public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadReportData(Date fromDate, Date toDate) {
		ArrayList<CurrentOpportunitiesReportGenerationBean> list = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
		if (list.size() > 0) {
			list.clear();
		}
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.reportSql,Arrays.asList(fromDate, toDate));
				logger.info("load Client List with date range- "+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportGenerationBean bean = new CurrentOpportunitiesReportGenerationBean();
					
					bean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
					bean.getCurrentOpportunitiesReportBean().setMonth("Client Name: "+resultSet.getString("clientname"));
					bean.getCurrentOpportunitiesReportBean().setBillNoString("RequirementID: "+resultSet.getInt("r_id"));
					bean.getCurrentOpportunitiesReportBean().setBillDateSql(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmountString("Skill Set: "+resultSet.getString("master_skill_set_name"));
					bean.getCurrentOpportunitiesReportBean().setPaid("Resource Name: "+resultSet.getString("resource_name"));
					bean.getCurrentOpportunitiesReportBean().setChqDetails(null);
					bean.setBackGround(bean.getBackGroundpaParent());
					bean.setStyle(bean.getBoldStyle());
					
					list.add(bean);
					sub_sql: {
						PreparedStatement preparedStatement2 = null;
						ResultSet resultSet2 = null;
						try {
							preparedStatement2 = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.subReportSql,Arrays.asList(bean.getTrackingDetailsId()));
							logger.info("Client DETAILS - "+ preparedStatement2.unwrap(PreparedStatement.class));
							resultSet2 = preparedStatement2.executeQuery();
							while (resultSet2.next()) {
								CurrentOpportunitiesReportGenerationBean subBean = new CurrentOpportunitiesReportGenerationBean();
								
								subBean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
								subBean.getClientInformationBean().setFullName(null);
								subBean.getCurrentOpportunitiesReportBean().setMonth(resultSet2.getString("month"));
								subBean.getRequirementGenerationBean().setRequirementId(null);
								subBean.getCurrentOpportunitiesReportBean().setBillNoString(resultSet2.getString("bill_no"));
								subBean.getSkillsetMasterbean().setSkillset(null);
								subBean.getCurrentOpportunitiesReportBean().setBillDateSql(resultSet2.getDate("bill_date"));
								subBean.getResourceMasterBean().setFullName(null);
								subBean.getCurrentOpportunitiesReportBean().setBillAmountString(resultSet2.getString("bill_amount"));
								subBean.getCurrentOpportunitiesReportBean().setPaid(resultSet2.getString("paid"));
								subBean.getCurrentOpportunitiesReportBean().setChqDetails(resultSet2.getString("chq_details"));
								subBean.setStyle(subBean.getLighterStyle());
								
                                list.add(subBean);
							}
						} finally {
							if (preparedStatement2 != null) {
								preparedStatement2.close();
							}
							if (resultSet2 != null) {
								resultSet2.close();
							}
						}
					}
				}
			} finally {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			logger.fatal(e);
			logger.error(e);
			e.printStackTrace();
		}
		return list;
	}*/
	
	public static ArrayList<ResourceMasterBean> onLoadResourceDetails(){
		ArrayList<ResourceMasterBean> resourceList = new ArrayList<ResourceMasterBean>();
		if(resourceList.size()>0){
			resourceList.clear();
		}
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.fetchResourceDetailsForCurOppurScreen,null);
						   
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								ResourceMasterBean bean = new ResourceMasterBean();
								bean.setResourceId(resultSet.getInt("id"));
								bean.setFullName(resultSet.getString("res_name")+" "+resultSet.getString("res_surname"));
								bean.setYearOfExperience(resultSet.getInt("res_experience"));
								bean.setAddress(resultSet.getString("res_address"));
								bean.setEmailId(resultSet.getString("res_emailid"));
								bean.getSkillsetMasterbean().setSkillset(resultSet.getString("rts_skill_name"));
								
								resourceList.add(bean);
							}  
							ResourceMasterBean resourceMasterBean = new ResourceMasterBean();
							resourceMasterBean.setFullName("-ALL-");
							resourceList.add(resourceMasterBean);
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
	
	public static ArrayList<ResourceMasterBean> onLoadResourceDetailsForSearch(String name){
		ArrayList<ResourceMasterBean> resourceList = new ArrayList<ResourceMasterBean>();
		if(resourceList.size()>0){
			resourceList.clear();
		}
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, ResourceAllocationSql.fetchResourceDetailsForCurOppurScreenForSerach,Arrays.asList(name.toUpperCase()+"%"));
						   
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								ResourceMasterBean bean = new ResourceMasterBean();
								bean.setResourceId(resultSet.getInt("id"));
								bean.setFullName(resultSet.getString("resource_full_name"));
								bean.setYearOfExperience(resultSet.getInt("res_experience"));
								bean.setAddress(resultSet.getString("res_address"));
								bean.setEmailId(resultSet.getString("res_emailid"));
								bean.getSkillsetMasterbean().setSkillset(resultSet.getString("rts_skill_name"));
								
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
	
	public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadOppurtunityWiseReportForClientDao(CurrentOpportunitiesReportGenerationBean bean) {
		ArrayList<CurrentOpportunitiesReportGenerationBean> list = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
		ArrayList<CurrentOpportunitiesReportGenerationBean> totalList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
		if (list.size() > 0) {
			list.clear();
		}
		if(totalList.size()>0){
			totalList.clear();
		}
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.oppurtunityWisereportForClientSql,Arrays.asList("%"+bean.getClientInformationBean().getFullName()+"%"));
				logger.info("- "+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportGenerationBean firstBean = new CurrentOpportunitiesReportGenerationBean();
					
					firstBean.getCurrentOpportunitiesBean().setTentureFromUtil(resultSet.getDate("tenure_from"));
					firstBean.getCurrentOpportunitiesBean().setTentureToUtil(resultSet.getDate("tenure_to"));
					firstBean.getClientInformationBean().setFullName(resultSet.getString("clientname"));
					firstBean.getResourceMasterBean().setFullName(resultSet.getString("resource_name"));
					firstBean.setRtsTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
					
					list = MonthShowingForReport.calculation(firstBean);
					
					totalList.addAll(list);
				}
			} finally {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			logger.fatal(e);
			logger.error(e);
			e.printStackTrace();
		}
		return totalList;
	}
	
	
	public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadOppurtunityWiseReportForClientDaoForAll(CurrentOpportunitiesReportGenerationBean bean) {
		ArrayList<CurrentOpportunitiesReportGenerationBean> list = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
		ArrayList<CurrentOpportunitiesReportGenerationBean> totalList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
		if (list.size() > 0) {
			list.clear();
		}
		if(totalList.size()>0){
			totalList.clear();
		}
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.oppurtunityWisereportForAllSql,null);
				logger.info("- "+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportGenerationBean firstBean = new CurrentOpportunitiesReportGenerationBean();
					
					firstBean.getCurrentOpportunitiesBean().setTentureFromUtil(resultSet.getDate("tenure_from"));
					firstBean.getCurrentOpportunitiesBean().setTentureToUtil(resultSet.getDate("tenure_to"));
					firstBean.getClientInformationBean().setFullName(resultSet.getString("clientname"));
					firstBean.getResourceMasterBean().setFullName(resultSet.getString("resource_name"));
					firstBean.setRtsTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
					
					list = MonthShowingForReport.calculation(firstBean);
					
					totalList.addAll(list);
				}
			} finally {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			logger.fatal(e);
			logger.error(e);
			e.printStackTrace();
		}
		return totalList;
	}
	
	public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadOppurtunityWiseReportForClientDaoForResource(CurrentOpportunitiesReportGenerationBean bean) {
		ArrayList<CurrentOpportunitiesReportGenerationBean> list = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
		ArrayList<CurrentOpportunitiesReportGenerationBean> totalList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
		if (list.size() > 0) {
			list.clear();
		}
		if(totalList.size()>0){
			totalList.clear();
		}
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.oppurtunityWiseReportresourceSql,Arrays.asList("%"+bean.getResourceMasterBean().getFullName()+"%"));
				logger.info("- "+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportGenerationBean firstBean = new CurrentOpportunitiesReportGenerationBean();
					
					firstBean.getCurrentOpportunitiesBean().setTentureFromUtil(resultSet.getDate("tenure_from"));
					//System.out.println(firstBean.getCurrentOpportunitiesBean().getTentureFromUtil());
					firstBean.getCurrentOpportunitiesBean().setTentureToUtil(resultSet.getDate("tenure_to"));
					firstBean.getClientInformationBean().setFullName(resultSet.getString("clientname"));
					firstBean.getResourceMasterBean().setFullName(resultSet.getString("resource_name"));
					firstBean.setRtsTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
					
					list = MonthShowingForReport.calculation(firstBean);
					//System.out.println("IN DAO CLASS SIZE:"+list.size());
					/*for(CurrentOpportunitiesReportGenerationBean bean1: list){
						System.out.println("MONTH :"+bean1.getCurrentOpportunitiesReportBean().getMonth()+"----"+bean1.getCurrentOpportunitiesBean().getTentureFromUtil());
					}*/
					totalList.addAll(list);
					//System.out.println("INDAO CLASS TOTAL LIST :"+totalList.size());
				}
			} finally {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			logger.fatal(e);
			logger.error(e);
			e.printStackTrace();
		}
		return totalList;
	}
	
	
	public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadOppurtunityWiseReportForClientDaoForResourceAll(CurrentOpportunitiesReportGenerationBean bean) {
		ArrayList<CurrentOpportunitiesReportGenerationBean> list = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
		ArrayList<CurrentOpportunitiesReportGenerationBean> totalList = new ArrayList<CurrentOpportunitiesReportGenerationBean>();
		if (list.size() > 0) {
			list.clear();
		}
		if(totalList.size()>0){
			totalList.clear();
		}
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.oppurtunityWiseReportresourceAllSql,null);
				logger.info("- "+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportGenerationBean firstBean = new CurrentOpportunitiesReportGenerationBean();
					
					firstBean.getCurrentOpportunitiesBean().setTentureFromUtil(resultSet.getDate("tenure_from"));
					//System.out.println(firstBean.getCurrentOpportunitiesBean().getTentureFromUtil());
					firstBean.getCurrentOpportunitiesBean().setTentureToUtil(resultSet.getDate("tenure_to"));
					firstBean.getClientInformationBean().setFullName(resultSet.getString("clientname"));
					firstBean.getResourceMasterBean().setFullName(resultSet.getString("resource_name"));
					firstBean.setRtsTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
					
					list = MonthShowingForReport.calculation(firstBean);
					//System.out.println("IN DAO CLASS SIZE:"+list.size());
					/*for(CurrentOpportunitiesReportGenerationBean bean1: list){
						System.out.println("MONTH :"+bean1.getCurrentOpportunitiesReportBean().getMonth()+"----"+bean1.getCurrentOpportunitiesBean().getTentureFromUtil());
					}*/
					totalList.addAll(list);
					//System.out.println("INDAO CLASS TOTAL LIST :"+totalList.size());
				}
			} finally {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			logger.fatal(e);
			logger.error(e);
			e.printStackTrace();
		}
		return totalList;
	}
	
	
	/**
	 * 
	 * This function is used for calculating Grand Total:-Prolay
	 */
	
	public static long calculateGrandTotal(LinkedHashSet<MonthReportBean> list)
	{
	   	long total = 0;
	   	
	   	for(MonthReportBean reportBean: list){
	   		if(!reportBean.getMonthName().equalsIgnoreCase("")){
	   			for(CurrentOpportunitiesReportGenerationBean generationBean: reportBean.getCurrentOpportunitiesReportGenerationBeanList()){
	   				if(generationBean.getResourceMasterBean().getFullName()!=null && 
	   						generationBean.getResourceMasterBean().getFullName().trim().length()>0){
	   					
	   					total+=generationBean.getCurrentOpportunitiesBean().getMargin().longValue();
	   				}
	   			}
	   		}
	   	}
	   	//System.out.println("IN DAO CLASS GRAND TOTAL IS ::"+total);
	   	return total;
	}
	
}
