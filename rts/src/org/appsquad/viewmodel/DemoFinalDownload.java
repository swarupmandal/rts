package org.appsquad.viewmodel;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.appsquad.database.DbConnection;

public class DemoFinalDownload{
	private static final long serialVersionUID = -7767828383799037391L;
	static Connection connection = null;
	final static Logger logger=Logger.getLogger(DemoFinalDownload.class);
	
	public static List<File> fetchingPathFromDb(String[] parts){
		List<File> files = new ArrayList<File>();
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch_path:{
					    PreparedStatement preparedStatement = null;
					    for(int i=0;i<parts.length;i++){
					    	try {
								String sql = "select res_upcv FROM rts_resource_master WHERE id = ? ";
								preparedStatement = connection.prepareStatement(sql);
								String val = parts[i];
								Integer reVal = Integer.parseInt(val);
								preparedStatement.setInt(1, reVal);
								logger.info("FETCHED PATH: "+preparedStatement.unwrap(PreparedStatement.class));
								ResultSet resultSet = preparedStatement.executeQuery();
								while(resultSet.next()){
									//String oldChar = "\"";
									//String newChar = "/";
									//String name = resultSet.getString("res_upcv").replace(oldChar, newChar);
									//System.out.println("NAME IS :"+name);
									//File file = new File(resultSet.getString("res_upcv"));
									String data = resultSet.getString("res_upcv");
									System.out.println("data is :"+data);
									files.add(new File(resultSet.getString("res_upcv")));
								}
							}finally{
								if(preparedStatement!=null){
									preparedStatement.close();
								}
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
		return files;
	}
	
	
	public static String fetchingPathFromDbAnother(String parts){
		String path = "";
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					
					//1st SQL block
					sql_fetch_path:{
					    PreparedStatement preparedStatement = null;
					    	try {
								String sql = "select res_upcv FROM rts_resource_master WHERE id = ? ";
								preparedStatement = connection.prepareStatement(sql);
								Integer reVal = Integer.parseInt(parts);
								preparedStatement.setInt(1, reVal);
								logger.info("FETCHED PATH: "+preparedStatement.unwrap(PreparedStatement.class));
								ResultSet resultSet = preparedStatement.executeQuery();
								while(resultSet.next()){
									path = resultSet.getString("res_upcv");
									path=path.replace('\\','/');
									//path = "\""+path+"\"";
									System.out.println("NEW STRING IS :"+path);
								}
							}finally{
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
		return path;
	}

	/*********************************************************************************************************************************/
	
	public static Connection getConnection() {
		return connection;
	}
	public static void setConnection(Connection connection) {
		DemoFinalDownload.connection = connection;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static Logger getLogger() {
		return logger;
	}
}
