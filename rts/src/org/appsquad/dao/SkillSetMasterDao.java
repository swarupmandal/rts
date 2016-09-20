package org.appsquad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.appsquad.bean.ClientInformationBean;
import org.appsquad.bean.SkillsetMasterbean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.ClientInformationsql;
import org.appsquad.sql.SkillSetMasterSql;
import org.appsquad.utility.Pstm;
import org.zkoss.zul.Messagebox;

public class SkillSetMasterDao {
	
	public static void insertSkillData(SkillsetMasterbean skillsetMasterbean){
		boolean isSaved = false;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_insert:{
					    PreparedStatement preparedStatementInsert = null;
					    try {
					    	preparedStatementInsert = Pstm.createQuery(connection, 
									SkillSetMasterSql.insertSkillSetQuery, Arrays.asList(skillsetMasterbean.getSkillset().toUpperCase(),skillsetMasterbean.getUserId(),skillsetMasterbean.getSkillsetdetails().toUpperCase()));
					   
							int i = preparedStatementInsert.executeUpdate();
							if(i>0){
								isSaved = true;	
							}
						} finally{
							if(preparedStatementInsert!=null){
								preparedStatementInsert.close();
							}
						}
				    }
				
					if( isSaved){
						Messagebox.show(" Skill Details Saved successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Skill Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
	}
	
	
	public static ArrayList<SkillsetMasterbean> onLoadSetDeatils(){
		ArrayList<SkillsetMasterbean> skillList = new ArrayList<SkillsetMasterbean>();
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch:{
					   PreparedStatement preparedStatement = null;
					   try {
						   preparedStatement = Pstm.createQuery(connection, SkillSetMasterSql.fetchSkillSetDetails, null);
							
							ResultSet resultSet = preparedStatement.executeQuery();
							while (resultSet.next()) {
								SkillsetMasterbean masterbean = new SkillsetMasterbean();
								masterbean.setId(resultSet.getInt("id"));
								masterbean.setSkillset(resultSet.getString("master_skill_set_name"));
								masterbean.setSkillsetdetails(resultSet.getString("skill_set_details"));
								
								skillList.add(masterbean);
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
		return skillList;
	}
	
	public static boolean updateClientData(SkillsetMasterbean masterbean){
		boolean isUpdated = false;
		Connection connection = null;
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_insert:{
					    PreparedStatement preparedStatementInsert = null;
					    try {
					    	preparedStatementInsert = Pstm.createQuery(connection, 
									SkillSetMasterSql.updateSkillSetDetails, Arrays.asList(masterbean.getSkillset().toUpperCase(),masterbean.getSkillsetdetails().toUpperCase(),masterbean.getId()));
					    	
					    	System.out.println(preparedStatementInsert);
							int i = preparedStatementInsert.executeUpdate();
							if(i>0){
								isUpdated = true;	
							}
						} finally{
							if(preparedStatementInsert!=null){
								preparedStatementInsert.close();
							}
						}
				    }
				
					if( isUpdated){
						Messagebox.show(" Client Details Updated successfully!","Information",Messagebox.OK,Messagebox.INFORMATION);
					}else{
						Messagebox.show(" Client Details failed due to internal error!","ERROR",Messagebox.OK,Messagebox.ERROR);
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
		return isUpdated;
	}

}
