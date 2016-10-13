package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.DemoBean;
import org.appsquad.dao.DemoDao;

public class DemoService {
    private static ArrayList<DemoBean> listForSkillSet = null;
    
	public static ArrayList<DemoBean> getDetailsForSkillService(DemoBean demoBean){
		return listForSkillSet = DemoDao.getDetailsForSkill(demoBean);
	}
	
	

	/********************************************************************************************************************************************/
	
	public static ArrayList<DemoBean> getListForSkillSet() {
		return listForSkillSet;
	}
	public static void setListForSkillSet(ArrayList<DemoBean> listForSkillSet) {
		DemoService.listForSkillSet = listForSkillSet;
	}
}
