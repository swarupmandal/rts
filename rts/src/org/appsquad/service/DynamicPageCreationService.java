package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.RoleMasterBean;
import org.appsquad.dao.DynamicPageCreationDao;

public class DynamicPageCreationService {
	
    public static ArrayList<RoleMasterBean> allPageNameFetchWithCheckBoxSe(RoleMasterBean masterBean){
    	ArrayList<RoleMasterBean> list = null;
    	list = DynamicPageCreationDao.allPageNameFetchWithCheckBoxDao(masterBean);
    	return list;
    }
    
    public static boolean insertMappingPageAndUser(RoleMasterBean masterBean, ArrayList<RoleMasterBean> list) throws Exception{
    	boolean isInsert = false;
    	isInsert = DynamicPageCreationDao.insertIntoPageUserMapperTable(masterBean, list);
    	return isInsert;
    }

}
