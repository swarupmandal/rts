package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.DemoBean;
import org.appsquad.dao.AssignedClientNameForResourceDao;

public class AssignedClientNameForResourceService {
    
    public static ArrayList<DemoBean> fetchResourceDetailsListService(DemoBean demoBean){
	   return AssignedClientNameForResourceDao.fetchResourceDetailsListDao(demoBean);
    }
    
    public static Integer fetchUserPresentWrtResourceService(DemoBean demoBean){
       return AssignedClientNameForResourceDao.fetchUserPresentWrtResourceDao(demoBean);	
    }
   
}
