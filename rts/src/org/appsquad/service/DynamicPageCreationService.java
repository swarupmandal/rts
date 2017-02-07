package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.RoleMasterBean;
import org.appsquad.dao.DynamicPageCreationDao;

public class DynamicPageCreationService {
    public static ArrayList<RoleMasterBean> allPageNameFetchWithCheckBoxSe(RoleMasterBean masterBean){
    	return DynamicPageCreationDao.allPageNameFetchWithCheckBoxDao(masterBean);
    }
    
    public static boolean insertMappingPageAndUser(RoleMasterBean masterBean, ArrayList<RoleMasterBean> list) throws Exception{
    	return DynamicPageCreationDao.insertIntoPageUserMapperTable(masterBean, list);
    }
}
