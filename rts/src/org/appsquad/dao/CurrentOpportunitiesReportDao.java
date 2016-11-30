package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.Logger;
import org.appsquad.bean.CurrentOpportunitiesReportBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.CurrentOpportunitiesReportSql;
import org.appsquad.utility.Pstm;
import org.zkoss.zul.Messagebox;

public class CurrentOpportunitiesReportDao {
	final static Logger logger = Logger.getLogger(CurrentOpportunitiesReportDao.class);
	
	public static ArrayList<CurrentOpportunitiesReportBean> loadTrackingDetails(){
		ArrayList<CurrentOpportunitiesReportBean> list = new ArrayList<CurrentOpportunitiesReportBean>();
		if(list.size()>0){
			list.clear();
		}
		try {
			Connection connection = DbConnection.createConnection();
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesReportSql.fetchTrackingDetailsSql, null);
				logger.info("Fetch Current Opportunities Tracking Details >>> >> > "+ preparedStatement.unwrap(PreparedStatement.class));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportBean bean = new CurrentOpportunitiesReportBean();
					bean.getClientInformationBean().setFullName(resultSet.getString("clientname"));
					bean.getRequirementGenerationBean().setRequirementId(resultSet.getInt("r_id"));
					bean.getSkillsetMasterbean().setSkillset(resultSet.getString("master_skill_set_name"));
					bean.getResourceMasterBean().setFullName(resultSet.getString("resource_name"));
					bean.setTrackingId(resultSet.getInt("rts_req_res_status_tracking_id"));
					bean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
					fetchTrackingDetailsWrtTrackingDetailsId(connection, bean.getTrackingDetailsId(), bean);
					
					list.add(bean);
				}
				System.out.println("IN DAO CLASS LIST SIZE IS :"+list.size());
			} catch (Exception e) {
				e.printStackTrace();
				String msg = e.getMessage();
				Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
				logger.fatal(e);
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
		return list;
	}
	
	public static void fetchTrackingDetailsWrtTrackingDetailsId(Connection connection, Integer trackingDetailsId,CurrentOpportunitiesReportBean bean) throws SQLException{
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesReportSql.fetchFinalDetailsSql, Arrays.asList(trackingDetailsId));
			logger.info("FETCH TRACKING DETAILS WITH RESPECT TO TRACK-ID >>> >> > "+ preparedStatement.unwrap(PreparedStatement.class));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
		      bean.setMonth(resultSet.getString("month"));
		      bean.setYear(resultSet.getString("year"));
		      bean.setBillNo(resultSet.getDouble("bill_no"));
		      bean.setBillDateSql(resultSet.getDate("bill_date"));
		      bean.setBillAmount(resultSet.getDouble("bill_amount"));
		      bean.setPaid(resultSet.getString("paid"));  
		      bean.setChqDetailsValue(resultSet.getFloat("chq_details"));
		      bean.setTimesheetPath(resultSet.getString("timesheet"));
		      bean.setInvoiceCopyPath(resultSet.getString("invoice_copy"));
		      
			}
		} finally {
			if(preparedStatement !=null){
				preparedStatement.close();
			}
		}
	}
	
	public static ArrayList<String> loadAllMonths(){
		ArrayList<String> monthList = new ArrayList<String>();
		try {
			Connection connection = DbConnection.createConnection();
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesReportSql.fetchMonthNameSql, null);
				logger.info("Fetch Month >>> >> > "+ preparedStatement.unwrap(PreparedStatement.class));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportBean bean = new CurrentOpportunitiesReportBean();
					bean.setMonth(resultSet.getString("month_name"));
					
					monthList.add(bean.getMonth());
				}
				System.out.println("IN DAO CLASS LIST SIZE IS :"+monthList.size());
			} catch (Exception e) {
				e.printStackTrace();
				String msg = e.getMessage();
				Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
				logger.fatal(e);
				logger.error(e);
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal(e);
			logger.error(e);
		}
		return monthList;
	}
	
	public static ArrayList<String> loadAllYears(){
		ArrayList<String> yearList = new ArrayList<String>();
		try {
			Connection connection = DbConnection.createConnection();
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesReportSql.fetchYearSql, null);
				logger.info("Fetch Year >>> >> > "+ preparedStatement.unwrap(PreparedStatement.class));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportBean bean = new CurrentOpportunitiesReportBean();
					bean.setYear(resultSet.getString("year"));
					
					yearList.add(bean.getYear());
				}
				System.out.println("IN DAO CLASS LIST SIZE IS :"+yearList.size());
			} catch (Exception e) {
				e.printStackTrace();
				String msg = e.getMessage();
				Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
				logger.fatal(e);
				logger.error(e);
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal(e);
			logger.error(e);
		}
		return yearList;
	}
	
	public static boolean insertTrackingData(CurrentOpportunitiesReportBean bean){
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
									CurrentOpportunitiesReportSql.insertIntoTrackingFinalTableSql, Arrays.asList(bean.getMonth(),bean.getYear(),bean.getTimesheetPath(),
											bean.getInvoiceCopyPath(),bean.getBillNo(),bean.getBillDateSql(),bean.getBillAmount(),bean.getPaid(),bean.getChqDetailsValue(),
											bean.getTrackingDetailsId()));
					    	logger.info("insertTrackingFinalData- " + preparedStatementInsert.unwrap(PreparedStatement.class));
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
				
					if(isSaved){
						Messagebox.show(" Final Tracking Details Saved Successfully ","Information",Messagebox.OK,Messagebox.INFORMATION);
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
	
	public static int countLastNumber(){
		int num = 0;
		int returnNum = 0;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesReportSql.countLastNumberSql, null);
						    logger.info("Count Last Number- " + preparedStatement.unwrap(PreparedStatement.class));
						    ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								num = resultSet.getInt(1);
								returnNum = num+1;
							} 
						} finally{
							if(preparedStatement!=null){
								preparedStatement.close();
							}
						}
				    }
				} catch (Exception e) {
					e.printStackTrace();
					logger.fatal(e);
					logger.error(e);					
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal(e);
			logger.error(e);
		}
		return returnNum;	
	}
	
	public static Integer fetchCountTrackingIdService(Integer trackingId){
		int count = 0;
		try {
			Connection connection = DbConnection.createConnection();
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesReportSql.fetchCountTrackingIdSql, Arrays.asList(trackingId));
				logger.info("Fetch Count Number: "+ preparedStatement.unwrap(PreparedStatement.class));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					count = resultSet.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
				String msg = e.getMessage();
				Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
				logger.fatal(e);
				logger.error(e);
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
	
	public static boolean deleteRoleData(CurrentOpportunitiesReportBean opportunitiesBean){
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
					    	preparedStatementInsert = Pstm.createQuery(connection,CurrentOpportunitiesReportSql.deleteTrackingIdSql, Arrays.asList(opportunitiesBean.getTrackingDetailsId()));
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
	
	public static boolean updateDetailsTableDao(CurrentOpportunitiesReportBean opportunitiesBean){
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
					    	preparedStatementInsert = Pstm.createQuery(connection,CurrentOpportunitiesReportSql.updateDetailsTableSql, Arrays.asList(opportunitiesBean.getTrackingDetailsId()));
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
}
