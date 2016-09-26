package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.appsquad.bean.RequirementGenerationBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.IndividualRequirementSql;
import org.appsquad.utility.Pstm;

public class IndividualRequirementDao {

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
						   preparedStatement = Pstm.createQuery(connection, IndividualRequirementSql.fetchReqIdList, null);
							
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
			e.printStackTrace();
		}
		return reqIdList;	
	} 
	
}