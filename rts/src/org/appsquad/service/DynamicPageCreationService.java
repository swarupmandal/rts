package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.RoleMasterBean;
import org.appsquad.dao.DynamicPageCreationDao;

public class DynamicPageCreationService {
	private static ArrayList<RoleMasterBean> list = null;
	private static boolean isInsert = false;
	
    public static ArrayList<RoleMasterBean> allPageNameFetchWithCheckBoxSe(RoleMasterBean masterBean){
    	
    	return list = DynamicPageCreationDao.allPageNameFetchWithCheckBoxDao(masterBean);
    }
    
    public static boolean insertMappingPageAndUser(RoleMasterBean masterBean, ArrayList<RoleMasterBean> list) throws Exception{
    	return isInsert = DynamicPageCreationDao.insertIntoPageUserMapperTable(masterBean, list);
    }

    /***************************************************************************************************************************************/
    
	public static ArrayList<RoleMasterBean> getList() {
		return list;
	}
	public static void setList(ArrayList<RoleMasterBean> list) {
		DynamicPageCreationService.list = list;
	}

	public static boolean isInsert() {
		return isInsert;
	}

	public static void setInsert(boolean isInsert) {
		DynamicPageCreationService.isInsert = isInsert;
	}
}
