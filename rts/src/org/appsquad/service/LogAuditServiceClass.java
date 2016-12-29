package org.appsquad.service;

import java.sql.Date;
import java.util.ArrayList;

import org.appsquad.bean.LogAuditBean;
import org.appsquad.dao.LogAuditDaoClass;

public class LogAuditServiceClass {
	
	public static boolean insertIntoLogTable(String mainScreenName, String childScreenName,String userId,String operation,Date creationDate,Integer operationId){
		boolean returnServiceClassFlag = LogAuditDaoClass.entryForLogAudit(mainScreenName, childScreenName, userId, operation, creationDate,operationId);
		return returnServiceClassFlag;
	}
	
	public static ArrayList<LogAuditBean> fetchLogDetails(Date fromDate, Date toDate){
		ArrayList<LogAuditBean> list = new ArrayList<LogAuditBean>();
		list = LogAuditDaoClass.fetchLogDetailsDao(fromDate, toDate);
		return list;
	}
	
}
