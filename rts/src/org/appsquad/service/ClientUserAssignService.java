package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.ClientUserAssignBean;
import org.appsquad.bean.UserprofileBean;
import org.appsquad.dao.ClientUserAssignDao;
import org.appsquad.dao.RoleMasterDao;

public class ClientUserAssignService {
	private static boolean flag = false;
	private static boolean flagDelete = false;
    private static int count = 0;
	
	public static ArrayList<UserprofileBean> fetchUserDetailsSearchUser(String name){
		ArrayList<UserprofileBean> list = new ArrayList<UserprofileBean>();
		list = RoleMasterDao.onLoadUserDeatilsWithSearch(name);
		return list;
	}

	public static boolean insertClientUserAssignData(ClientUserAssignBean clientUserAssignBean){
		return flag = ClientUserAssignDao.insertClientUserAssignData(clientUserAssignBean);
	}
	
	public static int countClientUserAssignData(ClientUserAssignBean clientUserAssignBean){
		return count = ClientUserAssignDao.CountDeatils(clientUserAssignBean);
	}
	
	public static boolean deleteClientUserAssignData(ClientUserAssignBean clientUserAssignBean){
		return flagDelete = ClientUserAssignDao.deleteClientUserAssignDataDao(clientUserAssignBean);
	}
	
	/****************************************************************************************************************************************/
	
	public static boolean isFlag() {
		return flag;
	}
	public static void setFlag(boolean flag) {
		ClientUserAssignService.flag = flag;
	}
	public static int getCount() {
		return count;
	}
	public static void setCount(int count) {
		ClientUserAssignService.count = count;
	}
	public static boolean isFlagDelete() {
		return flagDelete;
	}
	public static void setFlagDelete(boolean flagDelete) {
		ClientUserAssignService.flagDelete = flagDelete;
	}
}
