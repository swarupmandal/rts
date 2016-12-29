package org.appsquad.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.Logger;
import org.appsquad.bean.LogAuditBean;
import org.appsquad.database.DbConnection;
import org.appsquad.sql.LogAuditSqlClass;
import org.appsquad.utility.Dateformatter;
import org.appsquad.utility.Pstm;

public class LogAuditDaoClass {
	private static boolean logAuditFlag = false;
	final static Logger logger = Logger.getLogger(LogAuditDaoClass.class);
	
	public static boolean entryForLogAudit(String mainScreenName, String childScreenName,String userId,String operation,Date creationDate,Integer operationId){
		int count = 0;
		try {
			Connection connection=DbConnection.createConnection();
			PreparedStatement preparedStatement=null;
			try {
				preparedStatement= Pstm.createQuery(connection, LogAuditSqlClass.insertIntoLogAuditTable, Arrays.asList(mainScreenName,childScreenName,userId,operation,creationDate,operationId));
				logger.info("INSERT INTO LOG AUDIT TABLE:- " + preparedStatement.unwrap(PreparedStatement.class));
				count = preparedStatement.executeUpdate();
				if (count>0) {
					logAuditFlag=true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				connection.close();
			}
		} catch (Exception e) {
		   logger.fatal(e);	
		   e.printStackTrace();
		}
		return logAuditFlag;
	}
	
	public static ArrayList<LogAuditBean> fetchLogDetailsDao(Date fromDate, Date toDate) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<LogAuditBean> list = new ArrayList<LogAuditBean>();
		if(list.size()>0){
			list.clear();
		}
		try {
			connection = DbConnection.createConnection();
			sql_connection:{
				try {
					 sql_fetch:{
					      preparedStatement = Pstm.createQuery(connection,LogAuditSqlClass.fetchLogDetailsSql,Arrays.asList(fromDate, toDate));
					      logger.info("FETCH LOG DETAILS FROM TABLE:- " + preparedStatement.unwrap(PreparedStatement.class));
					      ResultSet resultSet = preparedStatement.executeQuery();
					      while(resultSet.next()){
					    	  LogAuditBean auditBean = new LogAuditBean();
					    	  auditBean.setMainScreenName(resultSet.getString("main_screen_name"));
					    	  auditBean.setChileScreenName(resultSet.getString("child_screen_name"));
					    	  auditBean.setOperation(resultSet.getString("operation"));
					    	  auditBean.setSessionUserId(resultSet.getString("user_id"));
					    	  auditBean.setCreationDateStr(resultSet.getString("created_date"));
					    	  if(auditBean.getCreationDateStr()!=null){
					    		  auditBean.setCreationDateValue(Dateformatter.toStringDate(auditBean.getCreationDateStr()));
					    	  }
					    	  java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    	  auditBean.setDateTimestampStr(resultSet.getString("created_date_timestamp"));
					    	  if(auditBean.getDateTimestampStr()!=null){
					    		  auditBean.setDateTimestampValue(resultSet.getString("created_date_timestamp"));
					    	  }
					    	  
					    	  list.add(auditBean);
					      }
				     }
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e);
					logger.fatal(e);
				}finally{
					if(connection!=null){
						connection.close();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			logger.fatal(e);
		}
		return list;
	}
    
	/*********************************LOG AUDIT GETTER AND SETTER METHOD ***************************************/
	
	public static boolean isLogAuditFlag() {
		return logAuditFlag;
	}
	public static void setLogAuditFlag(boolean logAuditFlag) {
		LogAuditDaoClass.logAuditFlag = logAuditFlag;
	}
}
