package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.appsquad.bean.RoleMasterBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.DynamicPageCreationSql;
import org.appsquad.sql.RoleMasterSql;
import org.appsquad.utility.Pstm;
import org.zkoss.zul.Messagebox;

public class DynamicPageCreationDao {
     
	 public static ArrayList<RoleMasterBean> allPageNameFetchWithCheckBoxDao(RoleMasterBean roleMasterBean){
		 ArrayList<RoleMasterBean> list = new ArrayList<RoleMasterBean>();
		 Connection connection = null;
		 try {
				connection = DbConnection.createConnection();
				sql_connection:{
					try {
						
						//1st SQL block
						sql_fetch:{
						   PreparedStatement preparedStatement = null;
						   try {
							   preparedStatement = Pstm.createQuery(connection, DynamicPageCreationSql.FETCHALLPAGEDETAILSSQL,null);
							   ResultSet resultSet = preparedStatement.executeQuery();
							   while (resultSet.next()) {
								   RoleMasterBean bean = new RoleMasterBean();
								   bean.setPageNameId(resultSet.getInt("menus_id"));
								   bean.setPageName(resultSet.getString("menus_name"));
								   if(countWrtMenusIdAndUserId(roleMasterBean.getRollId(), bean.getPageNameId())>0){
									   bean.setChkSelect(true);
								   }else{
									   bean.setChkSelect(false);
								   }
								   
								   list.add(bean);
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
		return list;
	 }
	 
	 public static int countWrtMenusIdAndUserId(Integer userSerialId, Integer menusId){
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
							   preparedStatement = Pstm.createQuery(connection, DynamicPageCreationSql.COUNTWRTUSERIDANDMENUSIDSQL, Arrays.asList(userSerialId,menusId));
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
					}finally{
						if(connection!=null){
							connection.close();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return count;
	 }
	 
	 public static int countWrtUserId(Integer roleId){
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
							   preparedStatement = Pstm.createQuery(connection, DynamicPageCreationSql.COUNTWRTUSERIDANDSQL, Arrays.asList(roleId));
							   System.out.println(preparedStatement);
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
					}finally{
						if(connection!=null){
							connection.close();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return count;
	 }
	 
	 public static boolean insertIntoPageUserMapperTable(RoleMasterBean bean, ArrayList<RoleMasterBean> pagelist) throws Exception{
		 boolean isInsert = false;
		 boolean isDelete = false;
		 Connection connection = null;
		 int countPresent = 0;
		 try {
			connection = DbConnection.createConnection();
			connection.setAutoCommit(false);
			sql_connection:{
				 try {
					 
					 countPresent = countWrtUserId(bean.getRollId());
					 System.out.println("COUNT PRESENT NUMBER IS :"+countPresent);
					 
					 if(countPresent>0){
						System.out.println("IF CONDIOTION"); 
						//1st sql block
						 sql_delete:{
						   PreparedStatement preparedStatementDelete = null;
						   try {
							   preparedStatementDelete = Pstm.createQuery(connection, 
										DynamicPageCreationSql.DELETEAPPPAGEWRTUSERSERIALID, Arrays.asList(bean.getRollId()));
						    	
						    	System.out.println("DELETE QUERY:"+preparedStatementDelete);
								int i = preparedStatementDelete.executeUpdate();
								if(i>0){
									isDelete = true;	
								}
							}finally{
								if(preparedStatementDelete!=null){
									preparedStatementDelete.close();
								}
							}
					     }
					 }else{
						 System.out.println("ELSE CONDIOTION"); 
						 for(RoleMasterBean masterBean: pagelist){
					    		if(masterBean.isChkSelect()){
					    			sql_insert:{
							    	 PreparedStatement preparedStatementInsert = null;
							    	 try {
							    		 preparedStatementInsert = Pstm.createQuery(connection, 
													DynamicPageCreationSql.INSERTINTOPAGEUSERMAPPERTABLE, Arrays.asList(bean.getRollId(),masterBean.getPageNameId()));
									     System.out.println("INSERT QUERY:"+preparedStatementInsert);
							    		 int i = preparedStatementInsert.executeUpdate();
										 if(i>0){
											isInsert = true;	
										 }
							    	 } finally{
										if(preparedStatementInsert!=null){
											preparedStatementInsert.close();
										}
									 }
							       }
					    		}
					    	}
					 }
					 
				    System.out.println("DYNAMIC CREATION PAGE SCREEN -> IS DELETE :"+isDelete);
				    
				    if(isDelete){
				    	//2nd SQL block
				    	for(RoleMasterBean masterBean: pagelist){
				    		if(masterBean.isChkSelect()){
				    			sql_insert:{
						    	 PreparedStatement preparedStatementInsert = null;
						    	 try {
						    		 preparedStatementInsert = Pstm.createQuery(connection, 
												DynamicPageCreationSql.INSERTINTOPAGEUSERMAPPERTABLE, Arrays.asList(bean.getRollId(),masterBean.getPageNameId()));
								     System.out.println("INSERT QUERY:"+preparedStatementInsert);
						    		 int i = preparedStatementInsert.executeUpdate();
									 if(i>0){
										isInsert = true;	
									 }
						    	 } finally{
									if(preparedStatementInsert!=null){
										preparedStatementInsert.close();
									}
								 }
						       }
				    		}
				    	}
				    }
				    
				    System.out.println("DYNAMIC CREATION PAGE SCREEN -> IS INSERT :"+isInsert);
				    
				    if(isInsert){
				    	Messagebox.show("Page Assigned For The User!", "Information", Messagebox.OK, Messagebox.INFORMATION);
						connection.commit();
				    }
				 
				} catch (Exception e) {
					connection.rollback();
					e.printStackTrace();
				}finally{
					if(connection!=null){
						connection.setAutoCommit(true);
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isInsert;
	 }
}
