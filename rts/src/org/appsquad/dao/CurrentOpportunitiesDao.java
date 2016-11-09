package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.appsquad.bean.CurrentOpportunitiesBean;
import org.appsquad.bean.UserClientMappingBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.CurrentOpportunitiesSql;
import org.appsquad.utility.Pstm;
import org.zkoss.zul.Messagebox;

public class CurrentOpportunitiesDao {
	
	final static Logger logger = Logger.getLogger(CurrentOpportunitiesBean.class);
	
	public static ArrayList<CurrentOpportunitiesBean> loadCurrenOpportunity(){
		ArrayList<CurrentOpportunitiesBean> list = new ArrayList<CurrentOpportunitiesBean>();
		if(list.size()>0){
			list.clear();
		}
		try {
			Connection connection = DbConnection.createConnection();
			PreparedStatement preparedStatement = null;
			try {
				preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesSql.loadOpportumityDetailsQuery, null);
				logger.info("Current Opportunities Load >>> >> > "+ preparedStatement.unwrap(PreparedStatement.class));
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CurrentOpportunitiesBean bean = new CurrentOpportunitiesBean();
					
					bean.setReqResStatusTrackingId(resultSet.getInt("rts_req_res_status_tracking_id"));
					bean.setClientId(resultSet.getInt("client_id"));
					bean.setClientName(resultSet.getString("clientname"));
					bean.setRid(resultSet.getInt("r_id"));
					bean.setResourceId(resultSet.getInt("resource_id"));
					bean.setResourceName(resultSet.getString("resource_name"));
					bean.setResourceType(resultSet.getString("type_name"));
					bean.setSkillset(resultSet.getString("master_skill_set_name"));
					bean.setUserClBeanList(userBeanList(connection, bean.getClientId()));
					
					list.add(bean);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				String msg = e.getMessage();
				Messagebox.show(msg, "Error", Messagebox.OK, Messagebox.ERROR);
				logger.fatal("Load Current Opportunities - " + e);
			}finally{
				if(preparedStatement != null){
					preparedStatement.close();
				}if(connection != null){
					connection.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static ArrayList<UserClientMappingBean> userBeanList(Connection connection, Integer clientId) throws SQLException{
		ArrayList<UserClientMappingBean> list = new ArrayList<UserClientMappingBean>();
		if(list.size()>0){
			list.clear();
		}
		PreparedStatement preparedStatement = null;
		try {
			
			preparedStatement = Pstm.createQuery(connection, CurrentOpportunitiesSql.loadUserDetailsList, Arrays.asList(clientId));
			logger.info("User Client Load >>> >> > "+ preparedStatement.unwrap(PreparedStatement.class));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserClientMappingBean bean = new UserClientMappingBean();
				
				bean.setUserId(resultSet.getInt("user_id"));
				bean.setUserName(resultSet.getString("user_name"));
				bean.setUserEmil(resultSet.getString("user_email"));
				list.add(bean);
			}
			
		} finally {
			if(preparedStatement !=null){
				preparedStatement.close();
			}
		}
		return list;
	}

}
