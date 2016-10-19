package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.DemoBean;
import org.appsquad.dao.AssignedClientNameForResourceDao;

public class AssignedClientNameForResourceService {
    private static ArrayList<DemoBean> list = null;	
    private static int countNumber = 0;
    
    public static ArrayList<DemoBean> fetchResourceDetailsListService(DemoBean demoBean){
	   return list = AssignedClientNameForResourceDao.fetchResourceDetailsListDao(demoBean);
    }
    
    public static Integer fetchUserPresentWrtResourceService(DemoBean demoBean){
       return countNumber = AssignedClientNameForResourceDao.fetchUserPresentWrtResourceDao(demoBean);	
    }
    
    /*****************************************************************************************************************************************/
    
	public static ArrayList<DemoBean> getList() {
		return list;
	}
	public static void setList(ArrayList<DemoBean> list) {
		AssignedClientNameForResourceService.list = list;
	}
	public static int getCountNumber() {
		return countNumber;
	}
	public static void setCountNumber(int countNumber) {
		AssignedClientNameForResourceService.countNumber = countNumber;
	}
}
