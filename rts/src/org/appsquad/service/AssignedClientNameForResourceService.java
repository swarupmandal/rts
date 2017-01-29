package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.DemoBean;
import org.appsquad.dao.AssignedClientNameForResourceDao;

public class AssignedClientNameForResourceService {
    
    public static ArrayList<DemoBean> fetchResourceDetailsListService(DemoBean demoBean){
       ArrayList<DemoBean> list = null;	
	   list = AssignedClientNameForResourceDao.fetchResourceDetailsListDao(demoBean);
	   return list;
    }
    
    public static Integer fetchUserPresentWrtResourceService(DemoBean demoBean){
       int countNumber = 0;
       countNumber = AssignedClientNameForResourceDao.fetchUserPresentWrtResourceDao(demoBean);	
       return countNumber;
    }
    
    /*****************************************************************************************************************************************/
   
}
