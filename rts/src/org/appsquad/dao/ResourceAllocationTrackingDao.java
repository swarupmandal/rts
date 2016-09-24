package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.ResourceAllocationTrackingSql;
import org.appsquad.utility.Pstm;


public class ResourceAllocationTrackingDao {

	
	public static ArrayList<ClientInformationBean> fetchClientDetails(){
		
		ArrayList<ClientInformationBean> list = new ArrayList<ClientInformationBean>();
		if(list.size()>0){
			list.clear();
			
		}
		
		try {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = DbConnection.createConnection();
				preparedStatement = Pstm.createQuery(connection, ResourceAllocationTrackingSql.loadClientList, null);
				
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					ClientInformationBean bean = new ClientInformationBean();
					bean.setClientId(resultSet.getInt("id"));
					bean.setFullName(resultSet.getString("full_name"));
					
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
			e.printStackTrace();
		}
		return list;
	}
	
	
}
