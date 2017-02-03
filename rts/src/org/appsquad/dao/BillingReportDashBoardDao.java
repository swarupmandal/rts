package org.appsquad.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import org.appsquad.bean.CurrentOpportunitiesReportGenerationBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.CurrentOpportunitiesReportGenerationSql;
import org.appsquad.utility.Dateformatter;
import org.appsquad.utility.Main;
import org.appsquad.utility.Pstm;

public class BillingReportDashBoardDao {
	
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
	
	public static int countForParentRowForDate(int trackingID,Date fromDate, Date toDate,Connection connection) throws Exception{
		int counter = 0;
		PreparedStatement preparedStatementFetch = null;
		try{
			String sql = "select count(*) from rts_paid_unpaid_details_for_report where rts_tracking_details_id = ? and bill_date >= ? and bill_date<= ? ";
			preparedStatementFetch = connection.prepareStatement(sql);
			preparedStatementFetch.setInt(1, trackingID);
			preparedStatementFetch.setDate(2, fromDate);
			preparedStatementFetch.setDate(3, toDate);
			
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

	
	public static int countForParentRowForBillAndDateTotal(int trackingID,Date fromDate, Date toDate,String status,Connection connection) throws Exception{
		int counter = 0;
		PreparedStatement preparedStatementFetch = null;
		try{
			String sql = "select count(*) from rts_paid_unpaid_details_for_report where rts_tracking_details_id = ? and bill_date >= ? and bill_date<= ? and paid = ? ";
			preparedStatementFetch = connection.prepareStatement(sql);
			preparedStatementFetch.setInt(1, trackingID);
			preparedStatementFetch.setDate(2, fromDate);
			preparedStatementFetch.setDate(3, toDate);
			preparedStatementFetch.setString(4, status);
			
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
	
	public static int countForParentRowForSelectionPaidStatus(int trackingID,String paid,Connection connection) throws Exception{
		int counter = 0;
		PreparedStatement preparedStatementFetch = null;
		try{
			String sql = "select count(*) from rts_paid_unpaid_details_for_report where rts_tracking_details_id = ? and paid = ? ";
			preparedStatementFetch = connection.prepareStatement(sql);
			preparedStatementFetch.setInt(1, trackingID);
			preparedStatementFetch.setString(2, paid);
			
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
	
	public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadReportDataForClient(int clientId) {
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
				preparedStatement = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.reportSql1,Arrays.asList(clientId));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportGenerationBean bean = new CurrentOpportunitiesReportGenerationBean();
					
					bean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
					bean.getCurrentOpportunitiesReportBean().setYear("Client Name: "+resultSet.getString("clientname"));
					bean.getCurrentOpportunitiesReportBean().setMonth("RequirementID: "+resultSet.getInt("r_id"));
					bean.getCurrentOpportunitiesReportBean().setBillNoString("Skill Set: "+resultSet.getString("master_skill_set_name"));
					bean.getCurrentOpportunitiesReportBean().setBillDateSql(null);
					bean.getCurrentOpportunitiesReportBean().setBillDate(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmountString("Resource Name: "+resultSet.getString("resource_name"));
					bean.getCurrentOpportunitiesReportBean().setBillAmount(null);
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
							preparedStatement2 = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.subReportSql1,Arrays.asList(bean.getTrackingDetailsId()));
							
							resultSet2 = preparedStatement2.executeQuery();
							while (resultSet2.next()) {
								CurrentOpportunitiesReportGenerationBean subBean = new CurrentOpportunitiesReportGenerationBean();
								
								subBean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
								subBean.getClientInformationBean().setFullName(null);
								subBean.getCurrentOpportunitiesReportBean().setYear(resultSet2.getString("year"));
								subBean.getCurrentOpportunitiesReportBean().setMonth(resultSet2.getString("month"));
								subBean.getRequirementGenerationBean().setRequirementId(null);
								subBean.getCurrentOpportunitiesReportBean().setBillNoString(resultSet2.getString("bill_no"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmount(resultSet2.getDouble("bill_amount"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmountString(Main.convert(resultSet2.getDouble("bill_amount")));
								
								subBean.getSkillsetMasterbean().setSkillset(null);
								subBean.getCurrentOpportunitiesReportBean().setBillDateSql(resultSet2.getDate("bill_date"));
								
								subBean.getCurrentOpportunitiesReportBean().setBillDateSql(resultSet2.getDate("bill_date"));
								subBean.getCurrentOpportunitiesReportBean().setBillDateString(resultSet2.getString("bill_date"));
								subBean.getCurrentOpportunitiesReportBean().setBillDate(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getBillDateString()));
								
								subBean.getCurrentOpportunitiesReportBean().setChqDate(resultSet2.getString("chq_date"));
								
								if(subBean.getCurrentOpportunitiesReportBean().getChqDate()!=null){
									subBean.getCurrentOpportunitiesReportBean().setChqDateString(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getChqDate()));
									subBean.getCurrentOpportunitiesReportBean().setChqDetails(resultSet2.getString("chq_details")+"/"+subBean.getCurrentOpportunitiesReportBean().getChqDateString());
								}
								
								subBean.getResourceMasterBean().setFullName(null);
								subBean.getCurrentOpportunitiesReportBean().setPaid(resultSet2.getString("paid"));
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
			
			if(list.size()>0){
				CurrentOpportunitiesReportGenerationBean reportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
				reportGenerationBean.getCurrentOpportunitiesReportBean().setPaid("");
				reportGenerationBean.getCurrentOpportunitiesReportBean().setChqDetails("Grand Total :"+Main.convert(grandTotalBillingAmount(list)));
				reportGenerationBean.setBackGround(reportGenerationBean.getAnotherStyle());
				reportGenerationBean.setStyle(reportGenerationBean.getBoldStyle());
				
				list.add(reportGenerationBean);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadReportDataForClientAndStatusCombination(int clientId,String status) {
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
				preparedStatement = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.reportSql1,Arrays.asList(clientId));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportGenerationBean bean = new CurrentOpportunitiesReportGenerationBean();
					
					bean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
					bean.getCurrentOpportunitiesReportBean().setYear("Client Name: "+resultSet.getString("clientname"));
					bean.getCurrentOpportunitiesReportBean().setMonth("RequirementID: "+resultSet.getInt("r_id"));
					bean.getCurrentOpportunitiesReportBean().setBillNoString("Skill Set: "+resultSet.getString("master_skill_set_name"));
					bean.getCurrentOpportunitiesReportBean().setBillDateSql(null);
					bean.getCurrentOpportunitiesReportBean().setBillDate(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmount(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmountString("Resource Name: "+resultSet.getString("resource_name"));
					bean.getCurrentOpportunitiesReportBean().setPaid(null);
					bean.getCurrentOpportunitiesReportBean().setChqDetails(null);
					bean.setBackGround(bean.getBackGroundpaParent());
					bean.setStyle(bean.getBoldStyle());
					
					int count = countForParentRowForSelectionPaidStatus(bean.getTrackingDetailsId(),status,connection);
					if(count>0){
						list.add(bean);
					}
					
					sub_sql: {
						PreparedStatement preparedStatement2 = null;
						ResultSet resultSet2 = null;
						try {
							preparedStatement2 = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.subReportSql3,Arrays.asList(bean.getTrackingDetailsId(),status));
							
							resultSet2 = preparedStatement2.executeQuery();
							while (resultSet2.next()) {
								CurrentOpportunitiesReportGenerationBean subBean = new CurrentOpportunitiesReportGenerationBean();
								
								subBean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
								subBean.getClientInformationBean().setFullName(null);
								subBean.getCurrentOpportunitiesReportBean().setYear(resultSet2.getString("year"));
								subBean.getCurrentOpportunitiesReportBean().setMonth(resultSet2.getString("month"));
								subBean.getRequirementGenerationBean().setRequirementId(null);
								subBean.getCurrentOpportunitiesReportBean().setBillNoString(resultSet2.getString("bill_no"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmount(resultSet2.getDouble("bill_amount"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmountString(Main.convert(resultSet2.getDouble("bill_amount")));
								
								subBean.getSkillsetMasterbean().setSkillset(null);
								subBean.getCurrentOpportunitiesReportBean().setBillDateSql(resultSet2.getDate("bill_date"));
								
								 
								subBean.getCurrentOpportunitiesReportBean().setBillDateString(resultSet2.getString("bill_date"));
								subBean.getCurrentOpportunitiesReportBean().setBillDate(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getBillDateString()));
								
								subBean.getCurrentOpportunitiesReportBean().setChqDate(resultSet2.getString("chq_date"));
								
								if(subBean.getCurrentOpportunitiesReportBean().getChqDate()!=null){
									subBean.getCurrentOpportunitiesReportBean().setChqDateString(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getChqDate()));
									subBean.getCurrentOpportunitiesReportBean().setChqDetails(resultSet2.getString("chq_details")+"/"+subBean.getCurrentOpportunitiesReportBean().getChqDateString());
								}
								
								subBean.getResourceMasterBean().setFullName(null);
								
								subBean.getCurrentOpportunitiesReportBean().setPaid(resultSet2.getString("paid"));
								
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
			
			if(list.size()>0){
				CurrentOpportunitiesReportGenerationBean reportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
				reportGenerationBean.getCurrentOpportunitiesReportBean().setPaid("");
				reportGenerationBean.getCurrentOpportunitiesReportBean().setChqDetails("Grand Total :"+Main.convert(grandTotalBillingAmount(list)));
				reportGenerationBean.setBackGround(reportGenerationBean.getAnotherStyle());
				reportGenerationBean.setStyle(reportGenerationBean.getBoldStyle());
				
				list.add(reportGenerationBean);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadReportDataForAllCombination(int clientId,String status,Date fromDate, Date toDate) {
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
				preparedStatement = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.reportSql1,Arrays.asList(clientId));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportGenerationBean bean = new CurrentOpportunitiesReportGenerationBean();
					
					bean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
					bean.getCurrentOpportunitiesReportBean().setYear("Client Name: "+resultSet.getString("clientname"));
					bean.getCurrentOpportunitiesReportBean().setMonth("RequirementID: "+resultSet.getInt("r_id"));
					bean.getCurrentOpportunitiesReportBean().setBillNoString("Skill Set: "+resultSet.getString("master_skill_set_name"));
					bean.getCurrentOpportunitiesReportBean().setBillDateSql(null);
					bean.getCurrentOpportunitiesReportBean().setBillDate(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmount(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmountString("Resource Name: "+resultSet.getString("resource_name"));
					bean.getCurrentOpportunitiesReportBean().setPaid(null);
					bean.getCurrentOpportunitiesReportBean().setChqDetails(null);
					bean.setBackGround(bean.getBackGroundpaParent());
					bean.setStyle(bean.getBoldStyle());
					
					int count = countForParentRowForBillAndDateTotal(bean.getTrackingDetailsId(),fromDate,toDate,status,connection);
					if(count>0){
						list.add(bean);
					}
					
					sub_sql: {
						PreparedStatement preparedStatement2 = null;
						ResultSet resultSet2 = null;
						try {
							preparedStatement2 = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.subReportSql4,Arrays.asList(bean.getTrackingDetailsId(),fromDate,toDate,status));
							
							resultSet2 = preparedStatement2.executeQuery();
							while (resultSet2.next()) {
								CurrentOpportunitiesReportGenerationBean subBean = new CurrentOpportunitiesReportGenerationBean();
								
								subBean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
								subBean.getClientInformationBean().setFullName(null);
								subBean.getCurrentOpportunitiesReportBean().setYear(resultSet2.getString("year"));
								subBean.getCurrentOpportunitiesReportBean().setMonth(resultSet2.getString("month"));
								subBean.getRequirementGenerationBean().setRequirementId(null);
								subBean.getCurrentOpportunitiesReportBean().setBillNoString(resultSet2.getString("bill_no"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmount(resultSet2.getDouble("bill_amount"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmountString(Main.convert(resultSet2.getDouble("bill_amount")));
								
								subBean.getSkillsetMasterbean().setSkillset(null);
								subBean.getCurrentOpportunitiesReportBean().setBillDateSql(resultSet2.getDate("bill_date"));
								
								subBean.getCurrentOpportunitiesReportBean().setBillDateString(resultSet2.getString("bill_date"));
								subBean.getCurrentOpportunitiesReportBean().setBillDate(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getBillDateString()));
								
								subBean.getCurrentOpportunitiesReportBean().setChqDate(resultSet2.getString("chq_date"));
								
								if(subBean.getCurrentOpportunitiesReportBean().getChqDate()!=null){
									subBean.getCurrentOpportunitiesReportBean().setChqDateString(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getChqDate()));
									subBean.getCurrentOpportunitiesReportBean().setChqDetails(resultSet2.getString("chq_details")+"/"+subBean.getCurrentOpportunitiesReportBean().getChqDateString());
								}
								
								subBean.getResourceMasterBean().setFullName(null);
								
								subBean.getCurrentOpportunitiesReportBean().setPaid(resultSet2.getString("paid"));
								
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
			
			if(list.size()>0){
				CurrentOpportunitiesReportGenerationBean reportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
				reportGenerationBean.getCurrentOpportunitiesReportBean().setPaid("");
				reportGenerationBean.getCurrentOpportunitiesReportBean().setChqDetails("Grand Total :"+Main.convert(grandTotalBillingAmount(list)));
				reportGenerationBean.setBackGround(reportGenerationBean.getAnotherStyle());
				reportGenerationBean.setStyle(reportGenerationBean.getBoldStyle());
				
				list.add(reportGenerationBean);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadReportAllData() {
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
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportGenerationBean bean = new CurrentOpportunitiesReportGenerationBean();
					
					bean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
					bean.getCurrentOpportunitiesReportBean().setYear("Client Name: "+resultSet.getString("clientname"));
					bean.getCurrentOpportunitiesReportBean().setMonth("RequirementID: "+resultSet.getInt("r_id"));
					bean.getCurrentOpportunitiesReportBean().setBillNoString("Skill Set: "+resultSet.getString("master_skill_set_name"));
					bean.getCurrentOpportunitiesReportBean().setBillDateSql(null);
					bean.getCurrentOpportunitiesReportBean().setBillDate(null);
					bean.getCurrentOpportunitiesReportBean().setBillDate(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmountString("Resource Name: "+resultSet.getString("resource_name"));
					bean.getCurrentOpportunitiesReportBean().setBillAmount(null);
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
							preparedStatement2 = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.subReportSql1,Arrays.asList(bean.getTrackingDetailsId()));
							
							resultSet2 = preparedStatement2.executeQuery();
							while (resultSet2.next()) {
								CurrentOpportunitiesReportGenerationBean subBean = new CurrentOpportunitiesReportGenerationBean();
								
								subBean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
								subBean.getClientInformationBean().setFullName(null);
								subBean.getCurrentOpportunitiesReportBean().setYear(resultSet2.getString("year"));
								subBean.getCurrentOpportunitiesReportBean().setMonth(resultSet2.getString("month"));
								subBean.getRequirementGenerationBean().setRequirementId(null);
								subBean.getCurrentOpportunitiesReportBean().setBillNoString(resultSet2.getString("bill_no"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmount(resultSet2.getDouble("bill_amount"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmountString(Main.convert(resultSet2.getDouble("bill_amount")));
								
								subBean.getSkillsetMasterbean().setSkillset(null);
								subBean.getCurrentOpportunitiesReportBean().setBillDateSql(resultSet2.getDate("bill_date"));
								subBean.getCurrentOpportunitiesReportBean().setBillDateString(resultSet2.getString("bill_date"));
								subBean.getCurrentOpportunitiesReportBean().setBillDate(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getBillDateString()));
								
							    subBean.getCurrentOpportunitiesReportBean().setChqDate(resultSet2.getString("chq_date"));
								
								if(subBean.getCurrentOpportunitiesReportBean().getChqDate()!=null){
									subBean.getCurrentOpportunitiesReportBean().setChqDateString(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getChqDate()));
									subBean.getCurrentOpportunitiesReportBean().setChqDetails(resultSet2.getString("chq_details")+"/"+subBean.getCurrentOpportunitiesReportBean().getChqDateString());
								}
								
								
								subBean.getResourceMasterBean().setFullName(null);
								subBean.getCurrentOpportunitiesReportBean().setPaid(resultSet2.getString("paid"));
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
			
			
			if(list.size()>0){
				CurrentOpportunitiesReportGenerationBean reportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
				reportGenerationBean.getCurrentOpportunitiesReportBean().setPaid("");
				reportGenerationBean.getCurrentOpportunitiesReportBean().setChqDetails("Grand Total :"+Main.convert(grandTotalBillingAmount(list)));
				reportGenerationBean.setBackGround(reportGenerationBean.getAnotherStyle());
				reportGenerationBean.setStyle(reportGenerationBean.getBoldStyle());
				
				list.add(reportGenerationBean);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static Double grandTotalBillingAmount(ArrayList<CurrentOpportunitiesReportGenerationBean> list){
		Double total = 0d;
		
		for(CurrentOpportunitiesReportGenerationBean bean: list){
			if(bean.getCurrentOpportunitiesReportBean().getBillAmount()!=null){
				total+=bean.getCurrentOpportunitiesReportBean().getBillAmount();
			}
		}
		return total;
	}
	
	
	public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadReportBillDateData(Date fromDate, Date toDate) {
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
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportGenerationBean bean = new CurrentOpportunitiesReportGenerationBean();
					
					bean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
					bean.getCurrentOpportunitiesReportBean().setYear("Client Name: "+resultSet.getString("clientname"));
					bean.getCurrentOpportunitiesReportBean().setMonth("RequirementID: "+resultSet.getInt("r_id"));
					bean.getCurrentOpportunitiesReportBean().setBillNoString("Skill Set: "+resultSet.getString("master_skill_set_name"));
					bean.getCurrentOpportunitiesReportBean().setBillDateSql(null);
					bean.getCurrentOpportunitiesReportBean().setBillDate(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmount(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmountString("Resource Name: "+resultSet.getString("resource_name"));
					bean.getCurrentOpportunitiesReportBean().setPaid(null);
					bean.getCurrentOpportunitiesReportBean().setChqDetails(null);
					bean.setBackGround(bean.getBackGroundpaParent());
					bean.setStyle(bean.getBoldStyle());
					
					int count = countForParentRowForDate(bean.getTrackingDetailsId(),fromDate,toDate,connection);
					if(count>0){
						list.add(bean);
					}
					
					sub_sql: {
						PreparedStatement preparedStatement2 = null;
						ResultSet resultSet2 = null;
						try {
							preparedStatement2 = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.subReportSql2,Arrays.asList(bean.getTrackingDetailsId(),fromDate,toDate));
							
							resultSet2 = preparedStatement2.executeQuery();
							while (resultSet2.next()) {
								CurrentOpportunitiesReportGenerationBean subBean = new CurrentOpportunitiesReportGenerationBean();
								
								subBean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
								subBean.getClientInformationBean().setFullName(null);
								subBean.getCurrentOpportunitiesReportBean().setYear(resultSet2.getString("year"));
								subBean.getCurrentOpportunitiesReportBean().setMonth(resultSet2.getString("month"));
								subBean.getRequirementGenerationBean().setRequirementId(null);
								subBean.getCurrentOpportunitiesReportBean().setBillNoString(resultSet2.getString("bill_no"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmount(resultSet2.getDouble("bill_amount"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmountString(Main.convert(resultSet2.getDouble("bill_amount")));
								
								subBean.getSkillsetMasterbean().setSkillset(null);
								subBean.getCurrentOpportunitiesReportBean().setBillDateSql(resultSet2.getDate("bill_date"));
								
								subBean.getCurrentOpportunitiesReportBean().setBillDateString(resultSet2.getString("bill_date"));
								subBean.getCurrentOpportunitiesReportBean().setBillDate(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getBillDateString()));
								
								subBean.getCurrentOpportunitiesReportBean().setChqDate(resultSet2.getString("chq_date"));
								
								if(subBean.getCurrentOpportunitiesReportBean().getChqDate()!=null){
									subBean.getCurrentOpportunitiesReportBean().setChqDateString(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getChqDate()));
									subBean.getCurrentOpportunitiesReportBean().setChqDetails(resultSet2.getString("chq_details")+"/"+subBean.getCurrentOpportunitiesReportBean().getChqDateString());
								}
								
								subBean.getResourceMasterBean().setFullName(null);
								subBean.getCurrentOpportunitiesReportBean().setPaid(resultSet2.getString("paid"));
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
			
			if(list.size()>0){
				CurrentOpportunitiesReportGenerationBean reportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
				reportGenerationBean.getCurrentOpportunitiesReportBean().setPaid("");
				reportGenerationBean.getCurrentOpportunitiesReportBean().setChqDetails("Grand Total :"+Main.convert(grandTotalBillingAmount(list)));
				reportGenerationBean.setBackGround(reportGenerationBean.getAnotherStyle());
				reportGenerationBean.setStyle(reportGenerationBean.getBoldStyle());
				
				list.add(reportGenerationBean);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadReportWrtBillAndDateTotal(Date fromDate, Date toDate,String status) {
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
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportGenerationBean bean = new CurrentOpportunitiesReportGenerationBean();
					
					bean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
					bean.getCurrentOpportunitiesReportBean().setYear("Client Name: "+resultSet.getString("clientname"));
					bean.getCurrentOpportunitiesReportBean().setMonth("RequirementID: "+resultSet.getInt("r_id"));
					bean.getCurrentOpportunitiesReportBean().setBillNoString("Skill Set: "+resultSet.getString("master_skill_set_name"));
					bean.getCurrentOpportunitiesReportBean().setBillDateSql(null);
					bean.getCurrentOpportunitiesReportBean().setBillDateSql(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmount(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmountString("Resource Name: "+resultSet.getString("resource_name"));
					bean.getCurrentOpportunitiesReportBean().setPaid(null);
					bean.getCurrentOpportunitiesReportBean().setChqDetails(null);
					bean.setBackGround(bean.getBackGroundpaParent());
					bean.setStyle(bean.getBoldStyle());
					
					int count = countForParentRowForBillAndDateTotal(bean.getTrackingDetailsId(),fromDate,toDate,status,connection);
					if(count>0){
						list.add(bean);
					}
					
					sub_sql: {
						PreparedStatement preparedStatement2 = null;
						ResultSet resultSet2 = null;
						try {
							preparedStatement2 = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.subReportSql4,Arrays.asList(bean.getTrackingDetailsId(),fromDate,toDate,status));
							
							resultSet2 = preparedStatement2.executeQuery();
							while (resultSet2.next()) {
								CurrentOpportunitiesReportGenerationBean subBean = new CurrentOpportunitiesReportGenerationBean();
								
								subBean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
								subBean.getClientInformationBean().setFullName(null);
								subBean.getCurrentOpportunitiesReportBean().setYear(resultSet2.getString("year"));
								subBean.getCurrentOpportunitiesReportBean().setMonth(resultSet2.getString("month"));
								subBean.getRequirementGenerationBean().setRequirementId(null);
								subBean.getCurrentOpportunitiesReportBean().setBillNoString(resultSet2.getString("bill_no"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmount(resultSet2.getDouble("bill_amount"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmountString(Main.convert(resultSet2.getDouble("bill_amount")));
								
								subBean.getSkillsetMasterbean().setSkillset(null);
								subBean.getCurrentOpportunitiesReportBean().setBillDateSql(resultSet2.getDate("bill_date"));
								
								subBean.getCurrentOpportunitiesReportBean().setBillDateString(resultSet2.getString("bill_date"));
								subBean.getCurrentOpportunitiesReportBean().setBillDate(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getBillDateString()));
								
								subBean.getCurrentOpportunitiesReportBean().setChqDate(resultSet2.getString("chq_date"));
								
								if(subBean.getCurrentOpportunitiesReportBean().getChqDate()!=null){
									subBean.getCurrentOpportunitiesReportBean().setChqDateString(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getChqDate()));
									subBean.getCurrentOpportunitiesReportBean().setChqDetails(resultSet2.getString("chq_details")+"/"+subBean.getCurrentOpportunitiesReportBean().getChqDateString());
								}
								
								subBean.getResourceMasterBean().setFullName(null);
								subBean.getCurrentOpportunitiesReportBean().setPaid(resultSet2.getString("paid"));
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
			
			if(list.size()>0){
				CurrentOpportunitiesReportGenerationBean reportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
				reportGenerationBean.getCurrentOpportunitiesReportBean().setPaid("");
				reportGenerationBean.getCurrentOpportunitiesReportBean().setChqDetails("Grand Total :"+Main.convert(grandTotalBillingAmount(list)));
				reportGenerationBean.setBackGround(reportGenerationBean.getAnotherStyle());
				reportGenerationBean.setStyle(reportGenerationBean.getBoldStyle());
				
				list.add(reportGenerationBean);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadReportWrtDateAndClient(Date fromDate, Date toDate,int clientId) {
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
				preparedStatement = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.reportSql1,Arrays.asList(clientId));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportGenerationBean bean = new CurrentOpportunitiesReportGenerationBean();
					
					bean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
					bean.getCurrentOpportunitiesReportBean().setYear("Client Name: "+resultSet.getString("clientname"));
					bean.getCurrentOpportunitiesReportBean().setMonth("RequirementID: "+resultSet.getInt("r_id"));
					bean.getCurrentOpportunitiesReportBean().setBillNoString("Skill Set: "+resultSet.getString("master_skill_set_name"));
					bean.getCurrentOpportunitiesReportBean().setBillDateSql(null);
					bean.getCurrentOpportunitiesReportBean().setBillDate(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmount(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmountString("Resource Name: "+resultSet.getString("resource_name"));
					bean.getCurrentOpportunitiesReportBean().setPaid(null);
					bean.getCurrentOpportunitiesReportBean().setChqDetails(null);
					bean.setBackGround(bean.getBackGroundpaParent());
					bean.setStyle(bean.getBoldStyle());
					
					int count = countForParentRowForDate(bean.getTrackingDetailsId(),fromDate,toDate,connection);
					if(count>0){
						list.add(bean);
					}
					
					sub_sql: {
						PreparedStatement preparedStatement2 = null;
						ResultSet resultSet2 = null;
						try {
							preparedStatement2 = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.subReportSql2,Arrays.asList(bean.getTrackingDetailsId(),fromDate,toDate));
							
							resultSet2 = preparedStatement2.executeQuery();
							while (resultSet2.next()) {
								CurrentOpportunitiesReportGenerationBean subBean = new CurrentOpportunitiesReportGenerationBean();
								
								subBean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
								subBean.getClientInformationBean().setFullName(null);
								subBean.getCurrentOpportunitiesReportBean().setYear(resultSet2.getString("year"));
								subBean.getCurrentOpportunitiesReportBean().setMonth(resultSet2.getString("month"));
								subBean.getRequirementGenerationBean().setRequirementId(null);
								subBean.getCurrentOpportunitiesReportBean().setBillNoString(resultSet2.getString("bill_no"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmount(resultSet2.getDouble("bill_amount"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmountString(Main.convert(resultSet2.getDouble("bill_amount")));
								
								subBean.getSkillsetMasterbean().setSkillset(null);
								subBean.getCurrentOpportunitiesReportBean().setBillDateSql(resultSet2.getDate("bill_date"));
								
								subBean.getCurrentOpportunitiesReportBean().setBillDateString(resultSet2.getString("bill_date"));
								subBean.getCurrentOpportunitiesReportBean().setBillDate(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getBillDateString()));
								
								subBean.getCurrentOpportunitiesReportBean().setChqDate(resultSet2.getString("chq_date"));
								
								if(subBean.getCurrentOpportunitiesReportBean().getChqDate()!=null){
									subBean.getCurrentOpportunitiesReportBean().setChqDateString(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getChqDate()));
									subBean.getCurrentOpportunitiesReportBean().setChqDetails(resultSet2.getString("chq_details")+"/"+subBean.getCurrentOpportunitiesReportBean().getChqDateString());
								}
								
								subBean.getResourceMasterBean().setFullName(null);
								
								subBean.getCurrentOpportunitiesReportBean().setPaid(resultSet2.getString("paid"));
								
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
			
			if(list.size()>0){
				CurrentOpportunitiesReportGenerationBean reportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
				reportGenerationBean.getCurrentOpportunitiesReportBean().setPaid("");
				reportGenerationBean.getCurrentOpportunitiesReportBean().setChqDetails("Grand Total :"+Main.convert(grandTotalBillingAmount(list)));
				reportGenerationBean.setBackGround(reportGenerationBean.getAnotherStyle());
				reportGenerationBean.setStyle(reportGenerationBean.getBoldStyle());
				
				list.add(reportGenerationBean);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadReportBillSelectionData(String paid) {
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
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesReportGenerationBean bean = new CurrentOpportunitiesReportGenerationBean();
					
					bean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
					bean.getCurrentOpportunitiesReportBean().setYear("Client Name: "+resultSet.getString("clientname"));
					bean.getCurrentOpportunitiesReportBean().setMonth("RequirementID: "+resultSet.getInt("r_id"));
					bean.getCurrentOpportunitiesReportBean().setBillNoString("Skill Set: "+resultSet.getString("master_skill_set_name"));
					bean.getCurrentOpportunitiesReportBean().setBillDateSql(null);
					bean.getCurrentOpportunitiesReportBean().setBillDate(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmount(null);
					bean.getCurrentOpportunitiesReportBean().setBillAmountString("Resource Name: "+resultSet.getString("resource_name"));
					bean.getCurrentOpportunitiesReportBean().setPaid(null);
					bean.getCurrentOpportunitiesReportBean().setChqDetails(null);
					bean.setBackGround(bean.getBackGroundpaParent());
					bean.setStyle(bean.getBoldStyle());
					
					int count = countForParentRowForSelectionPaidStatus(bean.getTrackingDetailsId(),paid,connection);
					if(count>0){
						list.add(bean);
					}
					
					sub_sql: {
						PreparedStatement preparedStatement2 = null;
						ResultSet resultSet2 = null;
						try {
							preparedStatement2 = Pstm.createQuery(connection,CurrentOpportunitiesReportGenerationSql.subReportSql3,Arrays.asList(bean.getTrackingDetailsId(),paid));
							
							resultSet2 = preparedStatement2.executeQuery();
							while (resultSet2.next()) {
								CurrentOpportunitiesReportGenerationBean subBean = new CurrentOpportunitiesReportGenerationBean();
								
								subBean.setTrackingDetailsId(resultSet.getInt("rts_tracking_details_id"));
								subBean.getClientInformationBean().setFullName(null);
								subBean.getCurrentOpportunitiesReportBean().setYear(resultSet2.getString("year"));
								subBean.getCurrentOpportunitiesReportBean().setMonth(resultSet2.getString("month"));
								subBean.getRequirementGenerationBean().setRequirementId(null);
								subBean.getCurrentOpportunitiesReportBean().setBillNoString(resultSet2.getString("bill_no"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmount(resultSet2.getDouble("bill_amount"));
								subBean.getCurrentOpportunitiesReportBean().setBillAmountString(Main.convert(resultSet2.getDouble("bill_amount")));
								
								subBean.getSkillsetMasterbean().setSkillset(null);
								subBean.getCurrentOpportunitiesReportBean().setBillDateSql(resultSet2.getDate("bill_date"));
								
								subBean.getCurrentOpportunitiesReportBean().setBillDateString(resultSet2.getString("bill_date"));
								subBean.getCurrentOpportunitiesReportBean().setBillDate(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getBillDateString()));
								
								subBean.getCurrentOpportunitiesReportBean().setChqDate(resultSet2.getString("chq_date"));
								
								if(subBean.getCurrentOpportunitiesReportBean().getChqDate()!=null){
									subBean.getCurrentOpportunitiesReportBean().setChqDateString(Dateformatter.toStringDate(subBean.getCurrentOpportunitiesReportBean().getChqDate()));
									subBean.getCurrentOpportunitiesReportBean().setChqDetails(resultSet2.getString("chq_details")+"/"+subBean.getCurrentOpportunitiesReportBean().getChqDateString());
								}
								
								subBean.getResourceMasterBean().setFullName(null);
								
								subBean.getCurrentOpportunitiesReportBean().setPaid(resultSet2.getString("paid"));
								
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
			
			if(list.size()>0){
				CurrentOpportunitiesReportGenerationBean reportGenerationBean = new CurrentOpportunitiesReportGenerationBean();
				reportGenerationBean.getCurrentOpportunitiesReportBean().setPaid("");
				reportGenerationBean.getCurrentOpportunitiesReportBean().setChqDetails("Grand Total :"+Main.convert(grandTotalBillingAmount(list)));
				reportGenerationBean.setBackGround(reportGenerationBean.getAnotherStyle());
				reportGenerationBean.setStyle(reportGenerationBean.getBoldStyle());
				
				list.add(reportGenerationBean);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
