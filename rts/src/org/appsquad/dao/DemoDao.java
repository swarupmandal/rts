package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.appsquad.bean.DemoBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.DemoSql;
import org.appsquad.utility.Pstm;

public class DemoDao {
	public static ArrayList<DemoBean> getDetailsForSkill(DemoBean demoBean){
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
					    	 preparedStatement = Pstm.createQuery(connection, DemoSql.FETCHSQLFORSKILL, Arrays.asList(demoBean.skillsetMasterbean.getSkillset()));
							   
							 ResultSet resultSet = preparedStatement.executeQuery();
							 while (resultSet.next()) {
								DemoBean bean = new DemoBean();
								bean.setId(resultSet.getInt("id"));
								bean.setFullName(resultSet.getString("fullname"));
								bean.setExperience(resultSet.getInt("res_experience"));
								bean.setAddress(resultSet.getString("res_address"));
								bean.setEmailId(resultSet.getString("res_emailid"));
								bean.setContactNumber(resultSet.getString("rts_contact_no"));
								
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
