package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.ClientUserAssignBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.ClientUserAssignDao;
import org.appsquad.dao.RoleMasterDao;

public class ClientUserAssignService {
	
	public static ArrayList<UserprofileBean> fetchUserDetailsSearchUser(String name){
		ArrayList<UserprofileBean> list = null;
		list = RoleMasterDao.onLoadUserDeatilsWithSearch(name);
		return list;
	}

	public static boolean insertClientUserAssignData(ClientUserAssignBean clientUserAssignBean){
		boolean flag = false;
		flag = ClientUserAssignDao.insertClientUserAssignData(clientUserAssignBean);
		return flag;
	}
	
	public static int countClientUserAssignData(ClientUserAssignBean clientUserAssignBean){
		int count = 0;
		count = ClientUserAssignDao.CountDeatils(clientUserAssignBean);
		return count;
	}
	
	public static boolean deleteClientUserAssignData(ClientUserAssignBean clientUserAssignBean){
		boolean flagDelete = false;
		flagDelete = ClientUserAssignDao.deleteClientUserAssignDataDao(clientUserAssignBean);
		return flagDelete;
	}
	
}
