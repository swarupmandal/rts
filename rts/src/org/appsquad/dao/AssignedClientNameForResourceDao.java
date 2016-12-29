package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.appsquad.bean.DemoBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.AssignedClientNameForResourceSql;
import org.appsquad.utility.Pstm;

public class AssignedClientNameForResourceDao {
	final static Logger logger=Logger.getLogger(AssignedClientNameForResourceDao.class);
	
	public static ArrayList<DemoBean> fetchResourceDetailsListDao(DemoBean demoBean){
		ArrayList<DemoBean> clientDetailslistWrtResource = new ArrayList<DemoBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						    preparedStatement = Pstm.createQuery(connection, AssignedClientNameForResourceSql.FETCHDETAILS, Arrays.asList(demoBean.getId()));
						    logger.info("onLoadClientDeatilsWrtResource ID - " + preparedStatement.unwrap(PreparedStatement.class));
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								DemoBean bean = new DemoBean();
								
								bean.setResourceName(resultSet.getString("resource_name"));
								bean.setClientOriginalName(resultSet.getString("clientname"));
								bean.setReqId(resultSet.getInt("r_id"));
								bean.setReqStatus(resultSet.getString("master_status_name"));
								bean.setReqSkillName(resultSet.getString("master_skill_set_name"));
								
								clientDetailslistWrtResource.add(bean);
							}  
						} finally{
							if(preparedStatement!=null){
								preparedStatement.close();
							}
						}
				    }
				} catch (Exception e) {
					e.printStackTrace();
					logger.fatal("-------------------->>>>>>>"+e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.error(e);
		}
		return clientDetailslistWrtResource;
	}
	
	public static Integer fetchUserPresentWrtResourceDao(DemoBean demoBean){
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
						    preparedStatement = Pstm.createQuery(connection, AssignedClientNameForResourceSql.COUNTUSERPRESENTORNOTWRTRESOURCE, Arrays.asList(demoBean.getId()));
						    logger.info("onLoadClientDeatilsWrtResource ID - " + preparedStatement.unwrap(PreparedStatement.class));
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
					logger.fatal("-------------------->>>>>>>"+e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.error(e);
		}
		return count;
	}
}
