package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.appsquad.bean.DemoBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.DemoSql;
import org.appsquad.utility.Pstm;

public class DemoDao {
	public static ArrayList<DemoBean> getDetails(){
		ArrayList<DemoBean> list = new ArrayList<DemoBean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1t SQL block
					sql1:{
					    PreparedStatement preparedStatement = null;
					    try {
					    	 preparedStatement = Pstm.createQuery(connection, DemoSql.fetchSql, null);
							   
							 ResultSet resultSet = preparedStatement.executeQuery();
							 while (resultSet.next()) {
								DemoBean bean = new DemoBean();
								bean.setName(resultSet.getString("res_name"));
								bean.setSurName(resultSet.getString("res_surname"));
								bean.setId(resultSet.getInt("id"));
								
								list.add(bean);
							 }  
						}finally{
							if(preparedStatement!=null){
								preparedStatement.close();
							}
						}
				    }
					
				} catch (Exception e) {
					e.printStackTrace();
				}if(connection!=null){
					connection.close();
				}
			}
		} catch (Exception e) {
		   e.printStackTrace();
		}
		return list;
	}
}
