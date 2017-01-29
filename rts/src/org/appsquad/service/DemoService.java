package org.appsquad.service;

import java.util.ArrayList;

import org.appsquad.bean.DemoBean;
import org.appsquad.dao.DemoDao;

public class DemoService {
    
	public static ArrayList<DemoBean> getDetailsForSkillService(DemoBean demoBean){
		ArrayList<DemoBean> listForSkillSet = null;
		listForSkillSet = DemoDao.getDetailsForSkill(demoBean);
		return listForSkillSet;
	}
	
	public static ArrayList<DemoBean> getDetailsForSkillAndDateService(DemoBean demoBean){
		ArrayList<DemoBean> listForSkillSetAndDate = null;
		listForSkillSetAndDate = DemoDao.getDetailsForSkillAndDate(demoBean);
		return listForSkillSetAndDate;
	}

	public static ArrayList<DemoBean> getDetailsForSkillAndDateAndClientService(DemoBean demoBean){
		ArrayList<DemoBean> listForSkillSetAndDateAndClient = null;
		listForSkillSetAndDateAndClient = DemoDao.getDetailsForSkillAndDateAndClient(demoBean);
		return listForSkillSetAndDateAndClient;
	}
	
	public static String getCvPathService(DemoBean demoBean){
		String cvPath = "";
		cvPath = DemoDao.getCvPath(demoBean);
		return cvPath;
	}
	
}
