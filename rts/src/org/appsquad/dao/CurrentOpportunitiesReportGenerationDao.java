package org.appsquad.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.Logger;
import org.appsquad.bean.CurrentOpportunitiesReportGenerationBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.CurrentOpportunitiesReportGenerationSql;
import org.appsquad.utility.Pstm;

public class CurrentOpportunitiesReportGenerationDao {
	final static Logger logger = Logger.getLogger(CurrentOpportunitiesReportGenerationDao.class);
	
	public static ArrayList<CurrentOpportunitiesReportGenerationBean> loadReportData(Date fromDate, Date toDate) {
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
					bean.getClientInformationBean().setFullName(resultSet.getString("clientname"));
					bean.getCurrentOpportunitiesReportBean().setMonth(null);
					bean.getRequirementGenerationBean().setRequirementId(resultSet.getInt("r_id"));
					bean.getCurrentOpportunitiesReportBean().setBillNoString(null);
					bean.getSkillsetMasterbean().setSkillset(resultSet.getString("master_skill_set_name"));
					bean.getCurrentOpportunitiesReportBean().setBillDateSql(null);
					bean.getResourceMasterBean().setFullName(resultSet.getString("resource_name"));
					bean.getCurrentOpportunitiesReportBean().setBillAmountString(null);
					bean.getCurrentOpportunitiesReportBean().setPaid(null);
					bean.getCurrentOpportunitiesReportBean().setChqDetails(null);
					bean.setBackGround(bean.getBackGroundpaParent());
					
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
	
}
