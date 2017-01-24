package org.appsquad.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.appsquad.bean.RuntimePopulateRoleBasedOnUserIdBean;
import org.appsquad.database.DbConnection;

public class RuntimePopulateRoleBasedOnUserId {
   public static String populateRoleBasedOnUserId(String userID){
	   String roleName = "";
	   try {
		 Connection connection = DbConnection.createConnection();
		 sql_connection:{
			 try {
				//1st sql block
				 ArrayList<RuntimePopulateRoleBasedOnUserIdBean> list = new ArrayList<RuntimePopulateRoleBasedOnUserIdBean>();
				 sql:{
				    PreparedStatement preparedStatementFetch = null;
				    ResultSet resultSet = null;
				    try{
				    	String sql = "select a1.id,a1.user_id,a2.role_id,a3.menus_id "
				    				+"from rts_user_master a1,rts_user_role_mapper a2,rts_page_user_mapper a3 "
				    				+"where a1.id = a2.user_id "
				    				+"and a2.role_id = a3.role_id "
				    				+"and a1.user_id like ? ";
				    	
				    	preparedStatementFetch = connection.prepareStatement(sql);
				    	preparedStatementFetch.setString(1, userID);
				    	System.out.println(preparedStatementFetch);
				    	resultSet = preparedStatementFetch.executeQuery();
				    	while(resultSet.next()){
				    		RuntimePopulateRoleBasedOnUserIdBean bean = new RuntimePopulateRoleBasedOnUserIdBean();
				    		bean.setMenusID(resultSet.getInt("menus_id"));
				    		if(bean.getMenusID()==11 || bean.getMenusID()==12){
				    			list.add(bean);	
				    		}
				    		
				    	}
				    }finally{
				    	if(preparedStatementFetch!=null){
				    		preparedStatementFetch.close();
				    	}
				    }
			     }
				 
				System.out.println("IN RUNTIME PAGE SIZE ::::"+list.size());
				if(list.size()>1){
					roleName = "APPROVER AND DATA ENTRY OPERATOR";
				}else if(list.size()==1){
					for(RuntimePopulateRoleBasedOnUserIdBean idBean: list){
						if(idBean.getMenusID()==11){
							roleName = "DATA ENTRY OPERATOR";
						}else if(idBean.getMenusID()==12){
							roleName = "APPROVER";
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
	 return roleName;  
   }
}
