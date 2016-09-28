package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.IndividualClientRequirementSql;
import org.appsquad.utility.Pstm;

public class IndividualClientReportDao {

	final static Logger logger=Logger.getLogger(IndividualClientReportDao.class);
	
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
	
}
