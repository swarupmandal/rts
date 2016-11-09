package org.appsquad.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
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

	final static Logger logger = Logger.getLogger(IndividualClientReportDao.class);

	public static ArrayList<IndividualClientReportSubBean> loadRidDetails(
			Connection connection, int id) {
		ArrayList<IndividualClientReportSubBean> arrayList = new ArrayList<IndividualClientReportSubBean>();
		if (arrayList.size() > 0) {
			arrayList.clear();
		}
		try {
			PreparedStatement preparedStatement = null;
			preparedStatement = Pstm.createQuery(connection,IndividualClientReportSql.loadRidDetailsList,Arrays.asList(id));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				IndividualClientReportSubBean bean = new IndividualClientReportSubBean();

				bean.setTestId1(resultSet.getInt("resource_id"));
				bean.setTestId2(resultSet.getInt("rts_req_resource_mapper"));

				arrayList.add(bean);
			}
		} catch (Exception e) {
            e.printStackTrace();
		}
		return arrayList;
	}

	public static ArrayList<RequirementGenerationBean> onLoadReqList() {
		ArrayList<RequirementGenerationBean> reqIdList = new ArrayList<RequirementGenerationBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection: {
				try {

					// 1st SQL block
					sql_fetch: {
						PreparedStatement preparedStatement = null;
						try {
							preparedStatement = Pstm
									.createQuery(
											connection,
											IndividualClientRequirementSql.fetchReqIdList,
											null);

							logger.info("onLoadReqList - "
									+ preparedStatement
											.unwrap(PreparedStatement.class));

							ResultSet resultSet = preparedStatement
									.executeQuery();
							while (resultSet.next()) {
								RequirementGenerationBean bean = new RequirementGenerationBean();
								bean.setReq_id(resultSet.getInt("r_id"));

								reqIdList.add(bean);
							}
						} finally {
							if (preparedStatement != null) {
								preparedStatement.close();
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (connection != null) {
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

	public static ArrayList<IndividualClientReportBean> loadRidList(int clientId) {

		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,
						IndividualClientReportSql.loadRidList,
						Arrays.asList(clientId));

				logger.info("load Rid List - "
						+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}
					bean.setEmailId("");

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm
									.createQuery(
											connection,
											IndividualClientReportSql.loadRidDetailsList,
											Arrays.asList(bean.getReqId()));

							logger.info("R_ID DETAILS - "
									+ preparedStatement2
											.unwrap(PreparedStatement.class));

							ResultSet resultSet2 = preparedStatement2
									.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}

								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;

	}

	public static ArrayList<IndividualClientReportBean> loadRidListWithDateRange(
			Date fromDate, Date toDate, int clientId) {

		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,IndividualClientReportSql.loadRidListWithDateRange,Arrays.asList(fromDate, toDate, clientId));

				logger.info("load Rid List with date range- "+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "+ bean.getCreatedDateValue());
					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));
					bean.setSkillSetLabel("Skill : "+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm.createQuery(connection,IndividualClientReportSql.loadRidDetailsList,Arrays.asList(bean.getReqId()));

							logger.info("R_ID DETAILS - "+ preparedStatement2.unwrap(PreparedStatement.class));

							ResultSet resultSet2 = preparedStatement2.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2.getString("res_name"));
								subBean.setYoExp(resultSet2.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2.getString("rts_contact_no"));
								subBean.setEmailId(resultSet2.getString("res_emailid"));
								subBean.setIntIntvStr(resultSet2.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter.toStringDate(subBean.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter.toStringDate(subBean.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}

								subBean.setStyle(subBean.getLighterStyle());

								subBean.setCompanyName(resultSet2.getString("other_info"));
								if (subBean.getCompanyName() == null) {
									subBean.setCompanyName("");
								}

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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;

	}

	public static ArrayList<IndividualClientReportBean> loadRidListWithDateRangeSkillReport(
			Date fromDate, Date toDate) {

		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm
						.createQuery(
								connection,
								IndividualClientReportSql.loadRidListWithDateRangeSkillWiseReport,
								Arrays.asList(fromDate, toDate));

				logger.info("load Rid List with date range- "
						+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();
					bean.setReqId(resultSet.getInt("req_id"));
					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));
					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "+ bean.getCreatedDateValue());
					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));
					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));
					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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
					sub_sql: {
						try {
							if (subList.size() > 0) {
								subList.clear();
							}
							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm.createQuery(connection,IndividualClientReportSql.loadRidDetailsList,Arrays.asList(bean.getReqId()));
							logger.info("R_ID DETAILS - "+ preparedStatement2.unwrap(PreparedStatement.class));
							ResultSet resultSet2 = preparedStatement2.executeQuery();
							while (resultSet2.next()) {
								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}

								subBean.setStyle(subBean.getLighterStyle());
								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
									subBean.setCompanyName("");
								}

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
						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}
		return list;

	}

	public static ArrayList<IndividualClientReportBean> loadRidListWithDateRangeAndSkill(
			Date fromDate, Date toDate, int skillId, int clientId) {
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,IndividualClientReportSql.loadRidListWithDateRangeAndSkill,Arrays.asList(fromDate, toDate, skillId,clientId));
				logger.info("Load Date and Skill and Client In Client Report Screen - "+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();
					bean.setReqId(resultSet.getInt("req_id"));
					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));
					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));
					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}
							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm.createQuery(connection,IndividualClientReportSql.loadRidDetailsList,Arrays.asList(bean.getReqId()));
							logger.info("Inner query of Load Date and Skill and Client In Client Report Screen - "+ preparedStatement2.unwrap(PreparedStatement.class));
							ResultSet resultSet2 = preparedStatement2.executeQuery();
							while (resultSet2.next()) {
								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2.getString("res_name"));
								subBean.setYoExp(resultSet2.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter.toStringDate(subBean.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter.toStringDate(subBean.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}

								subBean.setCompanyName(resultSet2.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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
						} finally {
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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}
		return list;
	}

	public static ArrayList<IndividualClientReportBean> loadRidListWithDateRangeAndSkillReport(
			Date fromDate, Date toDate, int skillId) {

		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm
						.createQuery(
								connection,
								IndividualClientReportSql.loadRidListWithDateRangeAndSkillReport,
								Arrays.asList(fromDate, toDate, skillId));

				logger.info("load Rid List with date range and skill - "
						+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm
									.createQuery(
											connection,
											IndividualClientReportSql.loadRidDetailsList,
											Arrays.asList(bean.getReqId()));

							logger.info("R_ID DETAILS - "
									+ preparedStatement2
											.unwrap(PreparedStatement.class));

							ResultSet resultSet2 = preparedStatement2
									.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}

								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<IndividualClientReportBean> loadRidListWithDateRangeAndRIdWise(
			Date fromDate, Date toDate, int rId) {

		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm
						.createQuery(
								connection,
								IndividualClientReportSql.loadRidListWithDateRangeAndRidWiseReport,
								Arrays.asList(fromDate, toDate, rId));

				logger.info("load Rid List with date range and skill - "
						+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm
									.createQuery(
											connection,
											IndividualClientReportSql.loadRidDetailsList,
											Arrays.asList(bean.getReqId()));

							logger.info("R_ID DETAILS - "
									+ preparedStatement2
											.unwrap(PreparedStatement.class));

							ResultSet resultSet2 = preparedStatement2
									.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}

								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<IndividualClientReportBean> loadRidListWithSkill(
			int skillId, int clientId) {

		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,
						IndividualClientReportSql.loadRidListWithSkill,
						Arrays.asList(skillId, clientId));

				logger.info("load Rid List with skill - "
						+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm
									.createQuery(
											connection,
											IndividualClientReportSql.loadRidDetailsList,
											Arrays.asList(bean.getReqId()));

							logger.info("R_ID DETAILS - "
									+ preparedStatement2
											.unwrap(PreparedStatement.class));

							ResultSet resultSet2 = preparedStatement2
									.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}
								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<IndividualClientReportBean> loadRidListWithStatus(
			int statusId, int clientId) {

		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,
						IndividualClientReportSql.loadRidListWithStatus,
						Arrays.asList(clientId));

				logger.info("load Rid List with skill - "
						+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm
									.createQuery(
											connection,
											IndividualClientReportSql.loadRidDetailsListWithStatus,
											Arrays.asList(bean.getReqId(),
													statusId));

							logger.info("R_ID DETAILS - "
									+ preparedStatement2
											.unwrap(PreparedStatement.class));

							ResultSet resultSet2 = preparedStatement2
									.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}
								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;
	}
	
	
	public static ArrayList<IndividualClientReportBean> loadClientListWithStatusDao(int statusId, int clientId) {
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,IndividualClientReportSql.loadClientListWithStatus,Arrays.asList(clientId));

				logger.info("load Rid List with skill - "+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm
									.createQuery(
											connection,
											IndividualClientReportSql.loadRidDetailsListWithStatus,
											Arrays.asList(bean.getReqId(),
													statusId));

							logger.info("R_ID DETAILS - "
									+ preparedStatement2
											.unwrap(PreparedStatement.class));

							ResultSet resultSet2 = preparedStatement2
									.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}
								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;
	}
	

	public static ArrayList<IndividualClientReportBean> loadRidListWithStatusSkillDate(
			Date fromDate, Date toDate, int skillId, int statusId, int clientId) {

		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm
						.createQuery(
								connection,
								IndividualClientReportSql.loadRidListWithDateAndSkillAndStatus,
								Arrays.asList(fromDate, toDate, skillId,
										clientId));

				logger.info("load Rid List with skill - "
						+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm
									.createQuery(
											connection,
											IndividualClientReportSql.loadRidDetailsListWithStatus,
											Arrays.asList(bean.getReqId(),
													statusId));
							logger.info("R_ID DETAILS - "
									+ preparedStatement2
											.unwrap(PreparedStatement.class));

							ResultSet resultSet2 = preparedStatement2
									.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}
								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<IndividualClientReportBean> loadRidListWithStatusSkillDateWiseReport(
			Date fromDate, Date toDate, int skillId, int statusId) {

		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm
						.createQuery(
								connection,
								IndividualClientReportSql.loadRidListWithDateRangeAndSkillReport,
								Arrays.asList(fromDate, toDate, skillId));

				logger.info("load Rid List with skill - "
						+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm
									.createQuery(
											connection,
											IndividualClientReportSql.loadRidDetailsListWithStatus,
											Arrays.asList(bean.getReqId(),
													statusId));
							logger.info("R_ID DETAILS - "
									+ preparedStatement2
											.unwrap(PreparedStatement.class));

							ResultSet resultSet2 = preparedStatement2
									.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}
								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<IndividualClientReportBean> loadRidListWithStatusRIdDateWiseReport(
			Date fromDate, Date toDate, int rid, int statusId) {

		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm
						.createQuery(
								connection,
								IndividualClientReportSql.loadRidListWithDateRangeAndRidReport,
								Arrays.asList(fromDate, toDate, rid));

				logger.info("load Rid List with skill - "
						+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm
									.createQuery(
											connection,
											IndividualClientReportSql.loadRidDetailsListWithStatus,
											Arrays.asList(bean.getReqId(),
													statusId));
							logger.info("R_ID DETAILS - "
									+ preparedStatement2
											.unwrap(PreparedStatement.class));

							ResultSet resultSet2 = preparedStatement2
									.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}
								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<IndividualClientReportBean> loadRidListWithStatusSkill(
			int skillId, int statusId, int clientId) {

		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm
						.createQuery(
								connection,
								IndividualClientReportSql.loadRidListWithSkillAndStatus,
								Arrays.asList(skillId, clientId));

				logger.info("load Rid List with skill - "
						+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm
									.createQuery(
											connection,
											IndividualClientReportSql.loadRidDetailsListWithStatus,
											Arrays.asList(bean.getReqId(),
													statusId));
							logger.info("R_ID DETAILS - "
									+ preparedStatement2
											.unwrap(PreparedStatement.class));

							ResultSet resultSet2 = preparedStatement2
									.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}
								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<IndividualClientReportBean> loadRidListWithStatusDate(Date fromDate, Date toDate, int statusId, int clientId) {
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,IndividualClientReportSql.loadRidListWithDateStatus,Arrays.asList(fromDate, toDate, clientId));
				logger.info("load Rid List with skill - "+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm.createQuery(connection,IndividualClientReportSql.loadRidDetailsListWithStatus,
																	Arrays.asList(bean.getReqId(),statusId));
							
							logger.info("R_ID DETAILS - "+ preparedStatement2.unwrap(PreparedStatement.class));
							ResultSet resultSet2 = preparedStatement2.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}
								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<IndividualClientReportBean> loadRidListWithStatusRIdDateClientWise(
			Date fromDate, Date toDate, int rId, int statusId, int clientId) {

		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm
						.createQuery(
								connection,
								IndividualClientReportSql.loadRidListWithDateAndRidStatusClient,
								Arrays.asList(fromDate, toDate, rId, clientId));

				logger.info("load Rid List with r_Id - "
						+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm
									.createQuery(
											connection,
											IndividualClientReportSql.loadRidDetailsListWithStatus,
											Arrays.asList(bean.getReqId(),
													statusId));
							logger.info("R_ID DETAILS - "
									+ preparedStatement2
											.unwrap(PreparedStatement.class));

							ResultSet resultSet2 = preparedStatement2
									.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}
								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;
	}

	public static ArrayList<IndividualClientReportBean> loadRidListWithDateRangeClientAndRID(Date fromDate, Date toDate, int rId, int clientId) {
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,IndividualClientReportSql.loadRidListWithDateRangeClientAndRId,
																	Arrays.asList(fromDate, toDate, rId, clientId));

				logger.info("load Rid List with date range and rid and client Id - "+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm.createQuery(connection,IndividualClientReportSql.loadRidDetailsList,
																	Arrays.asList(bean.getReqId()));

							logger.info("R_ID DETAILS - "+ preparedStatement2.unwrap(PreparedStatement.class));
							ResultSet resultSet2 = preparedStatement2.executeQuery();
							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}

								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;
	}
	
	public static ArrayList<IndividualClientReportBean> loadDateAndStatusForClientReportDao(Date fromDate, Date toDate, int statusId) {
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,IndividualClientReportSql.loadDateAndStatusForClientReportSql,Arrays.asList(fromDate, toDate));

				logger.info("load Rid List with skill - "+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm.createQuery(connection,IndividualClientReportSql.loadRidDetailsListWithStatus,Arrays.asList(bean.getReqId(),statusId));
							logger.info("R_ID DETAILS - "+ preparedStatement2.unwrap(PreparedStatement.class));

							ResultSet resultSet2 = preparedStatement2.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}
								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;
	}

	
	public static ArrayList<IndividualClientReportBean> loadDateAndClientForClientReportDao(Date fromDate, Date toDate, int clientId) {
		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,IndividualClientReportSql.loadRidListWithDateStatus,Arrays.asList(fromDate, toDate, clientId));

				logger.info("load Date and Client In client Report - "+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

					bean.setEmailId("");
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
					sub_sql: {
						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm.createQuery(connection,IndividualClientReportSql.loadDateAndClientInClientReportSql,Arrays.asList(bean.getReqId()));
							logger.info("Inner query In client Report for Date and Client - "+ preparedStatement2.unwrap(PreparedStatement.class));

							ResultSet resultSet2 = preparedStatement2.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2
										.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2
										.getString("res_name"));
								subBean.setYoExp(resultSet2
										.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2
										.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2
										.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2
										.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter
											.toStringDate(subBean
													.getIntIntvStr()));
								} else {
									subBean.setIntIntvValue("");
								}

								subBean.setClntIntvStr(resultSet2
										.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter
											.toStringDate(subBean
													.getClntIntvStr()));
								} else {
									subBean.setClntIntvValue("");
								}
								subBean.setCompanyName(resultSet2
										.getString("other_info"));
								if (subBean.getCompanyName() == null) {
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

						} finally {

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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}

		return list;
	}
	
	/******************************************************* FOR ALL SUMMARY ************************************************************************/

	public static ArrayList<IndividualClientReportBean> loadRidSummary(ArrayList<IndividualClientReportBean> parentList) {
		ArrayList<IndividualClientReportBean> summarList = new ArrayList<IndividualClientReportBean>();
		if (summarList.size() > 0) {
			summarList.clear();
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (list.size() > 0) {
			list.clear();
		}
		for (IndividualClientReportBean pb : parentList) {
			if (pb.getReqId() != null) {
				list.add(pb.getReqId());
			}
		}
		int totCount = 0;
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();

				for (Integer id : list) {

					preparedStatement = Pstm.createQuery(connection,IndividualClientReportSql.loadRidListSummay,Arrays.asList(id));
					logger.info("Summury List Rid - "+ preparedStatement.unwrap(PreparedStatement.class));
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						IndividualClientReportBean parentBean = new IndividualClientReportBean();

						parentBean.setrIdLabel("R id : "+ resultSet.getInt("req_id"));
						parentBean.setrIdDateLabel("Date : "+ resultSet.getString("created_date"));

						parentBean.setClientFullName("Client : "+ resultSet.getString("companyname"));
						parentBean.setSkillSetLabel("Skill : "+ resultSet.getString("master_skill_set_name"));
						parentBean.setNoOfReqLebel("Total Req : "+ resultSet.getInt("total_req_number"));

						parentBean.setStyle(parentBean.getBoldStyle());
						parentBean.setBackGroundStyle(parentBean.getBackGroundpaParent());

						parentBean.setRidLbFieldVis(true);
						parentBean.setRidDatelbFieldVis(true);
						parentBean.setClNameLbFieldVis(true);
						parentBean.setSklStLbFieldVis(true);
						parentBean.setNoOfReqVis(true);

						summarList.add(parentBean);

						subSql: {

							try {
								PreparedStatement preparedStatement2 = null;
								preparedStatement2 = Pstm.createQuery(connection,IndividualClientReportSql.loadStatusSummmary,Arrays.asList(id));
								logger.info("Summury List Rid Details - "+ preparedStatement2.unwrap(PreparedStatement.class));
								ResultSet resultSet2 = preparedStatement2.executeQuery();
								while (resultSet2.next()) {

									IndividualClientReportBean childBean = new IndividualClientReportBean();

									childBean.setrIdLabel(resultSet2.getString("final_status"));
									childBean.setNoOfReqLebel(resultSet2.getInt("total_count") + "");
									totCount = totCount+ resultSet2.getInt("total_count");
									childBean.setSkillSetLabel("");
									childBean.setStyle(childBean.getLighterStyle());
									childBean.setClientFullName("");
									childBean.setrIdDateLabel("");

									childBean.setRidLbFieldVis(true);
									childBean.setRidDatelbFieldVis(true);
							
									childBean.setClNameLbFieldVis(false);
									childBean.setSklStLbFieldVis(false);
									childBean.setNoOfReqVis(true);

									summarList.add(childBean);

								}
							} finally {
							}
						}

						total: {

							IndividualClientReportBean totalCountBean = new IndividualClientReportBean();

							totalCountBean.setrIdLabel("Total");
							totalCountBean.setNoOfReqLebel(totCount + "");

							totalCountBean.setSkillSetLabel("");
							totalCountBean.setStyle(parentBean.getBoldStyle());
							totalCountBean.setBackGroundStyle(parentBean
									.getTotalbackGroundCount());
							totalCountBean.setClientFullName("");
							totalCountBean.setrIdDateLabel("");

							totalCountBean.setRidLbFieldVis(true);
							totalCountBean.setRidDatelbFieldVis(true);
							totalCountBean.setClNameLbFieldVis(false);
							totalCountBean.setSklStLbFieldVis(false);
							totalCountBean.setNoOfReqVis(true);

							summarList.add(totalCountBean);
							totCount = 0;
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
			e.printStackTrace();
		}
		return summarList;
	}
	
	
	public static ArrayList<IndividualClientReportBean> loadRidSummaryTest(ArrayList<IndividualClientReportBean> parentList,Integer statusId) {
		ArrayList<IndividualClientReportBean> summarList = new ArrayList<IndividualClientReportBean>();
		if(statusId==200){
			if (summarList.size() > 0) {
				summarList.clear();
			}
			ArrayList<Integer> list = new ArrayList<Integer>();
			if (list.size() > 0) {
				list.clear();
			}
			for (IndividualClientReportBean pb : parentList) {
				if (pb.getReqId() != null) {
					list.add(pb.getReqId());
				}
			}
			int totCount = 0;
			try {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					connection = DbConnection.createConnection();
					for (Integer id : list) {
						preparedStatement = Pstm.createQuery(connection,IndividualClientReportSql.loadRidListSummay,Arrays.asList(id));
						logger.info("Summury List Rid - "+ preparedStatement.unwrap(PreparedStatement.class));
						resultSet = preparedStatement.executeQuery();
						while (resultSet.next()) {
							IndividualClientReportBean parentBean = new IndividualClientReportBean();

							parentBean.setrIdLabel("R id : "+ resultSet.getInt("req_id"));
							parentBean.setrIdDateLabel("Date : "+ resultSet.getString("created_date"));

							parentBean.setClientFullName("Client : "+ resultSet.getString("companyname"));
							parentBean.setSkillSetLabel("Skill : "+ resultSet.getString("master_skill_set_name"));
							parentBean.setNoOfReqLebel("Total Req : "+ resultSet.getInt("total_req_number"));

							parentBean.setStyle(parentBean.getBoldStyle());
							parentBean.setBackGroundStyle(parentBean.getBackGroundpaParent());

							parentBean.setRidLbFieldVis(true);
							parentBean.setRidDatelbFieldVis(true);
							parentBean.setClNameLbFieldVis(true);
							parentBean.setSklStLbFieldVis(true);
							parentBean.setNoOfReqVis(true);

							summarList.add(parentBean);
							subSql: {
								try {
									PreparedStatement preparedStatement2 = null;
									preparedStatement2 = Pstm.createQuery(connection,IndividualClientReportSql.loadStatusSummmary,Arrays.asList(id));
									logger.info("Summury List Rid DetailsPrseent For Status Null - "+ preparedStatement2.unwrap(PreparedStatement.class));
									ResultSet resultSet2 = preparedStatement2.executeQuery();
									while (resultSet2.next()) {

										IndividualClientReportBean childBean = new IndividualClientReportBean();

										childBean.setrIdLabel(resultSet2.getString("final_status"));
										childBean.setNoOfReqLebel(resultSet2.getInt("total_count") + "");
										totCount = totCount+ resultSet2.getInt("total_count");
										childBean.setSkillSetLabel("");
										childBean.setStyle(childBean.getLighterStyle());
										childBean.setClientFullName("");
										childBean.setrIdDateLabel("");

										childBean.setRidLbFieldVis(true);
										childBean.setRidDatelbFieldVis(true);
								
										childBean.setClNameLbFieldVis(false);
										childBean.setSklStLbFieldVis(false);
										childBean.setNoOfReqVis(true);

										summarList.add(childBean);

									}
								} finally {
								}
							}
							total: {

								IndividualClientReportBean totalCountBean = new IndividualClientReportBean();

								totalCountBean.setrIdLabel("Total");
								totalCountBean.setNoOfReqLebel(totCount + "");

								totalCountBean.setSkillSetLabel("");
								totalCountBean.setStyle(parentBean.getBoldStyle());
								totalCountBean.setBackGroundStyle(parentBean.getTotalbackGroundCount());
								totalCountBean.setClientFullName("");
								totalCountBean.setrIdDateLabel("");

								totalCountBean.setRidLbFieldVis(true);
								totalCountBean.setRidDatelbFieldVis(true);
								totalCountBean.setClNameLbFieldVis(false);
								totalCountBean.setSklStLbFieldVis(false);
								totalCountBean.setNoOfReqVis(true);

								summarList.add(totalCountBean);
								totCount = 0;
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
				e.printStackTrace();
			}
		}else{
			if (summarList.size() > 0) {
				summarList.clear();
			}
			ArrayList<Integer> list = new ArrayList<Integer>();
			if (list.size() > 0) {
				list.clear();
			}
			for (IndividualClientReportBean pb : parentList) {
				if (pb.getReqId() != null) {
					list.add(pb.getReqId());
				}
			}
			int totCount = 0;
			try {
				Connection connection = null;
				PreparedStatement preparedStatement = null;
				ResultSet resultSet = null;
				try {
					connection = DbConnection.createConnection();
					for (Integer id : list) {
						preparedStatement = Pstm.createQuery(connection,IndividualClientReportSql.loadRidListSummay,Arrays.asList(id));
						logger.info("Summury List Rid - "+ preparedStatement.unwrap(PreparedStatement.class));
						resultSet = preparedStatement.executeQuery();
						while (resultSet.next()) {
							IndividualClientReportBean parentBean = new IndividualClientReportBean();

							parentBean.setrIdLabel("R id : "+ resultSet.getInt("req_id"));
							parentBean.setrIdDateLabel("Date : "+ resultSet.getString("created_date"));

							parentBean.setClientFullName("Client : "+ resultSet.getString("companyname"));
							parentBean.setSkillSetLabel("Skill : "+ resultSet.getString("master_skill_set_name"));
							parentBean.setNoOfReqLebel("Total Req : "+ resultSet.getInt("total_req_number"));

							parentBean.setStyle(parentBean.getBoldStyle());
							parentBean.setBackGroundStyle(parentBean.getBackGroundpaParent());

							parentBean.setRidLbFieldVis(true);
							parentBean.setRidDatelbFieldVis(true);
							parentBean.setClNameLbFieldVis(true);
							parentBean.setSklStLbFieldVis(true);
							parentBean.setNoOfReqVis(true);

							summarList.add(parentBean);
							subSql: {
								try {
									PreparedStatement preparedStatement2 = null;
									preparedStatement2 = Pstm.createQuery(connection,IndividualClientReportSql.loadStatusSummmaryTestSql,Arrays.asList(id,statusId));
									logger.info("Summury List Rid DetailsPrseent For Status Prsent  - "+ preparedStatement2.unwrap(PreparedStatement.class));
									ResultSet resultSet2 = preparedStatement2.executeQuery();
									while (resultSet2.next()) {

										IndividualClientReportBean childBean = new IndividualClientReportBean();

										childBean.setrIdLabel(resultSet2.getString("final_status"));
										childBean.setNoOfReqLebel(resultSet2.getInt("total_count") + "");
										totCount = totCount+ resultSet2.getInt("total_count");
										childBean.setSkillSetLabel("");
										childBean.setStyle(childBean.getLighterStyle());
										childBean.setClientFullName("");
										childBean.setrIdDateLabel("");

										childBean.setRidLbFieldVis(true);
										childBean.setRidDatelbFieldVis(true);
								
										childBean.setClNameLbFieldVis(false);
										childBean.setSklStLbFieldVis(false);
										childBean.setNoOfReqVis(true);

										summarList.add(childBean);

									}
								} finally {
								}
							}
							total: {

								IndividualClientReportBean totalCountBean = new IndividualClientReportBean();

								totalCountBean.setrIdLabel("Total");
								totalCountBean.setNoOfReqLebel(totCount + "");

								totalCountBean.setSkillSetLabel("");
								totalCountBean.setStyle(parentBean.getBoldStyle());
								totalCountBean.setBackGroundStyle(parentBean.getTotalbackGroundCount());
								totalCountBean.setClientFullName("");
								totalCountBean.setrIdDateLabel("");

								totalCountBean.setRidLbFieldVis(true);
								totalCountBean.setRidDatelbFieldVis(true);
								totalCountBean.setClNameLbFieldVis(false);
								totalCountBean.setSklStLbFieldVis(false);
								totalCountBean.setNoOfReqVis(true);

								summarList.add(totalCountBean);
								totCount = 0;
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
				e.printStackTrace();
			}	
		}
		return summarList;
	}

	/*************************************************************** T E S T ********************************************************************/

	public static ArrayList<IndividualClientReportBean> loadRidListTest(
			int clientId) {

		ArrayList<IndividualClientReportBean> list = new ArrayList<IndividualClientReportBean>();
		if (list.size() > 0) {
			list.clear();
		}
		ArrayList<IndividualClientReportBean> subList = new ArrayList<IndividualClientReportBean>();

		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection,
						IndividualClientReportSql.loadRidList,
						Arrays.asList(clientId));

				logger.info("load Rid List - "
						+ preparedStatement.unwrap(PreparedStatement.class));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {

					IndividualClientReportBean bean = new IndividualClientReportBean();

					bean.setReqId(resultSet.getInt("req_id"));

					bean.setrIdLabel("R ID :" + resultSet.getInt("req_id"));

					bean.setCreatedDateStr(resultSet.getString("created_date"));
					if (bean.getCreatedDateStr() != null) {
						bean.setCreatedDateValue(Dateformatter
								.toStringDate(bean.getCreatedDateStr()));
						bean.setrIdDateLabel("Date : "
								+ bean.getCreatedDateValue());

					}

					bean.setSkillId(resultSet.getInt("req_skill_id"));

					bean.setSkillSetLabel("Skill : "
							+ resultSet.getString("master_skill_set_name"));

					bean.setClientFullName(resultSet.getString("client_name"));
					bean.setCompanyName(resultSet.getString("companyname"));

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

					sub_sql: {

						try {
							if (subList.size() > 0) {
								subList.clear();
							}

							PreparedStatement preparedStatement2 = null;
							preparedStatement2 = Pstm.createQuery(connection,IndividualClientReportSql.loadRidDetailsList,
																									Arrays.asList(bean.getReqId()));

							logger.info("R_ID DETAILS - "+ preparedStatement2.unwrap(PreparedStatement.class));

							ResultSet resultSet2 = preparedStatement2.executeQuery();

							while (resultSet2.next()) {

								IndividualClientReportBean subBean = new IndividualClientReportBean();

								subBean.setrIdLabel(resultSet2.getString("final_status"));
								subBean.setrIdDateLabel(resultSet2.getString("res_name"));
								subBean.setYoExp(resultSet2.getDouble("res_experience"));
								subBean.setSkillSetLabel(resultSet2.getString("rts_contact_no"));

								subBean.setEmailId(resultSet2.getString("res_emailid"));

								subBean.setIntIntvStr(resultSet2.getString("internal_interview_date"));
								if (subBean.getIntIntvStr() != null) {
									subBean.setIntIntvValue(Dateformatter.toStringDate(subBean.getIntIntvStr()));
								}

								subBean.setClntIntvStr(resultSet2.getString("client_interview_date"));
								if (subBean.getClntIntvStr() != null) {
									subBean.setClntIntvValue(Dateformatter.toStringDate(subBean.getClntIntvStr()));
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
						} finally {
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
			logger.fatal("Load R_ID List - " + e);
			e.printStackTrace();
		}
		return list;
	}
}
